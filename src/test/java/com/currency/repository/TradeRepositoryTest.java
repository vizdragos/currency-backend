package com.currency.repository;

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
public class TradeRepositoryTest {

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
	private TradeRepository tradeRepository;

	@Before
	public void before() {
		tradeRepository.deleteAll();
	}

	@Test
	public void save() {
		Trade savedTrade = tradeRepository.save(TEST_TRADE);
		assertNotNull("Trade should be saved", savedTrade);
		assertNotNull("Trade should have id", savedTrade.getId());
	}

	@Test
	public void find() {
		assertFalse("No trade should exist", tradeRepository.findAll().iterator().hasNext());

		Trade savedTrade = tradeRepository.save(TEST_TRADE);
		savedTrade = tradeRepository.findOne(savedTrade.getId());
		assertNotNull("Trade should be saved", savedTrade);
		assertNotNull("Trade should have id", savedTrade.getId());
	}


	@Test
	public void update() {
		Trade savedTrade = tradeRepository.save(TEST_TRADE);
		assertNotNull("Trade should be saved", savedTrade);
		assertNotNull("Trade should have id", savedTrade.getId());

		savedTrade.setOriginatingCountry(Country.RO);
		Trade updatedTrade = tradeRepository.save(savedTrade);
		updatedTrade = tradeRepository.findOne(savedTrade.getId());
		assertNotNull("Trade should be saved", updatedTrade);
		assertEquals("Country should be updated", Country.RO, updatedTrade.getOriginatingCountry());
	}

	@Test
	public void delete() {
		assertFalse("No trade should exist", tradeRepository.findAll().iterator().hasNext());

		Trade savedTrade = tradeRepository.save(TEST_TRADE);
		savedTrade = tradeRepository.findOne(savedTrade.getId());
		assertNotNull("Trade should be saved", savedTrade);
		assertNotNull("Trade should have id", savedTrade.getId());

		tradeRepository.delete(savedTrade.getId());
		savedTrade = tradeRepository.findOne(savedTrade.getId());
		assertNull("Trade should not exist", savedTrade);
	}

}
