/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.pantallas;

import grupo26.tpdiseno.entidades.FiltroBusquedaHuespedBUILDER;
import grupo26.tpdiseno.entidades.HuespedDTO;
import grupo26.tpdiseno.gestores.GestorHuesped;
import grupo26.tpdiseno.entidades.TipoDoc;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class PantallaBuscarHuesped {

    Scanner sc = new Scanner(System.in);

    public void buscarHuesped() {

        FiltroBusquedaHuespedBUILDER builder = new FiltroBusquedaHuespedBUILDER();

       
        
         int eleccion = 0;
        //System.out.println("\nPresione ENTER para mantener el valor actual, o escriba el nuevo valor.\n");
        System.out.println("Filtros de busqueda: ");
        do{
            System.out.println("\n");
            if(eleccion==1)System.out.print("*");
            System.out.println("Nombre - 1");
            if(eleccion==2)System.out.print("*");
            System.out.println("Apellido - 2");
            if(eleccion==3)System.out.print("*");
            System.out.println("Tipo de Documento - 3");
            if(eleccion==4)System.out.print("*");
            System.out.println("Numero de Documento - 4");
            
            System.out.println("\nCONFIRMAR - 5");
            System.out.println("CANCELAR - 6");
            System.out.println("\n Respuesta: ");
            eleccion = sc.nextInt();
            sc.nextLine();
            switch(eleccion){
            case 1:
                                        System.out.print("Ingrese un nombre: ");
                                        String unNombre = sc.nextLine().trim();
                                        if (!unNombre.isEmpty()) {
                                            builder.nombre(unNombre);
                                        }
                                        else{
                                            System.out.println("No se cargo ningun valor al filtro");
                                        }
                break;
                case 2:
                                System.out.print("Ingrese un apellido: ");
                                String unApellido = sc.nextLine().trim();
                                if (!unApellido.isEmpty()) {
                                    builder.apellido(unApellido);
                                }
                                else{
                                    System.out.println("No se cargo ningun valor al filtro");
                                }
                break;
                case 3:
                                TipoDoc unTipoDoc;
                                            System.out.println("Ingrese un tipo de documento: ");
                                            System.out.println("1 - DNI");
                                            System.out.println("2 - LE");
                                            System.out.println("3 - LC");
                                            System.out.println("4 - Pasaporte");
                                            System.out.println("5 - Otro");
                                            System.out.println("6 - CANCELAR");
                                            int eleccionDoc = sc.nextInt();
                                            sc.nextLine();
                                            switch (eleccionDoc) {
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
                                                    if (eleccionDoc == 6)  {
                                       builder.tipoDoc(unTipoDoc);
                                   }
                                   else{
                                       System.out.println("No se cargo ningun valor al filtro");
                                   }
                                break;
                case 4:
                              System.out.print("Ingrese un Numero de documento: ");
                                int unDocumento = sc.nextInt();
                                if(unDocumento != 0){
                                    builder.numDoc(unDocumento);
                                }
                                else{
                                    System.out.println("No se cargo ningun valor al filtro");
                                }
                    break;
            case 5:
                System.out.println("Buscando Huesped");
                break;
                
            case 6:
                System.out.println("Operacion Cancelada");
                break;
            default:
                System.out.println("Seleccion invalida, intentelo de nuevo");
                break;
        }
            
         }while(eleccion!=5 && eleccion!=6);
        
            
            
          //Prueba
        /*
        System.out.println("Ingresar Nombre? (si/no): ");
        if (sc.nextLine().equals("si")) {
            System.out.println("Nombre: ");
            builder.nombre(sc.nextLine());

        }
        System.out.println("Ingresar Apellido? (si/no): ");
        if (sc.nextLine().equals("si")) {
            System.out.println("Apellido: ");
            builder.apellido(sc.nextLine());
        }

        System.out.println("Ingresar Tipo de Documento? (si/no): ");
        if (sc.nextLine().equals("si")) {

            TipoDoc unTipoDoc;

            System.out.println("Tipo de documento: ");
            System.out.println("1 - DNI");
            System.out.println("2 - LE");
            System.out.println("3 - LC");
            System.out.println("4 - Pasaporte");
            System.out.println("5 - Otro");

            switch (sc.nextInt()) {
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
        if (sc.nextLine().equals("si")) {
            System.out.println("Num-Documento: ");
            builder.numDoc(sc.nextInt());
        }
        
        */

        GestorHuesped gestor = GestorHuesped.getInstancia();

        //Construimos el filtro usando el patron BUILDER
        List<HuespedDTO> lista = gestor.buscarHuesped(builder.Build());
        HuespedDTO huesped = this.seleccionarHuesped(lista);
        System.out.println("Ha seleccionado el huesped: ");
        System.out.println(huesped.toString());
        System.out.println("¿Que desea hacer con el huesped?");
        System.out.println("1. Modificar");
        System.out.println("2. Eliminar");
        System.out.println("3. Cancelar");

        int opcion;
        do {
            while (!sc.hasNextInt()) {
                System.out.print("Opcion invalida. Ingrese 1, 2 o 3: ");
                sc.next();
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    PantallaModificarHuesped panModificar = new PantallaModificarHuesped();
                    panModificar.modificarHuesped(huesped);
                    return;
                case 2:
                    gestor.eliminarHuesped(String.valueOf(huesped.getId()));
                    System.out.print("El huesped fue eliminado exitosamente");
                    return;
                case 3:
                    return;
                default:
                    System.out.print("Opción invalida. Ingrese 1, 2 o 3: ");
            }
        } while (true);
        
        
    }

    public HuespedDTO seleccionarHuesped(List<HuespedDTO> unaLista) {
        if (unaLista == null || unaLista.isEmpty()) {
            System.out.println("No hay huespedes disponibles para seleccionar.");
            return null;
        }
        System.out.println("Lista de huespedes:");
        for (HuespedDTO h : unaLista) {
            System.out.println(h.toString());
        }
        int eleccion;
        HuespedDTO seleccionado = null;
        do {
            System.out.print("Seleccione un huesped escribiendo su ID: ");
            while (!sc.hasNextInt()) {
                System.out.print("Entrada no valida. Ingrese un numero de ID: ");
                sc.next();
            }
            eleccion = sc.nextInt();
            for (HuespedDTO h : unaLista) {
                if (h.getId() == eleccion) {
                    seleccionado = h;
                    break;
                }
            }
            if (seleccionado == null) {
                System.out.println("ID no valido, ingrese otro:");
            }
        } while (seleccionado == null);

        return seleccionado;
    }

}
