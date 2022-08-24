package pl.proformada.calculator.service;

import java.util.Optional;
import pl.proformada.calculator.db.VesselRepository;
import pl.proformada.calculator.model.Vessel;

public class VesselService {

    private final VesselRepository vesselRepository;

    public VesselService(VesselRepository vesselRepository) {
        this.vesselRepository = vesselRepository;
    }

    public Vessel add(Vessel vessel){ return vesselRepository.add(vessel);}

    public Optional<Vessel> getByName (String vesselName){return vesselRepository.getByName(vesselName);}

    public void delete (String vesselName){
        vesselRepository.delete(vesselName);
    }
}
