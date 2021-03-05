package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.service.RatingService;

@Controller
public class RatingController {
	
	private static final Logger log = LoggerFactory.getLogger(RatingController.class);
    
	@Autowired
	RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model) {
    	
    	log.info("accessing /rating/list endpoint with home method");
    	
    	model.addAttribute("bidList", ratingService.findAll());
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
    	
    	log.info("accessing /rating/add endpoint with addRatingForm method");
    	
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
    	
    	log.info("accessing /rating/validate endpoint with validate method");
    	
    	if (!result.hasErrors()) {
    		ratingService.saveRating(rating);
            model.addAttribute("rating", ratingService.findAll());
            return "redirect:/rating/list";
        }
    	
        return "rating/add";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /rating/update/{id} endpoint with showUpdateForm method");
    	
        // TODO: get Rating by Id and to model then show to the form
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
    	
    	log.info("accessing /rating/update/{id} endpoint with updateRating method");
    	
        // TODO: check required fields, if valid call service to update Rating and return Rating list
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /rating/delete/{id} endpoint with deleteRating method");
    	
        // TODO: Find Rating by Id and delete the Rating, return to Rating list
        return "redirect:/rating/list";
    }
}
