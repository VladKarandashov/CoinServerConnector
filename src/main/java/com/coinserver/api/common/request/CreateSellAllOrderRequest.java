package com.coinserver.api.common.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSellAllOrderRequest {

    private String assetsSymbol;
}
