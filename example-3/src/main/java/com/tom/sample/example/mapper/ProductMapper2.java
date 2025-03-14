package com.tom.sample.example.mapper;

import org.springframework.stereotype.Service;

import com.tom.sample.example.dto.ProductRequest;
import com.tom.sample.example.dto.ProductResponse;
import com.tom.sample.example.model.Product;

@Service
public class ProductMapper2 {

	public Product toProduct(ProductRequest request) {
		if (request == null) {
			return null;
		}

		return Product.builder().name(request.name()).quantity(request.quantity()).price(request.price())
				.manufacturer(request.manufacturer()).build();

	}

	public ProductResponse fromProduct(Product product) {
		return new ProductResponse(product.getId(), product.getName(), product.getQuantity(), product.getPrice(),
				product.getManufacturer(), product.isActive(), product.getLastUpdated(), product.getDateCreated());
	}

}
