package org.batch.config;


import org.batch.entities.Product;
import org.batch.processor.ProductItemProcessor;
import org.batch.reader.ProductItemReader;
import org.batch.writer.ProductItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    private final Integer chunk = 2;

    @Bean
    public ProductItemReader productItemReader(){
        return new ProductItemReader();
    }

    @Bean
    public ProductItemProcessor productItemProcessor(){
        return new ProductItemProcessor();
    }

    @Bean
    public ProductItemWriter productItemWriter(){
        return new ProductItemWriter();
    }

    @Bean
    public Step procesarLote(){
        return stepBuilderFactory.get("procesarLote")
                .<Product, Product>chunk(chunk)
                .reader(productItemReader())
                .processor(productItemProcessor())
                .writer(productItemWriter())
                .build();
    }

    @Bean
    public Job job(){
        return jobBuilderFactory.get("procesarProductos")
                .start(procesarLote())
                .build();
    }

}
