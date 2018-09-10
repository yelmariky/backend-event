package fr.lmsys.backend.event.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket postsEventApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("api-backend-event")
				.apiInfo(apiInfo()).select().paths(postPathEvents()).build();
	}
	@Bean
	public Docket postsUserApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("api-backend-user")
				.apiInfo(apiInfo()).select().paths(postPathUsers()).build();
	}
	@Bean
	public Docket getEventApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("api-backend-images")
				.apiInfo(apiInfo()).select().paths(postDocumentationApi()).build();
	}

	private Predicate<String> postPathEvents() {
		return or(regex("/api/events.*"));
	}
	private Predicate<String> postPathUsers() {
		return or(regex("/api/user.*"));
	}
	private Predicate<String> postDocumentationApi() {
		return or(regex("/api/upload.*"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("API BACKEND EVENT")
				.description("api pour gérer les évenements")
				.termsOfServiceUrl("http://localhost:8081")
				.license("EVENEMENT API License LMSYS")
				.licenseUrl("lmsys.contact@gmail.com").version("1.0").build();
	}

}