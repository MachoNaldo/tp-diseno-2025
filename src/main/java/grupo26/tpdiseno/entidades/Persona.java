/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.entidades;

/**
 *
 * @author Diego
 */
public abstract class Persona {
    private String cuit;
    private String telefono;
    private String nacionalidad;
    private Direccion direccion;

    public Persona(String cuit, String telefono, String nacionalidad, Direccion direccion) {
        this.cuit = cuit;
        this.telefono = telefono;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
    }
    
    
}
