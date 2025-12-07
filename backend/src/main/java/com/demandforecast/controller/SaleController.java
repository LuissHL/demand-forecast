package com.demandforecast.controller;

import com.demandforecast.entity.Sale;
import com.demandforecast.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public Sale create(@RequestBody Sale sale) {
        return saleService.save(sale);
    }

    @GetMapping("/product/{productId}")
    public List<Sale> findByProduct(@PathVariable Long productId) {
        return saleService.findByProduct(productId);
    }
}
