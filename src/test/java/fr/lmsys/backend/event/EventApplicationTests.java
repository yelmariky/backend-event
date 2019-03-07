package fr.lmsys.backend.event;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@Ignore
@RunWith(SpringRunner.class)
@TestPropertySource(
		  locations = {"classpath:config-upload.properties"})
@SpringBootTest
public class EventApplicationTests {

	@Test
	public void contextLoads() {
	}

}
