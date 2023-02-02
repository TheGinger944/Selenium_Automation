package selenium.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class TestNGTask {
	WebDriver driver;
	
	@BeforeTest
	public void BeforeClass() throws InterruptedException {
		driver = new ChromeDriver();
		driver.get("https://www.wildberries.by/");
		driver.manage().window().maximize();
		Thread.sleep(3000);
	}
	
	
	@Test
	public void selectCategory() throws InterruptedException {
		driver.findElement(By.cssSelector(".header__hamburger")).click();
		Thread.sleep(3000);
		int expectedInd = 4;
		List<WebElement> categories = driver.findElements(By.cssSelector(".menu-item.has-arrow"));
		for (WebElement category: categories) {
			if (expectedInd == Integer.parseInt(category.getAttribute("data-ind"))){
				category.click();
				break;
			}
		}
		Thread.sleep(1500);
		driver.findElement(By.xpath("//li/span[text()='Коврики']")).click();
		try{
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-ind='2']>div>div")));
			try {
				WebElement checkbox = driver.findElement(By.cssSelector("div[data-ind='2']>div>div"));
				System.out.println("before click: " + checkbox.getAttribute("class"));
				checkbox.click();
				System.out.println("after click: " + checkbox.getAttribute("class"));
			} 
			catch (WebDriverException e) {
				System.out.println("An exceptional case.");
			}
		} 
		catch (TimeoutException e) {
				System.out.println("WebDriver couldn’t locate the element");
		}
		Thread.sleep(5000);
		if(driver.findElement(By.cssSelector(".chip[data-id='331']")).isDisplayed() == true) {
			System.out.println("Tag \"Коврик комнатный\" is displayed.");
		}
		else {
			System.out.println("Tag \"Коврик комнатный\" is not displayed.");
		}
	}
	
	@AfterTest
	public void AfterClass() {
		driver.quit();
	}
}
