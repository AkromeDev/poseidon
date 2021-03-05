package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class TradeController {
	
	private static final Logger log = LoggerFactory.getLogger(TradeController.class);
	
    // TODO: Inject Trade service

    @RequestMapping("/trade/list")
    public String home(Model model) {
    	
    	log.info("accessing /trade/list endpoint with home method");
    	
        // TODO: find all Trade, add to model
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
    	
    	log.info("accessing /trade/add endpoint with addUser method");
    	
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
    	
    	log.info("accessing /trade/validate endpoint with validate method");
    	
        // TODO: check data valid and save to db, after saving return Trade list
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /trade/update/{id} endpoint with showUpdateForm method");
    	
        // TODO: get Trade by Id and to model then show to the form
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
    	
    	log.info("accessing /trade/update/{id} endpoint with updateTrade method");
    	
        // TODO: check required fields, if valid call service to update Trade and return Trade list
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /trade/delete/{id} endpoint with deleteTrade method");
    	
        // TODO: Find Trade by Id and delete the Trade, return to Trade list
        return "redirect:/trade/list";
    }
}
