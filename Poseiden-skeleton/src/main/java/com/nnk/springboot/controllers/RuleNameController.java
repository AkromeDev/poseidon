package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;

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
public class RuleNameController {
	
	private static final Logger log = LoggerFactory.getLogger(RuleNameController.class);
	
    // TODO: Inject RuleName service

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
    	
    	log.info("accessing /ruleName/list endpoint with home method");
    	
        // TODO: find all RuleName, add to model
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName bid) {
    	
    	log.info("accessing /ruleName/add endpoint with addRuleForm method");
    	
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
    	
    	log.info("accessing /ruleName/validate endpoint with validate method");
    	
        // TODO: check data valid and save to db, after saving return RuleName list
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /ruleName/update/{id} endpoint with showUpdateForm method");
    	
        // TODO: get RuleName by Id and to model then show to the form
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	
    	log.info("accessing /ruleName/update/{id} endpoint with updateRuleName method");
    	
        // TODO: check required fields, if valid call service to update RuleName and return RuleName list
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /ruleName/delete/{id} endpoint with deleteRuleName method");
    	
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        return "redirect:/ruleName/list";
    }
}
