package com.inditex.prices.application.rest.mapper;

import com.inditex.prices.application.response.FindPriceResponse;
import com.inditex.prices.domain.Price;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

  public FindPriceResponse priceToResponse(Price price) {

    return FindPriceResponse.builder()
        .priceListId(price.getId())
        .brandId(price.getBrandId())
        .productId(price.getProductId())
        .startDate(price.getStartDate())
        .endDate(price.getEndDate())
        .price(price.getPrice())
        .currency(price.getCurrency())
        .build();
  }
}
