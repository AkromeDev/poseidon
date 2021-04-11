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

	/***
	 * 
	 * @param model
	 * @return to the page "list" with all the existing object for this entity
	 */
    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
    	
    	log.info("accessing /curvePoint/list endpoint with home method");
    	model.addAttribute("curvePoint", curveService.findAll());
        return "curvePoint/list";
    }

    /***
	 * 
	 * @param curve
	 * @return send user to the "add" view to create a new entity object
	 */
    @GetMapping("/curvePoint/add")
    public String addCurveForm(CurvePoint curve) {
    	
    	log.info("accessing /curvePoint/add endpoint with addCurveForm method");
    	
        return "curvePoint/add";
    }

    /***
     * 
     * @param curve
     * @param result
     * @param model
     * @return send the user back to the "list" page if the curve was correct. Keeps the user on the add page if the curve did contain errors
     */
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

    /***
     * sends the user to the "update" page containing the right curve
     * @param id
     * @param model
     * @return to the endpoint to post the update
     */
    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /curvePoint/update/{id} endpoint with showUpdateForm method");
    	
    	CurvePoint curvePoint = curveService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
        model.addAttribute("curvePoint", curvePoint);
    	
        return "curvePoint/update";
        
    }

    /***
     * 
     * @param id
     * @param curveList
     * @param result
     * @param model
     * @return send the user back to the "list" page when the curve was correct. Keeps the user on the update page if the curve did contain errors
     */
    @PostMapping("/curvePoint/update/{id}")
    public String updateCurvePoint(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
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
    
    /***
     * 
     * @param id
     * @param model
     * @return redirect user to the "list" page after deletion of the object
     */
    @GetMapping("/curvePoint/delete/{id}")
    public String deleteCurve(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /curvePoint/delete/{id} endpoint with deleteCurve method");

    	CurvePoint curvePoint = curveService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id));
    	curveService.delete(curvePoint);
        model.addAttribute("curvePoint", curveService.findAll());
        
        return "redirect:/curvePoint/list";
    }
}
