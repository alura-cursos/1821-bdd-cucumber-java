package br.com.alura.leilao.e2e.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class Browser {

	private WebDriver driver;

	public Browser() {
		this.driver = new BrowserFactory().createWebDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	protected WebDriver getDriver() {
		return driver;
	}

	public LoginPage getLoginPage() {
		return new LoginPage(driver);
	}

	public void seed() {
		driver.get("http://localhost:8080/db/seed");
	}

	public void clean() {
		driver.get("http://localhost:8080/db/clean");
		driver.manage().deleteAllCookies();
		driver.close();
	}
}
