package seoul.go.ggo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GgoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GgoApplication.class, args);
	}

}
