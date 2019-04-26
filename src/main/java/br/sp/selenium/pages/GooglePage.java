package br.sp.selenium.pages;

import static br.sp.selenium.core.DriverFactory.getDriver;

import org.openqa.selenium.By;

public class GooglePage {
	
	public void acessaPaginaMaximizada() {
		getDriver().manage().window().maximize();
		getDriver().get("https://google.com.br");
	}
	
	public void setPesquisar (String pesquisa) {
		getDriver().findElement(By.name("q")).sendKeys(pesquisa);
	}
	
	public void clickPesquisaGoogle () {
		getDriver().findElement(By.id("btnK")).click();
	}
	
	public void clickEstouComSorte () {
		getDriver().findElement(By.id("btnI")).click();
	}

}
