/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.entidades;

/**
 *
 * @author Diego
 */
public class Responsable_Pago extends Persona {
    private String razonSocial;

    public Responsable_Pago(String razonSocial, String cuit, String telefono, String nacionalidad, Direccion direccion) {
        super(cuit, telefono, nacionalidad, direccion);
        this.razonSocial = razonSocial;
    }
    
    
}
