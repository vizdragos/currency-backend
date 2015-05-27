package com.currency.web;

import com.currency.aop.socket.NotifyClients;
import com.currency.model.Trade;
import com.currency.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping(TradeController.PATH)
public class TradeController {

	public static final String PATH = "/trades";

	@Autowired
	private TradeService tradeService;

	@NotifyClients
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Trade createTrade(@RequestBody Trade trade) {
		return tradeService.create(trade);
	}

	@RequestMapping(method = RequestMethod.GET/*, consumes = MediaType.APPLICATION_JSON_VALUE*/, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Collection<Trade> getAll() {
		return tradeService.getAll();
	}

}
