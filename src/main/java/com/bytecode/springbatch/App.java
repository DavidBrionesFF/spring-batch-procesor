package com.bytecode.springbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//Agregamos la anotacion para ejecucion de tareas
@EnableScheduling
//Agregamos la anotacion para que detecte como una aplicacion spring
@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
