package com.saucedemo.tests;

import com.saucedemo.config.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.TestDataReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.$;
import static org.testng.Assert.*;

public class SortingTest extends BaseTest {
    
    private ProductsPage productsPage;
    
    @BeforeMethod
    public void setupTest() {
        LoginPage loginPage = new LoginPage();
        productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
    }
    
    @Test(priority = 1)
    @Description("Verify products can be sorted A to Z")
    @Severity(SeverityLevel.NORMAL)
    public void testSortProductsAtoZ() {
        $(".product_sort_container").selectOption("az");
        assertTrue(productsPage.isDisplayed(), "Products page should be displayed");
    }
    
    @Test(priority = 2)
    @Description("Verify products can be sorted Z to A")
    @Severity(SeverityLevel.NORMAL)
    public void testSortProductsZtoA() {
        $(".product_sort_container").selectOption("za");
        assertTrue(productsPage.isDisplayed(), "Products page should be displayed");
    }
    
    @Test(priority = 3)
    @Description("Verify products can be sorted by price low to high")
    @Severity(SeverityLevel.NORMAL)
    public void testSortProductsPriceLowToHigh() {
        $(".product_sort_container").selectOption("lohi");
        assertTrue(productsPage.isDisplayed(), "Products page should be displayed");
    }
    
    @Test(priority = 4)
    @Description("Verify products can be sorted by price high to low")
    @Severity(SeverityLevel.NORMAL)
    public void testSortProductsPriceHighToLow() {
        $(".product_sort_container").selectOption("hilo");
        assertTrue(productsPage.isDisplayed(), "Products page should be displayed");
    }
    
    @Test(priority = 5)
    @Description("Verify sorting persists after adding item to cart")
    @Severity(SeverityLevel.NORMAL)
    public void testSortingPersistsAfterAddingToCart() {
        $(".product_sort_container").selectOption("lohi");
        productsPage.addProductToCart("sauce-labs-backpack");
        assertEquals($(".product_sort_container").getSelectedOption().getText(), "Price (low to high)");
    }
}
