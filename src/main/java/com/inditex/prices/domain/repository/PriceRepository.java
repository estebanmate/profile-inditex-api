package com.inditex.prices.domain.repository;

import com.inditex.prices.domain.Price;
import com.inditex.prices.infrastructure.repository.h2.entities.PriceEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface PriceRepository {

  public Optional<List<Price>> findAllPrices(Specification<PriceEntity> spec, Pageable pageable);

}
