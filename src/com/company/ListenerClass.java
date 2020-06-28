package com.company;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.*;

import java.awt.event.InputMethodListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ListenerClass implements ITestListener {
    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {


    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {


        String str = iTestResult.getName();
        switch(str)
        {
            case "testInvalidFirstName":
                Reporter.log(" <- is invalid (Either contain Digits or begin with Small letter)");
                break;
            case "testInvalidLastName":
                Reporter.log(" <- is invalid (Either contain Digits or begin with Small letter)");
                break;
            case "testSameNames":
                Reporter.log(" <- is valid name entered AT BOTH First and Last Name");
            case "testPasswordStructure":
                Reporter.log(" <- is invalid pass (more than 8 OR 6-8 but all small OR 6-8 but all capital)");
            case "testconflictPassword":
                Reporter.log(" <- is Two valid DIFFERENT pass ");
            case "testMobile":
                Reporter.log(" <- is invalid phone begin with 01+letters OR start 01 with wrong length OR right length with no '01' start ");
            case "Emailunique":
                Reporter.log(" <- Same E-mail Registered Twice");
            case "testInvalidEmail":
                Reporter.log(" <- is invalid (name@domaincom OR namedomain.com)");
                break;
            case "SuccessLogin":
                Reporter.log(" Complete Data yet not registered");
                break;
            case "fullMandatory":
                Reporter.log(" Complete Data yet not registered");
                break;
            case "testemptymobile":
                Reporter.log("Mobile is missing");
                break;
            case "testemptyFname":
                Reporter.log("First Name is missing");
                break;
            case "testemptylname":
                Reporter.log("Last Name is missing");
                break;
            case "testemptyemail":
                Reporter.log("E-mail is missing");
                break;
            case "testemptypass":
                Reporter.log("Password is missing");
                break;
            case "testemptycpass":
                Reporter.log("Confirm Password is missing");
                break;


        }

    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {


    }
}










