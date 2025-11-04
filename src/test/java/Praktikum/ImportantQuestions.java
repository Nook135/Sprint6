package Praktikum;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class ImportantQuestions {
    private  WebDriver driver;
    private  WebDriverWait wait;

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
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

   // @BeforeEach
    public void setupFirefox() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }

    // Метод для параметризации
    private static Stream<Arguments> faqDataProvider() {
        return Stream.of(
                Arguments.of(0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                Arguments.of(1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                Arguments.of(2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                Arguments.of(3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                Arguments.of(4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                Arguments.of(5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                Arguments.of(6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                Arguments.of(7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }

    @ParameterizedTest
    @MethodSource("faqDataProvider")
    @DisplayName("Проверка ответов в аккордеоне 'Вопросы о важном'")
    void testFaqAccordion(int questionIndex, String expectedAnswer) {

        // СОЗДАЕМ PageObjectForImoprtantQuestions и используем его
        PageObjectForImoprtantQuestions page = new PageObjectForImoprtantQuestions(driver);

        // Закрываем куки через PageObjectForImoprtantQuestions
        page.acceptCookies();
        // Скроллим до раздела "Вопросы о важном"
        page.scrollToFaqSection();
        // Кликаем на первый вопрос "Сколько это стоит? И как оплатить?"
        page.clickQuestion(questionIndex);

        // ТЕСТ вопросы о главном параметризация
        // Получаем текст ответа
        String actualAnswer = page.getAnswerText(questionIndex);

        // Проверяем, что ответ отображается
        assertTrue(page.isAnswerDisplayed(questionIndex),
                "Ответ на вопрос " + questionIndex + " должен отображаться");

        // Проверяем точный текст ответа
        assertEquals(expectedAnswer, actualAnswer,
                "Текст ответа для вопроса " + questionIndex + " должен соответствовать ожидаемому");
    }

    @AfterEach
    void teardown() {
        driver.quit(); // Закрываем браузер после каждого теста
    }
}