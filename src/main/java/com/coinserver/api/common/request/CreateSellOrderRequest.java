package com.coinserver.api.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSellOrderRequest {

    private String assetsSymbol;

    private BigDecimal assetsCount;
}
