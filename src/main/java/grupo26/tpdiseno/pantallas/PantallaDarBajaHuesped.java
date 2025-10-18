/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.pantallas;

import grupo26.tpdiseno.gestores.GestorHuesped;

/**
 *
 * @author Gabri
 */
public class PantallaDarBajaHuesped {
    
   
    public void DarBajaHuesped(){
        GestorHuesped gestorH = GestorHuesped.getInstancia();
        gestorH.EliminarHuesped();
    }
    
    
    
}
