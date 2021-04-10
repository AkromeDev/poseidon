package com.nnk.springboot.controllerUnit;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.Application;
import com.nnk.springboot.config.EntityTestData;
import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
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
	
	@MockBean
	private BidListRepository bidRepo;
	
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

	@Test
	@DisplayName("Asserts that the /bidList/list page is displayed correctly")
	public void homeTest() throws Exception {
		
		listOfBid.add(bid);
		
		when(bidListService.findAll()).thenReturn(listOfBid);
		
        mockMvc.perform(get("/bidList/list"))
        		.andExpect(MockMvcResultMatchers.view().name("bidList/list"))
		        .andExpect(content().string(containsString("Id")))
		        .andExpect(content().string(containsString("Account")))
		        .andExpect(content().string(containsString("Type")))
		        .andExpect(content().string(containsString("Bid Quantity")))
		        .andExpect(content().string(containsString("Action")))
                .andExpect(status().isOk());
        
	}
	
	@Test
	@DisplayName("Asserts that the /bidList/add page is displayed correctly")
	public void addBidListTest() throws Exception {
		
        mockMvc.perform(get("/bidList/add"))
		        .andExpect(content().string(containsString("Add New Bid")))
                .andExpect(status().isOk());
	}
	
    @Test
    @DisplayName("Asserts that saveBidListTest is called correctly")
    void saveBidListTest() throws Exception {
    	
        mockMvc.perform(MockMvcRequestBuilders.post("/bidList/add")
                .content(new ObjectMapper().writeValueAsString(bid))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        
    }
}
