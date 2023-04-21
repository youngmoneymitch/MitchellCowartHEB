package com.MitchellCowart.MitchellCowartInterview.controller;

import com.MitchellCowart.MitchellCowartInterview.models.Cart;
import com.MitchellCowart.MitchellCowartInterview.models.Receipt;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.math.BigDecimal;

public interface IReceiptController {

    @PostMapping("subtotalBeforeDiscount")
    public ResponseEntity<BigDecimal> getSubtotalBeforeDiscount(@RequestBody @Valid Cart cart);

    @PostMapping("discountTotal")
    public ResponseEntity<BigDecimal> getDiscountTotal(@RequestBody @Valid Cart cart);

    @PostMapping("subtotalAfterDiscount")
    public ResponseEntity<BigDecimal> getSubtotalAfterDiscount(@RequestBody @Valid Cart cart);

    @PostMapping("taxableSubtotalAfterDiscount")
    public ResponseEntity<BigDecimal> getTaxableSubtotalAfterDiscount(@RequestBody @Valid Cart cart);

    @PostMapping("taxTotal/{taxableSubtotalAfterDiscount}")
    public ResponseEntity<BigDecimal> getTaxTotal(@PathVariable BigDecimal taxableSubtotalAfterDiscount);

    @PostMapping("/grandTotal")
    public ResponseEntity<BigDecimal> getGrandTotal(@RequestBody @Valid Cart cart);

    @PostMapping("/")
    public ResponseEntity<Receipt> getReceipt(@RequestBody @Valid Cart cart);

}
