package com.currency.service;

import com.currency.model.Country;
import com.currency.model.Currency;
import com.currency.model.Trade;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {"classpath:business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TradeServiceTest {

	private static final Trade TEST_TRADE = Trade.builder()
			.withUserId("123")
			.withCurrencyFrom(Currency.EUR)
			.withCurrencyTo(Currency.GBP)
			.withAmountSell(new BigDecimal(747.10))
			.withAmountBuy(new BigDecimal(1000))
			.withRate(0.7471d)
			.withTimePlaced(DateTime.now())
			.withOriginatingCountry(Country.FR)
			.build();

	@Autowired
	private TradeService tradeService;

	@Before
	public void before() {
		tradeService.deleteAll();
	}

	@Test
	public void create() {
		Trade createdTrade = tradeService.create(TEST_TRADE);
		assertNotNull("Trade should be created", createdTrade);
		assertNotNull("Trade should have id", createdTrade.getId());
	}

	@Test
	public void find() {
		assertFalse("No trade should exist", tradeService.getAll().iterator().hasNext());

		Trade createdTrade = tradeService.create(TEST_TRADE);
		createdTrade = tradeService.get(createdTrade.getId());
		assertNotNull("Trade should be created", createdTrade);
		assertNotNull("Trade should have id", createdTrade.getId());
	}


	@Test
	public void update() {
		Trade createdTrade = tradeService.create(TEST_TRADE);
		assertNotNull("Trade should be created", createdTrade);
		assertNotNull("Trade should have id", createdTrade.getId());

		createdTrade.setOriginatingCountry(Country.RO);
		Trade updatedTrade = tradeService.create(createdTrade);
		updatedTrade = tradeService.get(createdTrade.getId());
		assertNotNull("Trade should be created", updatedTrade);
		assertEquals("Country should be updated", Country.RO, updatedTrade.getOriginatingCountry());
	}

	@Test
	public void delete() {
		assertFalse("No trade should exist", tradeService.getAll().iterator().hasNext());

		Trade createdTrade = tradeService.create(TEST_TRADE);
		createdTrade = tradeService.get(createdTrade.getId());
		assertNotNull("Trade should be created", createdTrade);
		assertNotNull("Trade should have id", createdTrade.getId());

		tradeService.deleteById(createdTrade.getId());
		createdTrade = tradeService.get(createdTrade.getId());
		assertNull("Trade should not exist", createdTrade);
	}
}
