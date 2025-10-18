package grupo26.tpdiseno.pantallas;

import grupo26.tpdiseno.GestorUsuario;
import grupo26.tpdiseno.entidades.DatosInvalidosException;
import java.util.Scanner;


public class PantallaAutenticarUsuario {
    Scanner sc = new Scanner(System.in);
    
    
    public boolean iniciarSesion(){
        GestorUsuario gestor = GestorUsuario.getInstancia();
        boolean autenticado = false;
        boolean continuar = true;
        
       while(!autenticado && continuar){
        System.out.println("Ingrese Nombre de Usuario: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese Clave: ");
        String contra = sc.nextLine();
        try{
            gestor.autenticarUsuario(nombre, contra);
            autenticado = true;
            return true;
        }
        catch (DatosInvalidosException di){
            System.out.println(di);
            System.out.print("\nDesea reintentar? (Si/No): ");
            String respuesta = sc.nextLine().trim().toUpperCase();
                System.out.println(respuesta);
                if (respuesta.equals("NO")) {
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                }
        }
        
       }
      //  sc.close();
      return false;
    }
    
}
