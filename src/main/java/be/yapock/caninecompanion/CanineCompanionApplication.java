package be.yapock.caninecompanion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CanineCompanionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CanineCompanionApplication.class, args);
	}

}
