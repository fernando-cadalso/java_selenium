package br.com.alura.leilao;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LoginTest {

	/*
	 * Atributo para abstrair a API do Selenium.
	 */
	private LoginPage paginaDeLogin;

	@BeforeEach
	public void beforeEach() {
		/*
		 * Todo código da API do Selenium foi encapsulado na classe LoginPage
		 */
		this.paginaDeLogin = new LoginPage();
	}

	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}

	@Test
	public void deveAceitarUsuarioValido() {
		this.paginaDeLogin.preencheFormulario("fulano", "pass");
		this.paginaDeLogin.efetuarLogin();
		/*
		 * Inićio do teste. Verificar se a URL foi alterada após o login
		 */
		Assert.assertFalse(this.paginaDeLogin.isPaginaDeLogin());
		/*
		 * Verifica se o nome do usuário conectado aparece na sua sessão.
		 */
		Assert.assertEquals("fulano", this.paginaDeLogin.getUsuarioConectado());
	}

	@Test
	public void naoAceitaLoginComDadosInvalidos() {
		this.paginaDeLogin.preencheFormulario("invalido", "1234");
		this.paginaDeLogin.efetuarLogin();

		/*
		 * Inićio do teste. Verificar se a URL foi alterada após o login
		 */
		Assert.assertTrue(this.paginaDeLogin.isPaginaDeLoginComErro());
		/*
		 * Teste para verificar se a mensagem de erro é exibida.
		 */
		Assert.assertTrue(this.paginaDeLogin.contemTextoDaPagina("Usuário e senha inválidos."));
		/*
		 * Verifica se o nome do usuário conectado aparece na sua sessão.
		 */
		Assert.assertNull(this.paginaDeLogin.getUsuarioConectado());
	}

	@Test
	public void naoAcessaURLRestrita() {
		/*
		 * Tenta acessar um recurso restrito sem autorização.
		 */
		this.paginaDeLogin.navegarParaPaginaDeLances();
		/*
		 * Verifica se foi redirecionado para página de login.
		 */
		Assert.assertTrue(this.paginaDeLogin.isPaginaDeLogin());
		/*
		 * Verifica se não aparece um texto da página restrita.
		 */
		Assert.assertFalse(this.paginaDeLogin.contemTextoDaPagina("Dados do Leilão"));
	}
}
