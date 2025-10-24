package org.example.matrixspringapp165;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MatrixSpringApp165Application {

    public static void main(String[] args) {
        var con = SpringApplication.run(MatrixSpringApp165Application.class, args);

//        Car car1 = con.getBean("car", Car.class);
//        Car car2 = con.getBean("car", Car.class);
//        Car car3 = con.getBean("car", Car.class);
//
//        con.close();
    }

}
