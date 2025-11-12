package praktikum.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public RentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    // ПРО АРЕНДУ
    // Когда привезти самокат
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(By.xpath(".//div[contains(@class, 'Order_Content__bmtHS')]")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath(".//div[contains(@class, 'react-date picker')]")
        ));
    }

    // Выбор срока аренды
    public void setRentalPeriod(String period) {
        // Локатор для клика на поле выбора срока аренды
        By rentalPeriodField = By.xpath(".//div[@class='Dropdown-control']");

        // Кликаем чтобы открыть выпадающий список
        driver.findElement(rentalPeriodField).click();

        // Ждем пока появится нужный вариант в выпадающем списке
        WebElement periodOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(".//div[text()='" + period + "']")
        ));

        // Выбираем вариант
        periodOption.click();
    }
    // чек-бокс с цветом
    private By colorBlackCheckbox = By.id("black");
    public void setColorBlack() {
        driver.findElement(colorBlackCheckbox).click();
    }

    // комментарий курьеру
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }
    // жмем кнопку Заказать
    private By orderButton = By.xpath(".//button[contains(@class, 'Button_Button__ra12g Button_Middle__1CSJM') and text()='Заказать']");
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    // жмем в модальном окне "Да"
    private By confirmOrderButton = By.xpath(".//button[text()='Да']");
    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmOrderButton));
        driver.findElement(confirmOrderButton).click();
    }
    // Подтверждение заказа
    private By orderSuccessModal = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");
    public boolean isOrderSuccessDisplayed() {
        return driver.findElement(orderSuccessModal).isDisplayed();
    }
    public String getOrderSuccessText() {
        return driver.findElement(orderSuccessModal).getText();
    }
}