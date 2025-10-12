package grupo26.tpdiseno.entidades;

import grupo26.tpdiseno.servicios.UsuarioDAOJSON;

/*La contraseña tendrá al menos 5 letras y 3 números no iguales ni
consecutivos en forma creciente o decreciente.*/


public class GestorUsuario {
    
 public void autenticarUsuario (String unNombre, String unaContra){
      UsuarioDAOJSON uDAO = new UsuarioDAOJSON();
       try{
        if(uDAO.autenticarUsuario(unNombre, unaContra)){
            System.out.println("Datos Correctos");
        }
       } catch (DatosInvalidosException di){
           System.out.println(di);
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

