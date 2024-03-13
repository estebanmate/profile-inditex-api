package com.inditex.prices.domain.service;

import com.inditex.prices.application.request.FindPriceFilterRequest;
import com.inditex.prices.application.response.FindPriceResponse;
import com.inditex.prices.application.rest.exception.type.BaseException;
import com.inditex.prices.application.rest.exception.type.DataNotFoundException;
import com.inditex.prices.application.rest.mapper.PriceMapper;
import com.inditex.prices.application.rest.resources.ErrorMessages;
import com.inditex.prices.application.rest.resources.validation.PriceValidation;
import com.inditex.prices.domain.repository.PriceRepository;
import com.inditex.prices.infrastructure.repository.h2.entities.PriceEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class PriceServiceDomain implements PriceService {

  private PriceRepository priceRepository;

  private PriceMapper priceMapper;
  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @Override
  public FindPriceResponse findPriceByFilter(FindPriceFilterRequest findPriceFilterRequest) throws BaseException {

    log.info("Search Data with Request -> [{}]", findPriceFilterRequest);

    // Validate request data format and values
    PriceValidation.validatePriceFilterRequest(findPriceFilterRequest);

    Specification<PriceEntity> specification = getPricesSpecification(
        findPriceFilterRequest);

    Optional<List<com.inditex.prices.domain.Price>> priceList = priceRepository
        .findAllPrices(specification, PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority")));

    if (priceList.isEmpty()) {
      log.info("Price Data not found..");
      throw new DataNotFoundException(ErrorMessages.NOT_FOUND_DESCRIPTION_ERROR_MESSAGE);
    }

    // Map domain object to response
    return priceMapper.priceToResponse(priceList.stream().findFirst().get().get(0));
  }

  private static Specification<PriceEntity> getPricesSpecification(FindPriceFilterRequest findPriceFilterRequest) {
    LocalDateTime priceDate = LocalDateTime.parse(findPriceFilterRequest.getPriceDate(), dateTimeFormatter);

    return (Root<PriceEntity> priceEntityRoot, CriteriaQuery<?> criteriaQuery,
        CriteriaBuilder criteriaBuilder) -> {

      List<Predicate> predicates = new ArrayList<>();

      // Create filter in sql sentence
      predicates.add(criteriaBuilder.equal(priceEntityRoot.get("brandId"), findPriceFilterRequest.getBrandId()));
      predicates.add(criteriaBuilder.equal(priceEntityRoot.get("productId"), findPriceFilterRequest.getProductId()));
      predicates.add(criteriaBuilder.greaterThanOrEqualTo(priceEntityRoot.get("endDate"), priceDate));
      predicates.add(criteriaBuilder.lessThanOrEqualTo(priceEntityRoot.get("startDate"), priceDate));

      return criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
    };
  }
}
