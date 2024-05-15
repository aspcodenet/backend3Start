package se.systementor.backend3start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Objects;

@SpringBootApplication
public class Backend3StartApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            SpringApplication.run(Backend3StartApplication.class, args);
        } else if (Objects.equals(args[0], "ConsoleApp1")) {
            SpringApplication application = new SpringApplication(ConsoleApp1.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);

        }
    }
}
