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

        GestorHuesped gestor = GestorHuesped.getInstancia();

        //Construimos el filtro usando el patron BUILDER
        List<HuespedDTO> lista = gestor.buscarHuesped(builder.Build());
        HuespedDTO huesped = this.seleccionarHuesped(lista);
        System.out.println("Ha seleccionado el huesped: ");
        System.out.println(huesped.toString());
        System.out.println("¿Qué desea hacer con el huésped?");
        System.out.println("1. Modificar");
        System.out.println("2. Eliminar");
        System.out.println("3. Cancelar");

        int opcion;
        do {
            while (!sc.hasNextInt()) {
                System.out.print("Opción inválida. Ingrese 1, 2 o 3: ");
                sc.next();
            }
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    PantallaModificarHuesped panModificar = new PantallaModificarHuesped();
                    panModificar.modificarHuesped(huesped);
                    System.out.print("El huesped fue modificado exitosamente");
                    return;
                case 2:
                    gestor.eliminarHuesped(String.valueOf(huesped.getId()));
                    System.out.print("El huesped fue eliminado exitosamente");
                    return;
                case 3:
                    return;
                default:
                    System.out.print("Opción inválida. Ingrese 1, 2 o 3: ");
            }
        } while (true);
        //return listaHuespedes;
    }

    public HuespedDTO seleccionarHuesped(List<HuespedDTO> unaLista) {
        if (unaLista == null || unaLista.isEmpty()) {
            System.out.println("No hay huéspedes disponibles para seleccionar.");
            return null;
        }
        System.out.println("Lista de huéspedes:");
        for (HuespedDTO h : unaLista) {
            System.out.println(h.toString());
        }
        int eleccion;
        HuespedDTO seleccionado = null;
        do {
            System.out.print("Seleccione un huésped escribiendo su ID: ");
            while (!sc.hasNextInt()) {
                System.out.print("Entrada no válida. Ingrese un número de ID: ");
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
                System.out.println("ID no válido, ingrese otro:");
            }
        } while (seleccionado == null);

        return seleccionado;
    }

}
