package Praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PageObjectForImoprtantQuestions {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public PageObjectForImoprtantQuestions(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Кнопка принятия куки
    private By cookieButton = By.id("rcc-confirm-button");

    // Метод для принятия куки
    public void acceptCookies() {
        wait.until(ExpectedConditions.elementToBeClickable(cookieButton)).click();
    }
    // раздел Вопросы о важном
    private By faqSection = By.xpath(".//div[text()='Вопросы о важном']");

    // Метод для скролла до раздела "Вопросы о важном"
    public void scrollToFaqSection() {
        WebElement faqElement = wait.until(ExpectedConditions.visibilityOfElementLocated(faqSection));
        js.executeScript("arguments[0].scrollIntoView();", faqElement);
    }

    // локаторы для всех вопросов
    private By questions = By.xpath(".//div[contains(@class, 'accordion__heading')]");
    private By answers = By.xpath(".//div[contains(@class, 'accordion__panel')]");

    // Методы для работы с аккордеоном
    public void clickQuestion(int index) {
        wait.until(ExpectedConditions.elementToBeClickable(questions));
        driver.findElements(questions).get(index).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElements(answers).get(index)));
    }

    public String getAnswerText(int index) {
        WebElement answer = wait.until(ExpectedConditions.visibilityOf(driver.findElements(answers).get(index)));
        return driver.findElements(answers).get(index).getText();
    }

    public boolean isAnswerDisplayed(int index) {
        return driver.findElements(answers).get(index).isDisplayed();
    }
}
