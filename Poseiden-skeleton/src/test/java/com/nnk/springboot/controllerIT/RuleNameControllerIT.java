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
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(
		webEnvironment = WebEnvironment.RANDOM_PORT,
		classes = Application.class)
@AutoConfigureMockMvc(secure = false)
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class RuleNameControllerIT {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	CurveController ruleContro;
	
	@Autowired
	RuleNameRepository ruleRepo;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private RuleName rule;
	private RuleName updatedRule;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//Init MockMvc Object and build
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	    
		EntityTestData data = new EntityTestData();
	    rule = data.setRule();
	}

	@Test
	@DisplayName("1 Asserts that addRuleForm saves rule correctly to the database")
	public void addRuleFormIT() throws Exception {
		
		RuleName someRule = rule;
		someRule.setName("some name");
		someRule.setDescription("some description");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate")
				.content(new ObjectMapper().writeValueAsString(someRule))
				.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
		
		RuleName ruleFromRepo = ruleRepo.findById(1).get();
		
	 	assertEquals("some name", ruleFromRepo.getName());
        assertEquals("some description", ruleFromRepo.getDescription());
        assertEquals(ruleRepo.findAll().size(), 1);
        
        
	}
	
	@Test
	@DisplayName("2 Asserts that the rule has been correctly updated and that there is only one rule in the database")
	public void addRuleNameTest() throws Exception {
		
		ruleRepo.save(rule);
		updatedRule = rule;
		updatedRule.setName("new name");
		updatedRule.setDescription("new description");
		
		mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/update/{id}", 1)
					.accept(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(updatedRule))
	                .contentType(MediaType.APPLICATION_JSON))
	                .andExpect(status().is3xxRedirection());
		
		System.out.println("HERE MTF : " + ruleRepo.findAll());
		
        RuleName ruleFromRepo = ruleRepo.findById(1).get();
        
        assertEquals(updatedRule.getName(), ruleFromRepo.getName());
        assertEquals(updatedRule.getDescription(), ruleFromRepo.getDescription());
        assertEquals(1, ruleRepo.findAll().size());
	}
	
    @Test
    @DisplayName("3 Asserts that the rule with the Id 1 has been succesfully deleted")
    void saveRuleNameTest() throws Exception {
    	
    	ruleRepo.save(rule);
    	
        mockMvc.perform(MockMvcRequestBuilders.get("/ruleName/delete/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection());
        
        assertEquals(ruleRepo.findAll().size(), 0);
        
    }
    
	@Test
	@DisplayName("4 Asserts that Exception is thrown when the entity does not respect the validation and that the oject is not saved")
	public void addWrongRuleNameTest() throws Exception {
		
		updatedRule = rule;
		updatedRule.setName("");
		updatedRule.setDescription("");
		
		assertThrows(AssertionError.class, () -> {
			
		mockMvc.perform(MockMvcRequestBuilders.post("/ruleName/validate")
					.accept(MediaType.APPLICATION_JSON)
	                .content(new ObjectMapper().writeValueAsString(rule))
	                .contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest())
					.andExpect(model().attributeHasErrors("name"));
		});
		
		assertEquals(0, ruleRepo.findAll().size());
		
	}
}
