/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.servicios;

import grupo26.tpdiseno.entidades.DatosInvalidosException;
import grupo26.tpdiseno.entidades.DocumentoUsadoException;

/**
 *
 * @author Gabri
 */
public interface UsuarioDAO {
    
    boolean autenticarUsuario (String unNombre, String unaContra) throws DatosInvalidosException;
    
    
}
