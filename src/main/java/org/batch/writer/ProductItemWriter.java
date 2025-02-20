package org.batch.writer;


import lombok.extern.slf4j.Slf4j;
import org.batch.entities.Product;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

@Slf4j
public class ProductItemWriter implements ItemWriter<Product> {


    @Override
    public void write(List<? extends Product> list) throws Exception {
        for (Product product : list) {
            log.info("Product wrote {}", product);
        }
    }


}
