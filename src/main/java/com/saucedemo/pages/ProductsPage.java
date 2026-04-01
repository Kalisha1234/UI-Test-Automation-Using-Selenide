package com.saucedemo.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductsPage {
    
    private final SelenideElement pageTitle = $(".title");
    private final ElementsCollection inventoryItems = $$(".inventory_item");
    private final SelenideElement cartBadge = $(".shopping_cart_badge");
    private final SelenideElement cartLink = $(".shopping_cart_link");
    
    @Step("Verify products page is displayed")
    public boolean isDisplayed() {
        return pageTitle.exists() && pageTitle.getText().equals("Products");
    }
    
    @Step("Add product to cart: {productName}")
    public ProductsPage addProductToCart(String productName) {
        $("[data-test='add-to-cart-" + productName.toLowerCase().replace(" ", "-") + "']").shouldBe(Condition.enabled).click();
        return this;
    }
    
    @Step("Click on product: {productName}")
    public ProductDetailsPage clickProduct(String productName) {
        $$(".inventory_item_name").findBy(com.codeborne.selenide.Condition.text(productName)).click();
        return new ProductDetailsPage();
    }
    
    @Step("Get cart items count")
    public int getCartItemsCount() {
        return cartBadge.exists() ? Integer.parseInt(cartBadge.getText()) : 0;
    }
    
    @Step("Get cart badge count")
    public int getCartBadgeCount() {
        return cartBadge.exists() ? Integer.parseInt(cartBadge.getText()) : 0;
    }
    
    @Step("Open cart")
    public CartPage openCart() {
        cartLink.click();
        return new CartPage();
    }
    
    @Step("Get products count")
    public int getProductsCount() {
        return inventoryItems.size();
    }
}
