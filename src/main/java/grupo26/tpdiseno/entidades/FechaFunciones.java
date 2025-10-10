/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.entidades;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Gabri
 */
public interface FechaFunciones {
    
    default public Date CrearFecha(int dia, int mes, int anio){
        LocalDate fechaLocal = LocalDate.of(anio, mes, dia); 
         return( Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }
    
    
    
}
