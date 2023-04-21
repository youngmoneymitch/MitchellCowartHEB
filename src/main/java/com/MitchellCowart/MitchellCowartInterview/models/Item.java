package com.MitchellCowart.MitchellCowartInterview.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Data
@Validated
@AllArgsConstructor
public class Item {

    @NotBlank(message = "itemName is mandatory")
    private String itemName;

    @NotBlank(message = "Sku is mandatory")
    @Pattern(regexp = "^[0-9]{8}$")
    private String sku;

    @NotNull(message = "isTaxable is mandatory")
    @JsonProperty
    private boolean isTaxable;

    @NotNull(message = "Sku is mandatory")
    private boolean ownBrand;

    @NotNull(message = "Price is mandatory")
    private BigDecimal price;
}
