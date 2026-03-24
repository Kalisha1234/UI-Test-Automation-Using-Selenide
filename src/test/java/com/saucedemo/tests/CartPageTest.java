package com.saucedemo.tests;

import com.saucedemo.config.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.TestDataReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class CartPageTest extends BaseTest {
    
    private CartPage cartPage;
    private ProductsPage productsPage;
    
    @BeforeMethod
    public void setupCart() {
        LoginPage loginPage = new LoginPage();
        productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
    }
    
    @Test(priority = 1)
    @Description("Verify cart page is displayed")
    @Severity(SeverityLevel.CRITICAL)
    public void testCartPageIsDisplayed() {
        cartPage = productsPage.openCart();
        assertTrue(cartPage.isDisplayed(), "Cart page should be displayed");
    }
    
    @Test(priority = 2)
    @Description("Verify cart is empty when no products added")
    @Severity(SeverityLevel.NORMAL)
    public void testEmptyCart() {
        cartPage = productsPage.openCart();
        assertTrue(cartPage.isEmpty(), "Cart should be empty");
        assertEquals(cartPage.getCartItemsCount(), 0, "Cart items count should be 0");
    }
    
    @Test(priority = 3)
    @Description("Verify cart displays added product")
    @Severity(SeverityLevel.CRITICAL)
    public void testCartDisplaysAddedProduct() {
        productsPage.addProductToCart("sauce-labs-backpack");
        cartPage = productsPage.openCart();
        
        assertEquals(cartPage.getCartItemsCount(), 1, "Cart should contain 1 item");
    }
    
    @Test(priority = 4)
    @Description("Verify removing product from cart")
    @Severity(SeverityLevel.CRITICAL)
    public void testRemoveProductFromCart() {
        productsPage.addProductToCart("sauce-labs-backpack")
                .addProductToCart("sauce-labs-bike-light");
        cartPage = productsPage.openCart();
        
        assertEquals(cartPage.getCartItemsCount(), 2, "Cart should contain 2 items");
        
        cartPage.removeProduct("sauce-labs-backpack");
        assertEquals(cartPage.getCartItemsCount(), 1, "Cart should contain 1 item after removal");
    }
    
    @Test(priority = 5)
    @Description("Verify continue shopping button navigates back to products")
    @Severity(SeverityLevel.NORMAL)
    public void testContinueShoppingButton() {
        cartPage = productsPage.openCart();
        ProductsPage returnedPage = cartPage.continueShopping();
        
        assertTrue(returnedPage.isDisplayed(), "Should navigate back to products page");
    }
    
    @Test(priority = 6)
    @Description("Verify checkout button navigates to checkout page")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutButton() {
        productsPage.addProductToCart("sauce-labs-backpack");
        cartPage = productsPage.openCart();
        
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        assertNotNull(checkoutPage, "Should navigate to checkout page");
    }
}
