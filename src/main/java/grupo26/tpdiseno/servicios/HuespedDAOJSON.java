package grupo26.tpdiseno.servicios;

import grupo26.tpdiseno.entidades.DatosInvalidosException;
import grupo26.tpdiseno.entidades.DocumentoUsadoException;
import grupo26.tpdiseno.entidades.FiltroBusquedaHuesped;
import grupo26.tpdiseno.entidades.Huesped;
import grupo26.tpdiseno.entidades.PantallaBuscarHuesped;
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

         if (archivo.exists()) {
                try (BufferedReader lector = new BufferedReader(
                        new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                    String linea;
                     while ((linea = lector.readLine()) != null) {
                         if (linea.trim().equals("]")) break;

                         contenido.append(linea.trim()).append("\n");
                 }
                 }   catch (IOException e) {
                 System.out.println("Error leyendo archivo: " + e.getMessage());
                    }
                } else {
                contenido.append("[\n");
           }           


        String tipoDoc = huesped.getTipoDocumento().name();
        String numeroDoc = String.valueOf(huesped.getDocumentacion());

        if (forzar == false && contenido.toString().contains("\"tipoDocumento\": \"" + tipoDoc + "\"") &&
            contenido.toString().contains("\"documento\": \"" + numeroDoc + "\"")) {
            throw new DocumentoUsadoException(
                "El documento " + tipoDoc + " " + numeroDoc + " ya esta registrado."
            );
        }

        if (!contenido.toString().trim().equals("[")) {
           contenido.append(",\n");
        }

        contenido.append("{\"huesped\": {")
                .append("\"nombre\": \"").append(huesped.getNombres()).append("\", ")
                .append("\"apellido\": \"").append(huesped.getApellido()).append("\", ")
                .append("\"edad\": ").append(huesped.getEdad()).append(", ")
                .append("\"tipoDocumento\": \"").append(tipoDoc).append("\", ")
                .append("\"documento\": \"").append(numeroDoc).append("\", ")
                .append("\"nacionalidad\": \"").append(huesped.getNacionalidad()).append("\", ")
                .append("\"email\": \"").append(huesped.getEmail()).append("\"")
                .append("}}")
                .append("]");

        try (BufferedWriter escritor = new BufferedWriter(
            new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
            escritor.write(contenido.toString());
        } catch (IOException e) {
            System.out.println("️ Alto ahi ha ocurrido un error escribiendo archivo: " + e.getMessage());
        }
    }
    
    @Override
    public void buscarHuesped(FiltroBusquedaHuesped filtro, List<String> lista){
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
                                 if(linea.contains("huesped")){
                                     lista.add(linea);
                                 }
                             }

                            }
                     
                     }catch (IOException e) {
                        System.out.println("Error leyendo archivo: " + e.getMessage());
                    }
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
    
    @Override
    public void eliminarHuesped(String huesped){
        //public void agregarHuesped(Huesped huesped, boolean forzar) throws DocumentoUsadoException
        StringBuilder contenido = new StringBuilder();

         if (archivo.exists()) {
                try (BufferedReader lector = new BufferedReader(
                        new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                    String linea;
                     while ((linea = lector.readLine()) != null) {
                         if (!linea.trim().equals(huesped)) contenido.append(linea.trim()).append("\n");
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
    
    public void modificarHuesped(int num, String huesped, String variable){
       StringBuilder contenido = new StringBuilder();
        
          int iniciovariable=0;
          int finVariable;
          String variableantigua, huespednew ;
         

         switch(num){//la idea es poder segun el numero elegir para cambiar
            case 1: 
                iniciovariable = huesped.indexOf("Nombre: ") + "Nombre: ".length();//aca buscamos las posiciones para saber el lugar por donde hacer el cambio, antes iba a usar el repleace pero podia haber erro si se repetian datos,ej :nombre Jose  y apellido Jose
                finVariable = huesped.indexOf(",", iniciovariable);

                  huespednew = huesped.substring(0, iniciovariable) + variable + huesped.substring(finVariable);//aca hacemos el cambio pa y lo asignamos a una nueva variable

                break;
            case 2: 
                iniciovariable = huesped.indexOf("Apellido: ") + "Apellido: ".length();
                finVariable = huesped.indexOf(",", iniciovariable);

                  huespednew = huesped.substring(0, iniciovariable) + variable + huesped.substring(finVariable);
                break;
            case 3: 
                
                break;
            case 4: 
                 
                break;
            case 5: 
                  
                    break;
            case 6: 
                
                break;
            case 7: 
                
                break;
            case 8: 
                
                break;
            case 9: 
               
                break;
            case 10: 

                break;
                
                
             case 11:
                            
                break;
             
             case 12:

                break;
             
             case 13:

                break;
             
             case 14:

                break;
                
             case 15:

                break;
             
             case 16:

                break;
                            
            case 17:

                break;
                
            case 18:

                break;
            case 19:

                break;
             
            default:
             ;
       
       
       
       
       
       
       
       
         if (archivo.exists()) {
                try (BufferedReader lector = new BufferedReader(
                        new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {

                    String linea;
                     while ((linea = lector.readLine()) != null) {
                         if (!linea.trim().equals(huesped)) contenido.append(linea.trim()).append("\n");
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
       
       
   } 
    
}



