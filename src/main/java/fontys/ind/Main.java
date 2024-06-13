package fontys.ind;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// @SpringBootApplication() TODO: check this
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}