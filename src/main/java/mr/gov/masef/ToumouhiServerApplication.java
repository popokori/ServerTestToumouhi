package mr.gov.masef;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"mr.gov.masef"})
public class ToumouhiServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToumouhiServerApplication.class, args);
	}

}
