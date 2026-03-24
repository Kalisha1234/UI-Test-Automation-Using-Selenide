package com.saucedemo.tests.user;

import com.saucedemo.config.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.CheckoutPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.TestDataReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class PerformanceGlitchUserTest extends BaseTest {
    
    @Test(priority = 1)
    @Description("Verify performance glitch user can login with delay")
    @Severity(SeverityLevel.NORMAL)
    public void testPerformanceGlitchUserLogin() {
        LoginPage loginPage = new LoginPage();
        long startTime = System.currentTimeMillis();
        
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getPerformanceGlitchUsername(), TestDataReader.getStandardPassword());
        
        long duration = System.currentTimeMillis() - startTime;
        
        assertTrue(productsPage.isDisplayed(), "Performance glitch user should be able to login");
        System.out.println("Login duration: " + duration + "ms");
    }
    
    @Test(priority = 2)
    @Description("Verify performance glitch user can add products to cart")
    @Severity(SeverityLevel.NORMAL)
    public void testPerformanceGlitchUserAddToCart() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getPerformanceGlitchUsername(), TestDataReader.getStandardPassword());
        
        productsPage.addProductToCart("sauce-labs-backpack");
        assertEquals(productsPage.getCartBadgeCount(), 1, "Cart should contain 1 item");
    }
    
    @Test(priority = 3)
    @Description("Verify performance glitch user can complete checkout")
    @Severity(SeverityLevel.NORMAL)
    public void testPerformanceGlitchUserCheckout() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getPerformanceGlitchUsername(), TestDataReader.getStandardPassword());
        
        productsPage.addProductToCart("sauce-labs-backpack");
        CartPage cartPage = productsPage.openCart();
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        
        checkoutPage.fillInformation(
                TestDataReader.getCheckoutFirstName(),
                TestDataReader.getCheckoutLastName(),
                TestDataReader.getCheckoutPostalCode())
                .continueToOverview()
                .finishCheckout();
        
        assertEquals(checkoutPage.getCompletionMessage(), "Thank you for your order!", 
                "Performance glitch user should complete checkout successfully");
    }
}
