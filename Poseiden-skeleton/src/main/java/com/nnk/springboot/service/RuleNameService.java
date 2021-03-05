package com.nnk.springboot.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@Service
public class RuleNameService {
	
	private static final Logger log = LoggerFactory.getLogger(RuleNameService.class);

	@Autowired
	RuleNameRepository ruleRepo;
	
	public RuleName saveBid(RuleName rule) {
		log.info("SAVING..." + rule);
		
		return ruleRepo.save(rule);
	}

	public List<RuleName> findAll() {
		return ruleRepo.findAll();
	}
	
}
