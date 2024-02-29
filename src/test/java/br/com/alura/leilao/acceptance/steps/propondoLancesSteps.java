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
    private Lance lance, lance10, lance15;
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

    @Dado("que tenho vários lances válidos")
    public void queTenhoVariosLancesValidos() {
        Usuario usuario1 = new Usuario("fulano");
        lance10 = new Lance(usuario1, BigDecimal.TEN);
        Usuario usuario2 = new Usuario("beltrano");
        lance15 = new Lance(usuario2, new BigDecimal("15.0"));
        leilao = new Leilao("Tablet XPTO");
    }

    @Quando("propõe vários lances válidos")
    public void propoeVariosLancesValidos() {
        leilao.propoe(lance10);
        leilao.propoe(lance15);
    }

    @Entao("os lances sao aceitos")
    public void osLancesSaoAceitos() {

        Assert.assertEquals(2, leilao.getLances().size());
        Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
        Assert.assertEquals(new BigDecimal("15.0"), leilao.getLances().get(1).getValor());
    }

    @Dado("proponho um lance de {double} reais do usuario {string}")
    public void proponhoUmLanceDeReaisDoUsuario(Double valor, String nomeUsuario) {
    }
}
