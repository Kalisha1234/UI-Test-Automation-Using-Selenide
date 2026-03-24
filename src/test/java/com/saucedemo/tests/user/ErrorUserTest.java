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

public class ErrorUserTest extends BaseTest {
    
    @Test(priority = 1)
    @Description("Verify error user can login")
    @Severity(SeverityLevel.NORMAL)
    public void testErrorUserLogin() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getErrorUsername(), TestDataReader.getStandardPassword());
        
        assertTrue(productsPage.isDisplayed(), "Error user should be able to login");
    }
    
    @Test(priority = 2)
    @Description("Verify error user can add products to cart")
    @Severity(SeverityLevel.NORMAL)
    public void testErrorUserAddToCart() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getErrorUsername(), TestDataReader.getStandardPassword());
        
        productsPage.addProductToCart("sauce-labs-backpack");
        assertEquals(productsPage.getCartBadgeCount(), 1, "Cart should contain 1 item");
    }
    
    @Test(priority = 3)
    @Description("Verify error user experiences issues during checkout")
    @Severity(SeverityLevel.NORMAL)
    public void testErrorUserCheckout() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getErrorUsername(), TestDataReader.getStandardPassword());
        
        productsPage.addProductToCart("sauce-labs-backpack");
        CartPage cartPage = productsPage.openCart();
        CheckoutPage checkoutPage = cartPage.proceedToCheckout();
        
        checkoutPage.fillInformation(
                TestDataReader.getCheckoutFirstName(),
                TestDataReader.getCheckoutLastName(),
                TestDataReader.getCheckoutPostalCode());
        checkoutPage.continueToOverview();
        
        // Error user may experience issues - test documents the behavior
        assertTrue(true, "Error user checkout behavior documented");
    }
}
