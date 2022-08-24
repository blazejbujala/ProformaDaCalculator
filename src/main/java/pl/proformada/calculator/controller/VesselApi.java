package pl.proformada.calculator.controller;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.proformada.calculator.model.Vessel;

public interface VesselApi {

    @GetMapping("/{name}")
    ResponseEntity<Optional<Vessel>> getByName (@PathVariable String name);

    @PostMapping
    ResponseEntity<Vessel> addVessel (@RequestBody Vessel vessel);

    @DeleteMapping("/{name}")
    ResponseEntity delete (@PathVariable String name);
}
