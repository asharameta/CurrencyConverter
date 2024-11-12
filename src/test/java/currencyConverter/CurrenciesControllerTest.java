package currencyConverter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import base.controller.CurrencyController;

import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CurrenciesControllerTest {

	@InjectMocks
	private CurrencyController currenciesController;
	
	private MockMvc mockMvc;
	
	
	@BeforeEach
	void SetUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(currenciesController).build();
	}
	
	@Test
	void GetListOfCurrencies() throws Exception {
	    String expectedJson = "["
	            + "{\"code\": \"USD\", \"name\": \"Dollar\", \"symbol\": \"$\"}"
	            + "]";
		
		mockMvc.perform(get("/currencies")).andExpect(status().isOk()) 
        .andExpect(content().json(expectedJson));
	}
}
