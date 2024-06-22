package it.sportandreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "it.sportandreview.repository")
public class SportAndReviewApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SportAndReviewApiApplication.class, args);
	}

}
