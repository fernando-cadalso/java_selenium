package br.com.alura.leilao;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageObject {

	protected WebDriver nav;

	public PageObject(WebDriver nav) {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
		if (nav == null) {
			this.nav = new ChromeDriver();
		} else {
			this.nav = nav;
		}
		
		this.nav.manage().timeouts()
		.implicitlyWait(3, TimeUnit.SECONDS)
		.pageLoadTimeout(5, TimeUnit.SECONDS);
	}

	public void fechar() {
		nav.close();
	}

}
