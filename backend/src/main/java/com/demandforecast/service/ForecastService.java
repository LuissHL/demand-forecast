package com.demandforecast.service;

import com.demandforecast.client.ForecastClient;
import com.demandforecast.client.dto.ForecastRequestDTO;
import com.demandforecast.client.dto.ForecastResponseDTO;
import com.demandforecast.entity.Sale;
import com.demandforecast.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForecastService {

    private final SaleRepository saleRepository;
    private final ForecastClient forecastClient; // FEIGN

    public ForecastService(SaleRepository saleRepository, ForecastClient forecastClient) {
        this.saleRepository = saleRepository;
        this.forecastClient = forecastClient;
    }

    public Double predict(Long productId) {

        List<Sale> sales = saleRepository.findByProductId(productId);

        List<Integer> history = sales.stream()
                .map(Sale::getQuantity)
                .toList();

        try {
            ForecastRequestDTO request =
                    new ForecastRequestDTO(productId, history);

            ForecastResponseDTO response =
                    forecastClient.predict(request);

            return response.getForecast();

        } catch (Exception e) {

            if (history.isEmpty()) {
                return 0.0;
            }

            double avg = history.stream()
                    .mapToInt(i -> i)
                    .average()
                    .orElse(0);

            return Math.round(avg * 1.10 * 100.0) / 100.0;
        }
    }
}
