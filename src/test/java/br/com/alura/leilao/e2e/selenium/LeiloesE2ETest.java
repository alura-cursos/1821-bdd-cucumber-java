package br.com.alura.leilao.e2e.selenium;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.e2e.pages.AlterarLeilaoPage;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import br.com.alura.leilao.e2e.pages.NovoLeilaoPage;

public class LeiloesE2ETest extends E2ETestBase{
	
	private LeiloesPage leiloesPage;

	@BeforeEach
	void setup() {
		LoginPage loginPage = new LoginPage(getDriver());
		leiloesPage = loginPage.realizaLoginComoFulano();		
	}

	@Test
	public void deveCadastrarUmLeilao() {
		NovoLeilaoPage novoLeilaoPage = leiloesPage.visitaPaginaParaCriarUmNovoLeilao();
		String nome = "Commodore Amiga";
		String valor = "899.90";
		String data = "04/08/2020";
		
		novoLeilaoPage.preencheForm(nome, valor, data);
		
		leiloesPage.esperaCarregar();

		assertTrue(leiloesPage.existe(nome, valor, data));
	}
	
	
	@Test
	public void deveEditarUmLeilao() {
		AlterarLeilaoPage novoLeilaoPage = leiloesPage.visitaPaginaParaAltearLeilao();
		String nome = "Commodore Amiga";
		String valor = "899.90";
		String data = "04/08/2020";
		
		novoLeilaoPage.preencheForm(nome, valor, data);
		
		leiloesPage.esperaCarregar();

		assertTrue(leiloesPage.existe(nome, valor, data));
	}
	
}
