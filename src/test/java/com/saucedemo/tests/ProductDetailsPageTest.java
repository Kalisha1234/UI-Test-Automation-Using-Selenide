package com.saucedemo.tests;

import com.saucedemo.config.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductDetailsPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.TestDataReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ProductDetailsPageTest extends BaseTest {
    
    private ProductDetailsPage productDetailsPage;
    
    @BeforeMethod
    public void navigateToProductDetails() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
        productDetailsPage = productsPage.clickProduct("Sauce Labs Backpack");
    }
    
    @Test(priority = 1)
    @Description("Verify product name is displayed")
    @Severity(SeverityLevel.CRITICAL)
    public void testProductNameIsDisplayed() {
        String productName = productDetailsPage.getProductName();
        assertFalse(productName.isEmpty(), "Product name should be displayed");
        assertEquals(productName, "Sauce Labs Backpack", "Product name should match");
    }
    
    @Test(priority = 2)
    @Description("Verify product price is displayed")
    @Severity(SeverityLevel.CRITICAL)
    public void testProductPriceIsDisplayed() {
        String price = productDetailsPage.getProductPrice();
        assertFalse(price.isEmpty(), "Product price should be displayed");
        assertTrue(price.startsWith("$"), "Price should start with $ symbol");
    }
    
    @Test(priority = 3)
    @Description("Verify product description is displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testProductDescriptionIsDisplayed() {
        String description = productDetailsPage.getProductDescription();
        assertFalse(description.isEmpty(), "Product description should be displayed");
    }
    
    @Test(priority = 4)
    @Description("Verify add to cart button is displayed")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddToCartButtonIsDisplayed() {
        assertTrue(productDetailsPage.isAddToCartDisplayed(), 
                "Add to cart button should be displayed");
    }
    
    @Test(priority = 5)
    @Description("Verify adding product to cart from details page")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddProductToCartFromDetails() {
        productDetailsPage.addToCart();
        // Verify button changes or cart updates (implementation depends on UI behavior)
    }
    
    @Test(priority = 6)
    @Description("Verify back button navigates to products page")
    @Severity(SeverityLevel.NORMAL)
    public void testBackButtonNavigatesToProducts() {
        ProductsPage productsPage = productDetailsPage.goBack();
        assertTrue(productsPage.isDisplayed(), "Should navigate back to products page");
    }
}
