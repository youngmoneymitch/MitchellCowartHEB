package com.MitchellCowart.MitchellCowartInterview.service;

import com.MitchellCowart.MitchellCowartInterview.models.Cart;
import com.MitchellCowart.MitchellCowartInterview.models.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class SubtotalBeforeDiscountService {

    public BigDecimal calculateSubtotalBeforeDiscountService(Cart cart){

        List<Item> items = cart.getItems();

        BigDecimal subtotalBeforeDiscountService = new BigDecimal("0");

        for(Item item : items){
            subtotalBeforeDiscountService = subtotalBeforeDiscountService.add(item.getPrice());
        }

        log.info("subtotalBeforeDiscountService: " + subtotalBeforeDiscountService);
        return subtotalBeforeDiscountService;



    /**Different approach for stream approach. Upsides in readability and possible flat map benefits
    //could be considered and discussed depending on how we approach quantity in the API in the future.
    I opted for an enhanced for loop here to be consistent with other services*/


    /**
    public BigDecimal calculateSubtotalBeforeDiscountService(List<Item> items){
        BigDecimal subtotalBeforeDiscountService = items.stream()
                .flatMap(item -> Stream.of(item.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return subtotalBeforeDiscountService;
     */

    }
}
