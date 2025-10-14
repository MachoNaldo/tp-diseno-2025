/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.entidades;

/**
 *
 * @author Gabri
 */
public class SinConcordanciaException extends Exception{
    public SinConcordanciaException(String mensaje) {
        super(mensaje);
    }
    @Override
    public String toString() {
        return getMessage();
    }
}
