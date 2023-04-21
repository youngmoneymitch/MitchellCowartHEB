package com.MitchellCowart.MitchellCowartInterview.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Receipt {


    BigDecimal subtotalBeforeDiscount = new BigDecimal("0");

    BigDecimal discountTotal = new BigDecimal("0");

    BigDecimal subtotalAfterDiscount = new BigDecimal("0");

    BigDecimal taxableSubtotalAfterDiscount = new BigDecimal("0");

    @NotBlank(message = "taxtotal")
    BigDecimal taxTotal = new BigDecimal("0");

    @NotBlank(message = "taxtotal")
    BigDecimal grandTotal = new BigDecimal("0");

}
