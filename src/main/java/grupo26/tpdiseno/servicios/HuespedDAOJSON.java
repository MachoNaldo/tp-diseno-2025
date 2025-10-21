package grupo26.tpdiseno.servicios;

import grupo26.tpdiseno.entidades.SinConcordanciaException;
import grupo26.tpdiseno.entidades.DocumentoUsadoException;
import grupo26.tpdiseno.entidades.FiltroBusquedaHuesped;
import grupo26.tpdiseno.entidades.Huesped;
import grupo26.tpdiseno.entidades.HuespedDTO;
import static grupo26.tpdiseno.entidades.HuespedDTO.parsearHuesped;
import grupo26.tpdiseno.entidades.RegistroNoEncontradoException;
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

    /**
     * Agrega un nuevo huésped al archivo de registro.
     *
     * <p>
     * Este método lee el archivo con los datos, determina el ID más alto
     * registrado, valida que no exista otro huésped con el mismo tipo y número
     * de documento (a menos que se indique que se quiere forzar el registro
     * {@code forzar}), y luego escribe el nuevo huésped en el archivo,
     * actualizando el contenido en formato JSON.</p>
     *
     * @param huesped El objeto {@link Huesped} que se desea agregar.
     * @param forzar Si es {@code true}, permite agregar el huésped aunque ya
     * exista un documento igual. Si es {@code false}, lanza una excepción en
     * caso de documento duplicado.
     * @throws DocumentoUsadoException Si el documento del huésped ya está
     * registrado y no se permite forzar.
     *
     * @implNote Este método:
     * <ul>
     * <li>Calcula el próximo ID incrementando el mayor encontrado en el
     * archivo.</li>
     * <li>Reconstruye el contenido del archivo en memoria antes de
     * sobrescribirlo.</li>
     *
     * @see Huesped
     */
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
                        int inicioId = linea.indexOf("\"id\": ") + 6; // +6 para saltar {"id": "
                        int finId = linea.indexOf(",", inicioId);
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

    /**
     * Busca y devuelve una lista de huéspedDTO que coinciden con los criterios
     * de búsqueda especificados.
     *
     * <p>
     * El método lee todos los registros de huéspedes desde el archivo de datos,
     * convierte cada uno en un objeto {@link HuespedDTO}, y luego aplica los
     * filtros definidos en el objeto {@link FiltroBusquedaHuesped}. Si no se
     * encuentra ningún huésped que cumpla con las condiciones, se lanza una
     * excepción.</p>
     *
     * @param filtro Objeto {@link FiltroBusquedaHuesped} que contiene los
     * parámetros de búsqueda (nombre, apellido, tipo de documento, número de
     * documento, etc.).
     * @return Una lista de objetos {@link HuespedDTO} que coinciden con los
     * criterios del filtro.
     * @throws SinConcordanciaException Si no se encuentra ningún huésped que
     * cumpla los criterios de búsqueda.
     *
     * @implNote Este método:
     * <ul>
     * <li>Convierte cada línea en un {@link HuespedDTO} mediante el método
     * {@code parsearHuesped}.</li>
     * <li>Aplica los filtros en memoria después de cargar todos los
     * registros.</li>
     * <li>No modifica el archivo de datos; solo realiza operaciones de
     * lectura.</li>
     * </ul>
     *
     * @see HuespedDTO
     * @see FiltroBusquedaHuesped
     * @see #parsearHuesped(String)
     */
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

    /**
     * Modifica los datos de un huésped existente en el archivo de registro.
     *
     * <p>
     * El método busca en el archivo la entrada correspondiente al huésped cuyo
     * identificador coincide con el valor de {@code unHuesped.getId()},
     * reemplaza su información con los nuevos datos provistos y luego reescribe
     * el archivo completo con la modificación aplicada.</p>
     *
     * @param unHuesped El objeto {@link Huesped} con los datos actualizados que
     * deben reemplazar los del registro existente.
     *
     * @implNote Este método:
     * <ul>
     * <li>Identifica al huésped a modificar mediante su campo {@code id}.</li>
     * <li>Reconstruye el archivo completo en memoria, actualizando solo el
     * registro coincidente.</li>
     * <li>No lanza excepciones; los errores de E/S se informan mediante
     * mensajes en consola.</li>
     * </ul>
     *
     * @see Huesped
     */
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
                        } else {
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

    /**
     * Elimina del archivo de registro al huésped cuyo identificador coincida
     * con el valor especificado.
     *
     * <p>
     * El método recorre línea por línea el archivo de datos y omite la entrada
     * correspondiente al huésped con el {@code id} indicado por el parámetro
     * {@code unIndice}. Si no se encuentra ningún registro con ese
     * identificador, se lanza una excepción.</p>
     *
     * @param unIndice El identificador del huésped a eliminar, expresado como
     * cadena.
     * @throws RegistroNoEncontradoException Si no existe ningún huésped con el
     * ID especificado.
     *
     * @implNote Este método:
     * <ul>
     * <li>Excluye del resultado la línea que contiene el huésped con el ID
     * indicado.</li>
     * <li>Reescribe el archivo sin el registro eliminado.</li>
     * <li>Lanza {@link RegistroNoEncontradoException} si no se detecta el
     * huésped indicado.</li>
     * <li>Si el archivo no existe, se crea un archivo vacío con estructura
     * básica.</li>
     * </ul>
     *
     * @see Huesped
     * @see RegistroNoEncontradoException
     */
    @Override
    public void eliminarHuesped(String unIndice) throws RegistroNoEncontradoException {
        StringBuilder contenido = new StringBuilder();
        boolean encontrado = false;

        if (archivo.exists()) {
            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                String linea;
                while ((linea = lector.readLine()) != null) {
                    if (linea.trim().equals("]")) {
                        if (contenido.toString().endsWith(",\n")) {
                            int ultimaComa = contenido.lastIndexOf(",");
                            if (ultimaComa != -1) {
                                contenido.deleteCharAt(ultimaComa);
                            }
                        }
                        contenido.append(linea.trim());
                        break;
                    }

                    if (!(linea.contains("{\"id\": " + unIndice) && linea.contains("huesped"))) {
                        contenido.append(linea.trim()).append("\n");
                    } else {
                        encontrado = true;
                    }
                }

            } catch (IOException e) {
                System.out.println("Error leyendo archivo: " + e.getMessage());
            }
        } else {
            contenido.append("[\n]");
        }

        if (!encontrado) {
            throw new RegistroNoEncontradoException("No se encontró el huésped con ID " + unIndice);
        }

        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            escritor.write(contenido.toString());
        } catch (IOException e) {
            System.out.println("️ Alto ahi ha ocurrido un error escribiendo archivo: " + e.getMessage());
        }
    }

    //PARA FILTRAR LA LINEAS DEL ARCHIVO
    /**
     * Filtra una lista de huéspedes según el nombre especificado.
     *
     * <p>
     * Devuelve una nueva lista que contiene únicamente aquellos objetos
     * {@link HuespedDTO} cuyo nombre coincide (sin distinguir mayúsculas o
     * minúsculas) con el valor indicado por el parámetro {@code unNombre}.</p>
     *
     * @param lista La lista original de huéspedes a filtrar.
     * @param unNombre El nombre que se desea buscar en los registros.
     * @return Una lista de {@link HuespedDTO} cuyo campo {@code nombre}
     * coincide con el valor proporcionado.
     *
     * @implNote Este método utiliza una operación {@code stream()} y la función
     * {@code equalsIgnoreCase()} para realizar una comparación insensible a
     * mayúsculas y minúsculas. Devuelve una lista inmutable creada mediante
     * {@code toList()}.
     *
     * @see HuespedDTO
     */
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
