package com.MitchellCowart.MitchellCowartInterview.service;

import com.MitchellCowart.MitchellCowartInterview.models.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Slf4j
@Service
public class GrandTotalService {

    public BigDecimal calculateGrandTotal(Cart cart) {

        SubtotalBeforeDiscountService subtotalBeforeDiscountService = new SubtotalBeforeDiscountService();
        DiscountTotalService discountTotalService = new DiscountTotalService();
        TaxableSubtotalAfterDiscountService taxableSubtotalAfterDiscountService = new TaxableSubtotalAfterDiscountService();
        TaxTotalService taxTotalService = new TaxTotalService();

        BigDecimal grandTotal = new BigDecimal("0")
                                    .add(subtotalBeforeDiscountService.calculateSubtotalBeforeDiscountService(cart))
                                    .subtract(discountTotalService.calculateDiscount(cart))
                                    .subtract(taxTotalService.calculateTaxTotal(taxableSubtotalAfterDiscountService.calculateTaxableSubtotalAfterDiscounts(cart)));

        log.info("grandTotal: " + grandTotal);
        return grandTotal;
    }

    public BigDecimal calculateGrandTotal(BigDecimal subtotalBeforeDiscounts,
                                          BigDecimal discountTotal,
                                          BigDecimal taxTotal) {

        BigDecimal grandTotal = new BigDecimal("0");

        grandTotal = grandTotal
                    .add(subtotalBeforeDiscounts)
                    .subtract(discountTotal)
                    .subtract(taxTotal);

        log.info("grandTotal: " + grandTotal);
        return grandTotal;
    }
}
