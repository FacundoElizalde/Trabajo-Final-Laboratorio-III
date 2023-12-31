package ar.edu.utn.frbb.tup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ar.edu.utn.frbb.tup")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}