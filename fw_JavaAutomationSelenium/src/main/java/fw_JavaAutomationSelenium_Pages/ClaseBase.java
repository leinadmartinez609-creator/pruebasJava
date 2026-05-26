package fw_JavaAutomationSelenium_Pages;

import java.sql.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ClaseBase {
	
	
    protected WebDriver driver;
    protected LocalDate fecha ;
    protected String fechaFormateada;
    
    
    public ClaseBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Método genérico para escribir de forma segura
    public void escribir(WebElement elemento, String texto) {
        try {
            if (elemento.isDisplayed()) {
                elemento.clear();
                elemento.sendKeys(texto);
                System.out.println(" Acción exitosa sobre el elemento.");
            }
        } catch (Exception e) {
            System.out.println("❌ Error: No se pudo interactuar con el elemento.");
            // Aquí podrías agregar lógica para tomar una captura de pantalla
        }
    }
    
    
    /**@author Leinad Mtz
     * @return regresa la fecha de hoy en formato DDMMAAA
     * 
     */
    public String fechaHoy() {
    	fecha = LocalDate.now();
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("ddMMyyyy");
    	String fechaFormateada = fecha.format(formato);
    	return fechaFormateada;
    }
    
    
    public void clic(WebElement elemento) {
    	elemento.click();
    }
    
    
    public boolean Buscar_Texto_Dentro_de_Cadena(String Cadena, String Texto) {
    	
    	if(Cadena.contains(Texto)) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    
   public void goTO(String url) {
		WebDriver driver;
		driver = new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		
   }
   
   public boolean Extraer_informacion_del_Grid(WebElement Grid, String Cadena) {
	   List<WebElement> filas = Grid.findElements(By.tagName("tr"));

	    // 3. Recorrer las filas
	    for (WebElement fila : filas) {
	        // 4. Por cada fila, obtener sus columnas/celdas (td)
	        List<WebElement> celdas = fila.findElements(By.tagName("td"));

	        for (WebElement celda : celdas) {
	            // 5. Extraer el texto de la celda
	            String texto = celda.getText();
	            if(texto.equals(Cadena)) {
	            	return true;
	            }
	            System.out.print(texto + " | ");
	        }
	        System.out.println(); // Salto de línea al terminar una fila
	    }
	    return false;
   }
    
}