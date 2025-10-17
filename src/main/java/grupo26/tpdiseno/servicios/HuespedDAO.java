/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package grupo26.tpdiseno.servicios;
import grupo26.tpdiseno.entidades.Huesped;
import grupo26.tpdiseno.entidades.DocumentoUsadoException;
import grupo26.tpdiseno.entidades.FiltroBusquedaHuesped;
import grupo26.tpdiseno.entidades.SinConcordanciaException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Usuario
 */
public interface HuespedDAO {
    void agregarHuesped(Huesped huesped, boolean forzar) throws DocumentoUsadoException;
    void buscarHuesped(FiltroBusquedaHuesped unFiltro, List<String> unaLista) throws SinConcordanciaException;
    void modificarHuesped(Huesped unHuesped, String unIndice);
    void eliminarHuesped(String unHuesped);
    //boolean verificarHuesped(Huesped huesped);
    //void prueba(Huesped huesped) throws DocumentoUsadoException;
}
