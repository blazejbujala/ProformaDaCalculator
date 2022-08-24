package pl.proformada.calculator.db;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import pl.proformada.calculator.model.Vessel;

@Repository
public class InMemoryRepository implements VesselRepository {

    private final Map<String, Vessel> vessels = new HashMap<>();

    @Override
    public Vessel add(Vessel vessel) {
        if (vessels.get(vessel.getVesselName()) == null) {
            vessels.put(vessel.getVesselName(), vessel);
        }
        return vessel;
    }

    @Override
    public Optional<Vessel> getByName(String vesselName) {
        if (vessels.get(vesselName) != null) {
            return Optional.ofNullable(vessels.get(vesselName));
        } else {
            throw new NoSuchElementException("No vessel named : " + vesselName);
        }
    }

    @Override
    public void delete(String vesselName) {
        if (vessels.get(vesselName) != null) {
            vessels.remove(vesselName);
        } else {
            throw new NoSuchElementException("No vessel named : " + vesselName);
        }
    }

    @Override
    public void clear() {
        vessels.clear();
    }
}
