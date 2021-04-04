package com.nnk.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.nnk.springboot.controllers.BidListController;
import com.nnk.springboot.controllers.CurveController;
import com.nnk.springboot.controllers.RatingController;
import com.nnk.springboot.controllers.RuleNameController;
import com.nnk.springboot.controllers.TradeController;
import com.nnk.springboot.controllers.UserController;

/***
 * 
 * @author j.c.
 * Sanity Checks that ensure that the app has: A main starting method & The define working controllers for our entities
 * 
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Application.class) 
@SpringBootTest
public class WebApplicationTests {

	@Autowired
	BidListController bidContro;
	
	@Autowired
	CurveController curveContro;
	
	@Autowired
	RatingController ratingContro;
	
	@Autowired
	RuleNameController ruleContro;
	
	@Autowired
	TradeController tradeContro;
	
	@Autowired
	UserController userContro;
	
	@Test
	@DisplayName("Tests if the app has a main")
	public void contextLoads() {
	}
	
	@Test
	@DisplayName("Tests if BidListController loads correctly")
	public void bidControLoad() throws Exception {
		assertThat(bidContro).isNotNull();
	}
	
	@Test
	@DisplayName("Tests if CurveController loads correctly")
	public void curveControLoad() throws Exception {
		assertThat(curveContro).isNotNull();
	}
	
	@Test
	@DisplayName("Tests if RatingController loads correctly")
	public void ratingControLoad() throws Exception {
		assertThat(ratingContro).isNotNull();
	}
	
	@Test
	@DisplayName("Tests if RuleNameController loads correctly")
	public void ruleControLoad() throws Exception {
		assertThat(ruleContro).isNotNull();
	}
	
	@Test
	@DisplayName("Tests if TradeController loads correctly")
	public void tradeControLoad() throws Exception {
		assertThat(tradeContro).isNotNull();
	}
	
	@Test
	@DisplayName("Tests if UserController loads correctly")
	public void userControLoad() throws Exception {
		assertThat(userContro).isNotNull();
	}
	
}