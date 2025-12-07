package com.demandforecast.config;

import com.demandforecast.entity.Product;
import com.demandforecast.entity.Sale;
import com.demandforecast.repository.ProductRepository;
import com.demandforecast.repository.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public DataLoader(ProductRepository productRepository,
                      SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    public void run(String... args) {

        saleRepository.deleteAll(); // LIMPA TUDO
        productRepository.deleteAll();

        Product product = new Product();
        product.setName("Arroz 5kg");
        product.setCategory("Alimentos");
        product.setPrice(21.90);

        product = productRepository.save(product);

        for (int i = 1; i <= 1000; i++) {
            Sale sale = new Sale();
            sale.setProduct(product);
            sale.setQuantity((int) (Math.random() * 20) + 1);
            sale.setSaleDate(LocalDate.now().minusDays(1000 - i));

            saleRepository.save(sale);
        }

        System.out.println("✅ 1000 vendas recriadas com sucesso!");
    }
}
