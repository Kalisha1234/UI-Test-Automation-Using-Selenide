package com.saucedemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {
    
    private final ElementsCollection cartItems = $$(".cart_item");
    private final SelenideElement checkoutButton = $("#checkout");
    private final SelenideElement continueShoppingButton = $("#continue-shopping");
    private final SelenideElement pageTitle = $(".title");
    
    @Step("Get cart items count")
    public int getCartItemsCount() {
        return cartItems.size();
    }
    
    @Step("Remove product from cart: {productName}")
    public CartPage removeProduct(String productName) {
        $("[data-test='remove-" + productName.toLowerCase().replace(" ", "-") + "']").click();
        return this;
    }
    
    @Step("Proceed to checkout")
    public CheckoutPage proceedToCheckout() {
        checkoutButton.shouldBe(Condition.enabled).click();
        return new CheckoutPage();
    }
    
    @Step("Continue shopping")
    public ProductsPage continueShopping() {
        continueShoppingButton.click();
        return new ProductsPage();
    }
    
    @Step("Verify cart page is displayed")
    public boolean isDisplayed() {
        return pageTitle.shouldBe(Condition.visible).getText().equals("Your Cart");
    }
    
    @Step("Verify cart is empty")
    public boolean isEmpty() {
        return cartItems.size() == 0;
    }
}
