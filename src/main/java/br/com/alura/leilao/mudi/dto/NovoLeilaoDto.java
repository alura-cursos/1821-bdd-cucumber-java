package br.com.alura.leilao.mudi.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.alura.leilao.model.Leilao;

public class NovoLeilaoDto {
	
	private static DateTimeFormatter ofPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Long id;

	@NotNull
	@NotBlank
	@Size(min = 3, message = "minimo 3 caracteres")
	private String nome;

	@NotNull(message = "deve ser um valor maior de 0.1")
	@DecimalMin(value = "0.1", message = "deve ser um valor maior de 0.1")
	private BigDecimal valorInicial;

	@NotNull(message = "deve ser uma data no formato dd/MM/yyyy")
	@Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "deve ser uma data no formato dd/MM/yyyy")
	private String dataAbertura;

	public NovoLeilaoDto(Leilao leilao) {
		this.id = leilao.getId();
		this.nome = leilao.getNome();
		this.valorInicial = leilao.getValorInicial();
		this.dataAbertura = leilao.getDataAbertura().format(ofPattern);
	}

	public NovoLeilaoDto() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public String getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(String dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Leilao toLeilao() {
		LocalDate date = LocalDate.parse(this.dataAbertura, ofPattern);
		Leilao leilao = new Leilao(nome, valorInicial, date);
		leilao.setId(id);
		return leilao;
	}
}
