package grupo26.tpdiseno.pantallas;

import grupo26.tpdiseno.GestorUsuario;
import java.util.Scanner;


public class PantallaAutenticarUsuario {
    Scanner sc = new Scanner(System.in);
    
    
    public void iniciarSesion(){
        GestorUsuario gestor = GestorUsuario.getInstancia();
        
        System.out.println("Ingrese Nombre de Usuario: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese Clave: ");
        String contra = sc.nextLine();
       
        gestor.autenticarUsuario(nombre, contra);
    
        sc.close();
    }
    
}
