package com.nnk.springboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@Service
public class BidListService {

	@Autowired
	BidListRepository bidRepo;
	
	public BidList saveBid(BidList bid) {
		return bidRepo.save(bid);
	}

	public List<BidList> findAll() {
		return bidRepo.findAll();
	}
	
}
