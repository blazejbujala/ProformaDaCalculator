package pl.proformada.calculator.db;

import java.util.Optional;
import pl.proformada.calculator.model.Vessel;

public interface VesselRepository {

    Vessel add (Vessel vessel);

    Optional<Vessel> getByName (String vesselName);

    void delete (String vesselName);

    void clear();

}
