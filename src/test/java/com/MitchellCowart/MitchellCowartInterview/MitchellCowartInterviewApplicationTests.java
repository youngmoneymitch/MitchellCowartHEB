package com.MitchellCowart.MitchellCowartInterview;

import com.MitchellCowart.MitchellCowartInterview.service.*;
import com.MitchellCowart.MitchellCowartInterview.models.Cart;
import com.MitchellCowart.MitchellCowartInterview.models.Coupon;
import com.MitchellCowart.MitchellCowartInterview.models.Item;
import com.MitchellCowart.MitchellCowartInterview.models.Receipt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class MitchellCowartInterviewApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	Cart validCartSetup(){

		BigDecimal helper = new BigDecimal("10");
		BigDecimal helperDiscount = new BigDecimal("5.00");
		List<Item> items = new ArrayList<>();
		List<Coupon> coupons = new ArrayList<>();

		items.add(new Item(
				"testItem1",
				"11111111",
				true,
				true,
				helper
		));
		items.add(new Item(
				"testItem2",
				"22222222",
				true,
				false,
				helper
		));
		items.add(new Item(
				"testItem3",
				"33333333",
				false,
				true,
				helper
		));
		items.add(new Item(
				"testItem4",
				"44444444",
				false,
				false,
				helper));

		coupons.add(new Coupon(
				"testCoupon1",
				"11111111",
				helperDiscount
		));

		coupons.add(new Coupon(
				"testCoupon2",
				"12345678",
				helperDiscount
		));

		return new Cart(items, coupons);

	}

	@Test
	Cart invalidCartSetup() {

		BigDecimal helper = new BigDecimal("10");
		BigDecimal helperDiscount = new BigDecimal("5.00");
		List<Item> items = new ArrayList<>();
		List<Coupon> coupons = new ArrayList<>();

			items.add(new Item(
					"",
							"11",
							true,
							true,
					helper
					));
			items.add(new Item(
					"testItem2",
							"22222222",
							true,
							false,
					helper
					));
			items.add(new Item(
					"testItem3",
							"33333333",
							false,
							true,
					helper
					));
			items.add(new Item(
					"testItem4",
							"44444444",
							false,
							false,
					helper));

			coupons.add(new Coupon(
					"testCoupon1",
							"11111111",
					helperDiscount
					));

			coupons.add(new Coupon(
					"",
							"257",
					helperDiscount
					));

			return new Cart(items, coupons);

}

	@Test
	void receiptServiceChecker(){

		//given a valid cart object
		Cart cart = validCartSetup();

		//when ReceiptService is called
		SubtotalBeforeDiscountService subtotalBeforeDiscountService = new SubtotalBeforeDiscountService();
		DiscountTotalService discountTotalService = new DiscountTotalService();
		SubtotalAfterDiscountService subtotalAfterDiscountService = new SubtotalAfterDiscountService();
		TaxableSubtotalAfterDiscountService taxableSubtotalAfterDiscountService = new TaxableSubtotalAfterDiscountService();
		TaxTotalService taxTotalService = new TaxTotalService();
		GrandTotalService grandTotalService = new GrandTotalService();
		ReceiptService receiptService = new ReceiptService(subtotalBeforeDiscountService, discountTotalService, taxableSubtotalAfterDiscountService, taxTotalService, grandTotalService);

		Receipt receipt = receiptService.getFullReceipt(cart);

		//then the Receipt object will return proper values

		Assert.isTrue(receipt.getSubtotalBeforeDiscount().compareTo(subtotalBeforeDiscountService.calculateSubtotalBeforeDiscountService(cart)) == 0);
		Assert.isTrue(receipt.getDiscountTotal().compareTo(discountTotalService.calculateDiscount(cart)) == 0);
		Assert.isTrue(receipt.getSubtotalAfterDiscount().compareTo(subtotalAfterDiscountService.calculateSubtotalAfterDiscount(cart)) == 0);
		Assert.isTrue(receipt.getTaxableSubtotalAfterDiscount().compareTo(taxableSubtotalAfterDiscountService.calculateTaxableSubtotalAfterDiscounts(cart)) == 0);
		Assert.isTrue(receipt.getTaxTotal().compareTo(taxTotalService.calculateTaxTotal(receipt.getTaxableSubtotalAfterDiscount()).setScale(2, RoundingMode.HALF_EVEN)) == 0);
				Assert.isTrue(receipt.getGrandTotal().compareTo(grandTotalService.calculateGrandTotal(cart).setScale(2, RoundingMode.HALF_EVEN)) == 0);
	}


}
