/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.entidades;

/**
 * Se lanza cuando no se encuentra un registro correspondiente
 * a la búsqueda o eliminación solicitada.
 */
public class RegistroNoEncontradoException extends Exception {
    public RegistroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}