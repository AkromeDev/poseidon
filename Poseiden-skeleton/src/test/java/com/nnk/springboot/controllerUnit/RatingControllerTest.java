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
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.service.RatingService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class) 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
class RatingControllerTest {
	
	@LocalServerPort
	private int port;
	
	@MockBean
    private RatingController ratingContro;

	@MockBean
	private RatingService ratingService;
	
	@MockBean
	private RatingRepository ratingRepo;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
    private MockMvc mockMvc;
	
	private Rating rating;
	
	ObjectMapper mapper = new ObjectMapper();
	
	private List<Rating> listOfRating = new ArrayList<Rating>();

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//Init MockMvc Object and build
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    
		EntityTestData data = new EntityTestData();
	    rating = data.setRating();
	}

	@Test
	@DisplayName("Asserts that the /rating/list page is displayed correctly")
	public void homeTest() throws Exception {
		
		listOfRating.add(rating);
		
		when(ratingService.findAll()).thenReturn(listOfRating);
		
        mockMvc.perform(get("/rating/list"))
        		.andExpect(MockMvcResultMatchers.view().name("rating/list"))
		        .andExpect(content().string(containsString("Id")))
		        .andExpect(content().string(containsString("MoodysRating")))
		        .andExpect(content().string(containsString("SandPRating")))
		        .andExpect(content().string(containsString("FitchRating")))
		        .andExpect(content().string(containsString("Order")))
                .andExpect(status().isOk());
        
	}
	
	@Test
	@DisplayName("Asserts that the /rating/add page is displayed correctly")
	public void addRatingTest() throws Exception {
		
		
        mockMvc.perform(get("/rating/add"))
		        .andExpect(content().string(containsString("Add New Rating")))
                .andExpect(status().isOk());
	}
}
