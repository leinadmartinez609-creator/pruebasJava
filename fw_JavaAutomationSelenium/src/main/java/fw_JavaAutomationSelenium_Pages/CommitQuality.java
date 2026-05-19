package fw_JavaAutomationSelenium_Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Text;

public class CommitQuality extends ClaseBase {
    
  

    // 1. Localizamos el cuadro de búsqueda por su nombre (name="q")
    
 //   WebElement addP = driver.findElement(By.cssSelector("[data-testid='navbar-addproduct']"));
    
    @FindBy(name = "name") WebElement barraName;
    @FindBy(name = "price") WebElement barraPrice;
    @FindBy(name = "dateStocked") WebElement barraDate;
    @FindBy( css ="[data-testid='navbar-addproduct']") WebElement AddProduct;
    @FindBy( xpath = "//button[@type=\"submit\"]") WebElement btnSubmit;
    @FindBy(className  = "product-list-table") WebElement TablaProductos;
   

    // 2. Constructor para inicializar los elementos de la página
    public CommitQuality(WebDriver driver) {
    	super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
   
    
    
    //Aqui comenzamos a crear metodos
    
    
   public void escribir_Name(String texto) {
	   escribir(barraName, texto);
   }
   
   public void escribir_Price(String texto) {
	   escribir(barraPrice, texto);
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
   
   public void PressAddProduct() throws InterruptedException {
	   clic(AddProduct);
   }
   
   
   public void escribirFechaActual() {
	   System.out.println(fechaHoy());
	   escribir(barraDate, fechaHoy());
   }
    
   public void PressSubmit() {
	   clic(btnSubmit);
   }
   
  
   public boolean imprimirGrid(String texto) {
	   return Extraer_informacion_del_Grid(TablaProductos, texto);
   }
   
   
}