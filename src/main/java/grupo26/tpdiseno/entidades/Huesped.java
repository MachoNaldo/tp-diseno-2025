package grupo26.tpdiseno.entidades;

import java.util.Date;
import java.text.SimpleDateFormat;


public class Huesped extends Persona{
    private String nombres;
    private String apellido;
    private int edad;
    private TipoSexo sexo;
    private int documentacion;
    private Date fechaNacimiento;
    private TipoConsumidor consumidorFinal;
    private String email;
    private String ocupacion;

    
    public Huesped(HuespedDTO unDTO){
         super(unDTO.cuit, unDTO.telefono, unDTO.nacionalidad, unDTO.direccion);
         this.nombres = unDTO.nombres;
         this.apellido = unDTO.apellido;
         this.edad = unDTO.edad;
         this.sexo = unDTO.sexo;
         this.documentacion = unDTO.documentacion;
         this.fechaNacimiento = unDTO.fechaNacimiento;
         this.consumidorFinal = unDTO.consumidorFinal;
         this.email = unDTO.email;
         this.ocupacion = unDTO.ocupacion;
        
    }

   

    public String getNombres() {
        return nombres;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDocumentacion() {
        return documentacion;
    }

    @Override
    public String toString() {
        return "Huesped{" + "nombres=" + nombres + ", apellido=" + apellido + ", edad=" + edad + ", sexo=" + sexo + ", documentacion=" + documentacion + ", fechaNacimiento=" + fechaNacimiento + ", consumidorFinal=" + consumidorFinal + ", email=" + email + ", ocupacion=" + ocupacion + '}';
    }
    
    
    
      public void verNombreYApellido(){
        System.out.println("Nombre: " + this.nombres);
        System.out.println("Apellido: " + this.apellido);
        }
    
      
      public void verFechaNacimiento(){
          
          SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
          
          System.out.println(formato.format(fechaNacimiento));
        }
      
      
     
}
