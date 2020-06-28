package com.company;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import javax.interceptor.Interceptors;
import java.io.File;
import java.util.concurrent.TimeUnit;

@Listeners(ListenerClass.class)
public class MandatoryTest {

    WebDriver driver;

    @Parameters({"driverType", "pathTodriver"})
    @BeforeClass
    void intiatefunc(String type, String url) {
        System.setProperty(type, url);
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);


    }

    @BeforeMethod
    void setelements() throws Exception {
        //driver = new ChromeDriver();

        driver.manage().deleteAllCookies();
        driver.get("https://www.phptravels.net/register");
        //driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        try {

            WebElement fname = driver.findElement(By.name("firstname"));
            WebElement lname = driver.findElement(By.name("lastname"));
            WebElement phone = driver.findElement(By.name("phone"));
            WebElement email = driver.findElement(By.name("email"));
            WebElement pass = driver.findElement(By.name("password"));
            WebElement confirmpass = driver.findElement(By.name("confirmpassword"));
            fname.clear();
            lname.clear();
            email.clear();
            pass.clear();
            confirmpass.clear();
            phone.clear();
            GeneratorRegex generateElement = new GeneratorRegex();
            String password = generateElement.valid_pass();


            fname.sendKeys(generateElement.valid_name());
            lname.sendKeys(generateElement.valid_name());
            email.sendKeys(generateElement.valid_email());
            pass.sendKeys(password);
            confirmpass.sendKeys(password);
            phone.sendKeys(generateElement.valid_phone());

        } catch (NoSuchElementException e) {
            driver.quit();
            throw new Exception("can't find element");

        }


    }


    @Test(priority = 2)
    void fullMandatory() {
        String previousURL = driver.getCurrentUrl();


        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        form.click();

        try {
            new WebDriverWait(driver, 15).until(ExpectedConditions.urlContains("account"));
        } catch (NotFoundException e) {
            System.out.println(e.toString());
        }

        String currentURL = driver.getCurrentUrl();
        System.out.println(currentURL);
        System.out.println(previousURL);
        Assert.assertNotEquals(previousURL, currentURL);
    }


    @Test(priority = 1)
    void testemptymobile() {
        String previousURL = driver.getCurrentUrl();
        WebElement phonex = driver.findElement(By.name("phone"));
        phonex.clear();
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.click();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(previousURL, currentURL);
        //driver.quit();
    }

    @Test(priority = 1)
    void testemptyFname() {
        String previousURL = driver.getCurrentUrl();
        WebElement fname = driver.findElement(By.name("firstname"));
        fname.clear();
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();

        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.urlContains("account"));
        } catch (TimeoutException e) {
            String currentURL = driver.getCurrentUrl();
            Assert.assertEquals(previousURL, currentURL);
        }

    }

    @Test(priority = 1)
    void testemptylname() {
        String previousURL = driver.getCurrentUrl();
        WebElement lname = driver.findElement(By.name("lastname"));
        lname.clear();
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(previousURL, currentURL);
        //driver.quit();
    }

    @Test(priority = 1)
    void testemptyemail() {
        String previousURL = driver.getCurrentUrl();
        WebElement email = driver.findElement(By.name("email"));
        email.clear();
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(previousURL, currentURL);
        //driver.quit();
    }

    @Test(priority = 1)
    void testemptypass() {
        String previousURL = driver.getCurrentUrl();
        WebElement pass = driver.findElement(By.name("password"));
        pass.clear();
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(previousURL, currentURL);
        //driver.quit();

    }

    @Test(priority = 1)
    void testemptycpass() {
        String previousURL = driver.getCurrentUrl();
        WebElement cpass = driver.findElement(By.name("confirmpassword"));
        cpass.clear();
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(previousURL, currentURL);
        //driver.quit();
    }

    @AfterClass
    void endOfTestEmpty() {
        driver.quit();

    }

    @AfterMethod
    void checkForFailureSC(ITestResult result)
    {

        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) driver;
                File source = ts.getScreenshotAs(OutputType.FILE);
                String dest = System.getProperty("user.dir") + "/ErrorSc/" + result.getName() + ".png";
                Files.copy(source, new File(dest));
                System.out.println(new File(dest).getAbsolutePath());
                //  Reporter.log("<br> <img src ='" + new File(dest) + "' height=400 width =400 /> <br>");
                //Reporter.log("<a href='"+ new File(dest).getAbsolutePath() + "'> <img src='"+ new File(dest).getAbsolutePath() + "' height='100' width='100'/> </a>");
                System.out.println("Screenshot taken");
            } catch (Exception e) {

                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }


    }

}
