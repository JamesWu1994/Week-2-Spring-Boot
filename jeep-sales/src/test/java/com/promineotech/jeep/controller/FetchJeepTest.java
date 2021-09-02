package com.promineotech.jeep.controller;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.promineotech.jeep.controller.support.FetchJeepTestSupport;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class FetchJeepTest extends FetchJeepTestSupport{
	 @Test
	  void testThatJeepsAreReturnedWhenAValidModelAndTrimAreSupplied() {
	    // Given: a valid model, trim and URI
	    JeepModel model = JeepModel.WRANGLER;
	    String trim = "Sport";
	    String uri = String.format("%s?model=%s&trim=%s", getBaseUri(), model, trim);
	    //When a connection is made to the URI
	    ResponseEntity<Jeep> response= getRestTemplate().getForEntity(uri, Jeep.class);
	    
	    // Then a 200 HTTP Status code is return 
	    
	    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	    
	    // And: The actual list is the same as expected list
	    List<Jeep> expected = buildExpected();
	    assertThat(response.getBody()).isEqualTo(expected);
	 } 
  }

