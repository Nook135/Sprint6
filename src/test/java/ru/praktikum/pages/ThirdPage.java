package ru.praktikum.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class ThirdPage {
    private final WebDriver driver;

    public ThirdPage(WebDriver driver) {
        this.driver = driver;
    }

    // =====  ПРО АРЕНДУ =====

    // Поле «Когда привезти самокат»
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Поле выбора «Срок аренды»
    private By rentalPeriodField = By.xpath(".//div[text()='* Срок аренды']");
    // Чекбокс «чёрный самокат»
    private By colorBlackCheckbox = By.id("black");
    // Чекбокс «серый самокат»
    private By colorGreyCheckbox = By.id("grey");
    // Поле «Комментарий для курьера»
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка «Заказать» на второй странице
    private By orderButton = By.xpath(".//button[text()='Заказать']");
}
