package com.saucedemo.tests;

import com.saucedemo.config.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.utils.TestDataReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LoginPageTest extends BaseTest {
    
    @Test(priority = 1)
    @Description("Verify successful login with valid credentials")
    @Severity(SeverityLevel.BLOCKER)
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        ProductsPage productsPage = loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), TestDataReader.getStandardPassword());
        
        assertTrue(productsPage.isDisplayed(), "Products page should be displayed after successful login");
    }
    
    @Test(priority = 2)
    @Description("Verify login fails with invalid username")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithInvalidUsername() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(TestDataReader.getAppUrl())
                .login("invalid_user", TestDataReader.getStandardPassword());
        
        assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
        assertTrue(loginPage.getErrorMessage().contains("Username and password do not match"), 
                "Error message should indicate invalid credentials");
    }
    
    @Test(priority = 3)
    @Description("Verify login fails with invalid password")
    @Severity(SeverityLevel.CRITICAL)
    public void testLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getStandardUsername(), "wrong_password");
        
        assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
    }
    
    @Test(priority = 4)
    @Description("Verify login fails with empty credentials")
    @Severity(SeverityLevel.NORMAL)
    public void testLoginWithEmptyCredentials() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(TestDataReader.getAppUrl())
                .login("", "");
        
        assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed for empty credentials");
    }
    
    @Test(priority = 5)
    @Description("Verify locked out user cannot login")
    @Severity(SeverityLevel.CRITICAL)
    public void testLockedOutUser() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(TestDataReader.getAppUrl())
                .login("locked_out_user", TestDataReader.getStandardPassword());
        
        assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
        assertTrue(loginPage.getErrorMessage().contains("locked out"), 
                "Error message should indicate user is locked out");
    }
}
