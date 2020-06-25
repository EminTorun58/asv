package com.numero.uno.wedify.wedify.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.numero.uno.wedify.wedify.exception.GlobalExceptionHandler;
import com.numero.uno.wedify.wedify.util.MessageResolver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public abstract class BaseIntegrationTest {

	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@Autowired
	protected LocalValidatorFactoryBean localValidatorFactoryBean;

	@Autowired
	protected GlobalExceptionHandler globalExceptionHandler;

	@Autowired
	protected MessageResolver messageResolver;

	@Autowired
	FilterChainProxy springSecurityFilterChain;

	@Autowired
	WebApplicationContext context;

	@BeforeEach
	public void before() {
//		mockMvc = MockMvcBuilders.standaloneSetup(getControllerUnderTest())
//				.setControllerAdvice(globalExceptionHandler)
//				.setValidator(localValidatorFactoryBean)
//				.apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
//				.build();

		mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@AfterEach
	public void after() {
	}

	public String objectToJsonString(final Object obj) throws JsonProcessingException {
		return objectMapper.writeValueAsString(obj);
	}

	protected abstract Object getControllerUnderTest();

}
