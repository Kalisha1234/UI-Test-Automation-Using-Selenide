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

public class ProductsPageTest extends BaseTest {
    
    private ProductsPage productsPage;
    
    @BeforeMethod
    public void loginBeforeTest() {
        LoginPage loginPage = new LoginPage();
        productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
    }
    
    @Test(priority = 1)
    @Description("Verify products page is displayed after login")
    @Severity(SeverityLevel.BLOCKER)
    public void testProductsPageDisplay() {
        assertTrue(productsPage.isDisplayed(), "Products page should be displayed");
    }
    
    @Test(priority = 2)
    @Description("Verify products are listed on the page")
    @Severity(SeverityLevel.CRITICAL)
    public void testProductsAreListed() {
        int productsCount = productsPage.getProductsCount();
        assertTrue(productsCount > 0, "Products should be displayed on the page");
        assertEquals(productsCount, 6, "Should display 6 products");
    }
    
    @Test(priority = 3)
    @Description("Verify adding product to cart")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddProductToCart() {
        productsPage.addProductToCart("sauce-labs-backpack");
        assertEquals(productsPage.getCartItemsCount(), 1, "Cart should contain 1 item");
    }
    
    @Test(priority = 4)
    @Description("Verify adding multiple products to cart")
    @Severity(SeverityLevel.CRITICAL)
    public void testAddMultipleProductsToCart() {
        productsPage.addProductToCart("sauce-labs-backpack")
                .addProductToCart("sauce-labs-bike-light")
                .addProductToCart("sauce-labs-bolt-t-shirt");
        
        assertEquals(productsPage.getCartItemsCount(), 3, "Cart should contain 3 items");
    }
    
    @Test(priority = 5)
    @Description("Verify clicking on product navigates to details page")
    @Severity(SeverityLevel.NORMAL)
    public void testClickProductNavigatesToDetails() {
        ProductDetailsPage detailsPage = productsPage.clickProduct("Sauce Labs Backpack");
        assertEquals(detailsPage.getProductName(), "Sauce Labs Backpack", 
                "Should navigate to correct product details page");
    }
    
    @Test(priority = 6)
    @Description("Verify cart is empty initially")
    @Severity(SeverityLevel.NORMAL)
    public void testCartIsEmptyInitially() {
        assertEquals(productsPage.getCartItemsCount(), 0, "Cart should be empty initially");
    }
}
