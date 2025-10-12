package grupo26.tpdiseno.servicios;

import grupo26.tpdiseno.entidades.DocumentoUsadoException;
import grupo26.tpdiseno.entidades.Huesped;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class HuespedDAOJSON implements HuespedDAO {
    private final File archivo = new File("datosoficiales.json");

    @Override
    public void agregarHuesped(Huesped huesped) throws DocumentoUsadoException {
        StringBuilder contenido = new StringBuilder();

         if (archivo.exists()) {
                try (BufferedReader lector = new BufferedReader(
                        new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                    String linea;
                     while ((linea = lector.readLine()) != null) {
                         if (linea.trim().equals("]")) break;

                         contenido.append(linea.trim()).append("\n");
                 }
                 }   catch (IOException e) {
                 System.out.println("Error leyendo archivo: " + e.getMessage());
                    }
                } else {
                contenido.append("[\n");
           }           


        String tipoDoc = huesped.getTipoDocumento().name();
        String numeroDoc = String.valueOf(huesped.getDocumentacion());

        if (contenido.toString().contains("\"tipoDocumento\": \"" + tipoDoc + "\"") &&
            contenido.toString().contains("\"documento\": \"" + numeroDoc + "\"")) {
            throw new DocumentoUsadoException(
                "El documento " + tipoDoc + " " + numeroDoc + " ya esta registrado."
            );
        }

        if (!contenido.toString().trim().equals("[")) {
           contenido.append(",\n");
        }

        contenido.append("{\"huesped\": {")
                .append("\"nombre\": \"").append(huesped.getNombres()).append("\", ")
                .append("\"apellido\": \"").append(huesped.getApellido()).append("\", ")
                .append("\"edad\": ").append(huesped.getEdad()).append(", ")
                .append("\"tipoDocumento\": \"").append(tipoDoc).append("\", ")
                .append("\"documento\": \"").append(numeroDoc).append("\", ")
                .append("\"nacionalidad\": \"").append(huesped.getNacionalidad()).append("\", ")
                .append("\"email\": \"").append(huesped.getEmail()).append("\"")
                .append("}}")
                .append("\n]");

        try (BufferedWriter escritor = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            escritor.write(contenido.toString());
        } catch (IOException e) {
            System.out.println("️ Alto ahi ha ocurrido un error escribiendo archivo: " + e.getMessage());
        }
    }
}
