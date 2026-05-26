package fw_JavaAutomationSelenium_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Text;

public class ParaBank extends ClaseBase {
    
  

  
    //Declaracion de Elementos Web.
	
    @FindBy(xpath = "//a[@href=\"register.htm\"]") WebElement linkRegistrar;
    @FindBy(id = "customer.firstName") WebElement inputFirstName;
    @FindBy(id = "customer.lastName") WebElement inputLasttName;
    @FindBy(id = "customer.address.street") WebElement inputAdress;
    @FindBy(id = "customer.address.city") WebElement inputCity;
    @FindBy(id = "customer.address.state") WebElement inputState;
    @FindBy(id = "customer.address.zipCode") WebElement inputZipCode;
    @FindBy(id = "customer.phoneNumber") WebElement inputPhoneNumber;
    @FindBy(id = "customer.ssn") WebElement inputSsn;
    @FindBy(id = "customer.username") WebElement inputUserName;
    @FindBy(id = "customer.password") WebElement inputPassWord;
    @FindBy(id = "repeatedPassword") WebElement inputRepeatedPassWord;
    @FindBy(xpath = "//input[@value=\"Register\"]") WebElement btnRegister;
    @FindBy(css = "h1.title") WebElement lblBienvenida;
   
    
    
    // 2. Constructor para inicializar los elementos de la página
    public ParaBank(WebDriver driver) {
    	super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
   
    
    
    //Aqui comenzamos a crear metodos
    
    
   public void escribir_Apellido(String texto) {
	   escribir(inputLasttName, texto);
   }
   public void escribir_Nombre(String texto) {
	   escribir(inputFirstName, texto);
   }
   public void escribir_direccion(String texto) {
	   escribir(inputAdress, texto);
   }
   public void escribir_Ciudad(String texto) {
	   escribir(inputCity, texto);
   }
   public void escribir_Estado(String texto) {
	   escribir(inputState, texto);
   }
   public void escribir_ZipCode(String texto) {
	   escribir(inputZipCode, texto);
   }
   public void escribir_Telefono(String texto) {
	   escribir(inputPhoneNumber, texto);
   }
   public void escribir_Ssn(String texto) {
	   escribir(inputSsn, texto);
   }
   
   public void escribir_Usuario(String texto) {
	   escribir(inputUserName, texto);
   }
   public void escribir_Clave(String texto) {
	   escribir(inputPassWord, texto);
   }
   public void escribir_ClaveNuevamente(String texto) {
	   escribir(inputRepeatedPassWord, texto);
   }
   
   
   
   public boolean validarAcceso(String Texto) {
	 
	   return Buscar_Texto_Dentro_de_Cadena(  lblBienvenida.getText(), Texto);
   }
   
   
   
   public void PressEnter() {
	   try {
	        Actions action = new Actions(driver);
	        action.sendKeys(Keys.ENTER).perform();
	        System.out.println(" Se presiono Enter al navegador.");
	    } catch (Exception e) {
	        System.out.println("❌ Error al intentar presionar Enter de forma global.");
	    }
   }
   
   
    
   public void PressRegister() {
	   clic(btnRegister);
   }
   
   public void PressLinkRegister() {
	   clic(linkRegistrar);
   }

   
   
}