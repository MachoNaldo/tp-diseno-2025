package grupo26.tpdiseno;

/*import grupo26.tpdiseno.entidades.Huesped;
import grupo26.tpdiseno.entidades.GestorHuesped;
import grupo26.tpdiseno.entidades.PantallaDarAltaHuesped;*/
import grupo26.tpdiseno.entidades.*;
import grupo26.tpdiseno.servicios.*;

/*import grupo26.tpdiseno.entidades.Huesped;
import grupo26.tpdiseno.entidades.TipoConsumidor;
import grupo26.tpdiseno.entidades.TipoSexo;*/

public class Tpdiseno {

    public static void main(String[] args) {
    

       PantallaDarAltaHuesped pantalla = new PantallaDarAltaHuesped();
       
       GestorHuesped gestor = new GestorHuesped();
       
       //Huesped h1 = gestor.DarAltaHuesped(  pantalla.generarDTO()   );
 

         
         
           HuespedDTO nuevoDTO = new HuespedDTO(
            "20-12345678-9",          // cuit
            "12345678",             // telefono
            "Argentina",              // nacionalidad
            new Direccion("nombreCalle", 123, "3001", "Santa Fe", "Argentina", "Piso 2", "El Solar"),  //Direccion
            "Hamas",                   // nombres
            "Perez",                  // apellido
            34,                       // edad
            TipoSexo.Masculino,       // sexo
            90909001,                 // documentacion
            gestor.CrearFecha(10,11,2001), //FechaNacimiento
            TipoConsumidor.A,//Tipo de consumidor
            "gabi@hotmail.com",//email
            "Kiosquero" //Ocupacion 
           );
           
           Huesped h1 = gestor.DarAltaHuesped(  nuevoDTO   );
       
      // h1.verNombreYApellido();
       //h1.verFechaNacimiento();
       HuespedDAOJSON hDAO = new HuespedDAOJSON();
       try{
        hDAO.prueba(h1);
       } catch (DocumentoUsadoException dc){
           System.out.println(dc);
       }
    }
}
