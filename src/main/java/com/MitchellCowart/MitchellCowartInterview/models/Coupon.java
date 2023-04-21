package com.MitchellCowart.MitchellCowartInterview.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Coupon {

    @NotBlank(message = "Item name is mandatory")
    private String couponName;

    @NotBlank(message = "Applied Sku is mandatory")
    @Pattern(regexp = "^[0-9]{8}$")
    private String appliedSku;

    @NotNull(message = "Discount price is mandatory")
    private BigDecimal discountPrice;
}
