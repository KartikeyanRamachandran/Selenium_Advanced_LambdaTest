package com.lambdatest.testscenarios;

import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import com.lambdatest.basecode.SeleniumBase;


public class SeleniumAdv_TestScenario extends SeleniumBase {

	@Test
	public void TestScenario1() throws InterruptedException {
		
		WebElement welcomeBanner = driver.findElement(By.xpath("//p[contains(text(),'Try LambdaTest for Free')]"));

		try {
			waitObject.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//p[contains(text(),'Try LambdaTest for Free')]")));
			waitObject.until(ExpectedConditions.visibilityOf(welcomeBanner));
		} catch (Exception e) {
			System.out.println("Element Present on DOM but not visible on page..!!");
			e.printStackTrace();
		}finally {
			WebElement elementCloseBanner = driver.findElement(By.id("exit_popup_close"));
			executor.executeScript("arguments[0].click();", elementCloseBanner);
		}

		WebElement elementIntegration = driver.findElement(By.xpath("//div[@class='text-center mt-25']/a[@href='https://www.lambdatest.com/integrations']"));
		waitObject.until(ExpectedConditions.elementToBeClickable(elementIntegration));
		executor.executeScript("arguments[0].scrollIntoView(true);", elementIntegration);
		
		if(platformOfCurrentExecution.equalsIgnoreCase("macOS Sierra")) {
			clicklnk = Keys.chord(Keys.COMMAND,Keys.ENTER);
		}else {
			clicklnk = Keys.chord(Keys.CONTROL,Keys.ENTER);
		}
		
		elementIntegration.sendKeys(clicklnk);

		allOpenedWindows = driver.getWindowHandles();
		lambdaOpendedWindow = new ArrayList<String>(allOpenedWindows);
		System.out.println("Current Window Count "+allOpenedWindows.size());
		String newWindow = lambdaOpendedWindow.get(1);
		String currentWindow = driver.getWindowHandle();
		System.out.println("Current Opened Window :"+currentWindow);
		driver.switchTo().window(newWindow);

		String current_Url = driver.getCurrentUrl();
		assertObject.assertEquals(current_Url, expected_Url);

		WebElement elementCodelessAuto = driver.findElement(By.xpath("//h2[contains(text(),'Codeless Automation')]"));
		executor.executeScript("arguments[0].scrollIntoView(true);", elementCodelessAuto);

		driver.findElement(By.xpath("//a[contains(text(),'Integrate Testing Whiz with LambdaTest')]")).click();
		Thread.sleep(2000);
		String Actual_Title = driver.getTitle();

		if(expected_Title.equals(Actual_Title)) {
			System.out.println("Expected and Actual Title are Same");
		}else {
			assertObject.assertEquals(expected_Title, Actual_Title);
			System.out.println("Expected Title not matched with the Actual Title");
		}

		driver.switchTo().window(lambdaOpendedWindow.get(1)).close();
		driver.switchTo().window(lambdaOpendedWindow.get(0));

		allOpenedWindows = driver.getWindowHandles();
		System.out.println("Current Window Count "+allOpenedWindows.size());

		String currentUrl = driver.getCurrentUrl();
		String replacedURL = currentUrl.replace(currentUrl, newURL);
		driver.navigate().to(replacedURL);
		Thread.sleep(3000);
		driver.navigate().refresh();
		Thread.sleep(2000);

		driver.findElement(By.xpath("//li[@id='menu-item-10121']/a[@href='https://community.lambdatest.com/']")).click();
		String actualCurrentURL = driver.getCurrentUrl();

		if(expectedURL.equals(actualCurrentURL)) {
			System.out.println("Community url is matched");
		} else {
			System.out.println("Community url is not matched!");
		}
		
	}
}
