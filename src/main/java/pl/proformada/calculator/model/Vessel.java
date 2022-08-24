package pl.proformada.calculator.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Vessel {

    private String vesselName;
    private double loa;
    private double beam;
    private double MSD;
    private int GTred;
    private int GT;
    private int DWT;

    public Vessel(String vesselName, double loa, double beam, double MSD, int GTred, int GT, int DWT) {
        this.vesselName = vesselName;
        this.loa = loa;
        this.beam = beam;
        this.MSD = MSD;
        this.GTred = GTred;
        this.GT = GT;
        this.DWT = DWT;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public double getLoa() {
        return loa;
    }

    public void setLoa(double loa) {
        this.loa = loa;
    }

    public double getBeam() {
        return beam;
    }

    public void setBeam(double beam) {
        this.beam = beam;
    }

    public double getMSD() {
        return MSD;
    }

    public void setMSD(double MSD) {
        this.MSD = MSD;
    }

    public int getGTred() {
        return GTred;
    }

    public void setGTred(int GTred) {
        this.GTred = GTred;
    }

    public int getGT() {
        return GT;
    }

    public void setGT(int GT) {
        this.GT = GT;
    }

    public int getDWT() {
        return DWT;
    }

    public void setDWT(int DWT) {
        this.DWT = DWT;
    }

}
