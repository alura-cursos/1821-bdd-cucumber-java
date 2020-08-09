package br.com.alura.leilao.integration.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

public class TestBase {

	public List<Usuario> getUsuariosFulanoEBeltrano() {
		Usuario fulano = getUsuarioFulano();
		
		Usuario beltrano = new Usuario("beltrano", "beltrano@gmail.com", "pass");
		beltrano.setRole("ROLE_USER");
		beltrano.activa();
		
		return Arrays.asList(fulano, beltrano);
	}

	public Usuario getUsuarioFulano() {
		Usuario fulano = new Usuario("Fulano", "fulano@gmail.com", "pass");
		fulano.setRole("ROLE_USER");
		fulano.activa();
		return fulano;
	}
	
	
	public Usuario getUsuarioCigano() {
		Usuario fulano = new Usuario("Cigano", "cigano@gmail.com", "pass");
		fulano.setRole("ROLE_USER");
		fulano.activa();
		return fulano;
	}
	
	public Leilao geraLeilao(String nome, BigDecimal valor, LocalDate data, Usuario usuario) {
		return new Leilao(nome, valor, usuario);
	}
	
	public Leilao getLeilaoTablet() {
		Leilao leilao = new Leilao("Tablet",new BigDecimal("5.0"), getUsuarioFulano());
		return leilao;
	}
	
	public Leilao getLeilaoComputador() {
		Leilao leilao = new Leilao("Computador",new BigDecimal("530.0"), getUsuarioFulano());
		return leilao;
	}
	
	public List<Leilao> getLeiloesComputadorETablet() {
		return Arrays.asList(getLeilaoTablet(), getLeilaoComputador());
	}
}
