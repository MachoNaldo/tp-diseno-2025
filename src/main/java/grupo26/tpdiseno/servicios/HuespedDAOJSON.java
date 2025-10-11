/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.servicios;
import grupo26.tpdiseno.entidades.DocumentoUsadoException;
import grupo26.tpdiseno.entidades.Huesped;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Usuario
 */
public class HuespedDAOJSON implements HuespedDAO{
    File archivo = new File("datos.json");
    
    @Override
    public void agregarHuesped(Huesped huesped) {
        
        try (BufferedReader lector = new BufferedReader(
        new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
               StringBuilder datos = new StringBuilder();
               String linea;
               while((linea = lector.readLine()) != null){
                   datos.append(linea);
                   System.out.println(lector.readLine());
               }
        }
        catch (IOException e){
            
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Huesped buscarHuesped(Huesped huesped) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean verificarHuesped(Huesped huesped) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void prueba(Huesped huesped) throws DocumentoUsadoException{
        StringBuilder datos = new StringBuilder();
        File archivo = new File("datos.json");
        try (BufferedReader lector = new BufferedReader(
        new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
               
               String linea;
               while(!(linea = lector.readLine()).contains("]") ){
                       //System.out.println("Huesped detectad");
                       datos.append(linea);
                       if(linea.contains("[")){
                            datos.append("\n");
                        }
                       if(linea.contains("}},")){
                            datos.append("\n");
                        }
                   
               }

        }

        catch (IOException e){
            
        }
        System.out.println(datos);
        if(datos.toString().contains("\"documento\": \""+huesped.getDocumentacion()+"\"")){
            throw new DocumentoUsadoException("Documento "+huesped.getDocumentacion()+" usado");
        }
        String nuevoHuesped = ",\n {\"huesped\": {\"nombre\": \"" + huesped.getNombres() + "\", \"edad\": "+ huesped.getEdad() + ", \"documento\": \""+ huesped.getDocumentacion()+"\"}}\n]";
        datos.append(nuevoHuesped);
        try (BufferedWriter escritor = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream(archivo), StandardCharsets.UTF_8))) {
               escritor.write(datos.toString());
        }
        catch(IOException e){
        
        }
        
    }
    
}
