package grupo26.tpdiseno.pantallas;
import grupo26.tpdiseno.gestores.GestorHuesped;
import grupo26.tpdiseno.entidades.HuespedDTO;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PantallaDarAltaHuesped {
    
    Scanner sc = new Scanner(System.in);
  
    public void DarAltaHuesped(){
        GestorHuesped gestorH = GestorHuesped.getInstancia();
         gestorH.DarAltaHuesped(gestorH.generarHuespedDTO());
    }
    //Sobrecarga SOLO PARA AGREGAR MAS RAPIDO
    public void DarAltaHuesped(HuespedDTO unDTO){
        GestorHuesped gestorH = GestorHuesped.getInstancia();
        gestorH.DarAltaHuesped(unDTO);
    }
    
    
    
}
