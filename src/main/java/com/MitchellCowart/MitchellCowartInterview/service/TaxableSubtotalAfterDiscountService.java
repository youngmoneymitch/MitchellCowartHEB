package com.MitchellCowart.MitchellCowartInterview.service;

import com.MitchellCowart.MitchellCowartInterview.models.Cart;
import com.MitchellCowart.MitchellCowartInterview.models.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class TaxableSubtotalAfterDiscountService {

    public BigDecimal calculateTaxableSubtotalAfterDiscounts(Cart cart){

        List<Item> items = cart.getItems();

        BigDecimal taxableSubtotalAfterDiscounts = new BigDecimal("0");

        for(Item item : items){
            if(item.isTaxable() == true)
                taxableSubtotalAfterDiscounts = taxableSubtotalAfterDiscounts.add(item.getPrice());
        }

        log.info("taxableSubtotalAfterDiscounts: " + taxableSubtotalAfterDiscounts);
        return taxableSubtotalAfterDiscounts;
    }
}
