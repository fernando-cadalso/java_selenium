package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;

public class LoginPage extends PageObject {
	private static final String URL_LOGIN = "http://localhost:8080/login";

	public LoginPage() {
		/*
		 * Inicializa as configurações iniciais para o teste
		 */
		super(null);
		nav.navigate().to(URL_LOGIN);
	}

	public void preencheFormulario(String username, String password) {
		nav.findElement(By.id("username")).sendKeys(username);
		nav.findElement(By.id("password")).sendKeys(password);
	}

	public LeiloesPage efetuarLogin() {
		nav.findElement(By.id("login-form")).submit();
		return new LeiloesPage(nav);

	}

	public boolean isPaginaDeLogin() {
		return nav.getCurrentUrl().equals(URL_LOGIN);
	}

	public String getUsuarioConectado() {
		try {
			return nav.findElement(By.id("usuarioLogado")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}

	}

	public boolean contemTextoDaPagina(String texto) {
		return nav.getPageSource().contains(texto);
	}

	public void navegarParaPaginaDeLances() {
		nav.navigate().to("http://localhost:8080/leiloes/2");		
	}

	public boolean isPaginaDeLoginComErro() {
		return nav.getCurrentUrl().equals(URL_LOGIN + "?error");
	}

}