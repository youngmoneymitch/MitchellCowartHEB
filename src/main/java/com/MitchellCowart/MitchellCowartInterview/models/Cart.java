package com.MitchellCowart.MitchellCowartInterview.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@Valid
@AllArgsConstructor
public class Cart {

    @Valid
    @NotNull
    private List<Item> items;

    @Valid
    @NotNull
    private List<Coupon> coupons;
}
