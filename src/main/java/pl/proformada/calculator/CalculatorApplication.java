package pl.proformada.calculator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class CalculatorApplication {

	public static void main(String[] args) {

		SpringApplication.run(CalculatorApplication.class, args);
	}

}
