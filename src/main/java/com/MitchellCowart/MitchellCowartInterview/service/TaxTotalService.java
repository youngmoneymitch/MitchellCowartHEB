package com.MitchellCowart.MitchellCowartInterview.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Slf4j
@Service
public class TaxTotalService {

    static final BigDecimal TAX_RATE = new BigDecimal(".0825");

    //unused for now but very likely to be used again
    public BigDecimal getTaxRate() { return TAX_RATE;}

    public BigDecimal calculateTaxTotal(BigDecimal taxableSubtotalAfterDiscount) {

        taxableSubtotalAfterDiscount = taxableSubtotalAfterDiscount.multiply(TAX_RATE);
        log.info("taxTotal :" + taxableSubtotalAfterDiscount);
        return taxableSubtotalAfterDiscount;
    }

    //Slightly different with extra variable for readability. Performance vs readability tradeoff discussion needed
    /**
     BigDecimal taxTotal = new BigDecimal(0);
    taxTotal = taxableSubtotalAfterDiscount.multiply(TAX_RATE);
        log.info("taxTotal :" + taxTotal);
        return taxTotal;
    */
}
