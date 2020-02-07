package fr.lmsys.backend.event;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.BaseOAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProvider;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(
		  locations = {"classpath:config-upload.properties"})
@SpringBootTest
public class EventApplicationTests {
	
	

	private BaseOAuth2ProtectedResourceDetails resource;
	@MockBean
	private OAuth2RestTemplate restTemplate;

	private AccessTokenProvider accessTokenProvider = Mockito.mock(AccessTokenProvider.class);

	private ClientHttpRequest request;

	private HttpHeaders headers;

	@Before
	public void open() throws Exception {
		resource = new BaseOAuth2ProtectedResourceDetails();
		// Facebook and older specs:
		resource.setTokenName("bearer_token");
		restTemplate = new OAuth2RestTemplate(resource);
		restTemplate.setAccessTokenProvider(accessTokenProvider);
		//when(restTemplate.getAccessToken()).thenReturn(accessTokenProvider.obtainAccessToken(resource, null));
		request = Mockito.mock(ClientHttpRequest.class);
		headers = new HttpHeaders();
		Mockito.when(request.getHeaders()).thenReturn(headers);
		ClientHttpResponse response = Mockito.mock(ClientHttpResponse.class);
		HttpStatus statusCode = HttpStatus.OK;
		Mockito.when(response.getStatusCode()).thenReturn(statusCode);
		Mockito.when(request.execute()).thenReturn(response);
	}

	@Test
	public void contextLoads() {
	}

}
