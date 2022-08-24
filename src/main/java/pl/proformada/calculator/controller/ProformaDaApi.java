package pl.proformada.calculator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.proformada.calculator.model.ProformaDa;
import pl.proformada.calculator.model.Vessel;

public interface ProformaDaApi {

    @PostMapping
    ResponseEntity<ProformaDa> calculateProformaDa (@RequestBody Vessel vessel);
}
