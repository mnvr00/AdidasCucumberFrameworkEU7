package com.demoblaze.pages;

import com.demoblaze.utilities.BrowserUtils;
import com.demoblaze.utilities.Driver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage{

    @FindBy(xpath = "//a[.='Add to cart']")
    public WebElement addToCartElement;

    @FindBy(xpath = "//tbody//td[contains(.,'Sony vaio i5')]/../td[4]/a")
    public WebElement sonyVaioI5Delete;

    public void deleteProduct(String product){
        String locator = "//tbody//td[contains(.,'"+product+"')]/../td[4]/a";
        Driver.get().findElement(By.xpath(locator)).click();
        BrowserUtils.sleep(2);
    }

    public void navigateTo(String product, String category){
        Driver.get().findElement(By.linkText(category)).click();
        Driver.get().findElement(By.linkText(product)).click();
    }

    public void addToCart(){
        addToCartElement.click();
        // BrowserUtils.sleep(2);
        WebDriverWait wait = new WebDriverWait(Driver.get(),10);
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = Driver.get().switchTo().alert();
        alert.accept();
    }

    @FindBy(xpath = "//button[.='Place Order']")
    public WebElement placeOrder;

    @FindBy(id = "totalp")
    public WebElement totalPrice;


}