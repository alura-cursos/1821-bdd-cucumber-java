package br.com.alura.leilao.e2e.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

	private WebDriver driver;
	
	private static String URL_LOGIN_PAGE = "http://localhost:8080/login";

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LeiloesPage realizaLoginComo(String nome, String senha) {
    	driver.get(URL_LOGIN_PAGE);

        WebElement txtNome = driver.findElement(By.name("username"));
        WebElement txtEmail = driver.findElement(By.name("password"));

        txtNome.sendKeys(nome);
        txtEmail.sendKeys(senha);

        txtNome.submit();
                
        return new LeiloesPage(driver);
    }
    
    public LeiloesPage realizaLoginComoFulano() {
    	return realizaLoginComo("fulano", "pass");
    }

	public boolean estaNaPaginaDeLeiloes() {
		this.esperaCarregarPaginaDeLeiloes();
		return this.driver.getCurrentUrl().endsWith("/leiloes");
	}
	
	public void esperaCarregarPaginaDeLeiloes() {
		WebDriverWait wait = new WebDriverWait(driver,2);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Todos leilões')]")));
	}

	public boolean estaNaPaginaDeLoginComErro() {
		System.out.println(this.driver.getCurrentUrl());
		return this.driver.getCurrentUrl().endsWith("/login") 
				|| this.driver.getCurrentUrl().endsWith("/login?error");
	}

}
