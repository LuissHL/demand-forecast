package com.demandforecast.client.dto;

import java.util.List;

public class ForecastRequestDTO {
    private Long productId;
    private List<Integer> history;

    public ForecastRequestDTO(Long productId, List<Integer> history) {
        this.productId = productId;
        this.history = history;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public List<Integer> getHistory() {
        return history;
    }

    public void setHistory(List<Integer> history) {
        this.history = history;
    }
}

