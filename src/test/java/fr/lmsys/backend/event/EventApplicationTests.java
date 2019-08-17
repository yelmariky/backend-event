package fr.lmsys.backend.event;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(
		  locations = {"classpath:config-upload.properties"})
@SpringBootTest
public class EventApplicationTests {
	
	
	@Before
	public void init(){
		
	}

	@Test
	public void contextLoads() {
	}

}
