package it.sportandreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication(scanBasePackages = {"it.sportandreview"})
@EntityScan("it.sportandreview")
@EnableJpaRepositories("it.sportandreview.*")
@EnableJpaAuditing
public class SportAndReviewApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SportAndReviewApiApplication.class, args);
	}

}
