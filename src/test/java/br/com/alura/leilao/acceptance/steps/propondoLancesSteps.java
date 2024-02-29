package br.com.alura.leilao.acceptance.steps;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import org.junit.Assert;

import java.math.BigDecimal;

public class propondoLancesSteps {
    private Lance lance;
    private Leilao leilao;

    @Dado("que tenho um lance válido")
    public void queTenhoUmLanceValido() {
        Usuario usuario = new Usuario("fulano");
        lance = new Lance(usuario, BigDecimal.TEN);
    }

    @Quando("propõe o lance para o produto do leilão")
    public void propoeOLanceParaOProdutoDoLeilao() {
        leilao = new Leilao("Tablet XPTO");
        leilao.propoe(lance);
    }

    @Entao("o lance é aceito")
    public void oLanceEAceito() {
        Assert.assertEquals(1, leilao.getLances().size());
        Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
    }
}
