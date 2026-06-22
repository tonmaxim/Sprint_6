
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;
import pages.OrderPage;

import java.util.stream.Stream;


public class OrderTest extends Config{

    @ParameterizedTest
    @MethodSource("orderParameters")
    void checkOrder(String chooseOrderButton, String newFirstName,String newSecondName, String newAddress, String newMetro, String newPhone, String rentalPeriod, String scooterColor){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.orderButtonChoose(chooseOrderButton);
        OrderPage objOrderPage = new OrderPage(driver);
        objOrderPage.waitOrderHeader();
        objOrderPage.writeFirstName(newFirstName);
        objOrderPage.writeSecondName(newSecondName);
        objOrderPage.writeAddress(newAddress);
        objOrderPage.chooseMetro(newMetro);
        objOrderPage.writePhone(newPhone);
        objOrderPage.clickNextButton();
        objOrderPage.chooseDateDelivery();
        objOrderPage.chooseRental(rentalPeriod);
        objOrderPage.chooseScooterColor(scooterColor);
        objOrderPage.clickOrderButton();
        objOrderPage.clickYesButton();
        objOrderPage.checkOrderAccepted();
    }

    private static Stream<Arguments> orderParameters() {
        return Stream.of(
                Arguments.of("headerOrderButton", "Иван", "Иванов", "Адрес1", "Черкизовская", "+71234567890", "сутки", "черный"),
                Arguments.of("downOrderButton", "Петр", "Петров", "Адрес 2", "Спортивная", "+70987654321", "пятеро суток", "серый")
        );
    }

}
