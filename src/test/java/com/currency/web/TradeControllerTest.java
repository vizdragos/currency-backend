package com.currency.web;

import com.currency.model.Country;
import com.currency.model.Currency;
import com.currency.model.Trade;
import com.currency.service.TradeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test-business-config.xml", "classpath:mvc-config.xml"})
@WebAppConfiguration
public class TradeControllerTest {

	private static final Trade.Builder TEST_TRADE_BUILDER = Trade.builder()
			.withUserId("123")
			.withCurrencyFrom(Currency.EUR)
			.withCurrencyTo(Currency.GBP)
			.withAmountSell(new BigDecimal("747.10"))
			.withAmountBuy(new BigDecimal("1000"))
			.withRate(0.7471d)
			.withTimePlaced(DateTime.now())
			.withOriginatingCountry(Country.FR);

	private static final MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype()/*,Charset.forName("utf8")*/);

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private TradeService tradeServiceMock;

	@Autowired
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	@Before
	public void before() {
		//MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@After
	public void after() {
		Mockito.reset(tradeServiceMock);
	}

	@Test
	public void getAll() throws Exception {
		Trade trade = TEST_TRADE_BUILDER.withId(1l).build();
		when(tradeServiceMock.getAll()).thenReturn(Lists.newArrayList(trade));

		// test with jsonPath
		ResultActions resultActions = mockMvc.perform(get(TradeController.PATH).contentType(CONTENT_TYPE))
				.andExpect(status().isOk())
				.andExpect(content().contentType(CONTENT_TYPE))
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(trade.getId().intValue())))
				.andExpect(jsonPath("$[0].userId", is(trade.getUserId().toString())))
				.andExpect(jsonPath("$[0].currencyFrom", is(trade.getCurrencyFrom().toString())));

		resultActions.andDo(MockMvcResultHandlers.print());

		// test as java object
		Trade[] allTrades = objectMapper.readValue(new String(resultActions.andReturn().getResponse().getContentAsByteArray()), Trade[].class);
		assertEquals("All trades found", 1, allTrades.length);

		verify(tradeServiceMock, times(1)).getAll();
		verifyNoMoreInteractions(tradeServiceMock);
	}

	@Test
	public void createTrade() throws Exception {
		Trade newTrade = TEST_TRADE_BUILDER.withId(1l).build();
		when(tradeServiceMock.create(any(Trade.class))).thenReturn(newTrade);

		// test with jsonPath
		ResultActions resultActions = mockMvc.perform(post(TradeController.PATH)
				.content(objectMapper.writeValueAsString(TEST_TRADE_BUILDER.build()))
				.contentType(CONTENT_TYPE))
				.andExpect(status().isCreated())
				.andExpect(content().contentType(CONTENT_TYPE))
				.andExpect(jsonPath("$.id", is(newTrade.getId().intValue())));

		// test as java object
		Trade savedTrade = objectMapper.readValue(new String(resultActions.andReturn().getResponse().getContentAsByteArray()), Trade.class);
		assertNotNull("Trade should have id", savedTrade.getId());

		verify(tradeServiceMock, times(1)).create(any(Trade.class));
		verifyNoMoreInteractions(tradeServiceMock);
	}

}
