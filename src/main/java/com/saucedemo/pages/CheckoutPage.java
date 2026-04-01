package com.saucedemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage {
    
    private final SelenideElement firstNameField = $("#first-name");
    private final SelenideElement lastNameField = $("#last-name");
    private final SelenideElement postalCodeField = $("#postal-code");
    private final SelenideElement continueButton = $("#continue");
    private final SelenideElement finishButton = $("#finish");
    private final SelenideElement completeHeader = $(".complete-header");
    private final SelenideElement totalPrice = $(".summary_total_label");
    private final SelenideElement cancelButton = $("#cancel");
    private final SelenideElement errorMessage = $("[data-test='error']");
    
    @Step("Fill checkout information")
    public CheckoutPage fillInformation(String firstName, String lastName, String postalCode) {
        firstNameField.shouldBe(Condition.visible).setValue(firstName);
        lastNameField.shouldBe(Condition.visible).setValue(lastName);
        postalCodeField.shouldBe(Condition.visible).setValue(postalCode);
        return this;
    }
    
    @Step("Continue to overview")
    public CheckoutPage continueToOverview() {
        continueButton.shouldBe(Condition.enabled).click();
        return this;
    }
    
    @Step("Get total price")
    public String getTotalPrice() {
        return totalPrice.shouldBe(Condition.visible).getText();
    }
    
    @Step("Finish checkout")
    public CheckoutPage finishCheckout() {
        finishButton.shouldBe(Condition.enabled).click();
        return this;
    }
    
    @Step("Get completion message")
    public String getCompletionMessage() {
        return completeHeader.shouldBe(Condition.visible).getText();
    }
    
    @Step("Cancel checkout")
    public CartPage cancelCheckout() {
        cancelButton.shouldBe(Condition.enabled).click();
        return new CartPage();
    }
    
    @Step("Get error message")
    public String getErrorMessage() {
        return errorMessage.shouldBe(Condition.visible).getText();
    }
    
    @Step("Verify error is displayed")
    public boolean isErrorDisplayed() {
        try {
            return errorMessage.is(Condition.visible);
        } catch (Exception e) {
            return false;
        }
    }
}
