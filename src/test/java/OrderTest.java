
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.OrderPage;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class OrderTest {

    private WebDriver driver;

    private final By orderHeader = By.className("Order_Header__BZXOb");

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qa-scooter.education-services.ru/");
    }

    @ParameterizedTest
    @MethodSource("orderParameters")
    void checkOrder(String chooseOrderButton, String newFirstName,String newSecondName, String newAddress, String newMetro, String newPhone, String rentalPeriod, String scooterColor){
        MainPage objMainPage = new MainPage(driver);
        objMainPage.orderButtonChoose(chooseOrderButton);
        OrderPage objOrderPage = new OrderPage(driver);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(orderHeader));
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

    @AfterEach
    void quit(){
        driver.quit();
    }
}
