/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.entidades;

import java.util.Date;
import java.time.LocalDate;


public class Huesped extends Persona{
    private String nombres;
    private String apellido;
    private int edad;
    private TipoSexo sexo;
    private int documentacion;
    private LocalDate fechaNacimiento;
    private TipoConsumidor consumidorFinal;
    private String email;
    private String ocupacion;

    /*public Huesped(String cuit, String telefono, String nacionalidad,Direccion direccion, String nombres, String apellido, int edad, TipoSexo sexo, int documentacion, Date fechaNacimiento, TipoConsumidor consumidorFinal, String email, String ocupacion) {
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
    }*/
    
    public Huesped(HuespedDTO unDTO){
         super(unDTO.cuit,unDTO.telefono,unDTO.nacionalidad,unDTO.direccion);
         this.nombres = unDTO.nombres;
         this.apellido = unDTO.apellido;
         this.edad = unDTO.edad;
         this.sexo = unDTO.sexo;
         this.documentacion = unDTO.documentacion;
         this.fechaNacimiento = unDTO.fechaNacimiento;
         this.consumidorFinal = unDTO.consumidorFinal;
         this.email = unDTO.email;
         this.ocupacion = unDTO.ocupacion;
        
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
    
    
    
    
    
    
    
    
      public void verNombreYApellido(){
        System.out.println("Nombre: " + this.nombres);
        System.out.println("Apellido: " + this.apellido);
        }
    
}
