package com.currency.service.impl;

import com.currency.model.Trade;
import com.currency.repository.TradeRepository;
import com.currency.service.AbstractCrudService;
import com.currency.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceImpl extends AbstractCrudService<Trade, Long> implements TradeService {

	@Autowired
	protected TradeServiceImpl(TradeRepository tradeRepository) {
		super(tradeRepository);
	}
}
