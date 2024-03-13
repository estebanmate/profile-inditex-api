package com.inditex.prices.domain.service;

import com.inditex.prices.application.request.FindPriceFilterRequest;
import com.inditex.prices.application.response.FindPriceResponse;
import com.inditex.prices.application.rest.exception.type.BaseException;

public interface PriceService {

  public FindPriceResponse findPriceByFilter(FindPriceFilterRequest findPriceFilterRequest) throws BaseException;

}
