package Praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;


public class TopOrder {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setDriver() {
        if ("firefox".equals(System.getProperty("browser"))) {
            setupFirefox();
        } else {
            setupChrome();
        }
    }

    public void setupChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    public void setupFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    private static Stream<Arguments> orderDataProvider() {
        return Stream.of(
                Arguments.of("top", "Джони", "Сильверхенд", "ул. МегаБашня 9", "Молодёжная",
                        "88005553555", "04.11.2023", "сутки", "Оставьте пожалуйста у двери"),
                Arguments.of("bottom", "Панам", "Палмер", "пр. Ленинградский, д. 25", "Свиблово",
                        "89998887766", "10.12.2077", "двое суток", "Позвонить за час")
        );
    }
    @ParameterizedTest
    @MethodSource("orderDataProvider")
    public void testOrderWithDifferentData(String buttonType, String name, String surname,
                                           String address, String metro, String phone,
                                           String date, String period, String comment) throws InterruptedException {

        MainPage mainPage = new MainPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        RentPage rentPage = new RentPage(driver);

        // Принять куки
        mainPage.ClickCookieButton();

        // Выбор кнопки в зависимости от параметра
        if ("top".equals(buttonType)) {
            mainPage.ClickTopButton();
        } else {
            mainPage.ClickBottomOrderButton();
        }

        // Заполняем форму "Для кого самокат"
        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.selectMetroStation(metro);
        orderPage.setPhone(phone);

        orderPage.ClickNextButton();

        // Заполняем форму "Про аренду"
        rentPage.setDate(date);
        rentPage.setRentalPeriod(period);
        rentPage.setColorBlack();
        rentPage.setComment(comment);
        rentPage.clickOrderButton();
        rentPage.confirmOrder();

        Assertions.assertTrue(rentPage.isOrderSuccessDisplayed(), "Заказ оформлен");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}