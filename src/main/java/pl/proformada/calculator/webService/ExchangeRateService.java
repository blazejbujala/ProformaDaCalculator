package pl.proformada.calculator.webService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.proformada.calculator.model.ExchangeRate;

@Service
@RequiredArgsConstructor
public class ExchangeRateService {

    private RestTemplate restTemplate = new RestTemplate();
    private final String NBP_API = "http://api.nbp.pl/api/exchangerates/rates/a/";

    public ExchangeRate getExchangeRateEur (String currency){
        return restTemplate.getForObject(NBP_API + currency+"/", ExchangeRate.class);
    }
}
