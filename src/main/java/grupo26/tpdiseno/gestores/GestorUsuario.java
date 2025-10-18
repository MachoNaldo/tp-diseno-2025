package grupo26.tpdiseno;

import grupo26.tpdiseno.entidades.DatosInvalidosException;
import grupo26.tpdiseno.servicios.UsuarioDAOJSON;

/*La contraseña tendrá al menos 5 letras y 3 números no iguales ni
consecutivos en forma creciente o decreciente.*/


public class GestorUsuario {
    
 private static GestorUsuario instancia;
 private final UsuarioDAOJSON uDAO;

 private GestorUsuario() {
     this.uDAO = UsuarioDAOJSON.getInstancia();
 }
    
 public static GestorUsuario getInstancia() {
    if (instancia == null) {
        instancia = new GestorUsuario();
    }
    return instancia;
 }
    
  
 public void autenticarUsuario (String unNombre, String unaContra) throws DatosInvalidosException{
        if(uDAO.autenticarUsuario(unNombre, unaContra)){
            System.out.println("Datos Correctos");
        }
 }
    
 public static int contarLetras(String texto) {
    int contador = 0;

    for (int i = 0; i < texto.length(); i++) {
        char c = texto.charAt(i);
        if (Character.isLetter(c)) {
            contador++;
        }
    }

    return contador;
}

  public static int contarNum(String texto) {
    int contador = 0;

    for (int i = 0; i < texto.length(); i++) {
        char c = texto.charAt(i);
        if (Character.isDigit(c)) {
            contador++;
        }
    }

    return contador;
}
    
    
}

