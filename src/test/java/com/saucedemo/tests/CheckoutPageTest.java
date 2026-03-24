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

public class CheckoutPageTest extends BaseTest {
    
    private CheckoutPage checkoutPage;
    
    @BeforeMethod
    public void setupCheckout() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
        
        productsPage.addProductToCart("sauce-labs-backpack");
        CartPage cartPage = productsPage.openCart();
        checkoutPage = cartPage.proceedToCheckout();
    }
    
    @Test(priority = 1)
    @Description("Verify successful checkout with valid information")
    @Severity(SeverityLevel.BLOCKER)
    public void testSuccessfulCheckout() {
        checkoutPage.fillInformation(
                TestDataReader.getCheckoutFirstName(),
                TestDataReader.getCheckoutLastName(),
                TestDataReader.getCheckoutPostalCode())
                .continueToOverview()
                .finishCheckout();
        
        assertEquals(checkoutPage.getCompletionMessage(), "Thank you for your order!", 
                "Order completion message should be displayed");
    }
    
    @Test(priority = 2)
    @Description("Verify checkout fails with empty first name")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutWithEmptyFirstName() {
        checkoutPage.fillInformation("", "Doe", "12345");
        checkoutPage.continueToOverview();
        
        assertTrue(checkoutPage.isErrorDisplayed(), "Error should be displayed for empty first name");
    }
    
    @Test(priority = 3)
    @Description("Verify checkout fails with empty last name")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutWithEmptyLastName() {
        checkoutPage.fillInformation("John", "", "12345");
        checkoutPage.continueToOverview();
        
        assertTrue(checkoutPage.isErrorDisplayed(), "Error should be displayed for empty last name");
    }
    
    @Test(priority = 4)
    @Description("Verify checkout fails with empty postal code")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutWithEmptyPostalCode() {
        checkoutPage.fillInformation("John", "Doe", "");
        checkoutPage.continueToOverview();
        
        assertTrue(checkoutPage.isErrorDisplayed(), "Error should be displayed for empty postal code");
    }
    
    @Test(priority = 5)
    @Description("Verify cancel button returns to cart")
    @Severity(SeverityLevel.NORMAL)
    public void testCancelCheckout() {
        CartPage cartPage = checkoutPage.cancelCheckout();
        assertTrue(cartPage.isDisplayed(), "Should return to cart page");
    }
    
    @Test(priority = 6)
    @Description("Verify checkout with multiple products")
    @Severity(SeverityLevel.CRITICAL)
    public void testCheckoutWithMultipleProducts() {
        checkoutPage.cancelCheckout();
        
        ProductsPage productsPage = new ProductsPage();
        productsPage.addProductToCart("sauce-labs-bike-light")
                .addProductToCart("sauce-labs-bolt-t-shirt");
        
        CartPage cartPage = productsPage.openCart();
        assertEquals(cartPage.getCartItemsCount(), 3, "Cart should contain 3 items");
        
        CheckoutPage newCheckoutPage = cartPage.proceedToCheckout();
        newCheckoutPage.fillInformation(
                TestDataReader.getCheckoutFirstName(),
                TestDataReader.getCheckoutLastName(),
                TestDataReader.getCheckoutPostalCode())
                .continueToOverview()
                .finishCheckout();
        
        assertEquals(newCheckoutPage.getCompletionMessage(), "Thank you for your order!", 
                "Order should complete successfully with multiple products");
    }
}
