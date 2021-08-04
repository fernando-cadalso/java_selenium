package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import br.com.alura.leilao.PageObject;

public class LeiloesPage extends PageObject {
	
	private static final String URL_CADASTRAR_LEILAO = "http://localhost:8080/leiloes/new";

	public LeiloesPage(WebDriver nav) {
		/*
		 * Inicializa as configurações iniciais para o teste e recebe o
		 * navegador do contexto.
		 */
		super(nav);
	}

	public CadastrarLeilaoPage carregarFormulario() {

		this.nav.navigate().to(URL_CADASTRAR_LEILAO);
		
		return new CadastrarLeilaoPage(this.nav);
	}

	public boolean isLeilaoCadastrado(String nome, String valor, String data) {

		WebElement linhaDaTablea = this.nav.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
		WebElement colunaNome = linhaDaTablea.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaDataAbertura = linhaDaTablea.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValorInicial = linhaDaTablea.findElement(By.cssSelector("td:nth-child(3)"));
		
		return colunaNome.getText().equals(nome) 
				&& colunaDataAbertura.getText().equals(data) 
				&& colunaValorInicial.getText().equals(valor);
	}
}