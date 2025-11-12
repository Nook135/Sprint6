package praktikum;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import praktikum.pageobject.MainPage;
import praktikum.pageobject.OrderPage;
import praktikum.pageobject.RentPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class TopOrder extends BaseTest {


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
        mainPage.clickCookieButton();

        // Выбор кнопки в зависимости от параметра
        if ("top".equals(buttonType)) {
            mainPage.clickTopButton();
        } else {
            mainPage.clickBottomOrderButton();
        }

        // Заполняем форму "Для кого самокат"
        orderPage.setName(name);
        orderPage.setSurname(surname);
        orderPage.setAddress(address);
        orderPage.selectMetroStation(metro);
        orderPage.setPhone(phone);

        orderPage.сlickNextButton();

        // Заполняем форму "Про аренду"
        rentPage.setDate(date);
        rentPage.setRentalPeriod(period);
        rentPage.setColorBlack();
        rentPage.setComment(comment);
        rentPage.clickOrderButton();
        rentPage.confirmOrder();

        // Проверяем, что сообщение отображается
        assertTrue(rentPage.isOrderSuccessDisplayed(), "Сообщение об успешном заказе");

        // Проверяем текст сообщения
        String actualSuccessText = rentPage.getOrderSuccessText();
        String expectedSuccessText = "Заказ оформлен";
        assertTrue(actualSuccessText.contains(expectedSuccessText),
                "Сообщение об успешном заказе: " + expectedSuccessText);
    }

}