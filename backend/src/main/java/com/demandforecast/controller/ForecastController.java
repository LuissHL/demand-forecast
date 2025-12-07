package com.demandforecast.controller;

import com.demandforecast.dto.ForecastResultDTO;
import com.demandforecast.service.ForecastService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/forecast")
public class ForecastController {

    private final ForecastService forecastService;

    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }


    @GetMapping("/{productId}")
    public ForecastResultDTO getPredict(@PathVariable Long productId) {
        Double value = forecastService.predict(productId);
        return new ForecastResultDTO(productId, value);
    }

}
