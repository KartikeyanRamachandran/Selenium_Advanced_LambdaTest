# Run Selenium Tests With TestNG On LambdaTest


## Table Of Contents

* Pre-requisites
* Run Your First Test
* Parallel Testing With TestNG


## Pre-requisites

Before you can start performing Java automation testing with Selenium, you would need to:

- Install the latest **Java development environment** i.e. **JDK 1.8** or higher. We recommend using the latest version.

- Download the latest **Selenium Client** and its **WebDriver bindings** from the official website. Latest versions of Selenium Client and WebDriver are ideal for running your automation script on LambdaTest Selenium cloud grid.

- Install **Maven** which supports **TestNG** framework out of the box. **Maven** can be downloaded and installed following the steps from [the official website](https://maven.apache.org/). Maven can also be installed easily.

	
### Setting Up Your Authentication

**Step 1:** Make sure you have your LambdaTest credentials with you to run test automation scripts. You can get these credentials from your LambdaTest Profile.

**Step 2:** Set LambdaTest **Username** and **Access Key** in "Lamdatest_SeleniumAdvance\src\main\java\com\lambdatest\basecode\SeleniumBase.java"

## Run Your First Test

**Step 3:** >**Test Scenario**: Test scenario located in below package  "Lamdatest_SeleniumAdvance\src\main\java\com\lambdatest\testscenarios\SeleniumAdv_TestScenario.java"


### Configuring Your Test Capabilities

You need to passing browser, browser version, and operating system information via the TestNG xml, along with LambdaTest Selenium grid capabilities via capabilities object. The capabilities object in the above code are defined as:

		<parameter name="browser" value="Edge" />
		<parameter name="version" value="87.0" />
		<parameter name="platform" value="macOS Sierra" />
		<parameter name="testcasename" value="Execution_on_Edge_Browser" />

You can generate capabilities for your test requirements with the help of our inbuilt [Desired Capability Generator](https://www.lambdatest.com/capabilities-generator/?utm_source=github&utm_medium=repo&utm_campaign=Java-TestNG-Selenium).

### Executing The Test

**Step 4:** Test can be done through the TestNG xml file. to execute open the xml file from below path then Right click -> RunAs-> TestNG Suite.
"Lamdatest_SeleniumAdvance\TestScenarioRunnerFile_TestNG.xml"

Your test results would be displayed on the test console and on LambdaTest automation dashboard. 

## Run Parallel Tests Using TestNG

Here is an example `xml` file which would help you to run the test on various browsers at the same time.

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
<suite thread-count="2" parallel="classes" name="Parallel">

  <test thread-count="5" name="Test_1">
    <classes>
      <class name="com.lambdatest.testscenarios.SeleniumAdv_TestScenario"/>
    </classes>
  </test> <!-- Test -->
  
    <test thread-count="5" name="Test_2">
    <classes>
      <class name="com.lambdatest.testscenarios.SeleniumAdv_TestScenario"/>
    </classes>
  </test> <!-- Test -->
  
  
</suite> <!-- Suite -->