package grupo26.tpdiseno.entidades;


public class FiltroBusquedaHuesped {
    private String nombre;
    private String apellido;
    private TipoDoc tipoDoc;
    private int numDoc;

     public FiltroBusquedaHuesped(String unNombre, String unApellido, TipoDoc unTipoDoc, int unNumDoc) {
        this.nombre = unNombre;
        this.apellido = unApellido;
        this.tipoDoc = unTipoDoc;
        this.numDoc = unNumDoc;
    }
     
    public String getNombre() { 
        return this.nombre;
    }
    public String getApellido() { 
        return this.apellido;
    }
    public TipoDoc getTipoDoc() { 
        return this.tipoDoc; 
    }
    public int getNumDoc() { 
        return this.numDoc; 
    }
     
}

