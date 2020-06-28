package com.company;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;
import java.util.concurrent.TimeUnit;


@Listeners(ListenerClass.class)
public class FieldsTest {

    WebDriver driver;

    @Parameters({"driverType", "pathTodriver"})
    @BeforeClass
    void intiatclass(String type, String url) {
        System.setProperty(type, url);

    }

    @BeforeMethod
    void setFields() throws Exception {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get("https://www.phptravels.net/register");

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

    @Test(priority = 1, invocationCount = 1)
    void testInvalidFirstName()  //test invalid name in "First Name field"
    {
        String previousURL = driver.getCurrentUrl();
        WebElement fname = driver.findElement(By.name("firstname"));
        fname.clear();
        GeneratorRegex gn = new GeneratorRegex();
        String name = gn.invalid_name();
        fname.sendKeys(name);
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        if (!currentURL.equals(previousURL))
            Reporter.log("'" + name + "'");
        Assert.assertEquals(currentURL, previousURL);

    }


    @Test(priority = 1)
    void testInvalidLastName() //test invalid name in "Last Name" field
    {
        String previousURL = driver.getCurrentUrl();
        WebElement lname = driver.findElement(By.name("lastname"));
        lname.clear();
        GeneratorRegex gn = new GeneratorRegex();
        String val = gn.invalid_name();
        lname.sendKeys(val);
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        if (!currentURL.equals(previousURL))
            Reporter.log("'" + val + "'");
        Assert.assertEquals(currentURL, previousURL);

    }

    @Test(priority = 1)
    void testInvalidEmail() //Invalid Mail
    {
        String previousURL = driver.getCurrentUrl();
        WebElement email = driver.findElement(By.name("email"));
        email.clear();
        GeneratorRegex gn = new GeneratorRegex();
        String val = gn.invalid_email();
        email.sendKeys(val);
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        if (!currentURL.equals(previousURL))
            Reporter.log("'" + val + "'");
        Assert.assertEquals(currentURL, previousURL);
    }

    @Test(priority = 1)
    void testSameNames() //Same VALID Name in both First and Lastname
    {                   //Same VALID Name in both First and Lastname
        String previousURL = driver.getCurrentUrl();
        WebElement fname = driver.findElement(By.name("firstname"));
        WebElement lname = driver.findElement(By.name("lastname"));
        fname.clear();
        lname.clear();
        GeneratorRegex gn = new GeneratorRegex();
        String val = gn.valid_name();
        fname.sendKeys(val);
        lname.sendKeys(val);
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        if (!currentURL.equals(previousURL))
            Reporter.log("'" + val + "'");
        Assert.assertEquals(currentURL, previousURL);

    }

    @Test(priority = 1)
    void testPasswordStructure() //Same password in pass and confirmPass  with wrong structure
    {                           //Same password in pass and confirmPass  with wrong structure
        String previousURL = driver.getCurrentUrl();
        WebElement pass = driver.findElement(By.name("password"));
        WebElement cpass = driver.findElement(By.name("confirmpassword"));
        pass.clear();
        cpass.clear();
        GeneratorRegex gn = new GeneratorRegex();
        String val = gn.invalid_pass();
        pass.sendKeys(val);
        cpass.sendKeys(val);
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        if (!currentURL.equals(previousURL))
            Reporter.log("'" + val + "'");
        Assert.assertEquals(currentURL, previousURL);

    }


    @Test(priority = 1)
    void testconflictPassword() //Right Srtucture Pass wrong Confirm Value
    {
        String previousURL = driver.getCurrentUrl();
        WebElement pass = driver.findElement(By.name("password"));
        WebElement cpass = driver.findElement(By.name("confirmpassword"));
        pass.clear();
        cpass.clear();
        GeneratorRegex gn = new GeneratorRegex();
        String val = gn.valid_pass();
        pass.sendKeys(val);
        String cval = gn.valid_pass();
        cpass.sendKeys(cval);
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        if (!currentURL.equals(previousURL))
            Reporter.log("'" + val + "'" + "'" + cval + "'");
        Assert.assertEquals(currentURL, previousURL);

    }


    @Test(priority = 1)
    void testMobile() //not valid mobile 01sd6151sd  0106130581 -> wrong length
    {

        String previousURL = driver.getCurrentUrl();
        WebElement mobile = driver.findElement(By.name("phone"));
        mobile.clear();
        GeneratorRegex gn = new GeneratorRegex();
        String val = gn.invalid_phone();
        mobile.sendKeys(val);
        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentURL = driver.getCurrentUrl();
        if (!currentURL.equals(previousURL))
            Reporter.log("'" + val + "'");
        Assert.assertEquals(currentURL, previousURL);
    }


    @Test(priority = 1)
    void Emailunique()
    {

        /*first time insert with mail X*/

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
        String email1 = generateElement.valid_email();

        fname.sendKeys(generateElement.valid_name());
        lname.sendKeys(generateElement.valid_name());
        email.sendKeys(email1);
        pass.sendKeys(password);
        confirmpass.sendKeys(password);
        phone.sendKeys(generateElement.valid_phone());

        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();

        /*second time insert with  same mail */

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get("https://www.phptravels.net/register");

        fname = driver.findElement(By.name("firstname"));
        lname = driver.findElement(By.name("lastname"));
        phone = driver.findElement(By.name("phone"));
        email = driver.findElement(By.name("email"));
        pass = driver.findElement(By.name("password"));
        confirmpass = driver.findElement(By.name("confirmpassword"));
        fname.clear();
        lname.clear();
        email.clear();
        pass.clear();
        confirmpass.clear();
        phone.clear();

        fname.sendKeys(generateElement.valid_name());
        lname.sendKeys(generateElement.valid_name());
        email.sendKeys(email1);
        pass.sendKeys(password);
        confirmpass.sendKeys(password);
        phone.sendKeys(generateElement.valid_phone());

        String previousURL = driver.getCurrentUrl();

        form = driver.findElement(By.id("headersignupform"));
        form.submit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String current = driver.getCurrentUrl();

        if (!current.equals(previousURL))
            Reporter.log("'" + email1 + "'");
        Assert.assertEquals(current, previousURL);
    }


    @Test(priority = 2)
    void SuccessLogin() throws Exception
    {

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
        String email1 = generateElement.valid_email();

        fname.sendKeys(generateElement.valid_name());
        lname.sendKeys(generateElement.valid_name());
        email.sendKeys(email1);
        pass.sendKeys(password);
        confirmpass.sendKeys(password);
        phone.sendKeys(generateElement.valid_phone());

        WebElement form = driver.findElement(By.id("headersignupform"));
        form.submit();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.quit();

        /*second time insert with  same mail */

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.get("https://www.phptravels.net/login");

        try {

            WebElement username = driver.findElement(By.name("username"));
            WebElement passwordlogin = driver.findElement(By.name("password"));
            //WebElement loginform = driver.findElement(By.id("loginfrm"));
            username.clear();
            passwordlogin.clear();

            username.sendKeys(email1);
            passwordlogin.sendKeys(password);
        } catch (NoSuchElementException e) {
            driver.quit();
            throw new Exception("can't find element");
        }
        WebElement loginbutton = driver.findElement(By.xpath("/html/body/div[2]/div[1]/section/div/div[1]/div[2]/form/button"));
        loginbutton.click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String current = driver.getCurrentUrl();
        System.out.println(current);
        if (!current.equals("https://www.phptravels.net/account/"))
            Reporter.log(" ");

        Assert.assertEquals("https://www.phptravels.net/account/", current);
    }

    @AfterMethod
    void checkForFailure(ITestResult result)
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

        driver.quit();
    }

}
