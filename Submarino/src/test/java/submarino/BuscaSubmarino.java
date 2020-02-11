package submarino;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.TakesScreenshot;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class BuscaSubmarino {
	String url;
	WebDriver driver;
String nomePasta = new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime());
	
	//Funcoes e metodos de apoio
	// Tirar print da tela

	public void Print(String nomePrint) throws IOException {
		// Vamos fazer uma especializacao do Selenium trazendo um acessorio
		File foto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(foto, new File(
				"C:\\Users\\Computador\\FTS125-workspace\\Submarino\\target\\evidencias\\" + nomePasta + "\\" + nomePrint + ".png"));

	}

	@Before
	public void Iniciar() {
		url = "https://www.submarino.com.br/";
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Computador\\FTS125-workspace\\Submarino\\drivers\\chrome\\79\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(60000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
	}

	@After
	public void Finalizar() {
		driver.quit();
	}

	@Given("^que acesso o site do Extra$")
	public void que_acesso_o_site_do_Extra() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		driver.get(url);

		Print("Passo 1 - Acessei o site do Submarino");

		System.out.println("Passo 1 - Acessei o site do Extra");
	}

	@When("^preencho o termo \"([^\"]*)\" e clico na lupa$")
	public void preencho_o_termo_e_clico_na_lupa(String termo) throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		driver.findElement(By.id("h_search-input")).clear();
		driver.findElement(By.id("h_search-input")).sendKeys(termo);
		Print("Passo 2 - Preenchi o termode busca");
		driver.findElement(By.id("h_search-btn")).click();

		System.out.println("Passo 2 - Preenchi o termo e cliquei na lupa");
	}

	@Then("^exibe a lista de produtos$")
	public void exibe_a_lista_de_produtos() throws Throwable {
		// Write code here that turns the phrase above into concrete actions

		Thread.sleep(3000);
		assertEquals("Smartphone com Ofertas Incríveis no Submarino.com", driver.getTitle());
		// assertEquals("smartphone",
		// driver.findElement(By.cssSelector("ul.neemu-breadcrumb-container")).getText());

		Print("Passo 3.p - Exibiu a lista de produtos");

		System.out.println("Passo 3 - Validei o texto da aba e o que o site pesquisou");
	}
	
	@Then("^exibe a mensagem de produto nao encontrado$")
	public void exibe_a_mensagem_de_produto_nao_encontrado() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals("Qeafbfjnqonrf com Ofertas Incríveis no Submarino.com", driver.getTitle());
		assertTrue(driver.findElement(By.cssSelector("span.TextUI-sc-12tokcy-0.CIZtP")).getText().contains("Não encontramos nenhum resultado para"));
		//span[class'TextUI-sc-12tokcy-0 CIZtP']
		//span.TextUI-sc-12tokcy-0.CIZtP
		Print("Passo 3.n - Nao Exibiu a lista de produtos");
	    
	}

}
