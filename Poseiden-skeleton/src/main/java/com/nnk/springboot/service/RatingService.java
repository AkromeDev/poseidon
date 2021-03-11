package com.nnk.springboot.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;

@Service
public class RatingService {
	
	private static final Logger log = LoggerFactory.getLogger(RatingService.class);

	@Autowired
	RatingRepository RatingRepo;
	
	public Rating saveRating(Rating rating) {
		log.info("SAVING..." + rating);
		
		return RatingRepo.save(rating);
	}

	public List<Rating> findAll() {
		return RatingRepo.findAll();
	}

	public Optional<Rating> findById(Integer id) {
	
		return RatingRepo.findById(id);
	}
	
}
