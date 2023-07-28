package pl.proformada.calculator.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Getter
public class ExchangeRate {

    private Rate[] rates;

    public double getRatePLNEUR(){
        return Arrays.stream(rates).findFirst().get().getMid();
    }

}
