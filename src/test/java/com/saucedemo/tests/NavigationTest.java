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
import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.*;

public class NavigationTest extends BaseTest {
    
    private ProductsPage productsPage;
    
    @BeforeMethod
    public void setupTest() {
        LoginPage loginPage = new LoginPage();
        productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
    }
    
    @Test(priority = 1)
    @Description("Verify hamburger menu opens")
    @Severity(SeverityLevel.NORMAL)
    public void testHamburgerMenuOpens() {
        $("#react-burger-menu-btn").click();
        $(".bm-menu").shouldBe(visible);
    }
    
    @Test(priority = 2)
    @Description("Verify all items menu link works")
    @Severity(SeverityLevel.NORMAL)
    public void testAllItemsMenuLink() {
        $("#react-burger-menu-btn").click();
        $("#inventory_sidebar_link").click();
        assertTrue(productsPage.isDisplayed());
    }
    
    @Test(priority = 3)
    @Description("Verify about menu link exists")
    @Severity(SeverityLevel.MINOR)
    public void testAboutMenuLink() {
        $("#react-burger-menu-btn").click();
        $("#about_sidebar_link").shouldBe(visible);
    }
    
    @Test(priority = 4)
    @Description("Verify logout menu link works")
    @Severity(SeverityLevel.CRITICAL)
    public void testLogoutMenuLink() {
        $("#react-burger-menu-btn").click();
        $("#logout_sidebar_link").click();
        $("#login-button").shouldBe(visible);
    }
    
    @Test(priority = 5)
    @Description("Verify reset app state menu link exists")
    @Severity(SeverityLevel.NORMAL)
    public void testResetAppStateMenuLink() {
        $("#react-burger-menu-btn").click();
        $("#reset_sidebar_link").shouldBe(visible);
    }
    
    @Test(priority = 6)
    @Description("Verify menu closes with X button")
    @Severity(SeverityLevel.MINOR)
    public void testMenuClosesWithXButton() {
        $("#react-burger-menu-btn").click();
        $("#react-burger-cross-btn").click();
        $(".bm-menu").shouldNotBe(visible);
    }
    
    @Test(priority = 7)
    @Description("Verify shopping cart icon is clickable")
    @Severity(SeverityLevel.NORMAL)
    public void testShoppingCartIconClickable() {
        $(".shopping_cart_link").shouldBe(visible).click();
        $(".title").shouldHave(text("Your Cart"));
    }
    
    @Test(priority = 8)
    @Description("Verify Twitter link exists in footer")
    @Severity(SeverityLevel.MINOR)
    public void testTwitterLinkExists() {
        $(".social_twitter").shouldBe(visible);
    }
    
    @Test(priority = 9)
    @Description("Verify Facebook link exists in footer")
    @Severity(SeverityLevel.MINOR)
    public void testFacebookLinkExists() {
        $(".social_facebook").shouldBe(visible);
    }
    
    @Test(priority = 10)
    @Description("Verify LinkedIn link exists in footer")
    @Severity(SeverityLevel.MINOR)
    public void testLinkedInLinkExists() {
        $(".social_linkedin").shouldBe(visible);
    }
}
