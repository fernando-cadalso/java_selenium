package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {

	private LeiloesPage paginaDeLeiloes;
	private CadastrarLeilaoPage paginaDeCadastro;

	@BeforeEach
	public void beforeEach() {
		LoginPage paginaDeLogin = new LoginPage();
		paginaDeLogin.preencheFormulario("fulano", "pass");
		this.paginaDeLeiloes =  paginaDeLogin.efetuarLogin();
		this.paginaDeCadastro = this.paginaDeLeiloes.carregarFormulario();
	}
	
	@AfterEach
	public void afterEach() {
		this.paginaDeLeiloes.fechar();
	}
	
	@Test
	public void deveriaCadastrarLeilao() {
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String valor = "500.00";
		String nome = "Leilão do dia " + hoje;
		
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(nome, valor, hoje);
		/*
		 * Verifica se o leilão foi cadastrado com os valores informados.
		 */
		Assert.assertTrue(this.paginaDeLeiloes.isLeilaoCadastrado(nome, valor, hoje));
	}
	
	@Test
	public void deveriaValidarCadastroDeLeilao() {
		/*
		 * Não foi enviado null nos parâmetros pq o método sendKeys()
		 * não aceita null no seu parâmetro.
		 */
		paginaDeCadastro.cadastrarLeilao("", "", "");
		
		/*
		 * Verifica se o navegador está na página de cadastro.
		 */
		Assert.assertFalse(paginaDeCadastro.isPaginaAtual());
		/*
		 * Verifica se as mensagens de erro de preenchimento aparecem.
		 */
		Assert.assertTrue(paginaDeCadastro.isMensagensDeValidcao());
		
	}

}