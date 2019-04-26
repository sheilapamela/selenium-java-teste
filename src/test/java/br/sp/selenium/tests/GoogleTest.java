package br.sp.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static br.sp.selenium.core.DriverFactory.getDriver;
import static br.sp.selenium.core.DriverFactory.killDriver;

import br.sp.selenium.core.Execution;
import br.sp.selenium.pages.GooglePage;

public class GoogleTest {
	
private GooglePage pg;
	
	@Before
	public void inicializa() {
		getDriver();
		pg = new GooglePage();
	}
	
	@Test
	public void devePreencherPesquisaGoogle() {
		pg.acessaPaginaMaximizada();
		pg.setPesquisar("GitHub");
	}
	
	@Test
	public void deveUsarBotaoPesquisaGoogle() {
		pg.acessaPaginaMaximizada();
		pg.setPesquisar("GitHub");
		pg.clickPesquisaGoogle();
	}
	
	@Test
	public void deveUsarBotaoEstouComSorte() {
		pg.acessaPaginaMaximizada();
		pg.setPesquisar("GitHub");
		pg.clickEstouComSorte();
	}
	
	@After
	public void finaliza(){
		if(Execution.CLOSE_BROWSER) {
			killDriver();
		}
	}

}
