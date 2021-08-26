package com.flipkart.testcases;

import org.junit.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseClass{

	
	@Test
	public void loginTest() {
		
		Assert.assertEquals(homePage.getHomePageTitle(),"Online Shopping Site for Mobiles, Electronics, Furniture, Grocery, Lifestyle, Books & More. Best Offers!");
	}
}
