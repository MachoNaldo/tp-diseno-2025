/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package grupo26.tpdiseno.servicios;
import grupo26.tpdiseno.entidades.Huesped;
import grupo26.tpdiseno.entidades.DocumentoUsadoException;
/**
 *
 * @author Usuario
 */
public interface HuespedDAO {
    void agregarHuesped(Huesped huesped, boolean forzar) throws DocumentoUsadoException;
    //Huesped buscarHuesped(Huesped huesped);
    //boolean verificarHuesped(Huesped huesped);
    //void prueba(Huesped huesped) throws DocumentoUsadoException;
}
