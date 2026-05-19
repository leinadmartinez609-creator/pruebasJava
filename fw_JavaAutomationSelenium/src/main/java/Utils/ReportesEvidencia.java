package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.openqa.selenium.WebDriver;

public class ReportesEvidencia {

    // 1. Variables globales para mantener el estado durante la prueba
    private static XWPFDocument documento;
    private static WebDriver driver;

    // 2. Método para iniciar el registro en las anotaciones de TestNG
    public static void iniciarDocumento(WebDriver driverActivo, String nombreCaso, String automatizador) {
        documento = new XWPFDocument();
        driver = driverActivo;

        try {
            // 1. TÍTULO PRINCIPAL
            XWPFParagraph titulo = documento.createParagraph();
            titulo.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun runTitulo = titulo.createRun();
            runTitulo.setText("REPORTE DE EVIDENCIA DE PRUEBAS");
            runTitulo.setBold(true);
            runTitulo.setFontSize(16);
            runTitulo.setFontFamily("Arial");

            // 2. SUBTÍTULO
            XWPFParagraph subtitulo = documento.createParagraph();
            subtitulo.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun runSubtitulo = subtitulo.createRun();
            runSubtitulo.setText("Generado automáticamente por el Framework de Automatización");
            runSubtitulo.setFontSize(11);
            runSubtitulo.setItalic(true);
            runSubtitulo.setFontFamily("Arial");
            runSubtitulo.addCarriageReturn();

            // 3. SECCIÓN 1: DETALLES DE LA EJECUCIÓN
            XWPFParagraph seccion1 = documento.createParagraph();
            XWPFRun runSeccion1 = seccion1.createRun();
            runSeccion1.setText("1. Detalles de la Ejecución");
            runSeccion1.setBold(true);
            runSeccion1.setFontSize(13);
            runSeccion1.setFontFamily("Arial");
            runSeccion1.addCarriageReturn();

            // 4. CREACIÓN DE LA TABLA DE METADATOS (4 filas, 2 columnas)
            XWPFTable tabla = documento.createTable(4, 2);
            
            // Obtener fecha actual en formato DD/MM/AAAA
            String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            // Llenar la tabla con los datos correspondientes
            configurarCelda(tabla.getRow(0).getCell(0), "Caso de Prueba:");
            configurarCelda(tabla.getRow(0).getCell(1), nombreCaso);
            
            configurarCelda(tabla.getRow(1).getCell(0), "Automatizador:");
            configurarCelda(tabla.getRow(1).getCell(1), automatizador);
            
            configurarCelda(tabla.getRow(2).getCell(0), "Fecha de Ejecución:");
            configurarCelda(tabla.getRow(2).getCell(1), fechaActual);
            
            configurarCelda(tabla.getRow(3).getCell(0), "Estado Final:");
            configurarCelda(tabla.getRow(3).getCell(1), "PASSED / EXITOSO"); // Se asume exitoso si llega al final

            // Agregar espacio antes de la sección de pasos
            XWPFParagraph espacio = documento.createParagraph();
            espacio.createRun().addCarriageReturn();

            // 5. SECCIÓN 2: TÍTULO DE PASOS
            XWPFParagraph seccion2 = documento.createParagraph();
            XWPFRun runSeccion2 = seccion2.createRun();
            runSeccion2.setText("2. Pasos del Caso de Prueba y Evidencia Visual");
            runSeccion2.setBold(true);
            runSeccion2.setFontSize(13);
            runSeccion2.setFontFamily("Arial");
            runSeccion2.addCarriageReturn();

            System.out.println("¡Reporte inicializado basado en la plantilla con éxito! 📄");

        } catch (Exception e) {
            System.out.println("Error al estructurar la plantilla: " + e.getMessage());
        }
    }
    
    private static void configurarCelda(XWPFTableCell celda, String texto) {
        XWPFParagraph p = celda.getParagraphs().get(0);
        XWPFRun r = p.createRun();
        r.setText(texto);
        r.setFontFamily("Arial");
        r.setFontSize(11);
        if (texto.endsWith(":")) {
            r.setBold(true); // Negrita para las etiquetas de la izquierda
        }
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
        String carpetaEvidencias = "src/test/resources/Evidencias/CommitQuality/";
        
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