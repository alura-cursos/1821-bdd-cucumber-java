package br.com.alura.leilao.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Leilao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotBlank
	private String nome;

	@NotNull
	@DecimalMin(value = "0.1")
	private BigDecimal valorInicial;

	@OneToOne
	@JoinColumn(nullable = false)
	private Usuario usuario;

	@NotNull
	private LocalDate dataAbertura;

	@OneToMany(mappedBy = "leilao")
	private List<Lance> lances = new ArrayList<>();

	@Deprecated
	public Leilao() {
	}

	public Leilao(@NotNull @NotBlank String nome) {
		this.nome = nome;
	}
	
	public Leilao(@NotBlank String nome, @NotNull @DecimalMin("0.1") BigDecimal valorInicial, @NotNull LocalDate dataAbertura) {
		this.nome = nome;
		this.valorInicial = valorInicial;
		this.dataAbertura = dataAbertura;
	}
	

	public Leilao(@NotNull @NotBlank String nome, @NotNull @DecimalMin("0.1") BigDecimal valorInicial,
			@NotNull Usuario usuario) {
		this.nome = nome;
		this.valorInicial = valorInicial;
		this.usuario = usuario;
		this.dataAbertura = LocalDate.now();
	}

	public Leilao(@NotNull @NotBlank String nome, @NotNull @DecimalMin("0.1") BigDecimal valorInicial,
			@NotNull LocalDate data, @NotNull Usuario usuario) {
		this.nome = nome;
		this.valorInicial = valorInicial;
		this.usuario = usuario;
		this.dataAbertura = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public Date getDataAberturaDate() {
		return java.util.Date.from(this.dataAbertura.atStartOfDay()
			      .atZone(ZoneId.systemDefault())
			      .toInstant());
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setLances(List<Lance> lances) {
		this.lances = lances;
	}

	public boolean propoe(Lance lanceAtual) {
		
		if (this.estaSemLances() || ehUmLanceValido(lanceAtual)) {
			adicionarLance(lanceAtual);
			return true;
		}
		return false;
	}

	private void adicionarLance(Lance lance) {
		lances.add(lance);
		lance.setLeilao(this);
	}

	private boolean ehUmLanceValido(Lance lance) {
		return valorEhMaior(lance, ultimoLanceDado()) && 
				oUltimoUsuarioNaoEhOMesmoDo(lance) && 
				totalDeLancesDoUsuarioEhMenorIgual5(lance.getUsuario());
	}

	private boolean valorEhMaior(Lance lance, Lance ultimoLanceDado) {
		return lance.getValor().compareTo(ultimoLanceDado.getValor()) > 0;
	}


	private boolean totalDeLancesDoUsuarioEhMenorIgual5(Usuario usuario) {
		int totalDeLances = qtdDeLancesDo(usuario);
		return totalDeLances < 5;
	}

	private boolean oUltimoUsuarioNaoEhOMesmoDo(Lance lance) {
		Usuario ultimoUsuarioQueDeuLance = ultimoLanceDado().getUsuario();
		return !ultimoUsuarioQueDeuLance.equals(lance.getUsuario());
	}

	private int qtdDeLancesDo(Usuario usuario) {
		int total = 0;
		for (Lance l : lances) {
			if (l.getUsuario().equals(usuario))
				total++;
		}
		return total;
	}
	
	private boolean estaSemLances() {
		return this.lances.isEmpty();
	}

	private Lance ultimoLanceDado() {
		return lances.get(lances.size() - 1);
	}

	public List<Lance> getLances() {
		return Collections.unmodifiableList(lances);
	}

	public boolean temLances() {
		return !this.lances.isEmpty();
	}
}
