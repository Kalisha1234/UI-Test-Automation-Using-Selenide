package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    
    private final SelenideElement usernameField = $("#user-name");
    private final SelenideElement passwordField = $("#password");
    private final SelenideElement loginButton = $("#login-button");
    private final SelenideElement errorMessage = $("[data-test='error']");
    
    @Step("Open login page")
    public LoginPage openPage(String url) {
        open(url);
        return this;
    }
    
    @Step("Login with username: {username}")
    public ProductsPage login(String username, String password) {
        usernameField.setValue(username);
        passwordField.setValue(password);
        loginButton.click();
        return new ProductsPage();
    }
    
    @Step("Get error message")
    public String getErrorMessage() {
        return errorMessage.getText();
    }
    
    @Step("Verify error message is displayed")
    public boolean isErrorDisplayed() {
        return errorMessage.exists();
    }
}
