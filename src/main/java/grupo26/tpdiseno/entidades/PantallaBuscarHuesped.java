/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.entidades;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PantallaBuscarHuesped {
    Scanner sc = new Scanner(System.in);
    
    
    
    
    
    public List<String> buscarHuesped(){
        
        FiltroBusquedaHuespedBUILDER builder = new FiltroBusquedaHuespedBUILDER();
        
        System.out.println("Ingresar Nombre? (si/no): ");
        if(sc.nextLine().equals("si")){
            System.out.println("Nombre: ");
            builder.nombre(sc.nextLine()); 
            
        }
        System.out.println("Ingresar Apellido? (si/no): ");
        if(sc.nextLine().equals("si")){
            System.out.println("Apellido: ");
            builder.apellido(sc.nextLine()); 
        }
        
        
        System.out.println("Ingresar Tipo de Documento? (si/no): ");
        if(sc.nextLine().equals("si")){
            
            TipoDoc unTipoDoc;

            System.out.println("Tipo de documento: ");
            System.out.println("1 - DNI");
            System.out.println("2 - LE");
            System.out.println("3 - LC");
            System.out.println("4 - Pasaporte");
            System.out.println("5 - Otro");
        
            switch(sc.nextInt()){
                case 1: 
                    unTipoDoc = TipoDoc.DNI;
                    break;
                case 2: 
                    unTipoDoc = TipoDoc.LE;
                    break;
                case 3: 
                    unTipoDoc = TipoDoc.LC;
                    break;
                case 4: 
                    unTipoDoc = TipoDoc.PASAPORTE;
                    break;
                case 5: 
                    unTipoDoc = TipoDoc.OTRO;
                    break;
                default:
                    unTipoDoc = TipoDoc.DNI;
            } 
            
            builder.tipoDoc(unTipoDoc);
        }
        
        
        System.out.println("Ingresar Num-Documento? (si/no): ");
        if(sc.nextLine().equals("si")){
            System.out.println("Num-Documento: ");
             builder.numDoc(sc.nextInt()); 
        }
        
        
        List<String> listaHuespedes = new ArrayList();
        GestorHuesped gestor = new GestorHuesped();
        
        //Construimos el filtro usando el patron BUILDER
        gestor.buscarHuesped(builder.Build(), listaHuespedes);
        
        return listaHuespedes;
        
    }
    
    public void verLista(List<String> unaLista){
        for(String i: unaLista){
            System.out.println(i);
        }
    }
}
