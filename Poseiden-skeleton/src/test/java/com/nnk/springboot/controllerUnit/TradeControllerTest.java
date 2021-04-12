package com.nnk.springboot.controllerUnit;

import static org.hamcrest.Matchers.containsString;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.Application;
import com.nnk.springboot.config.EntityTestData;
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.service.TradeService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class) 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
class TradeControllerTest {
	
	@LocalServerPort
	private int port;
	
	@MockBean
    private TradeController tradeContro;

	@MockBean
	private TradeService tradeService;
	
	@MockBean
	private TradeRepository tradeRepo;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
    private MockMvc mockMvc;
	
	private Trade trade;
	
	ObjectMapper mapper = new ObjectMapper();
	
	private List<Trade> listOfTrade = new ArrayList<Trade>();

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//Init MockMvc Object and build
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    
		EntityTestData data = new EntityTestData();
	    trade = data.setTrade();
	}

	@Test
	@DisplayName("Asserts that the /trade/list page is displayed correctly")
	public void homeTest() throws Exception {
		
		listOfTrade.add(trade);
		
		when(tradeService.findAll()).thenReturn(listOfTrade);
		
        mockMvc.perform(get("/trade/list"))
        		.andExpect(MockMvcResultMatchers.view().name("trade/list"))
		        .andExpect(content().string(containsString("Id")))
		        .andExpect(content().string(containsString("Account")))
		        .andExpect(content().string(containsString("Type")))
		        .andExpect(content().string(containsString("Buy Quantity")))
		        .andExpect(content().string(containsString("Action")))
                .andExpect(status().isOk());
        
	}
	
	@Test
	@DisplayName("Asserts that the /trade/add page is displayed correctly")
	public void addTradeTest() throws Exception {
		
		
        mockMvc.perform(get("/trade/add"))
		        .andExpect(content().string(containsString("Add New Trade")))
                .andExpect(status().isOk());
	}
}
