package grupo26.tpdiseno.entidades;


public abstract class Persona {
    private String cuit;
    private String telefono;
    private String nacionalidad;
    private Direccion direccion;

    public Persona(String cuit, String telefono, String nacionalidad, Direccion direccion) {
        this.cuit = cuit;
        this.telefono = telefono;
        this.nacionalidad = nacionalidad;
        this.direccion = direccion;
    }
    
    public String getNacionalidad(){
        return nacionalidad;
    }
    
    
}
