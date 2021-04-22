package com.nnk.springboot.controllerIT;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.Application;
import com.nnk.springboot.config.EntityTestData;
import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = Application.class)
@AutoConfigureMockMvc(secure = false)
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class BidControllerTestIT {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	BidListController bidContro;
	
	@Autowired
	BidListRepository bidRepo;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private BidList bid;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//Init MockMvc Object and build
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	    
		EntityTestData data = new EntityTestData();
	    bid = data.setBid();
	}

	@Test
	@DisplayName("1 Asserts that addBidForm saves bid correctly to the database")
	public void addBidFormIT() throws Exception {
		
		BidList someBid = bid;
		someBid.setAccount("some account");
		someBid.setBidQuantity(666d);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/bidList/add")
				.content(new ObjectMapper().writeValueAsString(someBid))
				.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
		BidList bidFromRepo = bidRepo.findById(1).get();
		
	 	assertEquals(666, bidFromRepo.getBidQuantity());
        assertEquals(someBid.getAccount(), bidFromRepo.getAccount());
        assertEquals(bidRepo.findAll().size(), 1);
        
        
	}
	
	@Test
	@DisplayName("2 Asserts that the bid has been correctly updated and that there is only one bid in the database")
	public void addBidListTest() throws Exception {
		
		bidRepo.save(bid);
		
		BidList updatedBid = bid;
		
		updatedBid.setAccount("new account");
		updatedBid.setBidListId(null);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/bidList/update/{id}", 1)
				.flashAttr("bidList", updatedBid)
				.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
		
        BidList bidFromRepo = bidRepo.findById(1).get();
        
        assertEquals(updatedBid.getBidListId(), bidFromRepo.getBidListId());
        assertEquals(updatedBid.getAccount(), bidFromRepo.getAccount());
        assertEquals(1, bidRepo.findAll().size());
	}
	
    @Test
    @DisplayName("3 Asserts that the bid with the Id 1 has been succesfully deleted")
    void saveBidListTest() throws Exception {
    	
    	bidRepo.save(bid);
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/bidList/delete/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
        
        assertEquals(bidRepo.findAll().size(), 0);
        
    }
    
	@Test
	@DisplayName("4 Asserts that Exception is thrown when the entity does not respect the validation and that the oject is not saved")
	public void addWrongBidListTest() throws Exception {
		
		bid.setAccount("");
		bid.setBidQuantity(0d);
		
		assertThrows(NestedServletException.class, () -> {
			
		mockMvc.perform(MockMvcRequestBuilders.post("/bidList/add")
					.accept(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(bid))
	                .contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest())
					.andExpect(model().attributeHasErrors("account"));
		});
		
		assertEquals(0, bidRepo.findAll().size());
		
	}
}
