package com.demandforecast.client;

import com.demandforecast.client.dto.ForecastRequestDTO;
import com.demandforecast.client.dto.ForecastResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "forecastClient", url = "http://localhost:8000")
public interface ForecastClient {

    @PostMapping("/predict")
    ForecastResponseDTO predict(@RequestBody ForecastRequestDTO request);

}