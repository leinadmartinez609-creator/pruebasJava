package Utils;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.WebDriver;

public class ReportesEvidencia {

    // 1. Variables globales para mantener el estado durante la prueba
    private static XWPFDocument documento;
    private static WebDriver driver;

    // 2. Método para iniciar el registro en las anotaciones de TestNG
    public static void iniciarDocumento(WebDriver driverActivo) {
        documento = new XWPFDocument();
        driver = driverActivo;
        //System.out.println("¡Documento de evidencias inicializado! ");
    }
    
    public static void AddStep(String descripcion) {
        try {
            // 1. Escribimos la descripción en el Word
            XWPFParagraph parrafo = documento.createParagraph();
            XWPFRun corrida = parrafo.createRun();
            corrida.setText(descripcion);
            corrida.setBold(true);
            corrida.setFontSize(12);
            corrida.addCarriageReturn();

            // 2. Tomamos la captura en memoria (bytes) usando Selenium 📸
            byte[] capturaBytes = ((org.openqa.selenium.TakesScreenshot) driver)
                                    .getScreenshotAs(org.openqa.selenium.OutputType.BYTES);
            
            java.io.ByteArrayInputStream bis = new java.io.ByteArrayInputStream(capturaBytes);

            // 3. Insertamos la imagen al Word con Apache POI 🖼️
            // Especificamos el flujo, el tipo de imagen (PNG), un nombre temporal y las dimensiones (ancho y alto)
            corrida.addPicture(bis, XWPFDocument.PICTURE_TYPE_PNG, "captura.png", 
                               org.apache.poi.util.Units.toEMU(450), 
                               org.apache.poi.util.Units.toEMU(250));
            
            corrida.addCarriageReturn(); // Salto de línea para el siguiente paso
            System.out.println("Paso registrado con captura en memoria: " + descripcion);

        } catch (Exception e) {
            System.out.println("Error al registrar el paso con foto: " + e.getMessage());
        }
    }
    
    
    public static void guardarDocumento(String nombrePrueba) {
        String carpetaEvidencias = "src/test/resources/DataProvider/Evidencias/CommitQuality/";
        
        // Nos aseguramos de que la carpeta exista, si no, la crea
        java.io.File carpeta = new java.io.File(carpetaEvidencias);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
     // Generamos el sello de fecha y hora actual ⏱️
        java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy_HHmmss");
        String timestamp = ahora.format(formato);

        String rutaFinal = carpetaEvidencias + nombrePrueba +"_"+timestamp+".docx";

        try (java.io.FileOutputStream fos = new java.io.FileOutputStream(rutaFinal)) {
            // Escribimos todo el contenido acumulado en el archivo físico
            documento.write(fos);
            System.out.println("¡Evidencias guardadas con éxito en: " + rutaFinal + "!");
        } catch (Exception e) {
            System.out.println("Error al guardar el documento de Word: " + e.getMessage());
        }
    }
    
}