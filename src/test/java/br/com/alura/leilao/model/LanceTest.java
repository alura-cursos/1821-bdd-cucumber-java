package br.com.alura.leilao.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class LanceTest {

//    @Test(expected=IllegalArgumentException.class) //JUnit4
	@Test
	public void deveRecusarLancesComValorDeZero() {

		assertThrows(IllegalArgumentException.class, () -> new Lance(new Usuario("John Doe"), BigDecimal.ZERO));
	}

	@Test
	public void deveRecusarLancesComValorNegativo() {

		assertThrows(IllegalArgumentException.class, () -> new Lance(new Usuario("John Doe"), new BigDecimal("-10")));
	}
}
