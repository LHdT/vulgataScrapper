package vulgata;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
				
		driver.findElement(By.xpath("//*[text()='Bible Book List']/..")).click();
		
		
		
		List<WebElement> enlaceslibros = driver.findElements(By.xpath("//*[@class='go911112886']"));
		
		enlaceslibros.stream().forEach(enlace->{
			System.out.println(enlace.getText());
			enlace.click();
			
			List<WebElement> capitulos= driver.findElements(By.xpath("//tr[@class='go441370079']/td/a"));
			capitulos.stream().forEach(capitulo->{
				System.out.println(capitulo.getText());
				capitulo.sendKeys(Keys.chord(Keys.COMMAND,Keys.RETURN));
				ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
				
				driver.switchTo().window(tabs.get(1));
				
				
				
				driver.findElements(By.xpath("//*[@class='passage-text']//p")).stream().forEach(versiculo->{
//					System.out.println(versiculo.getText());
					List<String> copiaVersiculo = Arrays.stream(versiculo.getText().split(" ")).collect(Collectors.toList());
					copiaVersiculo.remove(0);
					String versiculoFormateado = copiaVersiculo.stream().reduce("", (e1,e2)->{
						return e1+" "+e2;
					});
				});
				
				
				
				driver.close();
				
				driver.switchTo().window(tabs.get(0));
				
				
				
				
				
//				
			});
			
			
			
		});

}

}
