package grupo26.tpdiseno.entidades;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class GestorHuesped implements FechaFunciones{
    
    public Huesped DarAltaHuesped(HuespedDTO unHuespedDTO){   
        return (new Huesped(unHuespedDTO));
    }
    
}
