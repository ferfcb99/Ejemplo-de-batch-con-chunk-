package org.batch.processor;

import lombok.extern.slf4j.Slf4j;
import org.batch.entities.Product;
import org.springframework.batch.item.ItemProcessor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ProductItemProcessor implements ItemProcessor<Product, Product> {


    @Override
    public Product process(Product product) throws Exception {
        log.info("Processing product: {}", product);

        product.setFechaActualizacion(new Timestamp(System.currentTimeMillis()));

        log.info("Processed product: {}", product);

        return product;
    }
}
