package pl.proformada.calculator.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.proformada.calculator.model.ProformaDa;
import pl.proformada.calculator.model.Vessel;
import pl.proformada.calculator.service.ProformaCalculatorService;

@Slf4j
@RestController
@RequestMapping(path = "/proformaDa", produces = {"application/json;charset=UTF-8"})
@RequiredArgsConstructor
public class ProformaCalculatorController implements ProformaDaApi {

    private final ProformaCalculatorService proformaCalculatorService;

    @Override
    public ResponseEntity<ProformaDa> calculateProformaDa(@RequestBody Vessel vessel) {
        log.debug("Getting proforma da of vessel");
        return ResponseEntity.ok().body(proformaCalculatorService.proformaDa(vessel));
    }
}
