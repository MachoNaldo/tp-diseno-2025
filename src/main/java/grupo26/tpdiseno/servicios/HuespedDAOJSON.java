package grupo26.tpdiseno.servicios;

import grupo26.tpdiseno.entidades.SinConcordanciaException;
import grupo26.tpdiseno.entidades.DocumentoUsadoException;
import grupo26.tpdiseno.entidades.FiltroBusquedaHuesped;
import grupo26.tpdiseno.entidades.Huesped;
import grupo26.tpdiseno.entidades.HuespedDTO;
import static grupo26.tpdiseno.entidades.HuespedDTO.parsearHuesped;
import grupo26.tpdiseno.entidades.TipoDoc;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAOJSON implements HuespedDAO {

    private final File archivo = new File("datosoficiales.json");

    private static HuespedDAOJSON instancia;

    private HuespedDAOJSON() {
    }

    public static HuespedDAOJSON getInstancia() {
        if (instancia == null) {
            instancia = new HuespedDAOJSON();
        }
        return instancia;
    }

    @Override
    public void agregarHuesped(Huesped huesped, boolean forzar) throws DocumentoUsadoException {
        StringBuilder contenido = new StringBuilder();
        int mayorID = -1;

        if (archivo.exists()) {
            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                String linea;
                while ((linea = lector.readLine()) != null) {
                    if (linea.contains("huesped")) {
                        int inicioId = linea.indexOf("\"id\": \"") + 7; // +7 para saltar {"id": "
                        int finId = linea.indexOf("\"", inicioId);
                        mayorID = Integer.parseInt(linea.substring(inicioId, finId));
                        
                    } else if (linea.trim().equals("]")) {
                        break;
                    }
                    contenido.append(linea.trim()).append("\n");
                }
            } catch (IOException e) {
                System.out.println("Error leyendo archivo: " + e.getMessage());
            }
        } else {
            contenido.append("[");
            contenido.append("\n]");
        }

        String tipoDoc = huesped.getTipoDocumento().name();
        String numeroDoc = String.valueOf(huesped.getDocumentacion());

        if (forzar == false && contenido.toString().contains("\"tipoDocumento\": \"" + tipoDoc + "\"")
                && contenido.toString().contains("\"documento\": \"" + numeroDoc + "\"")) {
            throw new DocumentoUsadoException("El documento " + tipoDoc + " " + numeroDoc + " ya esta registrado.");
        }
        mayorID++;
        String nuevo = contenido.toString().substring(contenido.toString().indexOf("["), contenido.toString().lastIndexOf("}}\n"));
        contenido.setLength(0); //Esto limpia el archivo
        contenido.append(nuevo);
        contenido.append("}},\n");
        contenido.append("{\"huesped\": {")
                .append("\"id\": ").append(mayorID).append(", ")
                .append("\"nombre\": \"").append(huesped.getNombres()).append("\", ")
                .append("\"apellido\": \"").append(huesped.getApellido()).append("\", ")
                .append("\"edad\": ").append(huesped.getEdad()).append(", ")
                .append("\"tipoDocumento\": \"").append(tipoDoc).append("\", ")
                .append("\"documento\": \"").append(numeroDoc).append("\", ")
                .append("\"nacionalidad\": \"").append(huesped.getNacionalidad()).append("\", ")
                .append("\"email\": \"").append(huesped.getEmail()).append("\", ")
                .append("\"hospedado\": \"").append(huesped.getHospedado() ? "si" : "no").append("\"")
                .append("}}")
                .append("\n]");

        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            escritor.write(contenido.toString());
        } catch (IOException e) {
            System.out.println("️ Alto ahi ha ocurrido un error escribiendo archivo: " + e.getMessage());
        }
    }
    
    @Override
    public List<HuespedDTO> buscarHuespedDTO(FiltroBusquedaHuesped filtro) throws SinConcordanciaException {
        List<HuespedDTO> huespedes = new ArrayList<>();
        //                 System.out.println("Entre");

        if (archivo.exists()) {
            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    if (linea.contains("huesped")) {
                        HuespedDTO huesped = parsearHuesped(linea);
                        //  System.out.println("Apellido leído: '" + huesped.getApellido() + "' (length: " + huesped.getApellido().length() + ")");
                        huespedes.add(huesped);
                        //  System.out.println(huesped.toString());

                    }
                }
            } catch (IOException e) {
                System.out.println("Error leyendo archivo: " + e.getMessage());
            }
        }

        // Aplicar filtros después de cargar todo
        List<HuespedDTO> listaFiltrada = huespedes;
        /*
System.out.println("=== VALORES DEL FILTRO ===");
System.out.println("Nombre: " + filtro.getNombre());
System.out.println("Apellido: " + filtro.getApellido());
System.out.println("TipoDoc: " + filtro.getTipoDoc());
System.out.println("NumDoc: " + filtro.getNumDoc());
System.out.println("========================");*/
        if (filtro.getNombre() != null) {
            listaFiltrada = filtrarPorNombre(listaFiltrada, filtro.getNombre());
        }
        if (filtro.getApellido() != null) {
            listaFiltrada = filtrarPorApellido(listaFiltrada, filtro.getApellido());
        }
        if (filtro.getTipoDoc() != null) {
            listaFiltrada = filtrarPorTipoDoc(listaFiltrada, filtro.getTipoDoc());
        }
        if (filtro.getNumDoc() != 0) {
            listaFiltrada = filtrarPorNumDoc(listaFiltrada, filtro.getNumDoc());
        }

        if (listaFiltrada.isEmpty()) {
            throw new SinConcordanciaException("Sin concordancia con los datos de busqueda");
        }

        return listaFiltrada;
    }

    @Override
    public void modificarHuesped(Huesped unHuesped) {
        StringBuilder contenido = new StringBuilder();
        if (archivo.exists()) {
            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                String linea;
                while ((linea = lector.readLine()) != null) {
                    if (linea.trim().equals("]")) {
                        break;
                    }
                    if (linea.contains("huesped") && linea.contains("{\"id\": " + String.valueOf(unHuesped.getId()))) {
                        contenido.append("{\"huesped\": {")
                                .append("\"id\": ").append(unHuesped.getId()).append(", ")
                                .append("\"nombre\": \"").append(unHuesped.getNombres()).append("\", ")
                                .append("\"apellido\": \"").append(unHuesped.getApellido()).append("\", ")
                                .append("\"edad\": ").append(unHuesped.getEdad()).append(", ")
                                .append("\"tipoDocumento\": \"").append(unHuesped.getTipoDocumento()).append("\", ")
                                .append("\"documento\": \"").append(unHuesped.getDocumentacion()).append("\", ")
                                .append("\"nacionalidad\": \"").append(unHuesped.getNacionalidad()).append("\", ")
                                .append("\"email\": \"").append(unHuesped.getEmail()).append("\", ")
                                .append("\"hospedado\": \"").append(unHuesped.getHospedado()).append("\"")
                                .append("}}");
                        if (linea.endsWith("}},")) {
                            contenido.append(",\n"); // quitar coma final si existe
                        }else{
                            contenido.append("\n");
                        }
                    } else {
                        contenido.append(linea.trim()).append("\n");
                    }
                }
                contenido.append("]");
            } catch (IOException e) {
                System.out.println("Error leyendo archivo: " + e.getMessage());
            }
        } else {
            contenido.append("[");
            contenido.append("\n]");
        }

        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            escritor.write(contenido.toString());
        } catch (IOException e) {
            System.out.println("️ Alto ahi ha ocurrido un error escribiendo archivo: " + e.getMessage());
        }

    }

    @Override
    public void eliminarHuesped(String unIndice) {
        //public void agregarHuesped(Huesped huesped, boolean forzar) throws DocumentoUsadoException
        StringBuilder contenido = new StringBuilder();

        if (archivo.exists()) {
            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                String linea;
                while ((linea = lector.readLine()) != null) {
                    if (linea.trim().equals("]")) {
                        int ultimaComa = contenido.lastIndexOf(",");
                        if (ultimaComa != -1 && ultimaComa == contenido.length() - 1) {// se fija si el final
                            contenido.deleteCharAt(ultimaComa);
                        }
                        contenido.append(linea.trim());
                        break;
                    }
                    if (!(linea.contains("{\"id\": " + unIndice) && linea.contains("huesped"))) { // CAMBIO NACHO
                        contenido.append(linea.trim());
                        contenido.append("\n");
                    }
                }

            } catch (IOException e) {
                System.out.println("Error leyendo archivo: " + e.getMessage());
            }
        } else {
            contenido.append("[\n");
        }

        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            escritor.write(contenido.toString());
        } catch (IOException e) {
            System.out.println("️ Alto ahi ha ocurrido un error escribiendo archivo: " + e.getMessage());
        }
    }

    //PARA FILTRAR LA LINEAS DEL ARCHIVO
    public List<HuespedDTO> filtrarPorNombre(List<HuespedDTO> lista, String unNombre) {
        return lista.stream()
                .filter(h -> h.getNombres().equalsIgnoreCase(unNombre))
                .toList();
    }

    public List<HuespedDTO> filtrarPorApellido(List<HuespedDTO> lista, String unApellido) {
        return lista.stream()
                .filter(h -> h.getApellido().equalsIgnoreCase(unApellido))
                .toList();
    }

    public List<HuespedDTO> filtrarPorTipoDoc(List<HuespedDTO> lista, TipoDoc unTipoDoc) {
        return lista.stream()
                .filter(h -> h.getTipoDocumento().equals(unTipoDoc))
                .toList();
    }

    public List<HuespedDTO> filtrarPorNumDoc(List<HuespedDTO> lista, int unNumDoc) {
        return lista.stream()
                .filter(h -> h.getTipoDocumento().equals(unNumDoc))
                .toList();

    }
}
