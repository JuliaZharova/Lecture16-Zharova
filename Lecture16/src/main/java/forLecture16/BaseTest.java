package forLecture16;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    public static WebDriver driver = null;

    @DataProvider(name = "invalidDataForEmailPassword")
    public Object[][] dataForEmailPassword() {
        return new Object[][]{
                {" ", " "}, //пустые поля
                {"julia.amp.zharova@gmail.com", "qwerty"}, //валидная почта, невалидный пароль
                {"julia@gmail.com", "Julia*22"}, // невалидная почта, валидный пароль
                {"j@gmail.com", "qwer5"}, // невалидная почта, невалидный пароль
        };
    }

    @DataProvider(name = "validDataForEmailPassword")
    public Object[][] dataForEmailAndPassword() {
        return new Object[][]{
                {"julia.amp.zharova@gmail.com", "Julia*22"},
        };
    }

    @BeforeTest (groups = {"cart", "login"})
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.manage().window().maximize();
    }

    @AfterTest (groups = {"cart", "login"})
    public void setDown() {
        driver.quit();
    }

    @BeforeMethod
            public void beforeMethod(){
        System.out.println("It's beforeMethod");
    }

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("It's beforeSuite");
    }

}
