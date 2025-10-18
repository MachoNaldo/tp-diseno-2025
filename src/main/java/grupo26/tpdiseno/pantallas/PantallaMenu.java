
package grupo26.tpdiseno.pantallas;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class PantallaMenu {

    private Scanner scanner;
    private  PantallaDarAltaHuesped pantallaDarAlta;
    private  PantallaBuscarHuesped pantallaBuscar;
    private  PantallaDarBajaHuesped pantallaEliminar;
    private  PantallaModificarHuesped pantallaModificar;

    public PantallaMenu() {
        this.scanner = new Scanner(System.in);
        this.pantallaDarAlta = new PantallaDarAltaHuesped();
        this.pantallaBuscar = new PantallaBuscarHuesped();
        this.pantallaEliminar = new PantallaDarBajaHuesped();
        this.pantallaModificar = new PantallaModificarHuesped();
    }

    public void mostrarMenu() {
        int opcion = 0;

        do {
            System.out.println("\n=== SISTEMA DE GESTION DE HUESPEDES ===");
            System.out.println("1. Dar de Alta Huesped");
            System.out.println("2. Buscar Huesped");
            System.out.println("3. Modificar Huesped");
            System.out.println("4. Dar de Baja Huesped");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                System.out.println(); // Línea en blanco

                switch (opcion) {
                    case 1:
                        pantallaDarAlta.DarAltaHuesped();
                        break;
                    case 2:
                        //pantallaBuscar.buscarHuesped();
                        pantallaModificar.ModificarHuesped();
                        break;
                    case 3:
                        
                        pantallaModificar.ModificarHuesped();
                        break;
                    case 4:
                        pantallaEliminar.DarBajaHuesped();
                        break;
                    case 0:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opcion invalida. Por favor, seleccione una opcion del 0 al 4.");
                }

                if (opcion != 0) {
                    System.out.println("\nPresione ENTER para continuar...");
                    scanner.nextLine();
                }

            } catch (Exception e) {
                System.out.println("Error: Debe ingresar un numero valido.");
                scanner.nextLine();
                opcion = -1;
            }

        } while (opcion != 0);
        scanner.close();
    }

}
