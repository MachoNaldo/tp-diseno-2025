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
    

       PantallaDarAltaHuesped pantallaH = new PantallaDarAltaHuesped();
       PantallaAutenticarUsuario pantallaU = new PantallaAutenticarUsuario();
       pantallaU.iniciarSesion();
       pantallaH.DarAltaHuesped();
         
         /*
           HuespedDTO nuevoDTO = new HuespedDTO(
            "20-12345678-9",          // cuit
            "12345678",             // telefono
            "Argentina",              // nacionalidad
            new Direccion("Aguafiestas", 123, "3001", "Santa Fe", "Argentina", "Piso 2", "El Solar"),  //Direccion
            "Gabriel",                   // nombres
            "Incelardo",                  // apellido
            44,                       // edad
            TipoSexo.Masculino,       // sexo
            TipoDoc.DNI,               //tipo doc perro
            20227731,                 // documentacion
            gestorH.CrearFecha(8,07,2001), //FechaNacimiento
            TipoConsumidor.A,//Tipo de consumidor
            "Gabriel@hotmail.com",//email
            "Vendedor de tutucas" //Ocupacion 
           );
           */
           //gestorH.DarAltaHuesped(nuevoDTO);
       
      // h1.verNombreYApellido();
       //h1.verFechaNacimiento();
       
      
    }
}
