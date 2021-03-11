package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;

@Service
public class CurveService {
	
	private static final Logger log = LoggerFactory.getLogger(CurveService.class);

	@Autowired
	CurvePointRepository curveRepo;
	
	public @Valid CurvePoint saveCurve(@Valid CurvePoint curvePoint) {
		log.info("SAVING..." + curvePoint);
		
		return curveRepo.save(curvePoint);
	}

	public List<CurvePoint> findAll() {
		return curveRepo.findAll();
	}

	public Optional<CurvePoint> findById(Integer id) {
		
		return curveRepo.findById(id);
	}
	
}
