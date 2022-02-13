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

import vulgata.model.Biblia;
import vulgata.model.Capitulo;
import vulgata.model.Libro;
import com.fasterxml.jackson.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.openqa.selenium.WebElement;

public class Scraper {

	private static void navegaListaLibros(WebDriver driver) {
		driver.get("https://www.biblegateway.com/");
//		*[contains(@aria-label,'Accept Coo')]
		driver.findElement(By.xpath("//*[contains(@aria-label,'Accept Coo')]")).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.xpath("//*[text()='Consent']/..")).click();

		driver.findElement(By.xpath("//*[contains(@class,'search-dropdown-display')]")).click();

		driver.findElement(By.xpath("//span[@aria-label='All']")).click();

		driver.findElement(By.xpath("//li[contains(text(),'Vulgata')]")).click();

		driver.findElement(By.xpath("//*[text()='Bible Book List']/..")).click();
	}

	private static WebDriver configuraDriver() {
		System.setProperty("webdriver.chrome.driver", "/Users/luishidalgodetena/Desktop/chromedriver");
		return new ChromeDriver();
	}

	private static String getJson(Biblia biblia) {
		ObjectMapper mapper = new ObjectMapper();
		// Converting the Object to JSONString
		try {
			return mapper.writeValueAsString(biblia);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) {

		WebDriver driver = configuraDriver();
		Biblia biblia = new Biblia();
		biblia.setTitulo("Vulgata");
		navegaListaLibros(driver);

		List<WebElement> enlaceslibros = driver.findElements(By.xpath("//*[@class='go911112886']"));

		enlaceslibros.stream().forEach(enlace -> {
			Libro libro = new Libro();
			libro.setTitulo(enlace.getText());
			biblia.getCapitulos().add(libro);
			enlace.click();

			List<WebElement> capitulos = driver.findElements(
					By.xpath("//tr[@class='go441370079']/td/a[string-length(normalize-space(text()))>3]"));
			capitulos.stream().forEach(capitulo -> {
				Capitulo cap = new Capitulo();
				libro.getCapitulos().add(cap);

				String xpath = "//a[contains(text(),'" + capitulo.getText().toString()
						+ "')]/../..//a[string-length()<4]";

				String numeroCapitulo = driver.findElement(By.xpath(xpath)).getText();
				cap.setNumero(Integer.parseInt(numeroCapitulo));
				cap.setTitulo(capitulo.getText());

				capitulo.sendKeys(Keys.chord(Keys.COMMAND, Keys.RETURN));
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

				driver.switchTo().window(tabs.get(1));

				driver.findElements(By.xpath("//*[@class='passage-text']//p")).stream().forEach(versiculo -> {


					List<String> copiaVersiculo = Arrays.stream(versiculo.getText().split(" "))
							.collect(Collectors.toList());
					copiaVersiculo.remove(0);
					String versiculoFormateado = copiaVersiculo.stream().reduce("", (e1, e2) -> {
						return e1 + " " + e2;
					});
					cap.getVersiculos().add(versiculoFormateado);
				});

				driver.close();

				driver.switchTo().window(tabs.get(0));

//				
			});

		});

		System.out.println(getJson(biblia));

	}

}
