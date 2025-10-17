package grupo26.tpdiseno.servicios;

import grupo26.tpdiseno.entidades.SinConcordanciaException;
import grupo26.tpdiseno.entidades.DatosInvalidosException;
import grupo26.tpdiseno.entidades.DocumentoUsadoException;
import grupo26.tpdiseno.entidades.FiltroBusquedaHuesped;
import grupo26.tpdiseno.entidades.Huesped;
import grupo26.tpdiseno.pantallas.PantallaBuscarHuesped;
import grupo26.tpdiseno.entidades.TipoDoc;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HuespedDAOJSON implements HuespedDAO {
    private final File archivo = new File("datosoficiales.json");
    
    private static HuespedDAOJSON instancia;
    
    private HuespedDAOJSON() {}
    
    public static HuespedDAOJSON getInstancia() {
        if (instancia == null) {
            instancia = new HuespedDAOJSON();
        }
        return instancia;
    }
    @Override
    public void agregarHuesped(Huesped huesped, boolean forzar) throws DocumentoUsadoException {
        StringBuilder contenido = new StringBuilder();
         int contador = 1;

         if (archivo.exists()) {
                try (BufferedReader lector = new BufferedReader(
                        new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                    String linea;
                     while ((linea = lector.readLine()) != null) {
                         if(linea.contains("huesped")){
                             contador++;
                         }
                         else if (linea.trim().equals("]")) break;
                         contenido.append(linea.trim()).append("\n");
                 }
                 }   catch (IOException e) {
                 System.out.println("Error leyendo archivo: " + e.getMessage());
                    }
                }
         
         else {
                contenido.append("[");
                contenido.append("\n]");
           }           


        String tipoDoc = huesped.getTipoDocumento().name();
        String numeroDoc = String.valueOf(huesped.getDocumentacion());

        if (forzar == false && contenido.toString().contains("\"tipoDocumento\": \"" + tipoDoc + "\"") &&
            contenido.toString().contains("\"documento\": \"" + numeroDoc + "\"")) {
            throw new DocumentoUsadoException("El documento " + tipoDoc + " " + numeroDoc + " ya esta registrado.");
        }

        
        String nuevo = contenido.toString().substring(contenido.toString().indexOf("["), contenido.toString().lastIndexOf("}}\n"));
        contenido.setLength(0); //Esto limpia el archivo
        contenido.append(nuevo);
        contenido.append("}},\n");
        contenido.append("{\"huesped\": {")
                    .append("\"id\": \"").append(contador).append("\", ")
                    .append("\"nombre\": \"").append(huesped.getNombres()).append("\", ")
                    .append("\"apellido\": \"").append(huesped.getApellido()).append("\", ")
                    .append("\"edad\": ").append(huesped.getEdad()).append(", ")
                    .append("\"tipoDocumento\": \"").append(tipoDoc).append("\", ")
                    .append("\"documento\": \"").append(numeroDoc).append("\", ")
                    .append("\"nacionalidad\": \"").append(huesped.getNacionalidad()).append("\", ")
                    .append("\"email\": \"").append(huesped.getEmail()).append("\", ")
                    .append("\"hospedado\": \"").append(huesped.getHospedado() ? "si" : "no").append("\"")
                    .append("}}")
                    .append("\n]");
       

        try (BufferedWriter escritor = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            escritor.write(contenido.toString());
        } catch (IOException e) {
            System.out.println("️ Alto ahi ha ocurrido un error escribiendo archivo: " + e.getMessage());
        }
    }
    
    @Override
    public void buscarHuesped(FiltroBusquedaHuesped filtro, List<String> lista) throws SinConcordanciaException{
        if (archivo.exists()) {
                try (BufferedReader lector = new BufferedReader(
                        new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                        String linea;
                         while ((linea = lector.readLine()) != null) {
                                if (linea.trim().equals("]")){
                                    
                                        if(filtro.getNombre() != null){
                                        filtrarPorNombre(lista, filtro.getNombre());
                                        }
                                        if(filtro.getApellido()!= null){
                                            filtrarPorApellido(lista, filtro.getApellido());
                                        }
                                        if(filtro.getTipoDoc() != null){
                                            filtrarPorTipoDoc(lista, filtro.getTipoDoc());
                                        }
                                        if(filtro.getNumDoc() != 0){
                                            filtrarPorNumDoc(lista, filtro.getNumDoc());
                                        }
                                 break;
                             }
                             else{
                                    //Hacemos que los registros solo muestren los datos que nos interesan
                                 if(linea.contains("huesped")){
                                     String registro;
                                     registro = linea.substring(linea.indexOf("{\"huesped\": "), linea.lastIndexOf("\"edad\": "));
                                     registro = (registro +  linea.substring(linea.indexOf("\"tipoDocumento\": "), linea.lastIndexOf("\", \"nacionalidad\": \"")) + "\"}");
                                     registro = linea.substring(linea.indexOf("{\"huesped\": "), linea.lastIndexOf("\"edad\": "));
                                     lista.add(registro);
                                 }
                             }
                            }
                     
                     }catch (IOException e) {
                        System.out.println("Error leyendo archivo: " + e.getMessage());
                    }
                }
        if(lista.isEmpty()) {throw new SinConcordanciaException("Sin concordancia con los datos de busqueda");}
    }
    
    @Override
    public void modificarHuesped(Huesped unHuesped, String unIndice){
    StringBuilder contenido = new StringBuilder();
         if (archivo.exists()) {
                try (BufferedReader lector = new BufferedReader(
                        new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                    
                    String linea;
                     while ((linea = lector.readLine()) != null) {
                         if (linea.trim().equals("]")){
                             break;
                         }
                         if(linea.contains(unIndice)){
                            contenido.append("{\"huesped\": {")
                            .append(unIndice.substring(unIndice.indexOf("\"id\": \""), unIndice.lastIndexOf("\"nombre\": \"")))
                            .append("\"nombre\": \"").append(unHuesped.getNombres()).append("\", ")
                            .append("\"apellido\": \"").append(unHuesped.getApellido()).append("\", ")
                            .append("\"edad\": ").append(unHuesped.getEdad()).append(", ")
                            .append("\"tipoDocumento\": \"").append(unHuesped.getTipoDocumento()).append("\", ")
                            .append("\"documento\": \"").append(unHuesped.getDocumentacion()).append("\", ")
                            .append("\"nacionalidad\": \"").append(unHuesped.getNacionalidad()).append("\", ")
                            .append("\"email\": \"").append(unHuesped.getEmail()).append("\", ")
                            .append(linea.substring(linea.indexOf("\"hospedado\": \""))).append("\n");
                          }
                         else{
                             contenido.append(linea.trim()).append("\n");
                         }    
                        }
                     contenido.append("]");
                 }catch (IOException e) {
                 System.out.println("Error leyendo archivo: " + e.getMessage());
                    }
                } else {
                contenido.append("[");
                contenido.append("\n]");
           }
       

        try (BufferedWriter escritor = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            escritor.write(contenido.toString());
        } catch (IOException e) {
            System.out.println("️ Alto ahi ha ocurrido un error escribiendo archivo: " + e.getMessage());
        }
    
    }
    
    @Override
    public void eliminarHuesped(String unIndice){
        boolean eliminarComa=false;
        //public void agregarHuesped(Huesped huesped, boolean forzar) throws DocumentoUsadoException
        StringBuilder contenido = new StringBuilder();
        int contador = 1;

         if (archivo.exists()) {
                try (BufferedReader lector = new BufferedReader(
                        new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                    String linea;
                     while ((linea = lector.readLine()) != null) {
                         if (linea.trim().equals("]")){
                             contenido.append(linea.trim());
                             break;
                         }
                         if(!linea.contains(unIndice)){
                             if(linea.contains("huesped")){
                                if(linea.contains("\"id\": \"" + (contador+1))){
                                    linea = linea.replace("\"id\": \"" + (contador + 1), ("\"id\": \"" + contador));
                                }
                                contador++;
                             }
                             contenido.append(linea).append("\n");
                         }
                 }
                     
                 }   catch (IOException e) {
                 System.out.println("Error leyendo archivo: " + e.getMessage());
                    }
                } else {
                contenido.append("[\n");
           }           

     



        try (BufferedWriter escritor = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            escritor.write(contenido.toString());
        } catch (IOException e) {
            System.out.println("️ Alto ahi ha ocurrido un error escribiendo archivo: " + e.getMessage());
        }
    }
    
    //PARA FILTRAR LA LINEAS DEL ARCHIVO
    public void filtrarPorNombre(List<String> lista, String unNombre){
        List<String> nuevaLista = new ArrayList();
        for(String i: lista){
            if(i.contains(("\"nombre\": \"" + unNombre + "\""))){
                nuevaLista.add(i);
            }
        }
        lista.clear();
        lista.addAll(nuevaLista);
    }
    public void filtrarPorApellido(List<String> lista, String unApellido){
        List<String> nuevaLista = new ArrayList();
        
        for(String i: lista){
            if(i.contains(("\"apellido\": \"" + unApellido + "\""))){
                nuevaLista.add(i);
            }
        }
        lista.clear();
        lista.addAll(nuevaLista);
    }
    public void filtrarPorTipoDoc(List<String> lista, TipoDoc unTipoDoc){
        List<String> nuevaLista = new ArrayList();
        
        for(String i: lista){
            if(i.contains(("\"tipoDocumento\": \"" + unTipoDoc + "\""))){
                nuevaLista.add(i);
            }
        }
        lista.clear();
        lista.addAll(nuevaLista);
    }
    public void filtrarPorNumDoc(List<String> lista, int unNumDoc){
        List<String> nuevaLista = new ArrayList();
        
        for(String i: lista){
            if(i.contains(("\"documento\": \"" + unNumDoc + "\""))){
                nuevaLista.add(i);
            }
        }
        lista.clear();
        lista.addAll(nuevaLista);
    }
    
    
   
        
}
