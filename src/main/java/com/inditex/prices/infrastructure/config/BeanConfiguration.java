package com.inditex.prices.infrastructure.config;

import com.inditex.prices.PricesApiApplication;
import com.inditex.prices.application.rest.mapper.PriceMapper;
import com.inditex.prices.domain.repository.PriceRepository;
import com.inditex.prices.domain.service.PriceServiceDomain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PricesApiApplication.class)
public class BeanConfiguration {

  @Bean
  PriceServiceDomain priceServiceDomain(final PriceRepository priceRepository, PriceMapper priceMapper) {
    return new PriceServiceDomain(priceRepository, priceMapper);
  }
}
