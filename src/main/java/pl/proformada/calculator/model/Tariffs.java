package pl.proformada.calculator.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Getter
public class Tariffs {

    private double tonnageFee = 4.05D;
    private double tonnageFeeForVesselsDWTAbove38k = 4.30D;
    private double quayFee = 0.53D;
    private double socialSailFund = 0.02D;
    private double ispsFee = 0.04D;
    private double baf = 0.1;
}