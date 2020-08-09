package br.com.alura.leilao.acceptance.steps;

import java.math.BigDecimal;

import org.junit.Assert;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class PropondoLanceSteps {

	private Lance lance;
	private Leilao leilao;
	
	@Dado("um lance valido")
	public void dado_um_lance_valido() {
		Usuario usuario = new Usuario("fulano");
		lance = new Lance(usuario , BigDecimal.TEN);
	}

	@Quando("propoe o lance")
	public void quando_propoe_o_lance() {
		leilao = new Leilao("Tablet XPTO");
		leilao.propoe(lance);
	}
	
	@Entao("o lance eh aceito")
	public void entao_o_lance_eh_aceito() {
		Assert.assertEquals(1, leilao.getLances().size());
		Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
	}
}
