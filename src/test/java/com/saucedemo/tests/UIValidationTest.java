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
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.*;

public class UIValidationTest extends BaseTest {
    
    private ProductsPage productsPage;
    
    @BeforeMethod
    public void setup() {
        LoginPage loginPage = new LoginPage();
        productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
    }
    
    @Test(priority = 1)
    @Description("Verify all product images are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testAllProductImagesDisplayed() {
        int imageCount = $$(".inventory_item_img").size();
        assertTrue(imageCount > 0, "Product images should be displayed");
    }
    
    @Test(priority = 2)
    @Description("Verify all product names are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testAllProductNamesDisplayed() {
        int nameCount = $$(".inventory_item_name").size();
        assertTrue(nameCount > 0, "Product names should be displayed");
    }
    
    @Test(priority = 3)
    @Description("Verify all product descriptions are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testAllProductDescriptionsDisplayed() {
        int descCount = $$(".inventory_item_desc").size();
        assertTrue(descCount > 0, "Product descriptions should be displayed");
    }
    
    @Test(priority = 4)
    @Description("Verify all product prices are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testAllProductPricesDisplayed() {
        int priceCount = $$(".inventory_item_price").size();
        assertTrue(priceCount > 0, "Product prices should be displayed");
    }
    
    @Test(priority = 5)
    @Description("Verify all add to cart buttons are displayed")
    @Severity(SeverityLevel.NORMAL)
    public void testAllAddToCartButtonsDisplayed() {
        int buttonCount = $$("[data-test^='add-to-cart']").size();
        assertTrue(buttonCount > 0, "Add to cart buttons should be displayed");
    }
    
    @Test(priority = 6)
    @Description("Verify product count matches expected")
    @Severity(SeverityLevel.NORMAL)
    public void testProductCountMatchesExpected() {
        int productCount = productsPage.getProductsCount();
        assertEquals(productCount, 6, "Should display 6 products");
    }
    
    @Test(priority = 7)
    @Description("Verify page title is correct")
    @Severity(SeverityLevel.MINOR)
    public void testPageTitleCorrect() {
        $(".title").shouldHave(text("Products"));
    }
    
    @Test(priority = 8)
    @Description("Verify app logo is displayed")
    @Severity(SeverityLevel.MINOR)
    public void testAppLogoDisplayed() {
        $(".app_logo").shouldBe(visible);
    }
    
    @Test(priority = 9)
    @Description("Verify footer text is displayed")
    @Severity(SeverityLevel.MINOR)
    public void testFooterTextDisplayed() {
        $(".footer_copy").shouldBe(visible);
    }
    
    @Test(priority = 10)
    @Description("Verify shopping cart badge updates correctly")
    @Severity(SeverityLevel.NORMAL)
    public void testShoppingCartBadgeUpdates() {
        productsPage.addProductToCart("sauce-labs-backpack");
        $(".shopping_cart_badge").shouldHave(text("1"));
    }
    
    @Test(priority = 11)
    @Description("Verify add to cart button changes to remove after click")
    @Severity(SeverityLevel.NORMAL)
    public void testAddToCartButtonChangesToRemove() {
        productsPage.addProductToCart("sauce-labs-backpack");
        $("[data-test='remove-sauce-labs-backpack']").shouldBe(visible);
    }
    
    @Test(priority = 12)
    @Description("Verify product image is clickable")
    @Severity(SeverityLevel.NORMAL)
    public void testProductImageClickable() {
        $$(".inventory_item_img").first().click();
        $(".inventory_details_name").shouldBe(visible);
    }
    
    @Test(priority = 13)
    @Description("Verify product name is clickable")
    @Severity(SeverityLevel.NORMAL)
    public void testProductNameClickable() {
        $$(".inventory_item_name").first().click();
        $(".inventory_details_name").shouldBe(visible);
    }
    
    @Test(priority = 14)
    @Description("Verify copyright text contains correct year")
    @Severity(SeverityLevel.MINOR)
    public void testCopyrightTextContainsYear() {
        $(".footer_copy").shouldHave(text("2024"));
    }
    
    @Test(priority = 15)
    @Description("Verify peek link in footer")
    @Severity(SeverityLevel.MINOR)
    public void testPeekLinkInFooter() {
        $(".footer_robot").shouldBe(visible);
    }
}
