package ru.praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    // ПЕРВАЯ СТРАНИЦА
    // КНОПКИ ЗАКАЗА

    // Верхняя кнопка "Заказать"
    private By orderButtonTop = By.xpath(".//button[text()='Заказать']");
    // Нижняя кнопка "Заказать"
    private By orderButtonDown = By.xpath("(.//button[text()='Заказать'])[2]");

    // РАЗДЕЛ "ВОПРОСЫ О ВАЖНОМ"
    // Длч скрола до раздела
    private By faqSection = By.xpath(".//div[text()='Вопросы о важном']");
    // Все вопросы (общий локатор)
    private By allQuestion = By.xpath(".//div[contains(@class, 'accordion__heading')]");

    // Все ответы (общий локатор)
    private By allAmswers = By.xpath(".//div[contains(@class, 'accordion__panel')]");

    // Конкретные вопросы по тексту
    private By question1 = By.xpath(".//div[text()='Сколько это стоит? И как оплатить?']");
    private By question2 = By.xpath(".//div[text()='Хочу сразу несколько самокатов! Так можно?']");
    private By question3 = By.xpath(".//div[text()='Как рассчитывается время аренды?']");
    private By question4 = By.xpath(".//div[text()='Можно ли заказать самокат прямо на сегодня?']");
    private By question5 = By.xpath(".//div[text()='Можно ли продлить заказ или вернуть самокат раньше?']");
    private By question6 = By.xpath(".//div[text()='Вы привозите зарядку вместе с самокатом?']");
    private By question7 = By.xpath(".//div[text()='Можно ли отменить заказ?']");
    private By question8 = By.xpath(".//div[text()='Я живу за МКАДом, привезёте?']");

    // ДРУГИЕ ЭЛЕМЕНТЫ

    // Кнопка принятия куки
    public static final By COOKIE_BUTTON = By.id("rcc-confirm-button");

  }