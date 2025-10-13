/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.servicios;

import grupo26.tpdiseno.entidades.DatosInvalidosException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Gabri
 */
public class UsuarioDAOJSON implements UsuarioDAO{
    private final File archivo = new File("datosoficiales.json");
    private static UsuarioDAOJSON instancia;
    
    private UsuarioDAOJSON() {}
    
    public static UsuarioDAOJSON getInstancia() {
        if (instancia == null) {
            instancia = new UsuarioDAOJSON();
        }
        return instancia;
    }
    
    @Override
    public boolean autenticarUsuario (String unNombre, String unaContra) throws DatosInvalidosException{
        
        if (archivo.exists()) {
                try (BufferedReader lector = new BufferedReader(
                        new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                    String linea;
                     while ((linea = lector.readLine()) != null) {
                         if (linea.trim().equals("]")) break;

                         
                         if (linea.contains("\"nombreUsuario\": \"" + unNombre + "\"") &&
                            linea.contains("\"clave\": \"" + unaContra + "\"")) {
                                  return true;
                            }
                         
                         
                         
                 } throw new DatosInvalidosException("Nombre de usuario o Clave incorrectos");
                 }   catch (IOException e) {
                 System.out.println("Error leyendo archivo: " + e.getMessage());
                    }
                } 
        
        
        return false;
    }
    
}
