package grupo26.tpdiseno;


import grupo26.tpdiseno.pantallas.PantallaDarAltaHuesped;
import grupo26.tpdiseno.pantallas.PantallaDarBajaHuesped;
import grupo26.tpdiseno.pantallas.PantallaBuscarHuesped;
import grupo26.tpdiseno.pantallas.PantallaAutenticarUsuario;
import grupo26.tpdiseno.pantallas.PantallaModificarHuesped;
import grupo26.tpdiseno.entidades.*;
import grupo26.tpdiseno.pantallas.PantallaMenu;
import grupo26.tpdiseno.servicios.*;
import java.util.ArrayList;
import java.util.List;


public class Tpdiseno {
    
    public static void main(String[] args) {
    
        //DTO SOLO PARA PROBAR RAPIDO
         FechaFunciones fecha = new FechaFunciones() {};
         HuespedDTO nuevoDTO;
         nuevoDTO = new HuespedDTO(
                "20-12345678-9",          // cuit
                "12345678",             // telefono
                "Argentina",              // nacionalidad
                new Direccion("Aguafiestas", 123, "3001", "Santa Fe", "Argentina", "Piso 2", "El Solar"),  //Direccion
                "Eduardo",                   // nombres
                "Sanabria",                  // apellido
                44,                       // edad
                TipoSexo.Masculino,       // sexo
                TipoDoc.DNI,               //tipo doc perro
                20227731,                 // documentacion
                fecha.CrearFecha(8, 7, 2001), //FechaNacimiento
                TipoConsumidor.A,//Tipo de consumidor
                "Gabriel@hotmail.com",//email
                "Vendedor de tutucas" //Ocupacion
         );
       
       PantallaMenu pantallaMenu = new PantallaMenu();
       pantallaMenu.mostrarMenu();
       //if(pantallaUsuario.iniciarSesion())pantallaMenu.mostrarMenu();
       //PantallaDarAltaHuesped pantallaDarAlta = new PantallaDarAltaHuesped();
        //pantallaDarAlta.DarAltaHuesped(); //Por Teclado
        //pantallaDarAlta.DarAltaHuesped(nuevoDTO); //DTO Precargado
    }
}
