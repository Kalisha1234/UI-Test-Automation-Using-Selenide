package com.saucedemo.tests.user;

import com.saucedemo.config.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.utils.TestDataReader;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class LockedOutUserTest extends BaseTest {
    
    @Test(priority = 1)
    @Description("Verify locked out user cannot login")
    @Severity(SeverityLevel.CRITICAL)
    public void testLockedOutUserCannotLogin() {
        LoginPage loginPage = new LoginPage();
        loginPage.openPage(TestDataReader.getAppUrl())
                .login(TestDataReader.getLockedOutUsername(), TestDataReader.getStandardPassword());
        
        assertTrue(loginPage.isErrorDisplayed(), "Error message should be displayed");
        assertTrue(loginPage.getErrorMessage().contains("locked out"), 
                "Error message should indicate user is locked out");
    }
}
