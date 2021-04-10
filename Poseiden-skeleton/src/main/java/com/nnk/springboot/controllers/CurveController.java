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
    public String addCurveForm(CurvePoint curve) {
    	
    	log.info("accessing /curvePoint/add endpoint with addCurveForm method");
    	
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@RequestBody @Valid CurvePoint curvePoint, BindingResult result, Model model) {
    	
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
    	
    	CurvePoint curvePoint = curveService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
    	
        return "curvePoint/update";
        
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @RequestBody @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
    	
    	log.info("accessing /curvePoint/update/{id} endpoint with updateCurvePoint method");
    	
    	if (result.hasErrors()) {
            return "curvePoint/update";
        }

    	curvePoint.setId(id);
        curveService.saveCurve(curvePoint);
        model.addAttribute("curvePoint", curveService.findAll());
    	
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurve(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /curvePoint/delete/{id} endpoint with deleteCurve method");

    	CurvePoint curvePoint = curveService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
    	curveService.delete(curvePoint);
        model.addAttribute("curvePoint", curveService.findAll());
        
        return "redirect:/curvePoint/list";
    }
}
