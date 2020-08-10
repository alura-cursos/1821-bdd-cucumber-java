package br.com.alura.leilao.acceptance.steps;

import org.junit.Assert;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class LoginSteps {

	private Browser browser;
	private LoginPage loginPage;
	private LeiloesPage leiloesPage;
	
	@Dado("o usuario valido")
	public void o_usuario_valido() {
		browser = new Browser();
		browser.seed();
		loginPage = browser.getLoginPage();
	}

	@Quando("realiza login")
	public void realiza_login() {
		leiloesPage = this.loginPage.realizaLoginComo("fulano", "pass");
	}
	
	@Entao("é redirecionado para a pagina de leiloes")
	public void é_redirecionado_para_a_pagina_de_leiloes() {
		Assert.assertTrue(this.leiloesPage.estaNaPaginaDeLeiloes());
		browser.clean();
	}
	
	@Dado("o usuario invalido")
	public void o_usuario_invalido() {
		browser = new Browser();
		browser.seed();
		loginPage = browser.getLoginPage();
	}
	
	@Quando("tenta se logar")
	public void tenta_se_logar() {
		this.loginPage.realizaLoginComo("fulano", "xpto");
	}
	
	@Entao("continua na página de login")
	public void continua_na_página_de_login() {
		Assert.assertTrue(this.loginPage.estaNaPaginaDeLoginComErro());
		browser.clean();
	}

	
}
