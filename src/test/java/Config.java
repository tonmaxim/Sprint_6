import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class Config {

    protected WebDriver driver;

    public static final String pageUrl = "https://qa-scooter.education-services.ru/";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Config.pageUrl);
    }

    @AfterEach
    void quit(){
        driver.quit();
    }

}
