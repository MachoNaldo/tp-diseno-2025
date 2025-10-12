package grupo26.tpdiseno.entidades;

/*La contraseña tendrá al menos 5 letras y 3 números no iguales ni
consecutivos en forma creciente o decreciente.*/


public class GestorUsuario {
    
    public boolean autenticarUsuario(String unUsuario, String unaContra){
        
        if(unaContra.length()>=8 && contarLetras(unaContra) >=5 && contarNum(unaContra) >=3){
            return true;
        }
        else{
            return false;
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

