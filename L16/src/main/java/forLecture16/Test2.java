package forLecture16;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Test2 extends BaseTest{
    static SoftAssert softAssertion;
    @BeforeTest(groups = {"cart"})
    public static void getPageAddProduct() {
        driver.get("https://rozetka.com.ua/207066595/p207066595/");

        WebElement product = driver.findElement(By.xpath("//h1[@class=\"product__title\"]"));
        System.out.println("Product name: " + product.getText());

        WebElement buyButton = driver.findElement(By.xpath("//button[@class=\"buy-button button button_with_icon button_color_green button_size_large ng-star-inserted\"]"));
        System.out.println("Buy button is enabled: " + buyButton.isEnabled());
        buyButton.click();

        WebElement popUpCart = driver.findElement(By.xpath("//div[@class=\"modal__holder modal__holder_show_animation modal__holder--large\"]"));
        System.out.println("Pop-up Cart is displayed: " + popUpCart.isDisplayed());
    }

    @Test(groups = {"cart"})
    public void addingItemToCart() {
        softAssertion = new SoftAssert();

        WebElement nameCart = driver.findElement(By.xpath("//h3[@class=\"modal__heading\"]"));
        System.out.println(nameCart.getText());
        softAssertion.assertEquals(nameCart.getText(), "Корзина");

        WebElement cartProductTitle = driver.findElement(By.xpath("//a[@class=\"cart-product__title\" ]"));
        softAssertion.assertEquals(cartProductTitle.getText(), driver.findElement(By.xpath("//h1[@class=\"product__title\"]")).getText());

        WebElement placeAnOrderButton = driver.findElement(By.linkText("Оформить заказ"));
        System.out.println(placeAnOrderButton.getText());
        softAssertion.assertEquals(placeAnOrderButton.getText(), "Оформить заказ");

        WebElement continueShoppingButton = driver.findElement(By.linkText("Продолжить покупки"));
        System.out.println(continueShoppingButton.getText());
        softAssertion.assertEquals(continueShoppingButton.getText(), "Продолжить покупки");

        softAssertion.assertAll();

        System.out.println("This product was successfully added to cart.");
    }

    @Test(groups = {"cart"})
    public void removeAnItemFromCart(){
        softAssertion = new SoftAssert();

        WebElement nameCart = driver.findElement(By.xpath("//h3[@class=\"modal__heading\"]"));
        String cartName = nameCart.getText();
        System.out.println(cartName);
        softAssertion.assertEquals(cartName, "Корзина");

        WebElement cartProductTitle = driver.findElement(By.xpath("//a[@class=\"cart-product__title\" ]"));
        softAssertion.assertTrue(cartProductTitle.isDisplayed());

        WebElement threeDotsButton = driver.findElement(By.id("cartProductActions0"));
        System.out.println("Button 'Three dots' is displayed: " + threeDotsButton.isDisplayed());
        threeDotsButton.click();

        WebElement removeButton = driver.findElement(By.xpath("//button[@class=\"button button--medium button--with-icon button--link context-menu-actions__button\"]"));
        System.out.println(removeButton.getText());
        softAssertion.assertEquals(removeButton.getText(), "Удалить");
        removeButton.click();

        softAssertion.assertTrue(driver.findElement(By.xpath("//div[@class=\"modal__holder modal__holder_show_animation modal__holder--large\"]")).isDisplayed(),
                "Pop-up Cart isn't displayed");
        softAssertion.assertEquals(cartName, "Корзина");

        WebElement imgCart = driver.findElement(By.xpath("//img[@class=\"cart-dummy__illustration\"]"));
        System.out.println("Image is displayed: " + imgCart.isDisplayed());
        softAssertion.assertTrue(imgCart.isDisplayed());

        WebElement titleCartIsEmpty = driver.findElement(By.xpath("//h4[@class=\"cart-dummy__heading\"]"));
        softAssertion.assertEquals(titleCartIsEmpty.getText(), "Корзина пуста");
        System.out.println(titleCartIsEmpty.getText());

        softAssertion.assertAll();

        System.out.println("This product was successfully removed from cart.");
    }

}
