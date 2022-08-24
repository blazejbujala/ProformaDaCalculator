package pl.proformada.calculator.controller;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.proformada.calculator.model.Vessel;
import pl.proformada.calculator.service.VesselService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/vessels", produces = {"application/json;charset=UTF-8"})
public class VesselController implements VesselApi {

    private final VesselService vesselService;

    @Override
    public ResponseEntity<Optional<Vessel>> getByName(String name) {
        return ResponseEntity.ok(vesselService.getByName(name));
    }

    @Override
    public ResponseEntity<Vessel> addVessel(Vessel vessel) {
        return ResponseEntity.ok(vesselService.add(vessel));
    }

    @Override
    public ResponseEntity delete(String name) {
        vesselService.delete(name);
        return ResponseEntity.accepted().build();
    }
}
