package com.nnk.springboot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@Service
public class TradeService {
	
	private static final Logger log = LoggerFactory.getLogger(TradeService.class);

	@Autowired
	TradeRepository tradeRepo;
	
	public Trade saveBid(Trade trade) {
		log.info("SAVING..." + trade);
		
		return tradeRepo.save(trade);
	}

	public List<Trade> findAll() {
		return tradeRepo.findAll();
	}
	
}
