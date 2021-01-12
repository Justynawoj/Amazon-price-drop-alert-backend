package com.amazon_price_drop_alert.clients;

import com.amazon_price_drop_alert.clients.domains.ProductDto;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository <ProductDto, Long> {
}
