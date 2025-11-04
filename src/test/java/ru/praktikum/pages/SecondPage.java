package ru.praktikum.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

public class SecondPage {
    private final WebDriver driver;

    public SecondPage(WebDriver driver) {
        this.driver = driver;
    }
    // =====  ДЛЯ КОГО ЗАКАЗ =====
    // Поле «Имя»
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    // Поле «Фамилия»
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // Поле «Адрес: куда привезти заказ»
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Поле «Станция метро»
    private By metroField = By.xpath(".//button[@value='2']");
    // Поле «Телефон: на него позвонит курьер»
    private By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка «Далее»
    private By nextButton = By.xpath(".//button[text()='Далее']");


}
