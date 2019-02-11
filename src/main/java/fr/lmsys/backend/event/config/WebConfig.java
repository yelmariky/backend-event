package fr.lmsys.backend.event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@Profile("prod")
public class WebConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/**")
						.allowedOrigins("https://lebonevenement.fr", "http://lebonevenement.fr",
								"https://www.lebonevenement.fr", "http://www.lebonevenement.fr")
						.allowedMethods("POST", "GET", "PUT", "DELETE").allowCredentials(false).maxAge(3600);

			}
		};
	}

}
