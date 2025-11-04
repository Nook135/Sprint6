package Praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //локатор для кнопки принять куки
    private By cookieButton = By.id("rcc-confirm-button");
    // метод нажимающий на кнопку принятия кук
    public void ClickCookieButton() {
        driver.findElement(cookieButton).click();
    }

    // локатор верхняя кнопка "Заказать"
    private By orderButtonTop = By.xpath(".//button[text()='Заказать']");
    // Метод кликает по кнопке Заказать вверху экрана
    public void ClickTopButton() {
        driver.findElement(orderButtonTop).click();
    }
    // локатор нижняя кнопка "Заказать"
    private By orderButtonBottom = By.xpath("//div[contains(@class, 'Home_FinishButton')]//button[text()='Заказать']");

    // Метод кликает по кнопке Заказать внизу экрана
    public void ClickBottomOrderButton() {
        // Прокручиваем к нижней кнопке
        WebElement bottomButton = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", bottomButton);
        bottomButton.click();
    }
}