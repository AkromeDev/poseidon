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

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.service.RuleNameService;

@Controller
public class RuleNameController {
	
	private static final Logger log = LoggerFactory.getLogger(RuleNameController.class);
	
	@Autowired
	RuleNameService ruleService;

	/***
	 * 
	 * @param model
	 * @return to the page "list" with all the existing object for this entity
	 */
    @RequestMapping("/ruleName/list")
    public String home(Model model) {
    	
    	log.info("accessing /ruleName/list endpoint with home method");
    	
    	model.addAttribute("ruleName", ruleService.findAll());
    	
        return "ruleName/list";
    }

    /***
	 * 
	 * @param rule
	 * @return send user to the "add" view to create a new entity object
	 */
    @GetMapping("/ruleName/add")
    public String addRuleForm(RuleName rule) {
    	
    	log.info("accessing /ruleName/add endpoint with addRuleForm method");
    	
        return "ruleName/add";
    }

    /***
     * 
     * @param rule
     * @param result
     * @param model
     * @return send the user back to the "list" page if the rule was correct. Keeps the user on the add page if the rule did contain errors
     */
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

    /***
     * sends the user to the "update" page containing the right rule
     * @param id
     * @param model
     * @return to the endpoint to post the update
     */
    @GetMapping("/ruleName/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /ruleName/update/{id} endpoint with showUpdateForm method");
    	
    	RuleName ruleName = ruleService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
        model.addAttribute("ruleName", ruleName);
        
        return "ruleName/update";
    }

    /***
     * 
     * @param id
     * @param ruleName
     * @param result
     * @param model
     * @return send the user back to the "list" page when the rule was correct. Keeps the user on the update page if the rule did contain errors
     */
    @PostMapping("/ruleName/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
    	
    	log.info("accessing /ruleName/update/{id} endpoint with updateRuleName method");
    	
    	if (result.hasErrors()) {
            return "ruleName/update";
        }

    	ruleName.setId(id);
    	ruleService.saveRule(ruleName);
        model.addAttribute("ruleName", ruleService.findAll());
        
        return "redirect:/ruleName/list";
    }

    /***
     * 
     * @param id
     * @param model
     * @return redirect user to the "list" page after deletion of the object
     */
    @GetMapping("/ruleName/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /ruleName/delete/{id} endpoint with deleteRuleName method");
    	
    	RuleName ruleName = ruleService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
    	ruleService.delete(ruleName);
        model.addAttribute("ruleName", ruleService.findAll());
        
        return "redirect:/ruleName/list";
    }
}
