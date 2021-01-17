package com.amazon_price_drop_alert.repositories;

import com.amazon_price_drop_alert.dtos.ProductDto;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductDto, Long> {
}
