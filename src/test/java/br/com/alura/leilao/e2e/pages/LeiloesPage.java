package br.com.alura.leilao.e2e.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeiloesPage {

	private WebDriver driver;

	private static String PAGE_URL = "http://localhost:8080/leiloes";
	
	public LeiloesPage(WebDriver driver) {
		this.driver = driver;
	}

	public void visita() {
		driver.get(PAGE_URL);
	}

	public boolean existe(String nomeProduto, String valor, String usuario) {
		return driver.getCurrentUrl().endsWith("/leiloes") && driver.getPageSource().contains(nomeProduto) && 
				driver.getPageSource().contains(valor);
	}

	public NovoLeilaoPage visitaPaginaParaCriarUmNovoLeilao() {
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novo_leilao_link")));
		
		WebElement href = driver.findElement(By.id("novo_leilao_link"));
		wait.until(ExpectedConditions.elementToBeClickable(href));
		href.click();

		return new NovoLeilaoPage(driver);
	}

	public DetalhesDoLeilaoPage visitaLeilaoPaginaParaDarLance() {
		driver.findElement(By.linkText("dar lance")).click();
		return new DetalhesDoLeilaoPage(driver);
	}

	public AlterarLeilaoPage visitaPaginaParaAltearLeilao() {
		driver.findElement(By.linkText("editar")).click();
		return new AlterarLeilaoPage(driver);
	}

	public DetalhesDoLeilaoPage visitaPaginaDoLeilaoDo(String donoDoLeilao) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		String xpath = "//table[@class='table table-hover']/tbody/tr/td[contains(text(),'" +
						donoDoLeilao+ "')]/following-sibling::td/a";

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
		
		WebElement href = driver.findElement(
				By.xpath(xpath));
		
		wait.until(ExpectedConditions.elementToBeClickable(href));

		href.click();
		
		return new DetalhesDoLeilaoPage(driver);
	}

	public void esperaCarregar() {
		WebDriverWait wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Todos leilões')]")));
	}

	public boolean naoPodeDarLanceNoLeilaoCriado(String donoDoLeilao) {
		WebElement href = driver.findElement(
				By.xpath("//table[@class='table table-hover']/tbody/tr/td[contains(text(),'" +donoDoLeilao+ "')]/following-sibling::td/a"));
		return href.getText().contains("editar");
	}
}
