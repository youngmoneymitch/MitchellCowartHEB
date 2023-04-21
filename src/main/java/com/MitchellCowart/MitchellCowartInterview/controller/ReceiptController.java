package com.MitchellCowart.MitchellCowartInterview.controller;

import com.MitchellCowart.MitchellCowartInterview.service.*;
import com.MitchellCowart.MitchellCowartInterview.models.Cart;
import com.MitchellCowart.MitchellCowartInterview.models.Receipt;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

@Slf4j
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class ReceiptController implements IReceiptController {

    private final SubtotalBeforeDiscountService subtotalBeforeDiscountService;
    private final DiscountTotalService discountTotalService;
    private final SubtotalAfterDiscountService subtotalAfterDiscountService;
    private final TaxableSubtotalAfterDiscountService taxableSubtotalAfterDiscountService;
    private final TaxTotalService taxTotalService;
    private final GrandTotalService grandTotalService;
    private final ReceiptService receiptService;

    @PostMapping("subtotalBeforeDiscount")
    public ResponseEntity<BigDecimal> getSubtotalBeforeDiscount(@RequestBody @Valid Cart cart) {
        log.info("/subtotalBeforeDiscount, executing SubtotalBeforeDiscountService");
        return new ResponseEntity<>(subtotalBeforeDiscountService.calculateSubtotalBeforeDiscountService(cart), HttpStatus.OK);
    }

    @PostMapping("discountTotal")
    public ResponseEntity<BigDecimal> getDiscountTotal(@RequestBody @Valid Cart cart) {
        log.info("/discountTotal, executing DiscountTotalService");
        return new ResponseEntity<>(discountTotalService.calculateDiscount(cart), HttpStatus.OK);
    }

    @PostMapping("subtotalAfterDiscount")
    public ResponseEntity<BigDecimal> getSubtotalAfterDiscount(@RequestBody @Valid Cart cart) {
        log.info("/subtotalAfterDiscount, executing SubtotalAfterDiscountService");
        return new ResponseEntity<>(subtotalAfterDiscountService.calculateSubtotalAfterDiscount(cart), HttpStatus.OK);
    }

    @PostMapping("taxableSubtotalAfterDiscount")
    public ResponseEntity<BigDecimal> getTaxableSubtotalAfterDiscount(@RequestBody @Valid Cart cart){
        log.info("/taxableSubtotalAfterDiscount, executing TaxableSubtotalAfterDiscount");
        return new ResponseEntity<>(taxableSubtotalAfterDiscountService.calculateTaxableSubtotalAfterDiscounts(cart), HttpStatus.OK);
    }

    @PostMapping("taxTotal/{taxableSubtotalAfterDiscount}")
    public ResponseEntity<BigDecimal> getTaxTotal(@PathVariable BigDecimal taxableSubtotalAfterDiscount) {
        log.info("/taxTotal/" + taxableSubtotalAfterDiscount + ", executing TaxTotalService");
        return new ResponseEntity<>(taxTotalService.calculateTaxTotal(taxableSubtotalAfterDiscount), HttpStatus.OK);
    }

    @PostMapping("/grandTotal")
    public ResponseEntity<BigDecimal> getGrandTotal(@RequestBody @Valid Cart cart) {
        log.info("/grandTotal, executing GrandTotalService");
        return new ResponseEntity<>(grandTotalService.calculateGrandTotal(cart), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Receipt> getReceipt(@RequestBody @Valid Cart cart) {
        log.info("/, executing Receipt Service");
        return new ResponseEntity<>(receiptService.getFullReceipt(cart), HttpStatus.OK);
    }



}
