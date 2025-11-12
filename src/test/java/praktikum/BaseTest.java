package praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final String BASE_URL = "https://qa-scooter.praktikum-services.ru/";

    @BeforeEach
    public void setDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            setupFirefox();
        } else {
            setupChrome();
        }
    }

    //@BeforeEach
    public void setupChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);
    }

    // @BeforeEach
    public void setupFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);
    }

    @AfterEach
    void teardown() {
        driver.quit(); // Закрываем браузер после каждого теста
    }
}