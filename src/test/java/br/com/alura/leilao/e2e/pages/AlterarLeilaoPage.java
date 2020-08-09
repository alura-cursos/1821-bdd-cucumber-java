package br.com.alura.leilao.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AlterarLeilaoPage {

	private WebDriver driver;

	public AlterarLeilaoPage(WebDriver driver) {
		this.driver = driver;
	}

	public void preencheForm(String nome, String valor, String data) {

		WebElement txtNome = driver.findElement(By.name("nome"));
        WebElement txtValor = driver.findElement(By.name("valorInicial"));
        WebElement txtData = driver.findElement(By.name("dataAbertura"));

        txtNome.clear();
        txtNome.sendKeys(nome);
        txtValor.clear();
        txtValor.sendKeys(valor);
        txtData.clear();
        txtData.sendKeys(data);

        txtNome.submit();
	}

}
