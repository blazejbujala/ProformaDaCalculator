package pl.proformada.calculator.model;

import org.springframework.stereotype.Component;

@Component
public class ExchangeRate {

    private double ratePLNEUR = 4.50;

    public double getRatePLNEUR() {
        return ratePLNEUR;
    }
}
