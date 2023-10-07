package az.elvinali.demo;

import az.elvinali.demo.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringRestExampleApplication {

    public static void main(String[] args) {
        //h2 database
        SpringApplication.run(SpringRestExampleApplication.class, args);

    }

}
