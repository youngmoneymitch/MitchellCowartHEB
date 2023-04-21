package com.MitchellCowart.MitchellCowartInterview.service;

import com.MitchellCowart.MitchellCowartInterview.models.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Slf4j
@Service
public class SubtotalAfterDiscountService {

    public BigDecimal calculateSubtotalAfterDiscount(Cart cart){

        SubtotalBeforeDiscountService subtotalBeforeDiscountService = new SubtotalBeforeDiscountService();
        DiscountTotalService discountTotalService = new DiscountTotalService();
        BigDecimal subtotalAfterDiscount = new BigDecimal("0");

        subtotalAfterDiscount = subtotalAfterDiscount
                                .add(subtotalBeforeDiscountService.calculateSubtotalBeforeDiscountService(cart))
                                .subtract(discountTotalService.calculateDiscount(cart));

        log.info("subtotalAfterDiscount: " + subtotalAfterDiscount);
        return  subtotalAfterDiscount;
    }
}
