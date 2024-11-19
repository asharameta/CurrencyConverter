package base.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import base.model.CurrencyDAO;
import base.model.ExchangeRateDAO;
import base.service.CurrencyService;
import base.service.ExchangeRateService;

@Configuration
@ComponentScan("base")
@PropertySource("classpath:application.properties")
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
	@Value("${spring.datasource.driver}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public CurrencyDAO currencyDAO(JdbcTemplate jdbcTemplate) {
		return new CurrencyDAO(jdbcTemplate);
	}
	
	@Bean
	public ExchangeRateDAO exchangeRateDAO(JdbcTemplate jdbcTemplate) {
		return new ExchangeRateDAO(jdbcTemplate);
	}
	
	@Bean
	public CurrencyService currencyService(CurrencyDAO currencyDAO) {
		return new CurrencyService(currencyDAO);
	}
	
	@Bean
	public ExchangeRateService exchangeRateService(ExchangeRateDAO exchangeRateDAO) {
		return new ExchangeRateService(exchangeRateDAO);
	}
}
