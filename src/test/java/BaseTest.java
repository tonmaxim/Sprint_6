import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    protected WebDriver driver;

    public static final String PAGE_URL = "https://qa-scooter.education-services.ru/";

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(BaseTest.PAGE_URL);
    }

    @AfterEach
    void quit(){
        driver.quit();
    }

}
