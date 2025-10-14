package grupo26.tpdiseno.entidades;

import grupo26.tpdiseno.servicios.HuespedDAOJSON;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;


public class GestorHuesped implements FechaFunciones{
    
    private static GestorHuesped instancia;
    private final HuespedDAOJSON hDAO;
    
    private GestorHuesped() {
        this.hDAO = HuespedDAOJSON.getInstancia();
    }
    
    public static GestorHuesped getInstancia() {
        if (instancia == null) {
            instancia = new GestorHuesped();
        }
        return instancia;
    }
    
    public void DarAltaHuesped(HuespedDTO unHuespedDTO){
        
        Huesped h1 = new Huesped(unHuespedDTO);
        
       try{
        hDAO.agregarHuesped(h1, false);
       } catch (DocumentoUsadoException dc){
           System.out.println(dc);
       }
        
        
        System.out.println("El huesped se cargo con exito en el sistema");
    }
    
   public void buscarHuesped(FiltroBusquedaHuesped unFiltro, List<String> unaLista){
       
       try{
        hDAO.buscarHuesped(unFiltro, unaLista);
       } catch (SinConcordanciaException e){
           System.out.println(e);
           System.out.println("Pasanda a Dar Alta de Huesped...");
           new PantallaDarAltaHuesped().DarAltaHuesped();
       }
       
   }
   
  
    
    
}
