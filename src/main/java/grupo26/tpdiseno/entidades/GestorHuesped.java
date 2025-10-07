package grupo26.tpdiseno.entidades;


public class GestorHuesped{
    
    public void DarAltaHuesped(HuespedDTO unHuesped){
        //HuespedSQL huespedNuevo;
        
        Huesped nuevo = new Huesped(unHuesped);
        
        nuevo.verNombreYApellido();
        
        
        
    } 
    
    
}
