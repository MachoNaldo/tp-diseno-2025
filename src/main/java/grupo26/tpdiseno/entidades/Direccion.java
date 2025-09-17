/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.entidades;

/**
 *
 * @author Diego
 */
public class Direccion {
    private String nombreCalle;
    private int numCalle;
    private String codPostal;
    private String localidad;
    private String pais;
    private String piso;
    private String departamento;

    public Direccion(String nombreCalle, int numCalle, String codPostal, String localidad, String pais, String piso, String departamento) {
        this.nombreCalle = nombreCalle;
        this.numCalle = numCalle;
        this.codPostal = codPostal;
        this.localidad = localidad;
        this.pais = pais;
        this.piso = piso;
        this.departamento = departamento;
    }
    
    
}
