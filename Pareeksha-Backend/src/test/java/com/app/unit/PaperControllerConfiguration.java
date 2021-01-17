package com.app.unit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;

@Profile("test")
@Configuration
public class PaperControllerConfiguration {

	@Autowired
	MockMvc mockMvc;
	
	
	
}
