package pl.proformada.calculator.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.proformada.calculator.webService.ExchangeRateService;
import pl.proformada.calculator.model.ExchangeRate;
import pl.proformada.calculator.model.PilotTariff;
import pl.proformada.calculator.model.ProformaDa;
import pl.proformada.calculator.model.Tariffs;
import pl.proformada.calculator.model.TowageAndMooringTariff;
import pl.proformada.calculator.model.Vessel;

@RequiredArgsConstructor
@Service
public class ProformaCalculatorService {

    private final ExchangeRateService exchangeRateService = new ExchangeRateService();
    private Tariffs tariffs = new Tariffs();
    private ExchangeRate exchangeRate = exchangeRateService.getExchangeRateEur("eur");
    private TowageAndMooringTariff towageAndMooringTariff = new TowageAndMooringTariff();
    private PilotTariff pilotTariff = new PilotTariff();

    ProformaDa.Builder builder = new ProformaDa.Builder();

    public double calculateVesselVolumen(double loa, double beam, double MSD) {
        return loa * beam * MSD;
    }

    public double calculateTonnageFee(int DWT, int GTred) {
        if (DWT <= 38000) {
            return BigDecimal.valueOf(tariffs.getTonnageFee()
                    * GTred
                    / exchangeRate.getRatePLNEUR())
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return BigDecimal.valueOf(tariffs.getTonnageFeeForVesselsDWTAbove38k() * GTred / exchangeRate.getRatePLNEUR())
            .setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double calculateQuayFee(int GTred) {
        return BigDecimal.valueOf(tariffs.getQuayFee()
                * GTred
                / exchangeRate.getRatePLNEUR())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double calculateSocialSailFund(int GT) {
        if (GT * tariffs.getSocialSailFund() >= 300) {
            return BigDecimal.valueOf(300 / exchangeRate.getRatePLNEUR())
                    .setScale(2, RoundingMode.HALF_UP)
                    .doubleValue();
        }
        return BigDecimal.valueOf(tariffs.getSocialSailFund() * GT / exchangeRate.getRatePLNEUR())
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public double checkTowageDues(double loa, double beam, double MSD) {
        double vol = calculateVesselVolumen(loa, beam, MSD);
        for (int i = 0; i < towageAndMooringTariff.getTowageMooringDues().length; i++) {
            if (vol >= towageAndMooringTariff.getTowageMooringDues()[i][0] && vol <= towageAndMooringTariff.getTowageMooringDues()[i][1]) {
                return towageAndMooringTariff.getTowageMooringDues()[i][2];
            }
        }
        throw new NoSuchElementException("No element in Towage tariff found, pls check vessels dtls");
    }

    public int checkNumberOfTugs(double loa) {
        if (loa > 110 && loa <= 160) {
            return 2;
        } else if (160 < loa && loa <= 220) {
            return 3;
        } else if (loa > 220) {
            return 4;
        }
        return 1;
    }

    public int calculateTowingRopeCost(double loa) {
        return (checkNumberOfTugs(loa) - 1) * 65;
    }

    public double calculateTotalTowageDues(double loa, double beam, double MSD) {
        return 2 * (checkNumberOfTugs(loa)
                * checkTowageDues(loa, beam, MSD)
                * (1 + tariffs.getBaf())
                + calculateTowingRopeCost(loa));
    }

    public double checkMooringDues(double loa, double beam, double MSD) {
        double vol = calculateVesselVolumen(loa, beam, MSD);
        for (int i = 0; i < towageAndMooringTariff.getTowageMooringDues().length; i++) {
            if (vol >= towageAndMooringTariff.getTowageMooringDues()[i][0]
                    && vol <= towageAndMooringTariff.getTowageMooringDues()[i][1])
            {
                return towageAndMooringTariff.getTowageMooringDues()[i][3];
            }
        }
        throw new NoSuchElementException("No element in Mooring tariff found");
    }

    public double calculateTotalMooringDues(double loa, double beam, double MSD) {
        return 2 * checkMooringDues(loa, beam, MSD);
    }

    public int checkPilotDues(double loa, double beam, double MSD) {
        double vol = calculateVesselVolumen(loa, beam, MSD);
        for (int i = 0; i < pilotTariff.getPilotDues().length; i++) {
            if (vol >= pilotTariff.getPilotDues()[i][0] && vol <= pilotTariff.getPilotDues()[i][1]) {
                return pilotTariff.getPilotDues()[i][2];
            }
        }
        throw new NoSuchElementException("No element Pilot tariff found");
    }


    public double calculatePilotsDues(double loa, double beam, double MSD) {
        if (loa > 200) {
            return BigDecimal.valueOf(2 * checkPilotDues(loa, beam, MSD) * 1.25).setScale(2, RoundingMode.HALF_UP).doubleValue();
        }
        return BigDecimal.valueOf(2 * checkPilotDues(loa, beam, MSD)).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double calculateIspsDues(int GT) {
        if (GT * tariffs.getIspsFee() >= 3000) {
            return 3000;
        }
        return BigDecimal.valueOf(GT * tariffs.getIspsFee()).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public double calculateTotalCost(double loa, double beam, double MSD, int DWT, int Gtred, int GT) {
        return calculateTonnageFee(DWT, Gtred)
            + calculateQuayFee(Gtred)
            + calculateSocialSailFund(GT)
            + calculateTotalTowageDues(loa, beam, MSD)
            + calculateTotalMooringDues(loa, beam, MSD)
            + calculatePilotsDues(loa, beam, MSD)
            + calculateIspsDues(GT);
    }

    public ProformaDa proformaDa(Vessel vessel) {
        return builder
            .tonnageFee(calculateTonnageFee(vessel.getDWT(), vessel.getGTred()))
            .quayFee(calculateQuayFee(vessel.getGTred()))
            .socialSailFund(calculateSocialSailFund(vessel.getGT()))
            .towageDues(calculateTotalTowageDues(vessel.getLoa(), vessel.getBeam(), vessel.getMSD()))
            .totalMooring(calculateTotalMooringDues(vessel.getLoa(), vessel.getBeam(), vessel.getMSD()))
            .pilotDues(calculatePilotsDues(vessel.getLoa(), vessel.getBeam(), vessel.getMSD()))
            .ispsDuea(calculateIspsDues(vessel.getGT()))
            .totalCost(calculateTotalCost(vessel.getLoa(), vessel.getBeam(), vessel.getMSD(), vessel.getDWT(), vessel.getGTred(), vessel.getGT()))
            .build();
    }
}
