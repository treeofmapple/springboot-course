package com.tom.sample.example.util;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.tom.sample.example.dto.ProductRequest;
import com.tom.sample.example.model.Product;
import com.tom.sample.example.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SystemUtils {

	private final ProductRepository repository;
	
	public void mergeData(Product product, ProductRequest request) {
		product.setName(request.name());
		product.setPrice(request.price());
		product.setQuantity(request.quantity());
		product.setManufacturer(request.manufacturer());
	}
	
	@Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    @CacheEvict(value = "products", allEntries = true)
    public void deactivateOldProducts() {
        LocalDateTime thirtyDaysAgo = LocalDateTime.now().minusDays(30);
        List<Product> oldProducts = repository.findByActiveTrueAndDateCreatedBefore(thirtyDaysAgo);

        for (Product product : oldProducts) {
            product.setActive(false);
        }

        repository.saveAll(oldProducts);
        System.out.println("Deactivated " + oldProducts.size() + " old products.");
    }
	
}
