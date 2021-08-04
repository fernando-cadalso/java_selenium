package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.PageObject;

public class CadastrarLeilaoPage extends PageObject {
	
	private static final String URL_CADASTRAR_LEILAO = "http://localhost:8080/leiloes/new";

	public CadastrarLeilaoPage(WebDriver nav) {
		/*
		 * Inicializa as configurações iniciais para o teste e recebe o navegador do
		 * contexto.
		 */
		super(nav);
	}

	public LeiloesPage cadastrarLeilao(String nome, String valorInicial, String dataAbertura) {
		this.nav.findElement(By.id("nome")).sendKeys(nome);
		this.nav.findElement(By.id("valorInicial")).sendKeys(valorInicial);
		this.nav.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
		this.nav.findElement(By.id("button-submit")).submit();

		return new LeiloesPage(nav);

	}

	public boolean isPaginaAtual() {
		String paginaAtual = this.nav.getCurrentUrl();

		return paginaAtual.equals(URL_CADASTRAR_LEILAO);
	}

	public boolean isMensagensDeValidcao() {
		String pageSource = this.nav.getPageSource();
		
		return pageSource.contains("minimo 3 caracteres")
				&& pageSource.contains("não deve estar em branco")
				&& pageSource.contains("deve ser um valor maior de 0.1")
				&& pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
	}
}