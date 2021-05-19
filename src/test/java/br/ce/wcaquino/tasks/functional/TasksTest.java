package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {

	public WebDriver acessarAplicacao() throws MalformedURLException {
		WebDriver driver = new ChromeDriver();
		
		driver.navigate().to("http://localhost:8001/tasks/");
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		//clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		//escrever descricao
		driver.findElement(By.id("task")).sendKeys("Teste funcional");
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		//clicar salvar
		driver.findElement(By.id("saveButton")).click();
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Success!", message);
		
		driver.quit();
	}
	
	@Test
	public void naoDeveSalvarSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		//clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
		//clicar salvar
		driver.findElement(By.id("saveButton")).click();
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the task description", message);
		
		driver.quit();
	}
	
	@Test
	public void naoDeveSalvarTaskSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		//clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		//escrever descricao
		driver.findElement(By.id("task")).sendKeys("Teste funcional");
		//clicar salvar
		driver.findElement(By.id("saveButton")).click();
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Fill the due date", message);
		
		driver.quit();
	}
	
	@Test
	public void naoDeveSalvarTaskComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		//clicar em Add Todo
		driver.findElement(By.id("addTodo")).click();
		//escrever descricao
		driver.findElement(By.id("task")).sendKeys("Teste funcional");
		//escrever data
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
		//clicar salvar
		driver.findElement(By.id("saveButton")).click();
		//validar mensagem de sucesso
		String message = driver.findElement(By.id("message")).getText();
		Assert.assertEquals("Due date must not be in past", message);
		
		driver.quit();
	}
}
