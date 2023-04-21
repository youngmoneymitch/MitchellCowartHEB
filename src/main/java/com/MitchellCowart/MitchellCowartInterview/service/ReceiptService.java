package com.MitchellCowart.MitchellCowartInterview.service;

import com.MitchellCowart.MitchellCowartInterview.models.Cart;
import com.MitchellCowart.MitchellCowartInterview.models.Receipt;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@Service
@AllArgsConstructor
public class ReceiptService {

    SubtotalBeforeDiscountService subtotalBeforeDiscountService;
    DiscountTotalService discountTotalService;
    TaxableSubtotalAfterDiscountService taxableSubtotalAfterDiscountService;
    TaxTotalService taxTotalService;
    GrandTotalService grandTotalService;

    public Receipt getFullReceipt(Cart cart){

        BigDecimal subtotalBeforeDiscount = subtotalBeforeDiscountService.calculateSubtotalBeforeDiscountService(cart);
        BigDecimal discountTotal = discountTotalService.calculateDiscount(cart);
        BigDecimal subtotalAfterDiscount = subtotalBeforeDiscount.subtract(discountTotal);
        BigDecimal taxableSubtotalAfterDiscount = taxableSubtotalAfterDiscountService.calculateTaxableSubtotalAfterDiscounts(cart);
        BigDecimal taxTotal = taxTotalService.calculateTaxTotal(taxableSubtotalAfterDiscount).setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal grandTotal = grandTotalService.calculateGrandTotal(subtotalBeforeDiscount,
                                discountTotal,
                                taxTotal);

        Receipt receipt = new Receipt(subtotalBeforeDiscount, discountTotal, subtotalAfterDiscount, taxableSubtotalAfterDiscount, taxTotal, grandTotal);

        log.info("receipt: " + receipt);
        return receipt;

    }

    //Here is a solution that doesn't use variables to store the different values. This uses less memory but is less readable, so I have opted for the above.

        /*
        receipt.setSubtotalBeforeDiscount(
                subtotalBeforeDiscountService.calculateSubtotalBeforeDiscountService(cart)
        );

        receipt.setDiscountTotal(
                discountTotalService.calculateDiscount(cart)
        );

        receipt.setSubtotalAfterDiscount(
                receipt.getSubtotalBeforeDiscount().subtract(receipt.getDiscountTotal())
        );

        receipt.setTaxableSubtotalAfterDiscount(
                taxableSubtotalAfterDiscountService.calculateTaxableSubtotalAfterDiscounts(cart)
        );

        receipt.setTaxTotal(
                taxTotalService.calculateTaxTotal(
                        receipt.getTaxableSubtotalAfterDiscount())
                        .setScale(2, RoundingMode.HALF_EVEN)
        );

        receipt.setGrandTotal(
                grandTotalService.calculateGrandTotal(receipt.getSubtotalBeforeDiscount(),
                receipt.getDiscountTotal(),
                receipt.getTaxTotal())
        );

        receipt.setGrandTotal(receipt.getGrandTotal());

         */



}
