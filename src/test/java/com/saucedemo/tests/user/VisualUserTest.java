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

public class VisualUserTest extends BaseTest {
    
    @Test(priority = 1)
    @Description("Verify visual user can login")
    @Severity(SeverityLevel.NORMAL)
    public void testVisualUserLogin() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getVisualUsername(), TestDataReader.getStandardPassword());
        
        assertTrue(productsPage.isDisplayed(), "Visual user should be able to login");
    }
    
    @Test(priority = 2)
    @Description("Verify visual user can add products to cart")
    @Severity(SeverityLevel.NORMAL)
    public void testVisualUserAddToCart() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getVisualUsername(), TestDataReader.getStandardPassword());
        
        productsPage.addProductToCart("sauce-labs-backpack");
        assertEquals(productsPage.getCartBadgeCount(), 1, "Cart should contain 1 item");
    }
    
    @Test(priority = 3)
    @Description("Verify visual user can complete checkout despite visual issues")
    @Severity(SeverityLevel.NORMAL)
    public void testVisualUserCheckout() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getVisualUsername(), TestDataReader.getStandardPassword());
        
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
                "Visual user should complete checkout successfully");
    }
}
