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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.Application;
import com.nnk.springboot.config.EntityTestData;
import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = Application.class)
@AutoConfigureMockMvc(secure = false)
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class TradeControllerIT {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	CurveController tradeContro;
	
	@Autowired
	TradeRepository tradeRepo;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private Trade trade;
	private Trade updatedTrade;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//Init MockMvc Object and build
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	    
		EntityTestData data = new EntityTestData();
	    trade = data.setTrade();
	}

	@Test
	@DisplayName("1 Asserts that addTradeForm saves trade correctly to the database")
	public void addTradeFormIT() throws Exception {
		
		Trade someTrade = trade;
		someTrade.setAccount("some account");
		someTrade.setType("some type");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate")
				.flashAttr("trade", someTrade)
				.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
		
		Trade tradeFromRepo = tradeRepo.findById(1).get();
		
	 	assertEquals("some account", tradeFromRepo.getAccount());
        assertEquals("some type", tradeFromRepo.getType());
        assertEquals(tradeRepo.findAll().size(), 1);
        
        
	}
	
	@Test
	@DisplayName("2 Asserts that the trade has been correctly updated and that there is only one trade in the database")
	public void addTradeTest() throws Exception {
		
		tradeRepo.save(trade);
		updatedTrade = trade;
		updatedTrade.setAccount("new account");
		updatedTrade.setType("new type");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/trade/update/{id}", 1)
					.accept(MediaType.APPLICATION_JSON)
					.flashAttr("trade", updatedTrade)
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().is3xxRedirection());
		
		System.out.println("HERE MTF : " + tradeRepo.findAll());
		
        Trade tradeFromRepo = tradeRepo.findById(1).get();
        
        assertEquals(updatedTrade.getAccount(), tradeFromRepo.getAccount());
        assertEquals(updatedTrade.getType(), tradeFromRepo.getType());
        assertEquals(1, tradeRepo.findAll().size());
	}
	
    @Test
    @DisplayName("3 Asserts that the trade with the Id 1 has been succesfully deleted")
    void saveTradeTest() throws Exception {
    	
    	tradeRepo.save(trade);
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/trade/delete/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
        
        assertEquals(tradeRepo.findAll().size(), 0);
        
    }
    
	@Test
	@DisplayName("4 Asserts that Exception is thrown when the entity does not respect the validation and that the oject is not saved")
	public void addWrongTradeTest() throws Exception {
		
		updatedTrade = trade;
		updatedTrade.setAccount("");
		updatedTrade.setType("");
		
		assertThrows(AssertionError.class, () -> {
			
		mockMvc.perform(MockMvcRequestBuilders.post("/trade/validate")
					.accept(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(updatedTrade))
	                .contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest())
					.andExpect(model().attributeHasErrors("account"));
		});
		
		assertEquals(0, tradeRepo.findAll().size());
		
	}
}
