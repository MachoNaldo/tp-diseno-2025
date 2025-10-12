package grupo26.tpdiseno.entidades;
import java.util.Date;
import java.io.Serializable;



public class HuespedDTO implements Serializable{
    final String cuit;
    final String telefono;
    final String nacionalidad;
    final Direccion direccion;
    
    final String nombres;
    final String apellido;
    final int edad;
    final TipoSexo sexo;
    final TipoDoc tipoDocumento;
    final int documentacion;
    final Date fechaNacimiento;
    final TipoConsumidor consumidorFinal;
    final String email;
    final String ocupacion;

    
    

    
    public HuespedDTO(
            String cuit, 
            String telefono, 
            String nacionalidad, 
            Direccion direccion, 
            String nombres, 
            String apellido, 
            int edad, 
            TipoSexo sexo, 
            TipoDoc tipoDocumento,
            int documentacion, 
            Date fechaNacimiento, 
            TipoConsumidor consumidorFinal, 
            String email, 
            String ocupacion) {
        
        this.cuit = cuit;
        this.telefono = telefono;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
        this.nombres = nombres;
        this.apellido = apellido;
        this.edad = edad;
        this.sexo = sexo;
        this.tipoDocumento = tipoDocumento;
        this.documentacion = documentacion;
        this.fechaNacimiento = fechaNacimiento;
        this.consumidorFinal = consumidorFinal;
        this.email = email;
        this.ocupacion = ocupacion;
    }

  
    
}
