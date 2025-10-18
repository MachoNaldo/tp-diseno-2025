/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.pantallas;

import grupo26.tpdiseno.gestores.GestorHuesped;
import grupo26.tpdiseno.entidades.HuespedDTO;

/**
 *
 * @author Gabri
 */
public class PantallaModificarHuesped {
    
    public void ModificarHuesped(){
        GestorHuesped gestorH = GestorHuesped.getInstancia();
        gestorH.ModificarHuesped(gestorH.generarHuespedDTO());
    }
    
    //Sobrecarga SOLO PARA PROBAR MAS RAPIDO
     public void ModificarHuesped(HuespedDTO unDTO){
        GestorHuesped gestorH = GestorHuesped.getInstancia();
        gestorH.ModificarHuesped(unDTO);
    }
}
