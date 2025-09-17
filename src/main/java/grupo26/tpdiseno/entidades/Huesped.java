/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.entidades;

import java.util.Date;

/**
 *
 * @author Diego
 */
public class Huesped extends Persona{
    private String nombres;
    private String apellido;
    private int edad;
    private TipoSexo sexo;
    private int documentacion;
    private Date fechaNacimiento;
    private TipoConsumidor consumidorFinal;
    private String email;
    private String ocupacion;

    public Huesped(String cuit, String telefono, String nacionalidad,Direccion direccion, String nombres, String apellido, int edad, TipoSexo sexo, int documentacion, Date fechaNacimiento, TipoConsumidor consumidorFinal, String email, String ocupacion) {
        super(cuit,telefono,nacionalidad,direccion);
        this.nombres = nombres;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.documentacion = documentacion;
        this.fechaNacimiento = fechaNacimiento;
        this.consumidorFinal = consumidorFinal;
        this.email = email;
        this.ocupacion = ocupacion;
    }

   

    public String getNombres() {
        return nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDocumentacion() {
        return documentacion;
    }

    @Override
    public String toString() {
        return "Huesped{" + "nombres=" + nombres + ", apellido=" + apellido + ", edad=" + edad + ", sexo=" + sexo + ", documentacion=" + documentacion + ", fechaNacimiento=" + fechaNacimiento + ", consumidorFinal=" + consumidorFinal + ", email=" + email + ", ocupacion=" + ocupacion + '}';
    }
    
    
}
