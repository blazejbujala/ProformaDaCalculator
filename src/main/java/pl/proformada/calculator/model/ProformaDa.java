package pl.proformada.calculator.model;

import lombok.*;

@RequiredArgsConstructor
@Data
@Getter
@Setter
@Builder
public class ProformaDa {

    private double tonnageFee;
    private double quayFee;
    private double socialSailFund;
    private double towageDues;
    private double totalMooring;
    private double pilotDues;
    private double ispsDues;
    private double totalCost;

    public static class Builder {
        private double tonnageFee;
        private double quayFee;
        private double socialSailFund;
        private double towageDues;
        private double totalMooring;
        private double pilotDues;
        private double ispsDues;
        private double totalCost;

        public Builder() {
        }

        public Builder tonnageFee(double tonnageFee) {
            this.tonnageFee = tonnageFee;
            return this;
        }

        public Builder quayFee(double quayFee) {
            this.quayFee = quayFee;
            return this;
        }

        public Builder socialSailFund(double socialSailFund) {
            this.socialSailFund = socialSailFund;
            return this;
        }

        public Builder towageDues(double towageDues) {
            this.towageDues = towageDues;
            return this;
        }

        public Builder totalMooring(double totalMooring) {
            this.totalMooring = totalMooring;
            return this;
        }

        public Builder pilotDues(double pilotDues) {
            this.pilotDues = pilotDues;
            return this;
        }

        public Builder ispsDuea(double ispsDues) {
            this.ispsDues = ispsDues;
            return this;
        }

        public Builder totalCost(double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public ProformaDa build() {
            ProformaDa proformaDa = new ProformaDa();
            proformaDa.setTonnageFee(tonnageFee);
            proformaDa.setQuayFee(quayFee);
            proformaDa.setSocialSailFund(socialSailFund);
            proformaDa.setTowageDues(towageDues);
            proformaDa.setTotalMooring(totalMooring);
            proformaDa.setPilotDues(pilotDues);
            proformaDa.setIspsDues(ispsDues);
            proformaDa.setTotalCost(totalCost);
            return proformaDa;
        }
    }
}
