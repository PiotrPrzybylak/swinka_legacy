package it.morfoza;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApplicationDemo {

    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "demo");
        System.setProperty("JDBC_DATABASE_URL", "jdbc");
        Application.main(args);
    }

}
