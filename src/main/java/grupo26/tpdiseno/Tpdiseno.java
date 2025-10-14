package grupo26.tpdiseno;


import grupo26.tpdiseno.entidades.*;
import grupo26.tpdiseno.servicios.*;
import java.util.ArrayList;
import java.util.List;


public class Tpdiseno {

    public static void main(String[] args) {
    

       PantallaAutenticarUsuario pantallaUsuario = new PantallaAutenticarUsuario();
       //pantallaUsuario.iniciarSesion();
       
       PantallaDarAltaHuesped pantallaDarAlta = new PantallaDarAltaHuesped();
       //pantallaDarAlta.DarAltaHuesped();
       
       PantallaBuscarHuesped  pantallaBuscar = new PantallaBuscarHuesped();
       pantallaBuscar.verLista(pantallaBuscar.buscarHuesped());


       
      
    }
}
