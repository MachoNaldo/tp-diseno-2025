/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.entidades;

/**
 *
 * @author Gabri
 */
public class FiltroBusquedaHuespedBUILDER {
    
        private String nombre;
        private String apellido;
        private TipoDoc tipoDoc;
        private int numDoc;
    
        
        
        public FiltroBusquedaHuespedBUILDER nombre(String unNombre) {
            this.nombre = unNombre;
            return this;
        }

        public FiltroBusquedaHuespedBUILDER apellido(String unApellido) {
            this.apellido = unApellido;
            return this;
        }

        public FiltroBusquedaHuespedBUILDER tipoDoc(TipoDoc unTipoDoc) {
            this.tipoDoc = unTipoDoc;
            return this;
        }

        public FiltroBusquedaHuespedBUILDER numDoc(int unNumDoc) {
            this.numDoc = unNumDoc;
            return this;
        }
        
        public FiltroBusquedaHuesped Build(){
            return (new FiltroBusquedaHuesped(this.nombre, this.apellido, this.tipoDoc, this.numDoc));
        }
                 
        
        
}
