package dev.sodeog.seleniumPlayground;

import com.opencsv.CSVIterator;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.*;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
//import org.lombok.*;

public class googleTest {
    protected WebDriver driver;

//    @BeforeSuite
    public void beforeSuite() throws Exception {
        System.setProperty("headless", "false"); // You can set this property elsewhere
        String headless = System.getProperty("headless");
    }


//    @BeforeTest //This will run before the tests to ensure test setup
    public void testSetup() throws Exception {
        String uid= (PropfileReader.getUserData("userId"));
        String pwd = (PropfileReader.getUserData("password"));
        String url = (PropfileReader.getUserData("url"));
        int implicitWait = new Integer(PropfileReader.getSetting("implicitTimeout")).intValue();
        String browser = (PropfileReader.getSetting("browser"));
        String browserOptions = (PropfileReader.getSetting("browserOptions"));
    }

    @AfterSuite
    public void afterSuite() {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    @DataProvider(name = "environmentBased")
    public Object[][] environmentBased() {

        Map<String, String> env = System.getenv();
        String environment = env.get("ENV");
        try {
            /* TODO: TEST */
            String dataFileName = this.getClass().getClassLoader().toString() + "../resources/" + environment + ".csv";
            CSVReader reader = new CSVReader(new FileReader(dataFileName));
            List<String[]> myEntries = reader.readAll();
            return (Object[][]) myEntries.toArray();

        } catch (IOException | CsvException iocsve) {
            System.out.println("Configuration properties file cannot be found\n"
                    + iocsve.getMessage()
                    + "\n" +  System.getProperty("user.dir"));
        }
/**
 * TODO: what should we return if we get an exception, e.g. this fails?
 */
        return new Object[0][];
    }


    @Test(dataProvider = "environmentBased")
    public void environmentVariables(String one) {
        System.out.println(
            "User Details: " +
            PropfileReader.getUserData("abc")
        );
    }
}