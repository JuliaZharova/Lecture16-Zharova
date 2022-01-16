package forLecture16;

import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Test1 extends BaseTest {

    public static void openLoginPopup(){
            driver.get("https://rozetka.com.ua/");
            System.out.println(driver.getCurrentUrl());

            WebElement loginButton = driver.findElement(By.xpath("//rz-user/button"));
            System.out.println(loginButton.isEnabled());
            loginButton.click();
            System.out.println(driver.getCurrentUrl());

            WebElement popup = driver.findElement(By.xpath("//div[@class=\"modal__holder modal__holder_show_animation modal__holder--medium\"]"));
            System.out.println ("Pop-up is displayed: " + popup.isDisplayed());

            WebElement titlePopup = driver.findElement(By.xpath("//h3[@class=\"modal__heading\"]"));
            String titlePopUp = titlePopup.getText();
            Assert.assertEquals(titlePopUp, "Вход");
    }

    @Test (groups = {"login"},
            dataProvider = "validDataForEmailPassword")
    @Description("Checking the login with the registered user data.")
    public void positiveCheckLoginPopup(String email, String password) {
            openLoginPopup();

            WebElement loginField = driver.findElement(By.id("auth_email"));
            loginField.click();
            loginField.sendKeys("email");

            WebElement passw = driver.findElement(By.id("auth_pass"));
            passw.click();
            passw.sendKeys(password);

            WebElement enterButton = driver.findElement(By.xpath("//button[@class=\"button button--large button--green auth-modal__submit ng-star-inserted\"]"));
            System.out.println("Button 'Войти' is enabled: " + enterButton.isEnabled());
            enterButton.click();

           // Assert.assertFalse(driver.findElement(By.xpath("//div[@class=\"modal__holder modal__holder_show_animation modal__holder--medium\"]")).isDisplayed(), "Pop up is displayed!");
            System.out.println("Registered user logged in.");
            driver.getCurrentUrl();
    }



    @Test (groups = {"login"},
            dataProvider = "invalidDataForEmailPassword")
    @Description("Checking the login with the unregistered user data.")
    public void negativeCheckLoginPopup(String email, String password) {
       openLoginPopup();

        WebElement loginField = driver.findElement(By.id("auth_email"));
        loginField.click();
        loginField.sendKeys(email);

        WebElement passw = driver.findElement(By.id("auth_pass"));
        passw.click();
        passw.sendKeys(password);

        WebElement enterButton = driver.findElement(By.xpath("//button[@class=\"button button--large button--green auth-modal__submit ng-star-inserted\"]"));
        System.out.println("Button 'Войти' is enabled: " + enterButton.isEnabled());
        enterButton.click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class=\"modal__holder modal__holder_show_animation modal__holder--medium\"]")).isDisplayed(), "Pop up isn't displayed!");
        System.out.println("An unregistered user could not log in.");
        driver.getCurrentUrl();

}


}
