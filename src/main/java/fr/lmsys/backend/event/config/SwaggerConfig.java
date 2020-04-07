package fr.lmsys.backend.event.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Value("security.oauth2.client.access-token-uri")
	private String accesTokenUri;
	@Value("security.oauth2.client.user-authorization-uri")
	private String userAuthorizationUri;
	@Value("security.oauth2.client.client-secret")
	private String clientSecret;
	@Value("security.oauth2.client.client-id")
	private String clientId;

	@Bean
	public Docket postsEventApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("api-backend-event").apiInfo(apiInfo()).select()
				.paths(postDocumentationApi()).build().securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext()));
	}

	@Bean
	public Docket postsUserApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("api-backend-user").apiInfo(apiInfo()).select()
				.paths(postDocumentationApi()).build().securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext()));
	}

	@Bean
	public Docket postImagesEventApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("api-backend-images").apiInfo(apiInfo()).select()
				.paths(postDocumentationApi()).build().securitySchemes(Arrays.asList(securityScheme()))
				.securityContexts(Arrays.asList(securityContext()));
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
		  .securityReferences(
			Arrays.asList(new SecurityReference("spring_oauth", scopes())))
		  .forPaths(PathSelectors.regex("/api.*"))
		  .build();
	}

	private SecurityScheme securityScheme() {
		GrantType grantType = new AuthorizationCodeGrantBuilder()
			.tokenEndpoint(new TokenEndpoint(accesTokenUri, "oauthtoken"))
			.tokenRequestEndpoint(
			  new TokenRequestEndpoint(userAuthorizationUri, clientId, clientSecret))
			.build();
	 
		SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
			.grantTypes(Arrays.asList(grantType))
			.scopes(Arrays.asList(scopes()))
			.build();
		return oauth;
	}

	private AuthorizationScope[] scopes() {
		AuthorizationScope[] scopes = { 
		  new AuthorizationScope("read", "for read operations"), 
		  new AuthorizationScope("write", "for write operations"), 
		  new AuthorizationScope("openid", "Access foo API") };
		return scopes;
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
		return new ApiInfoBuilder().title("API BACKEND EVENT").description("api pour gérer les évenements")
				.termsOfServiceUrl("http://localhost:8085").license("EVENEMENT API License LMSYS")
				.licenseUrl("http://lmsys-lab.fr").version("1.0").build();
	}

}