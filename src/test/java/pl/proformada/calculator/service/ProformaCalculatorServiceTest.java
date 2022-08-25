package pl.proformada.calculator.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.proformada.calculator.model.ProformaDa;
import pl.proformada.calculator.model.Vessel;

class ProformaCalculatorServiceTest {

    Vessel vessel = new Vessel("Fish", 125.4, 12.3, 6.5, 4500, 5000, 7000);
    Vessel bigVessel = new Vessel("Arka", 280.4, 42.3, 15.0, 46254, 58156, 103231);

    ProformaCalculatorService proformaCalculatorService = new ProformaCalculatorService();

    @Test
    void shouldCalculateVesselVolumen() {
        double result = proformaCalculatorService.calculateVesselVolumen(vessel.getLoa(), vessel.getBeam(), vessel.getMSD());
        Assertions.assertEquals(10025.73, result);
    }

    @Test
    void shouldCalculateTonnageFee() {
        double result = proformaCalculatorService.calculateTonnageFee(vessel.getDWT(), vessel.getGTred());
        Assertions.assertEquals(3300, result);
    }

    @Test
    void shouldCalculateTonnageFeeForBigVessel() {
        double result = proformaCalculatorService.calculateTonnageFee(bigVessel.getDWT(), bigVessel.getGTred());
        Assertions.assertEquals(35975.33, result);
    }

    @Test
    void shouldCalculateQuayFee() {
        double result = proformaCalculatorService.calculateQuayFee(vessel.getGTred());
        Assertions.assertEquals(450, result);
    }

    @Test
    void shouldCalculateSocialSailFund() {
        double result = proformaCalculatorService.calculateSocialSailFund(vessel.getGT());
        Assertions.assertEquals(22.22, result);
    }

    @Test
    void shouldCalculateSocialSailFundForBigVessel() {
        double result = proformaCalculatorService.calculateSocialSailFund(bigVessel.getGT());
        Assertions.assertEquals(66.67, result);
    }

    @Test
    void shouldCheckTowageDuesInTowageTariff() {
        double result = proformaCalculatorService.checkTowageDues(vessel.getLoa(), vessel.getBeam(), vessel.getMSD());
        Assertions.assertEquals(578, result);
    }

    @Test
    void shouldCheckTowageDuesInTowageTariffForGiantVessel() {
        double result = proformaCalculatorService.checkTowageDues(1000, 55, 25);
        Assertions.assertEquals(4467, result);
    }

    @Test
    void checkNumberOfTugs() {
        double result = proformaCalculatorService.checkNumberOfTugs(vessel.getLoa());
        Assertions.assertEquals(2, result);
    }

    @Test
    void checkNumberOfTugsForBigVessel() {
        double result = proformaCalculatorService.checkNumberOfTugs(bigVessel.getLoa());
        Assertions.assertEquals(4, result);
    }

    @Test
    void shouldCalculateTowingRopeCost() {
        double result = proformaCalculatorService.calculateTowingRopeCost(vessel.getLoa());
        Assertions.assertEquals(65, result);
    }

    @Test
    void shouldCalculateTowingRopeCosForBigVessel() {
        double result = proformaCalculatorService.calculateTowingRopeCost(bigVessel.getLoa());
        Assertions.assertEquals(195, result);
    }

    @Test
    void shouldCalculateTotalTowageDues() {
        double result = proformaCalculatorService.calculateTotalTowageDues(vessel.getLoa(), vessel.getBeam(), vessel.getMSD());
        Assertions.assertEquals(2996.88, result);
    }

    @Test
    void shouldCalculateTotalTowageDuesForBigVessel() {
        double result = proformaCalculatorService.calculateTotalTowageDues(bigVessel.getLoa(), bigVessel.getBeam(), bigVessel.getMSD());
        Assertions.assertEquals(36657.52, result);
    }

    @Test
    void shouldCheckMooringDues() {
        double result = proformaCalculatorService.checkMooringDues(vessel.getLoa(), vessel.getBeam(), vessel.getMSD());
        Assertions.assertEquals(138, result);
    }

    @Test
    void shouldCalculateTotalMooringDues() {
        double result = proformaCalculatorService.calculateTotalMooringDues(vessel.getLoa(), vessel.getBeam(), vessel.getMSD());
        Assertions.assertEquals(276, result);
    }

    @Test
    void checkPilotDues() {
        double result = proformaCalculatorService.checkPilotDues(vessel.getLoa(), vessel.getBeam(), vessel.getMSD());
        Assertions.assertEquals(474, result);
    }

    @Test
    void shouldCalculatePilotsDues() {
        double result = proformaCalculatorService.calculatePilotsDues(vessel.getLoa(), vessel.getBeam(), vessel.getMSD());
        Assertions.assertEquals(948, result);
    }

    @Test
    void shouldCalculateIspsDues() {
        double result = proformaCalculatorService.calculateIspsDues(vessel.getGT());
        Assertions.assertEquals(200, result);
    }

    @Test
    void shouldCalculateIspsDuesForBigVessel() {
        double result = proformaCalculatorService.calculateIspsDues(80000);
        Assertions.assertEquals(3000, result);
    }

    @Test
    void shouldCalculateTotalCost() {
        double result = proformaCalculatorService.calculateTotalCost(vessel.getLoa(), vessel.getBeam(), vessel.getMSD(), vessel.getDWT(), vessel.getGTred(), vessel.getGT());
        Assertions.assertEquals(8193.1,result);
    }

    @Test
    void shouldBuildProformaDa() {

        ProformaDa result = proformaCalculatorService.proformaDa(vessel);

        ProformaDa proformaDa = new ProformaDa.Builder()
            .ispsDuea(200)
            .pilotDues(948)
            .quayFee(450)
            .socialSailFund(22.22)
            .tonnageFee(3300)
            .towageDues(2996.88)
            .totalMooring(276)
            .totalCost(8193.1)
            .build();
        Assertions.assertEquals(proformaDa, result);
    }
}