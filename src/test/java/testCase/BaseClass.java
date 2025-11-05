package testCase;

import java.io.FileReader;
import java.net.URI;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public Logger logger;
    public Properties p;

    public WebDriver getDriver() {
        return driver.get();
    }

    @BeforeClass(groups = { "Sanity", "Regression", "Master" })
    @Parameters({ "os", "browser" })
    public void setup(@Optional("Windows") String os, @Optional("chrome") String br) throws Exception {

        FileReader file = new FileReader("src/test/resources/config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        String execution_env = p.getProperty("execution_env"); // local / remote

        if (execution_env.equalsIgnoreCase("remote")) {

            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setBrowserName(br.toLowerCase());

            driver.set(new RemoteWebDriver(new URI(p.getProperty("grid_url")).toURL(), cap));
            logger.info("=== Running on Selenium GRID → " + br + " ===");
        } else {

            switch (br.toLowerCase()) {

                case "chrome":
                    io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();
                    driver.set(new ChromeDriver());
                    break;

                case "firefox":
                    io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver().setup();
                    driver.set(new FirefoxDriver());
                    break;

                case "edge":
                    io.github.bonigarcia.wdm.WebDriverManager.edgedriver().setup();
                    driver.set(new EdgeDriver());
                    break;

                default:
                    throw new IllegalArgumentException("Invalid browser: " + br);
            }

            logger.info("=== Running Locally → " + br + " ===");
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().get(p.getProperty("appURL2"));
    }

    @AfterClass
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            logger.info("=== Browser Closed ===");
        }
    }
}

