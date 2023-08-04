package ar.edu.utn.frbb.tup;

import  ar.edu.utn.frbb.tup.controller.CarreraController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ar.edu.utn.frbb.tup.controller")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

