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

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.service.CurveService;

@Controller
public class CurveController {
	
	private static final Logger log = LoggerFactory.getLogger(CurveController.class);
	
	@Autowired
	CurveService curveService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
    	
    	log.info("accessing /curvePoint/list endpoint with home method");
    	model.addAttribute("curvePoint", curveService.findAll());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
    	
    	log.info("accessing /curvePoint/add endpoint with addBidForm method");
    	
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
    	
    	log.info("accessing /curvePoint/validate endpoint with validate method");
    	
        if (!result.hasErrors()) {
        	curveService.saveCurve(curvePoint);
            model.addAttribute("curvePoint", curveService.findAll());
            return "redirect:/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /curvePoint/update/{id} endpoint with showUpdateForm method");
    	
        // TODO: get CurvePoint by Id and to model then show to the form
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
    	
    	log.info("accessing /curvePoint/update/{id} endpoint with updateBid method");
    	
        // TODO: check required fields, if valid call service to update Curve and return Curve list
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /curvePoint/delete/{id} endpoint with deleteBid method");
    	
        // TODO: Find Curve by Id and delete the Curve, return to Curve list
        return "redirect:/curvePoint/list";
    }
}
