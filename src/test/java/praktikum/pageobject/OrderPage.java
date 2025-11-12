package praktikum.pageobject;

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

    // Локаторы
    private By inputName = By.xpath(".//input[@placeholder='* Имя']");                    // Поле ввода имени
    private By inputSurname = By.xpath(".//input[@placeholder='* Фамилия']");             // Поле ввода фамилии
    private By inputAddress = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']"); // Поле ввода адреса
    private By inputPhone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']"); // Поле ввода телефона
    private By nextButton = By.xpath(".//button[text()='Далее']");                        // Кнопка "Далее"

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Методы
    public void setName(String name) {
        driver.findElement(inputName).sendKeys(name);  // Ввод имени в поле
    }

    public void setSurname(String surname) {
        driver.findElement(inputSurname).sendKeys(surname);  // Ввод фамилии в поле
    }

    public void setAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);  // Ввод адреса в поле
    }

    public void setPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);  // Ввод телефона в поле
    }

    public void сlickNextButton() {
        driver.findElement(nextButton).click();  // Клик по кнопке "Далее"
    }

    public void selectMetroStation(String stationName) {
        // Выбор станции метро из выпадающего списка
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
}