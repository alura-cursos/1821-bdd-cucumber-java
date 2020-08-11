package br.com.alura.leilao.acceptance.steps;

import org.junit.Assert;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import br.com.alura.leilao.e2e.pages.NovoLeilaoPage;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class LeilaoSteps {

	private LoginPage loginPage;
	private LeiloesPage leiloesPage;
	private NovoLeilaoPage novoLeilaoPage;
	private Browser browser;

	@Dado("um usuario logado")
	public void um_usuario_logado() {
		this.browser = new Browser();
		browser.seed();
		loginPage = browser.getLoginPage();
		leiloesPage = loginPage.realizaLoginComoFulano();
	}

	@Quando("acessa a pagina de novo leilao")
	public void acessa_a_pagina_de_novo_leilao() {
		novoLeilaoPage = this.leiloesPage.visitaPaginaParaCriarUmNovoLeilao();
	}

	@Quando("prenche o formulario com dados validos")
	public void prenche_o_formulario_com_dados_validos() {
		this.leiloesPage = this.novoLeilaoPage.preencheForm("PC Novo", "1500", "01/11/2020");
	}

	@Entao("volta para a pagina de leiloes")
	public void volta_para_a_pagina_de_leiloes() {
		Assert.assertTrue(this.leiloesPage.estaNaPaginaDeLeiloes());
	}

	@Entao("o novo leilao aparece na tabela")
	public void o_novo_leilao_aparece_na_tabela() {
		this.leiloesPage.existe("PC Novo", "1500", "01/11/2020", "fulano");
		this.browser.clean();
	}
}
