package com.inditex.prices.infrastructure.config;

import com.inditex.prices.infrastructure.repository.h2.SpringDataH2PriceRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackageClasses = SpringDataH2PriceRepository.class)
public class H2Configuration {

}