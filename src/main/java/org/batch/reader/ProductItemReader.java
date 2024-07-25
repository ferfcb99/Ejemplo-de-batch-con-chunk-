package org.batch.reader;

import lombok.extern.slf4j.Slf4j;
import org.batch.entities.Product;
import org.batch.services.IProductService;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

@Slf4j
public class ProductItemReader implements ItemReader<Product> {

    @Autowired
    private IProductService productService;

    private Iterator<Product> productIterator;


    @Override
    public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        log.info("Reading product");

        List<Product> products =  this.productService.getAllProducts();
        log.info("Found {} products", products.size());

        return products.stream().filter(elem -> elem.getFechaActualizacion() == null).findFirst().orElse(null);

    }
}
