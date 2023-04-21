package com.MitchellCowart.MitchellCowartInterview.service;

import com.MitchellCowart.MitchellCowartInterview.models.Cart;
import com.MitchellCowart.MitchellCowartInterview.models.Coupon;
import com.MitchellCowart.MitchellCowartInterview.models.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class DiscountTotalService {

    public BigDecimal calculateDiscount(Cart cart){

        List<Coupon> coupons = cart.getCoupons();
        List<Item> items = cart.getItems();
        BigDecimal totalDiscount = new BigDecimal("0");
        HashMap<String, BigDecimal> discountMap = new HashMap();

        coupons.forEach(
                coupon -> discountMap.put(coupon.getAppliedSku(), coupon.getDiscountPrice())
        );

        for (Item item : items) {
            if (discountMap.get(item.getSku()) != null) {
                if (discountMap.get(item.getSku()).compareTo(item.getPrice()) >= 0) {
                    totalDiscount = totalDiscount.add(item.getPrice());
                } else {
                    totalDiscount = totalDiscount.add(discountMap.get(item.getSku()));
                }
            }
        }

        log.info("totalDiscount: " + totalDiscount);
        return  totalDiscount;
    }
}
