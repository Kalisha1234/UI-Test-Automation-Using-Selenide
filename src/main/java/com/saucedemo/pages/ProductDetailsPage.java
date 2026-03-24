package com.saucedemo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$;

public class ProductDetailsPage {
    
    private final SelenideElement productName = $(".inventory_details_name");
    private final SelenideElement productPrice = $(".inventory_details_price");
    private final SelenideElement productDescription = $(".inventory_details_desc");
    private final SelenideElement addToCartButton = $("[data-test^='add-to-cart']");
    private final SelenideElement removeButton = $("[data-test^='remove']");
    private final SelenideElement backButton = $("#back-to-products");
    
    @Step("Get product name")
    public String getProductName() {
        return productName.getText();
    }
    
    @Step("Get product price")
    public String getProductPrice() {
        return productPrice.getText();
    }
    
    @Step("Get product description")
    public String getProductDescription() {
        return productDescription.getText();
    }
    
    @Step("Add product to cart")
    public ProductDetailsPage addToCart() {
        addToCartButton.click();
        return this;
    }
    
    @Step("Remove product from cart")
    public ProductDetailsPage removeFromCart() {
        removeButton.click();
        return this;
    }
    
    @Step("Go back to products")
    public ProductsPage goBack() {
        backButton.click();
        return new ProductsPage();
    }
    
    @Step("Verify add to cart button is displayed")
    public boolean isAddToCartDisplayed() {
        return addToCartButton.exists();
    }
}
