package com.inditex.prices.application.rest.resources.validation;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

import com.inditex.prices.application.request.FindPriceFilterRequest;
import com.inditex.prices.application.rest.exception.type.InvalidFieldException;
import com.inditex.prices.application.rest.exception.type.MissingFieldException;
import com.inditex.prices.application.rest.resources.ErrorMessages;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PriceValidation {

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @SuppressWarnings("unused")
  public static void validatePriceFilterRequest(FindPriceFilterRequest findPriceFilterRequest)
      throws InvalidFieldException, MissingFieldException {

    // Check productId presence and value
    if (isNull(findPriceFilterRequest.getProductId())) {
      log.error(ErrorMessages.MISSING_FIELD_ERROR_MESSAGE, "PRODUCT_ID");
      throw new MissingFieldException("PRODUCT_ID");
    } else if (findPriceFilterRequest.getProductId() < 1) {
      log.error(ErrorMessages.INVALID_FIELD_ERROR_MESSAGE, "PRODUCT_ID");
      throw new InvalidFieldException("PRODUCT_ID");
    }

    // Check brandId presence and value
    if (isNull(findPriceFilterRequest.getBrandId())) {
      log.error(ErrorMessages.MISSING_FIELD_ERROR_MESSAGE, "BRAND_ID");
      throw new MissingFieldException("BRAND_ID");
    } else if (findPriceFilterRequest.getBrandId() < 1) {
      log.error(ErrorMessages.INVALID_FIELD_ERROR_MESSAGE, "BRAND_ID");
      throw new InvalidFieldException("BRAND_ID");
    }

    // Check date presence and value
    if (isBlank(findPriceFilterRequest.getPriceDate())) {
      log.error(ErrorMessages.MISSING_FIELD_ERROR_MESSAGE, "PRICE_DATE");
      throw new MissingFieldException("PRICE_DATE");
    } else {
      try {
        LocalDateTime.parse(findPriceFilterRequest.getPriceDate(), dateTimeFormatter);
      } catch (DateTimeParseException ex) {
        log.error(ErrorMessages.INVALID_FIELD_ERROR_MESSAGE, "PRICE_DATE");
        throw new InvalidFieldException("PRICE_DATE");
      }
    }
  }
}
