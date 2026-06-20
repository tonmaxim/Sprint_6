package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class OrderPage {

    private final WebDriver driver;

    // Поле "Имя"
    private final By firstName = By.xpath(".//input[contains(@placeholder,'* Имя')]");

    // Поле "Фамилия"
    private final By secondName = By.xpath(".//input[contains(@placeholder,'* Фамилия')]");

    // Поле "Адрес"
    private final By address = By.xpath(".//input[contains(@placeholder,'* Адрес: куда привезти заказ')]");

    // Поле "Станция метро"
    private final By metro = By.xpath(".//input[contains(@placeholder,'* Станция метро')]");

    // Поле "Телефон"
    private final By phone = By.xpath(".//input[contains(@placeholder,'* Телефон: на него позвонит курьер')]");

    // Кнопка "Далее"
    private final By nextButton = By.xpath(".//button[contains(@class,'Button_Middle__1CSJM')]");

    // Поле "Когда привезти"
    private final By whenToBring = By.xpath(".//input[contains(@placeholder,'* Когда привезти самокат')]");

    // Поле "Срок аренды"
    private final By rental = By.className("Dropdown-placeholder");

    // Цвет "Черный жемчуг"
    private final By blackScooterColor = By.id("black");

    // Цвет "Cерая безысходность"
    private final By greyScooterColor = By.id("grey");

    // Кнопка "Заказать" внизу
    private final By downOrderButton = By.xpath(".//button[contains(@class,'Button_Middle__1CSJM') and (text() = 'Заказать')]");

    // Кнопка "Да" в модалке подтверждения заказа
    private final By yes = By.xpath(".//button[contains(@class,'Button_Middle__1CSJM') and (text() = 'Да')]");

    // Окно с сообщением об успешном создании заказа.
    private final By orderAccepted = By.xpath(".//div[contains(@class,'Order_ModalHeader__3FDaJ') and (text() = 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    // Ввести имя
    public void writeFirstName(String newFirstName){
        driver.findElement(firstName).isEnabled();
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(newFirstName);
    }


    // Ввести фамилию
    public void writeSecondName(String newSecondName){
        driver.findElement(secondName).isEnabled();
        driver.findElement(secondName).clear();
        driver.findElement(secondName).sendKeys(newSecondName);
    }

    // Ввести адрес
    public void writeAddress(String newAddress){
        driver.findElement(address).isEnabled();
        driver.findElement(address).clear();
        driver.findElement(address).sendKeys(newAddress);
    }

    // Выбрать метро
    public void chooseMetro(String newMetro){
        String station = ".//div[text()= '";
        driver.findElement(metro).isEnabled();
        driver.findElement(metro).click();
        WebElement element = driver.findElement(By.xpath(station + newMetro + "']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.xpath(station + newMetro + "']")).click();
    }

    // Ввести номер телефона
    public void writePhone(String newPhone){
        driver.findElement(phone).isEnabled();
        driver.findElement(phone).sendKeys(newPhone);
    }

    // Нажать кнопку "Далее"
    public void clickNextButton(){
        driver.findElement(nextButton).isEnabled();
        driver.findElement(nextButton).click();
    }

    // Выбрать дату доставки
    public void chooseDateDelivery(){
        driver.findElement(whenToBring).isEnabled();
        driver.findElement(whenToBring).click();
        driver.findElement(By.xpath(".//div[contains(@class, 'react-datepicker__day--keyboard-selected react-datepicker__day--today')]")).click();

    }

    // Выбрать срок аренды
    public void chooseRental(String rentalPeriod){
        String rentalLocator = ".//div[contains(@class,'Dropdown-option') and (text() = '";
        driver.findElement(rental).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu")));
        driver.findElement(By.xpath(rentalLocator + rentalPeriod + "')]")).click();

    }

    // Выбрать цвет
    public void chooseScooterColor(String scooterColor){
        driver.findElement(whenToBring).isEnabled();
        driver.findElement(whenToBring).click();
        if (scooterColor.equals("черный")){
            driver.findElement(blackScooterColor).click();
        } else if (scooterColor.equals("серый")){
            driver.findElement(greyScooterColor).click();
        }

    }

    // Нахать "Заказать"
    public void clickOrderButton(){
        driver.findElement(downOrderButton).isEnabled();
        driver.findElement(downOrderButton).click();
    }

    // Нахать "Да" в модалке подтверждения
    public void clickYesButton(){
        driver.findElement(yes).isEnabled();
        driver.findElement(yes).click();
    }

    public void checkOrderAccepted(){
        driver.findElement(orderAccepted).isDisplayed();
    }
}
