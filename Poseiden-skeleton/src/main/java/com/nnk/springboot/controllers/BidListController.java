package com.nnk.springboot.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;

@Controller
public class BidListController {
	
	private static final Logger log = LoggerFactory.getLogger(BidListController.class);
    
	@Autowired
	BidListService bidListService;

	/***
	 * 
	 * @param model
	 * @return a List containing all BidLists
	 */
	@GetMapping("/bidList/list")
    public String home(Model model) {
    	
    	log.info("accessing /bidList/list endpoint with home method");
    	model.addAttribute("bidList", bidListService.findAll());
        return "bidList/list";
    }

	/***
	 * 
	 * @param bid
	 * @return add view for a new bid
	 */
    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
    	
    	log.info("accessing /bidList/add endpoint with addBidForm method");
        return "bidList/add";
    }
    
    /***
     * 
     * @param bid
     * @return the bid that has been saved to the database
     */
    @PostMapping("/bidList/add")
    public BidList saveBidList(@RequestBody BidList bid) {
    	
    	log.info("accessing /bidList/add endpoint with saveBidList method");
    	return bidListService.saveBid(bid);
    }

    /***
     * 
     * @param bid
     * @param result
     * @param model
     * @return send the user back to the "list" page when the bid was correct. Keeps the user on the add page if the bid did contain errors
     */
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
    	
    	log.info("accessing /bidList/validate endpoint with validate method" + bid);
    	
    	if (!result.hasErrors()) {
            bidListService.saveBid(bid);
            model.addAttribute("bidList", bidListService.findAll());
            return "redirect:/bidList/list";
        }
        return "bidList/add";
    }

    /***
     * 
     * @param id
     * @param model
     * @return to the endpoint to post the update
     */
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /bidList/update/{id} endpoint with showUpdateForm method");
    	
    	BidList bidList = bidListService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bidList);
    	
        return "bidList/update";
    }

    /***
     * 
     * @param id
     * @param bidList
     * @param result
     * @param model
     * @return send the user back to the "list" page when the bid was correct. Keeps the user on the update page if the bid did contain errors
     */
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable final Integer id, @Valid @RequestBody BidList bidList,
                             BindingResult result, Model model) {
    	
    	log.info("accessing /bidList/update/{id} endpoint with updateBid method");
    	
    	if (result.hasErrors()) {
            return "bidList/update";
        }

    	bidList.setBidListId(id);
        bidListService.saveBid(bidList);
        model.addAttribute("bidList", bidListService.findAll());
    	
        return "redirect:/bidList/list";
    }

    /***
     * 
     * @param id
     * @param model
     * @return redirect user to the "list" page after deletion of the object
     */
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
    	
    	log.info("accessing /bidList/delete/{id} endpoint with deleteBid method");
    	
    	BidList bidList = bidListService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
    	bidListService.delete(bidList);
        model.addAttribute("bidList", bidListService.findAll());
    	
        return "redirect:/bidList/list";
    }
}
