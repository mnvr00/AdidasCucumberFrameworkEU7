package com.demoblaze.step_definitions;

import com.demoblaze.pages.PlaceOrderPage;
import com.demoblaze.pages.ProductPage;
import com.demoblaze.utilities.BrowserUtils;
import com.demoblaze.utilities.ConfigurationReader;
import com.demoblaze.utilities.Driver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;


public class PurchaseSteps {

        ProductPage productpage = new ProductPage();
        PlaceOrderPage placeOrder = new PlaceOrderPage();
        int expectedpurchaseAmount;

        @Given("User is on the home page")
        public void user_is_on_the_home_page() {
            Driver.get().get(ConfigurationReader.get("demoblaze.url"));
        }


        @When("User adds {string} from {string}")
        public void user_adds_from(String product, String category) {
                productpage.navigateTo(product,category);
                BrowserUtils.waitForPageToLoad(3);
                productpage.addToCart();
                productpage.home.click();
        }

        @And("User navigates to cart and removes {string}")
        public void userNavigatesToCartAndRemoves(String product) {
                productpage.cart.click();
                BrowserUtils.waitForPageToLoad(3);
                productpage.deleteProduct(product);

        }


        @And("User clicks on place order")
        public void userClicksOnPlaceOrder() {
                productpage.cart.click();
                BrowserUtils.waitForPageToLoad(3);
                expectedpurchaseAmount = Integer.parseInt(productpage.totalPrice.getText());
                BrowserUtils.waitForPageToLoad(3);
                productpage.placeOrder.click();

        }

        @And("User fills the form for order and clicks on purchase button")
        public void userFillsTheFormForOrderAndClicksOnPurchaseButton() {
              placeOrder.fillForm();

        }

        @Then("Order ID and order amount should be as expected")
        public void orderIDAndOrderAmountShouldBeAsExpected() {

                String orderDetailsText = placeOrder.orderDetails.getText();
                BrowserUtils.waitForPageToLoad(3);
                String orderId = orderDetailsText.split("\n")[0];
                System.out.println("orderId = " + orderId);

                int actualPurchaseAmount = Integer.parseInt(orderDetailsText.split("\n")[1].split(" ")[1]);
                System.out.println("actualPurchaseAmount = " + actualPurchaseAmount);
                BrowserUtils.waitForPageToLoad(3);

                Assert.assertEquals("Price is NOT as expected",actualPurchaseAmount,expectedpurchaseAmount);

        }
}
