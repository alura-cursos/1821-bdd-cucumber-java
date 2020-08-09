package br.com.alura.leilao.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CriadorDeLeilao {

	private String nome;
	private List<Lance> lances = new ArrayList<>();
	public CriadorDeLeilao para(String nome) {
		this.nome = nome;
		return this;
	}

	public CriadorDeLeilao lance(Usuario usuario, BigDecimal valor) {
		Lance lance = new Lance(usuario, valor);
		this.lances.add(lance);
		return this;
	}
	
	public Leilao constroi() {
		Leilao leilao = new Leilao(nome);
		this.lances.forEach( lance -> leilao.propoe(lance));
		return leilao;
	}

}
