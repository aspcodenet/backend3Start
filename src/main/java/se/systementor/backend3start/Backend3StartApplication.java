package se.systementor.backend3start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.systementor.backend3start.security.UserDataSeeder;

import java.util.Objects;

@SpringBootApplication
public class Backend3StartApplication {


    @Autowired
    private UserDataSeeder userDataSeeder;

    public static void main(String[] args) {
        if (args.length == 0) {
            SpringApplication.run(Backend3StartApplication.class, args);
        } else if (Objects.equals(args[0], "ConsoleApp1")) {
            SpringApplication application = new SpringApplication(ConsoleApp1.class);
            application.setWebApplicationType(WebApplicationType.NONE);
            application.run(args);

        }
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            userDataSeeder.Seed();
        };
    }



}
