# Environment
*Required or Used*
  - Java 
  - Selenium 
  - TestNG
  - Chrome Driver,Browser
        -Used Intellij as an IDE

# How It works
##### Run testng.xml file:
  - ***Create Folder 'ErrorSC' and 'CustomReport' at main repo folder same level as src***
  - ***Provide chromedriver path at parameter with name "pathTodriver at testng.xml file"***
  - A tag for each method is provided (include/exclude)
  - Run testng.xml file
  - Once all testcases are complete , a ***Custom report***  will launch with SCs and reasons for failure cases .
  - ScreenShots at  *'...TestingSite/ErrorSc/'* ; Custom Report at *'...TestingSite/CustomReport/'*
  
# Components and features
- **testng.xml**:
    -main file to run tests
- **FieldsTest.java**:
   -Test Class that test the validation of limitations of Each field  *ex:(FirstName must begin with capital letter)* (handles error exceptions)
- **MandatoryTest.java**:
    -Test Class to test behavior when trying to submit form with one missing value each time (handles error exceptions)
- **GeneratorRegex.java**:
    -An Implemented Class to generate valid and invalid testcases using regular expression
- **ListenerClass.java**:
    -A class that implements **TestNG [ITestListener] Interface** to check on test failures
-**CustomReport.java**:
    -A class that implements **TestNG [IReporter] Interface** to build a custom report

# Limitations:
- In some testcases browser will close , so keep it running. (faced an issue to reload register page after successful SignUp or successful Login in the same driver).
- Didn't emplement HTTP Interceptor (Tried using spring boot but got run out of time)
