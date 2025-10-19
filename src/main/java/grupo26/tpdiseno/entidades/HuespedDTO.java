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
