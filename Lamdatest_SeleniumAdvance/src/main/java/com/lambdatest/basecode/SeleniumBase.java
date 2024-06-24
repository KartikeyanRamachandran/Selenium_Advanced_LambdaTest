package com.lambdatest.basecode;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

public class SeleniumBase {

	//Lambda_test__accessdetails
	public String username = "karthikyuva75";
	public String accesskey = "NfUl1JCQsjmoz8gJOck5j0CcBsRn7aW4T0dpC7YHkwYwVv6Tpd";
	public String gridURL = "@hub.lambdatest.com/wd/hub";
	boolean status = false;


	public static ChromeOptions options;
	public static RemoteWebDriver driver = null;
	public static WebDriverWait waitObject;
	public static SoftAssert assertObject;
	public static JavascriptExecutor executor;
	public static DesiredCapabilities capabilities;

	//Variables
	public String expected_Url = "https://www.lambdatest.com/integrations";
	public String expected_Title = "TestingWhiz Integration | LambdaTest";
	public Set<String> allOpenedWindows;
	public List<String> lambdaOpendedWindow;
	public String newURL = "https://www.lambdatest.com/blog";
	public String expectedURL = "https://community.lambdatest.com/";
	public String platformOfCurrentExecution;
	public String clicklnk;

	@Parameters({"browser","version","platform","testcasename"})
	@BeforeTest
	public void initBrowser(String browserName,String browserVersion,String osPlatform,String testcaseName) throws InterruptedException {
		platformOfCurrentExecution = osPlatform;
		System.out.println("browsername" +browserName+ "    "+platformOfCurrentExecution);
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", browserName); //from testng xml
		capabilities.setCapability("browserVersion", browserVersion); //from testng xml
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", "karthikyuva75");
		ltOptions.put("accessKey", "NfUl1JCQsjmoz8gJOck5j0CcBsRn7aW4T0dpC7YHkwYwVv6Tpd");
		ltOptions.put("visual", true);
		ltOptions.put("edge.popups", true);
		ltOptions.put("platformName", osPlatform); //from testng xml
		ltOptions.put("build", "LambdaTest_Selenium_Advance");
		ltOptions.put("project", "LambdaTest_Selenium_Advanced");
		ltOptions.put("name", testcaseName); //from testng xml
		capabilities.setCapability("LT:Options", ltOptions);
		try {
			options = new ChromeOptions();
			options.addArguments("--remote-allow-origins");
			driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		waitObject = new WebDriverWait(driver, Duration.ofSeconds(20));
		executor = (JavascriptExecutor) driver;
		assertObject = new SoftAssert();

		driver.get("https://www.lambdatest.com/");

		try {
			// Adjust the selector to match the Cookiebot accept button
			WebElement acceptCookie = driver.findElement(By.xpath("//*[@id='CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll' and contains(text(),'Allow all')]"));
			waitObject.until(ExpectedConditions.elementToBeClickable(acceptCookie));
			acceptCookie.click();
			System.out.println("Cookiebot popup accepted");
		} catch (Exception e) {
			System.out.println("Cookiebot popup not found or already accepted");
		}
		Thread.sleep(3000);
	}


	@AfterTest(alwaysRun =  true)
	public void tearDown() {
		driver.executeScript("lambda-status=" + "Pass");
		if (driver != null) {
			driver.quit();
		}
	}

}
