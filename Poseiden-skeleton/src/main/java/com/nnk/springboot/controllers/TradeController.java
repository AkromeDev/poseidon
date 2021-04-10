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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.service.TradeService;

@Controller
public class TradeController {
	
	private static final Logger log = LoggerFactory.getLogger(TradeController.class);
	
	@Autowired
	TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
    	
    	log.info("accessing /trade/list endpoint with home method");
    	model.addAttribute("trade", tradeService.findAll());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade trade) {
    	
    	log.info("accessing /trade/add endpoint with addTrade method");
    	
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@RequestBody @Valid Trade trade, BindingResult result, Model model) {
    	
    	log.info("accessing /trade/validate endpoint with validate method");
    	
    	if (!result.hasErrors()) {
    		tradeService.saveTrade(trade);
            model.addAttribute("trade", tradeService.findAll());
            return "redirect:/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /trade/update/{id} endpoint with showUpdateForm method");
    	
    	Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
        model.addAttribute("trade", trade);
        
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @RequestBody @Valid Trade trade,
                             BindingResult result, Model model) {
    	
    	log.info("accessing /trade/update/{id} endpoint with updateTrade method");
    	
    	if (result.hasErrors()) {
            return "trade/update";
        }

    	trade.setId(id);
    	tradeService.saveTrade(trade);
        model.addAttribute("trade", tradeService.findAll());
        
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /trade/delete/{id} endpoint with deleteTrade method");
    	
    	Trade trade = tradeService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid trade Id:" + id));
    	tradeService.delete(trade);
        model.addAttribute("trade", tradeService.findAll());
        
        return "redirect:/trade/list";
    }
}
