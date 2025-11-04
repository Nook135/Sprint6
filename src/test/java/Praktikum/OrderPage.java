package Praktikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final JavascriptExecutor js;
    private final WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // локатор ввести имя
    private By inputName = By.xpath(".//input[@placeholder='* Имя']");
    // метод для ввода символов в строку имя
    public void setName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    // локатор ввести фамилию
    private By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");
    // метод для ввода символов в строку фамилии
    public void setSurname(String surname) {
        driver.findElement(inputSurname).sendKeys(surname);
    }
    // локатор ввести адрес
    private By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // метод для ввода символов в строку фамилии
    public void setAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }


    // МЕТРО (Черкизовская)
    public void selectMetroStation(String stationName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.xpath(".//input[@placeholder='* Станция метро']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath(".//button[contains(@class, 'Order_SelectOption')]")
        ));

        WebElement stationElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(".//button[contains(@class, 'Order_SelectOption')][contains(., '" + stationName + "')]")
        ));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", stationElement);

        wait.until(ExpectedConditions.elementToBeClickable(stationElement));
        stationElement.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.attributeToBe(
                By.xpath(".//input[@placeholder='* Станция метро']"), "value", ""
        )));
    }

    // локатор ввести телефон
    private By inputPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // метод для ввода символов в строку фамилии
    public void setPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
    }

    // локатор нажать кнопку Далее
    private By nextButton = By.xpath(".//button[text()='Далее']");
    // Метод кликает по кнопке Далее
    public void ClickNextButton() {
        driver.findElement(nextButton).click();
    }
}