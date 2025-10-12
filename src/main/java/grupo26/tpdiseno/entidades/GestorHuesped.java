package grupo26.tpdiseno.entidades;

import grupo26.tpdiseno.servicios.HuespedDAOJSON;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class GestorHuesped implements FechaFunciones{
    
    
    
    public void DarAltaHuesped(HuespedDTO unHuespedDTO){
        
        Huesped h1 = new Huesped(unHuespedDTO);
        
        
        HuespedDAOJSON hDAO = new HuespedDAOJSON();
       try{
        hDAO.agregarHuesped(h1, false);
       } catch (DocumentoUsadoException dc){
           System.out.println(dc);
       }
        
        
        System.out.println("El huesped se cargo con exito en el sistema");
    }
    
    
}
