package br.com.alura.leilao;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HelloWorldSelenuim {

	@Test
	public void hello() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
		WebDriver nav = new ChromeDriver();
//		System.setProperty("webdriver.gecko.driver", "driver/geckodriver");
//		WebDriver nav = new FirefoxDriver();
		nav.navigate().to("http://localhost:8080/leiloes");
		nav.close();
	}

}
