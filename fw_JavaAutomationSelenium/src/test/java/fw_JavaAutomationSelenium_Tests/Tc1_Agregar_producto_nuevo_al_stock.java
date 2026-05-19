package fw_JavaAutomationSelenium_Tests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import fw_JavaAutomationSelenium_Pages.CommitQuality;

import org.testng.annotations.*;
import org.testng.annotations.Test;

import Utils.DataProvider;
import Utils.ReportesEvidencia;

import org.testng.Assert;


public class Tc1_Agregar_producto_nuevo_al_stock {
	DataProvider le = new DataProvider();
	ReportesEvidencia re = new ReportesEvidencia();
	String rutaDataProvider =  "src/test/resources/DataProvider/CommitQuality/Tc1_CommitQuality.xlsx";
	String Jabon =le.leerCeldaPorColumna(rutaDataProvider,1, "Nombre_Producto");
	String Precio = le.leerCeldaPorColumna(rutaDataProvider,1, "Precio");
	String Nombre_Test_Case = "TC1_Agregar_producto_nuevo_al_stock";
	
	WebDriver driver;
	
	
	
	@BeforeClass
	public void BeforeClass() {
		driver = new ChromeDriver();
		driver.get("https://commitquality.com");
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public void BeforeMethod() {
		re.iniciarDocumento(driver);
	}

	//Este Test prueba TESTNG en una pagina de practica para pruebas QA
	@Test	
	public void Tc1_Agregar_producto_nuevo_al_stock() throws InterruptedException {
	
		CommitQuality cq = new CommitQuality(driver);		
		
		cq.PressAddProduct();
		re.AddStep("1.- Presionar Add Product");
		
		
		cq.escribir_Name(Jabon);
		re.AddStep("2.- llenar 'Name'");
		
		
		cq.escribir_Price(Precio);
		re.AddStep("3.- Llenar 'Price'");
		
		
		
		cq.escribirFechaActual();
		re.AddStep("4.- llenar campo fecha con la fecha actual");
		
		
		
		cq.PressSubmit();
		re.AddStep("5.- Presionar Submit");
		
		
		
		assertTrue(cq.imprimirGrid(Jabon), "No se pudo encontrar el producto en el Grid");
		re.AddStep("6.- validar que aparezca el producto ingresado en el grid principal");
	}

	
	
	@AfterMethod
	public void AfterMethod() {
		re.guardarDocumento(Nombre_Test_Case);
	}
	
	@AfterClass
	public void AfterClass() {
		driver.close();
	}
	
	
	}
	
		
		
		
		
	


