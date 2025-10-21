package grupo26.tpdiseno.entidades;

import java.util.Date;
import java.io.Serializable;

public class HuespedDTO implements Serializable {

    String cuit;
    String telefono;
    String nacionalidad;
    Direccion direccion;
    int id;
    boolean hospedado;
    String nombres;
    String apellido;
    int edad;
    TipoSexo sexo;
    TipoDoc tipoDocumento;
    int documentacion;
    Date fechaNacimiento;
    TipoConsumidor consumidorFinal;
    String email;
    String ocupacion;

    public HuespedDTO(
            String cuit,
            String telefono,
            String nacionalidad,
            Direccion direccion,
            String nombres,
            String apellido,
            int edad,
            TipoSexo sexo,
            TipoDoc tipoDocumento,
            int documentacion,
            Date fechaNacimiento,
            TipoConsumidor consumidorFinal,
            String email,
            String ocupacion) {

        this.cuit = cuit;
        this.telefono = telefono;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
        this.nombres = nombres;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.tipoDocumento = tipoDocumento;
        this.documentacion = documentacion;
        this.fechaNacimiento = fechaNacimiento;
        this.consumidorFinal = consumidorFinal;
        this.email = email;
        this.ocupacion = ocupacion;
    }

    public HuespedDTO( //constructor para dtos que dan el id y hospedado
            String nacionalidad,
            boolean hospedado,
            int id,
            String nombres,
            String apellido,
            int edad,
            TipoDoc tipoDocumento,
            int documentacion,
            String email) {
        this.id = id;
        this.hospedado = hospedado;
        this.nacionalidad = nacionalidad;
        this.nombres = nombres;
        this.apellido = apellido;
        this.edad = edad;
        this.tipoDocumento = tipoDocumento;
        this.documentacion = documentacion;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Huesped {" + "id=" + id + ", nacionalidad=" + nacionalidad + ", nombres=" + nombres + ", apellido=" + apellido + ", edad=" + edad + ", tipoDocumento=" + tipoDocumento + ", documentacion=" + documentacion + ", email=" + email + ", hospedado=" + hospedado + '}';
    }

    /**
     * Parsea una línea con formato JSON y crea un objeto {@link HuespedDTO} a
     * partir de los valores obtenidos.
     *
     * <p>
     * El método busca los campos correspondientes a un huésped dentro de la
     * cadena JSON, los convierte a sus tipos adecuados (enteros,
     * booleanos, enumeraciones, etc.) y retorna una nueva instancia de
     * {@code HuespedDTO}.</p>
     *
     * <p>
     * Ejemplo de formato esperado:</p>
     * <pre>
     * {"id": 1, "nombre": "Juan", "apellido": "Pérez", "edad": 30,
     *  "tipoDocumento": "DNI", "nacionalidad": "Argentina",
     *  "email": "juan@mail.com", "documento": "12345678", "hospedado": "si"}
     * </pre>
     *
     * @param linea La línea de texto que contiene los datos del huésped en
     * formato JSON.
     * @return Un objeto {@code HuespedDTO} construido a partir de los valores
     * extraídos.
     *
     * @throws NumberFormatException Si alguno de los campos numéricos (por
     * ejemplo, {@code id}, {@code edad} o {@code documento}) no contiene un
     * número válido.
     * @throws IllegalArgumentException Si el valor de {@code tipoDocumento} no
     * coincide con ningún elemento del enum {@link TipoDoc}.
     *
     * @implNote
     * <ul>
     * <li>El método depende del método auxiliar {@code extraerValor(...)} para
     * obtener cada campo.</li>
     * <li>El campo {@code hospedado} se considera verdadero si su valor es
     * exactamente {@code "si"}.</li>
     * <li>Se asume que todos los campos requeridos están presentes y
     * correctamente formateados.</li>
     * </ul>
     */
    public static HuespedDTO parsearHuesped(String linea) {
        int id = Integer.parseInt(extraerValor(linea, "\"id\": "));
        String nombre = extraerValor(linea, "\"nombre\": \"");
        String apellido = extraerValor(linea, "\"apellido\": \"");
        int edad = Integer.parseInt(extraerValor(linea, "\"edad\": "));
        String tipoDocStr = extraerValor(linea, "\"tipoDocumento\": \"");
        String nacionalidad = extraerValor(linea, "\"nacionalidad\": \"");
        String email = extraerValor(linea, "\"email\": \"");
        int documento = Integer.parseInt(extraerValor(linea, "\"documento\": \""));
        String hospedadoStr = extraerValor(linea, "\"hospedado\": \"");

        boolean hospedado = hospedadoStr.equals("si");

        TipoDoc tipoDocumento = TipoDoc.valueOf(tipoDocStr.toUpperCase());

        return new HuespedDTO(nacionalidad, hospedado, id, nombre, apellido, edad, tipoDocumento, documento, email);
    }

    /**
     * Extrae el valor asociado a un campo específico dentro de una cadena con
     * formato JSON.
     *
     * <p>
     * El método busca la primera aparición del nombre del campo indicado por
     * {@code campo} dentro de la cadena {@code linea}, y devuelve el texto
     * comprendido entre el final del nombre del campo y el siguiente separador
     * (coma o llave de cierre). Si el campo no se encuentra, devuelve una
     * cadena vacía.</p>
     *
     * @param linea La línea de texto que contiene los datos en formato JSON o
     * similar.
     * @param campo El nombre del campo cuyo valor se desea extraer (por
     * ejemplo, {@code "\"nombre\": "}).
     * @return El valor asociado al campo, sin comillas ni espacios adicionales.
     * Si el campo no se encuentra, devuelve una cadena vacía.
     *
     * @implNote
     * <ul>
     * <li>El método asume el formato JSON utilizado para los datos en el que
     * los campos están separados por comas o llaves de cierre.</li>
     * <li>Elimina las comillas del valor mediante {@code replace("\"", "")} y
     * aplica {@code trim()}.</li>
     * <li>Devuelve una cadena vacía si el campo no existe o si no se puede
     * determinar su delimitador de fin.</li>
     * </ul>
     */
    private static String extraerValor(String linea, String campo) {
        int inicio = linea.indexOf(campo); // busca el indice del indice del campo que buscamos
        if (inicio == -1) {
            return "";
        }
        inicio += campo.length();// se mueve a la ultima " del campo

        int fin = linea.indexOf(",", inicio);
        if (fin == -1) {
            fin = linea.indexOf("}", inicio);
        }
        if (fin == -1) {
            fin = linea.length();
        }

        return linea.substring(inicio, fin).replace("\"", "").trim(); // si es un valor string el replace elimina el ultimo "
    }

    public int getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public TipoDoc getTipoDocumento() {
        return tipoDocumento;
    }

    public int getDocumentacion() {
        return documentacion;
    }

    public String getEmail() {
        return email;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public boolean isHospedado() {
        return hospedado;
    }

    public int getEdad() {
        return edad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void setTipoDocumento(TipoDoc tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public void setDocumentacion(int documentacion) {
        this.documentacion = documentacion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
