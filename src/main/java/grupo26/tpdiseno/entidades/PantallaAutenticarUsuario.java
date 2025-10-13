package grupo26.tpdiseno.entidades;

import java.util.Scanner;


public class PantallaAutenticarUsuario {
    Scanner sc = new Scanner(System.in);
    
    
    public void iniciarSesion(){
        GestorUsuario gestor = new GestorUsuario();
        
        System.out.println("Ingrese Nombre de Usuario: ");
        String nombre = sc.nextLine();
        System.out.println("Ingrese Clave: ");
        String contra = sc.nextLine();
       
        gestor.autenticarUsuario(nombre, contra);
    
        sc.close();
    }
    
}
