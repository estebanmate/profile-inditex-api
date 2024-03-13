package com.inditex.prices.infrastructure.repository.h2;

import com.inditex.prices.domain.Price;
import com.inditex.prices.domain.repository.PriceRepository;
import com.inditex.prices.infrastructure.repository.h2.entities.PriceEntity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class H2DbPriceRepository implements PriceRepository {

  private final SpringDataH2PriceRepository springDataH2PriceRepository;

  @Autowired
  public H2DbPriceRepository(SpringDataH2PriceRepository springDataH2PriceRepository) {
    this.springDataH2PriceRepository = springDataH2PriceRepository;
  }

  public Optional<List<Price>> findAllPrices(Specification<PriceEntity> spec, Pageable pageable) {
    Page<PriceEntity> pricePage = springDataH2PriceRepository.findAll(spec, pageable);
    if (!pricePage.isEmpty()) {
      return Optional.of(pricePage.map(PriceEntity::toDomainPrice).stream().collect(Collectors.toList()));
    } else {
      return Optional.empty();
    }
  }

}
