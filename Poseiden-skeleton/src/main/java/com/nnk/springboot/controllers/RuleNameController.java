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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;

@Controller
public class RuleNameController {
	
	private static final Logger log = LoggerFactory.getLogger(RuleNameController.class);
	
	@Autowired
	RuleNameService ruleService;

    @RequestMapping("/ruleName/list")
    public String home(Model model) {
    	
    	log.info("accessing /ruleName/list endpoint with home method");
    	
    	model.addAttribute("ruleName", ruleService.findAll());
    	
        return "ruleName/list";
    }

    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName rule) {
    	
    	log.info("accessing /ruleName/add endpoint with addRuleForm method");
    	
        return "ruleName/add";
    }

    @PostMapping("/ruleName/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
    	
    	log.info("accessing /ruleName/validate endpoint with validate method");
    	
    	if (!result.hasErrors()) {
    		ruleService.saveRule(ruleName);
            model.addAttribute("ruleName", ruleService.findAll());
            return "redirect:/ruleName/list";
        }
    	
        return "ruleName/add";
    }

    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /ruleName/update/{id} endpoint with showUpdateForm method");
    	
    	RuleName ruleName = ruleService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("ruleName", ruleName);
        
        return "ruleName/update";
    }

    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	
    	log.info("accessing /ruleName/update/{id} endpoint with updateRuleName method");
    	
    	if (result.hasErrors()) {
            return "ruleName/update";
        }

    	ruleName.setId(id);
    	ruleService.saveRule(ruleName);
        model.addAttribute("users", ruleService.findAll());
        
        return "redirect:/ruleName/list";
    }

    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /ruleName/delete/{id} endpoint with deleteRuleName method");
    	
        // TODO: Find RuleName by Id and delete the RuleName, return to Rule list
        return "redirect:/ruleName/list";
    }
}
