package Utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;

public class DataProvider {

    // Ahora el método recibe el número de fila y el NOMBRE de la columna 🏷️
    public static String leerCeldaPorColumna(String ruta, int numeroFila, String nombreColumna) {
        String rutaArchivo =ruta;
        String valorCelda = "";

        try (FileInputStream fis = new FileInputStream(new File(rutaArchivo));
             Workbook libro = WorkbookFactory.create(fis)) {
            
            Sheet hoja = libro.getSheetAt(0);
            
            // 1. Creamos un mapa para asociar el nombre de la columna con su índice numérico
            Map<String, Integer> mapaColumnas = new HashMap<>();
            Row filaEncabezado = hoja.getRow(0); // Fila 0 tiene los títulos
            
            // 2. Recorremos los encabezados para llenar el mapa
            for (Cell celda : filaEncabezado) {
                String textoEncabezado = celda.getStringCellValue();
                int indiceColumna = celda.getColumnIndex();
                mapaColumnas.put(textoEncabezado, indiceColumna);
            }
            
            // 3. Buscamos el número de columna que corresponde al nombre solicitado
            if (mapaColumnas.containsKey(nombreColumna)) {
                int numeroColumna = mapaColumnas.get(nombreColumna);
                
                // 4. Vamos a la fila que nos pidieron y extraemos el dato
                Row filaDatos = hoja.getRow(numeroFila);
                Cell celdaDatos = filaDatos.getCell(numeroColumna);
                
                valorCelda = celdaDatos.toString();
            } else {
                System.out.println("La columna '" + nombreColumna + "' no existe en el archivo.");
            }
            
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return valorCelda;
    }
}