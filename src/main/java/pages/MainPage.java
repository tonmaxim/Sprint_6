package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage {
    private final WebDriver driver;

    // Кнопка "Заказать" в хедере
    private final By headerOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g']");

    // Кнопка "Заказать" внизу
    private final By downOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']");

    private final By blockOfQuestions = By.xpath(".//div[@class = 'accordion']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Клик по вопросу
    public void clickQuestion(String locatorQuestion) {
        WebElement element = driver.findElement(By.id(locatorQuestion));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.id(locatorQuestion)).click();
    }


    // Получение ответа на вопрос
    public String getAnswerText(int index){
        String locatorQuestion = "accordion__heading-";
        String locatorAnswer = "accordion__panel-";
        clickQuestion(locatorQuestion + index);
        String answerText = driver.findElement(By.id(locatorAnswer + index)).getText();
        return answerText;
    }

    // Клик по кнопке "Заказать" в хедере
    public void headerOrderButtonClick(){
        driver.findElement(headerOrderButton).click();
    }

    // Клик по кнопке "Заказать" внизу
    public void downOrderButtonClick(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(downOrderButton));
        WebElement element = driver.findElement(downOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(downOrderButton).click();
    }

    // Выбор кнопки в зависимости от переданного параметра
    public void orderButtonChoose(String chooseOrderButton) {
        if (chooseOrderButton.equals("headerOrderButton")){
            headerOrderButtonClick();
        } else if (chooseOrderButton.equals("downOrderButton")) {
            downOrderButtonClick();
        }
    }

    // Ожидание блока с вопросами
    public void waitBlockOfQuestions(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(blockOfQuestions));
    }

}
