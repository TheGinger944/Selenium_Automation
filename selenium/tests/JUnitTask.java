package selenium.tests;

import java.time.Duration;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JUnitTask {
	WebDriver driver;
	
	@Before
	public void setUp() throws InterruptedException {
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
				checkbox.click();
				assertTrue(checkbox.getAttribute("class").contains("is-active"));
			} 
			catch (WebDriverException e) {
				System.out.println("An exceptional case.");
			}
		} 
		catch (TimeoutException e) {
				System.out.println("WebDriver couldn’t locate the element");
		}
		Thread.sleep(5000);
		assertTrue(driver.findElement(By.cssSelector(".chip[data-id='331']")).isDisplayed());
}
	
	@After
	public void terminate() {
		driver.quit();
	}
}


