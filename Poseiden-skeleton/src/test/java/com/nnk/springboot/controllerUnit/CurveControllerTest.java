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
import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.service.CurveService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class) 
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(secure = false)
class CurveControllerTest {
	
	@LocalServerPort
	private int port;
	
	@MockBean
    private CurveController curveContro;

	@MockBean
	private CurveService curveService;
	
	@MockBean
	private CurvePointRepository curveRepo;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
    private MockMvc mockMvc;
	
	private CurvePoint curve;
	
	ObjectMapper mapper = new ObjectMapper();
	
	private List<CurvePoint> listOfCurve = new ArrayList<CurvePoint>();

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		//Init MockMvc Object and build
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	    
		EntityTestData data = new EntityTestData();
	    curve = data.setCurve();
	}

	@Test
	@DisplayName("Asserts that the /curvePoint/list page is displayed correctly")
	public void homeTest() throws Exception {
		
		listOfCurve.add(curve);
		
		when(curveService.findAll()).thenReturn(listOfCurve);
		
        mockMvc.perform(get("/curvePoint/list"))
        		.andExpect(MockMvcResultMatchers.view().name("curvePoint/list"))
		        .andExpect(content().string(containsString("CurvePointId")))
		        .andExpect(content().string(containsString("Term")))
		        .andExpect(content().string(containsString("Value")))
		        .andExpect(content().string(containsString("Action")))
                .andExpect(status().isOk());
        
	}
	
	@Test
	@DisplayName("Asserts that the /curve/add page is displayed correctly")
	public void addCurveTest() throws Exception {
		
		
        mockMvc.perform(get("/curvePoint/add"))
		        .andExpect(content().string(containsString("Add New Curve")))
                .andExpect(status().isOk());
	}
}
