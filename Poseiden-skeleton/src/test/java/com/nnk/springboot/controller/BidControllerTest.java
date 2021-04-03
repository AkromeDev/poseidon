package com.nnk.springboot.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.Application;
import com.nnk.springboot.config.EntityTestData;
import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.service.BidListService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class) 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
class BidControllerTest {
	
	@LocalServerPort
	private int port;
	
	@MockBean
    private BidListController bidContro;

	@MockBean
	private BidListService bidListService;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
    private MockMvc mockMvc;
	
	private BidList bid;
	
	ObjectMapper mapper = new ObjectMapper();
	
	private List<BidList> listOfBid = new ArrayList<BidList>();

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//Init MockMvc Object and build
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    
		EntityTestData data = new EntityTestData();
	    bid = data.setBid();
	}

//	@Test
//	public void bidListLoad() throws Exception {
//		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/bidList/list",
//				String.class)).contains("Bid List");
//	}
	
	@Test
	@DisplayName("Asserts that the bids are retrieved by the home method")
	public void homeTest() throws Exception {
		
		listOfBid.add(bid);
		
		when(bidListService.findAll()).thenReturn(Arrays.asList(bid));
        mockMvc.perform(get("/bidList/list"))
        // TODO Resolve hamcrest problem. The jar is not at the right place, it should be above junit.
//		        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
		        .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(model().attribute("bidList", hasSize(1)))
//        		.andExpect(model().attribute("bidList", hasItem(bid)))
//                .andExpect(model().attribute("bidList", 
//                		hasProperty(("account"), is("account"))))
                .andExpect(status().isOk());
	}
	
	
    @Test
    @DisplayName("Asserts that a bid has been saved")
    void saveBidListTest() throws Exception {
        when(bidContro.saveBidList(any(BidList.class))).thenReturn(bid);
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/add")
                .content(new ObjectMapper().writeValueAsString(bid))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
	
}
