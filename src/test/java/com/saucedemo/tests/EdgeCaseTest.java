package com.saucedemo.tests;

import com.saucedemo.config.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.TestDataReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class EdgeCaseTest extends BaseTest {
    
    private ProductsPage productsPage;
    
    @BeforeMethod
    public void setup() {
        LoginPage loginPage = new LoginPage();
        productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
    }
    
    @Test(priority = 1)
    @Description("Verify adding all products to cart")
    @Severity(SeverityLevel.NORMAL)
    public void testAddAllProductsToCart() {
        productsPage.addProductToCart("sauce-labs-backpack")
                .addProductToCart("sauce-labs-bike-light")
                .addProductToCart("sauce-labs-bolt-t-shirt")
                .addProductToCart("sauce-labs-fleece-jacket")
                .addProductToCart("sauce-labs-onesie")
                .addProductToCart("test.allthethings()-t-shirt-(red)");
        
        assertEquals(productsPage.getCartBadgeCount(), 6, "Cart should contain all 6 products");
    }
    
    @Test(priority = 2)
    @Description("Verify removing all products from cart")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveAllProductsFromCart() {
        productsPage.addProductToCart("sauce-labs-backpack")
                .addProductToCart("sauce-labs-bike-light");
        
        CartPage cartPage = productsPage.openCart();
        cartPage.removeProduct("sauce-labs-backpack")
                .removeProduct("sauce-labs-bike-light");
        
        assertTrue(cartPage.isEmpty(), "Cart should be empty");
    }
    
    @Test(priority = 3)
    @Description("Verify adding same product multiple times")
    @Severity(SeverityLevel.NORMAL)
    public void testAddSameProductMultipleTimes() {
        productsPage.addProductToCart("sauce-labs-backpack");
        int firstCount = productsPage.getCartBadgeCount();
        
        // Try to add again (button should be "Remove" now, so this won't add)
        assertEquals(firstCount, 1, "Product should only be added once");
    }
    
    @Test(priority = 4)
    @Description("Verify cart persists across page navigation")
    @Severity(SeverityLevel.NORMAL)
    public void testCartPersistsAcrossNavigation() {
        productsPage.addProductToCart("sauce-labs-backpack");
        productsPage.clickProduct("Sauce Labs Bike Light");
        
        assertEquals(productsPage.getCartBadgeCount(), 1, "Cart should persist");
    }
    
    @Test(priority = 5)
    @Description("Verify continue shopping from cart returns to products")
    @Severity(SeverityLevel.NORMAL)
    public void testContinueShoppingFromCart() {
        CartPage cartPage = productsPage.openCart();
        ProductsPage returnedPage = cartPage.continueShopping();
        
        assertTrue(returnedPage.isDisplayed(), "Should return to products page");
    }
    
    @Test(priority = 6)
    @Description("Verify empty cart checkout attempt")
    @Severity(SeverityLevel.NORMAL)
    public void testEmptyCartCheckout() {
        CartPage cartPage = productsPage.openCart();
        assertTrue(cartPage.isEmpty(), "Cart should be empty initially");
    }
    
    @Test(priority = 7)
    @Description("Verify product details back button")
    @Severity(SeverityLevel.NORMAL)
    public void testProductDetailsBackButton() {
        productsPage.clickProduct("Sauce Labs Backpack").goBack();
        assertTrue(productsPage.isDisplayed(), "Should return to products page");
    }
    
    @Test(priority = 8)
    @Description("Verify add to cart from product details page")
    @Severity(SeverityLevel.NORMAL)
    public void testAddToCartFromProductDetails() {
        productsPage.clickProduct("Sauce Labs Backpack").addToCart();
        assertEquals(productsPage.getCartBadgeCount(), 1, "Cart should contain 1 item");
    }
    
    @Test(priority = 9)
    @Description("Verify remove from cart from product details page")
    @Severity(SeverityLevel.NORMAL)
    public void testRemoveFromCartFromProductDetails() {
        productsPage.addProductToCart("sauce-labs-backpack");
        productsPage.clickProduct("Sauce Labs Backpack").removeFromCart();
        
        assertEquals(productsPage.getCartBadgeCount(), 0, "Cart should be empty");
    }
    
    @Test(priority = 10)
    @Description("Verify rapid clicking add to cart button")
    @Severity(SeverityLevel.NORMAL)
    public void testRapidClickingAddToCart() {
        productsPage.addProductToCart("sauce-labs-backpack");
        int count = productsPage.getCartBadgeCount();
        
        assertEquals(count, 1, "Rapid clicking should not add multiple items");
    }
}
