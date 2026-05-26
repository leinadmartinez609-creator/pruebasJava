package ParaBank;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import fw_JavaAutomationSelenium_Pages.CommitQuality;
import fw_JavaAutomationSelenium_Pages.ParaBank;

import org.testng.annotations.*;
import org.testng.annotations.Test;
import Utils.DataProvider;
import Utils.ReportesEvidencia;
import org.testng.Assert;


public class Tc1_Registrar_nuevo_usuario {
	DataProvider le = new DataProvider();
	ReportesEvidencia re = new ReportesEvidencia();
	String rutaDataProvider =  "src/test/resources/DataProvider/ParaBank/Tc1_Registrar_nuevo_usuario.xlsx";
	
	
	//Parametros a utilizar
	String Nombre =le.leerCeldaPorColumna(rutaDataProvider,1, "Nombre");
	String Apellido = le.leerCeldaPorColumna(rutaDataProvider,1, "Apellido");
	String Calle = le.leerCeldaPorColumna(rutaDataProvider,1, "Calle");
	String Ciudad = le.leerCeldaPorColumna(rutaDataProvider,1, "Ciudad");
	String Estado = le.leerCeldaPorColumna(rutaDataProvider,1, "Estado");
	String Telefono = le.leerCeldaPorColumna(rutaDataProvider,1, "Telefono");
	String CP = le.leerCeldaPorColumna(rutaDataProvider,1, "CodigoPostal");
	String Usuario = le.leerCeldaPorColumna(rutaDataProvider,1, "Usuario");
	String Pass = le.leerCeldaPorColumna(rutaDataProvider,1, "Clave");
	String SSN = le.leerCeldaPorColumna(rutaDataProvider,1, "SSN");
	String Nombre_Test_Case = "Tc1_Registrar_nuevo_usuario";
	
	
	String Automatizador = "Axel Martinez";
	WebDriver driver;
	
	
	
	@BeforeClass
	public void BeforeClass() {
		driver = new ChromeDriver();
		driver.get("https://parabank.parasoft.com/"); 
		driver.manage().window().maximize();
		
	}
	
	@BeforeMethod
	public void BeforeMethod() {
		re.iniciarDocumento(driver,Nombre_Test_Case,Automatizador);
	}

	
	@Test	
	public void Tc1_Registrar_nuevo_usuario() throws InterruptedException {
	
		System.out.println(Telefono + CP + SSN);
		ParaBank pb = new ParaBank(driver);		
		
		pb.PressLinkRegister();
		re.AddStep("1.- Presionar 'Register'");
		
		pb.escribir_Nombre(Nombre);
		re.AddStep("2.- Escribir nombre en 'First Name'");
		
		pb.escribir_Apellido(Apellido);
		re.AddStep("3.- Escribir apellido en 'Last Name' ");
		
		pb.escribir_direccion(Calle);
		re.AddStep("4.- Escribir Calle en 'Address'");
		
		pb.escribir_Ciudad(Ciudad);
		re.AddStep("5.- Escribir ciudad en 'City' ");
		
		pb.escribir_Estado(Estado);
		re.AddStep("6.- Escribir Estado en 'State'");
		
		pb.escribir_ZipCode(CP);
		re.AddStep("7.- Escribir codigo Postal en 'ZipCode' ");
		
		pb.escribir_Telefono(Telefono);
		re.AddStep("8.- Escribir Telefono en 'Phone Number'");
		
		pb.escribir_Ssn(SSN);
		re.AddStep("9.- Escribir Numero de Seguro en 'SSN'");
		
		pb.escribir_Usuario(Usuario);
		re.AddStep("10.- Escribir Usuario en 'User'");
		
		pb.escribir_Clave(Pass);
		re.AddStep("11.- Escribir la Clave en 'Password'");
		
		pb.escribir_ClaveNuevamente(Pass);
		re.AddStep("12.- Confirmar Clave en 'Confirm'");
		
		pb.PressRegister();
		re.AddStep("13.- Presionar Boton 'Register'");
	
		assertTrue(pb.validarAcceso(Usuario));
		re.AddStep("14.- Validar que se haya registrado Exitosamente");
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
	
		
		
		
		
	


