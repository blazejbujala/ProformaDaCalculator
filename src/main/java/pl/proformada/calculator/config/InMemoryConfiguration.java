package pl.proformada.calculator.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.proformada.calculator.db.InMemoryRepository;
import pl.proformada.calculator.db.VesselRepository;
import pl.proformada.calculator.service.VesselService;

@Slf4j
@Configuration
public class InMemoryConfiguration {

    @Bean
    public VesselRepository vesselRepository() {
        log.info("Creating new In Memory Repository");
        return new InMemoryRepository();
    }

    @Bean
    public VesselService vesselService() {
        return new VesselService(vesselRepository());
    }


}
