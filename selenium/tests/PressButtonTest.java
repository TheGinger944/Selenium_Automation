package selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertTrue;

public class PressButtonTest {
	@Test
	public void runTest() {
	WebDriver driver = new ChromeDriver();
	driver.get("http://seasonvar.ru/");
	driver.findElement(By.xpath("//a[contains(text(),\'Лучшие сериалы\')]")).click();
	WebElement pageName = driver.findElement(By.xpath("//span[contains(.,\'Топ сериалов\')]"));
	assertTrue(pageName.isDisplayed());
	driver.close();
	}

}
