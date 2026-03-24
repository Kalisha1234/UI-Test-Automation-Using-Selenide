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

public class StandardUserTest extends BaseTest {
    
    @Test(priority = 1)
    @Description("Verify standard user can login successfully")
    @Severity(SeverityLevel.BLOCKER)
    public void testStandardUserLogin() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
        
        assertTrue(productsPage.isDisplayed(), "Standard user should login successfully");
    }
    
    @Test(priority = 2)
    @Description("Verify standard user can add products to cart")
    @Severity(SeverityLevel.CRITICAL)
    public void testStandardUserAddToCart() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
        
        productsPage.addProductToCart("sauce-labs-backpack");
        assertEquals(productsPage.getCartBadgeCount(), 1, "Cart should contain 1 item");
    }
    
    @Test(priority = 3)
    @Description("Verify standard user can complete checkout")
    @Severity(SeverityLevel.BLOCKER)
    public void testStandardUserCheckout() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
        
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
                "Standard user should complete checkout successfully");
    }
}
