package pl.proformada.calculator.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Tariffs {

    private double tonnageFee = 3.30D;
    private double tonnageFeeForVesselsDWTAbove38k = 3.50D;
    private double quayFee = 0.45D;
    private double socialSailFund = 0.02D;
    private double ispsFee = 0.04D;
    private double baf = 0.24D;

    public double getBaf() {
        return baf;
    }

    public double getTonnageFee() {
        return tonnageFee;
    }

    public double getTonnageFeeForVesselsDWTAbove38k() {
        return tonnageFeeForVesselsDWTAbove38k;
    }

    public double getQuayFee() {
        return quayFee;
    }

    public double getSocialSailFund() {
        return socialSailFund;
    }

    public double getIspsFee() { return ispsFee; }
}
