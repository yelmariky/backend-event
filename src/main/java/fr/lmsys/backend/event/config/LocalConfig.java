package fr.lmsys.backend.event.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


	@Configuration
	@Profile("local")
	@EnableWebMvc
	public class LocalConfig extends WebMvcConfigurerAdapter {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
			registry.addMapping("/api/**").allowedOrigins("http://localhost:4200").allowedMethods("POST", "GET", "PUT", "DELETE").allowCredentials(false).maxAge(3600);
		}
	}
