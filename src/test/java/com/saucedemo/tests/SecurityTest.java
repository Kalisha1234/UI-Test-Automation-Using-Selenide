package com.saucedemo.tests;

import com.saucedemo.config.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestDataReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.*;
import static org.testng.Assert.*;

public class SecurityTest extends BaseTest {
    
    @Test(priority = 1)
    @Description("Verify direct URL access to inventory page without login redirects to login")
    @Severity(SeverityLevel.CRITICAL)
    public void testDirectInventoryAccessWithoutLogin() {
        open(TestDataReader.getAppUrl() + "/inventory.html");
        $("[data-test='error']").shouldBe(visible);
    }
    
    @Test(priority = 2)
    @Description("Verify direct URL access to cart page without login redirects to login")
    @Severity(SeverityLevel.CRITICAL)
    public void testDirectCartAccessWithoutLogin() {
        open(TestDataReader.getAppUrl() + "/cart.html");
        $("[data-test='error']").shouldBe(visible);
    }
    
    @Test(priority = 3)
    @Description("Verify direct URL access to checkout without login redirects to login")
    @Severity(SeverityLevel.CRITICAL)
    public void testDirectCheckoutAccessWithoutLogin() {
        open(TestDataReader.getAppUrl() + "/checkout-step-one.html");
        $("[data-test='error']").shouldBe(visible);
    }
    
    @Test(priority = 4)
    @Description("Verify SQL injection in username field")
    @Severity(SeverityLevel.BLOCKER)
    public void testSQLInjectionInUsername() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(TestDataReader.getAppUrl())
                .login("' OR '1'='1", "secret_sauce");
        assertTrue(loginPage.isErrorDisplayed(), "SQL injection should be prevented");
    }
    
    @Test(priority = 5)
    @Description("Verify XSS in username field")
    @Severity(SeverityLevel.BLOCKER)
    public void testXSSInUsername() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(TestDataReader.getAppUrl())
                .login("<script>alert('XSS')</script>", "secret_sauce");
        assertTrue(loginPage.isErrorDisplayed(), "XSS should be prevented");
    }
    
    @Test(priority = 6)
    @Description("Verify special characters in username")
    @Severity(SeverityLevel.NORMAL)
    public void testSpecialCharactersInUsername() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(TestDataReader.getAppUrl())
                .login("!@#$%^&*()", "secret_sauce");
        assertTrue(loginPage.isErrorDisplayed(), "Special characters should show error");
    }
    
    @Test(priority = 7)
    @Description("Verify very long username")
    @Severity(SeverityLevel.NORMAL)
    public void testVeryLongUsername() {
        LoginPage loginPage = new LoginPage();
        String longUsername = "a".repeat(1000);
        loginPage.openPage(TestDataReader.getAppUrl())
                .login(longUsername, "secret_sauce");
        assertTrue(loginPage.isErrorDisplayed(), "Very long username should show error");
    }
    
    @Test(priority = 8)
    @Description("Verify very long password")
    @Severity(SeverityLevel.NORMAL)
    public void testVeryLongPassword() {
        LoginPage loginPage = new LoginPage();
        String longPassword = "a".repeat(1000);
        loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), longPassword);
        assertTrue(loginPage.isErrorDisplayed(), "Very long password should show error");
    }
    
    @Test(priority = 9)
    @Description("Verify case sensitivity in username")
    @Severity(SeverityLevel.NORMAL)
    public void testCaseSensitivityInUsername() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(TestDataReader.getAppUrl())
                .login("STANDARD_USER", TestDataReader.getStandardPassword());
        assertTrue(loginPage.isErrorDisplayed(), "Username should be case sensitive");
    }
    
    @Test(priority = 10)
    @Description("Verify case sensitivity in password")
    @Severity(SeverityLevel.NORMAL)
    public void testCaseSensitivityInPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), "SECRET_SAUCE");
        assertTrue(loginPage.isErrorDisplayed(), "Password should be case sensitive");
    }
}
