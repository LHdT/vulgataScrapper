package vulgata;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;

public class Scraper {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "/Users/luishidalgodetena/Desktop/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.biblegateway.com/");
//		*[contains(@aria-label,'Accept Coo')]
		driver.findElement(By.xpath("//*[contains(@aria-label,'Accept Coo')]")).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[text()='Consent']/..")).click();

		
		driver.findElement(By.xpath("//*[contains(@class,'search-dropdown-display')]")).click();
		
		driver.findElement(By.xpath("//span[@aria-label='All']")).click();
		
		driver.findElement(By.xpath("//li[contains(text(),'Vulgata')]")).click();
		
		driver.findElement(By.xpath("//div[@class='js-dropdown-submit']/div/button[@aria-label='Search']")).click();
		
		driver.findElement(By.xpath("//*[text()='Bible Book List']/..")).click();
		
		
		
		List<WebElement> enlaceslibros = driver.findElements(By.xpath("//*[@class='go911112886']"));
		
		enlaceslibros.stream().forEach(enlace->{
			enlace.click();
			String clicklnk = Keys.chord(Keys.CONTROL,Keys.ENTER);
			List<WebElement> enlacesCapitulos = driver.findElements(By.xpath("//tr[@class='go441370079']"));
			enlacesCapitulos.stream().forEach(element->{
				element.sendKeys(clicklnk);
			});
			
		});
		
	}

}

