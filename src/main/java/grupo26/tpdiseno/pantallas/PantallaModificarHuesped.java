/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.pantallas;

import grupo26.tpdiseno.gestores.GestorHuesped;
import grupo26.tpdiseno.entidades.HuespedDTO;
import grupo26.tpdiseno.entidades.TipoDoc;
import java.util.Scanner;

/**
 *
 * @author Gabri
 */
public class PantallaModificarHuesped {

    Scanner sc = new Scanner(System.in);
    GestorHuesped gestorH;

    public PantallaModificarHuesped() {
        this.gestorH = GestorHuesped.getInstancia();
    }

    public void modificarHuesped(HuespedDTO huesped) {
        int eleccion = 0;
        boolean b1 = false, b2 = false, b3 = false, b4 = false, b5 = false, b6 = false, b7 = false;
        //System.out.println("\nPresione ENTER para mantener el valor actual, o escriba el nuevo valor.\n");
        System.out.println("¿Que dato desea modificar?");
        do {
            System.out.println("\n");
            if (b1 == true) {
                System.out.print("*");
            }
            System.out.println("Nombre - 1");
            if (b2 == true) {
                System.out.print("*");
            }
            System.out.println("Apellido - 2");
            if (b3 == true) {
                System.out.print("*");
            }
            System.out.println("Edad - 3");
            if (b4 == true) {
                System.out.print("*");
            }
            System.out.println("Tipo de Documento - 4");
            if (b5 == true) {
                System.out.print("*");
            }
            System.out.println("Numero de Documento - 5");
            if (b6 == true) {
                System.out.print("*");
            }
            System.out.println("Nacionalidad - 6");
            if (b7 == true) {
                System.out.print("*");
            }
            System.out.println("email - 7");

            System.out.println("\nGUARDAR CAMBIOS - 8");
            System.out.println("CANCELAR - 9");
            System.out.println("\n Respuesta: ");
            eleccion = sc.nextInt();
            sc.nextLine();
            switch (eleccion) {
                case 1:
                    System.out.print("Nombre actual: " + huesped.getNombres() + "\nNuevo nombre: ");
                    String nuevoNombre = sc.nextLine().trim();
                    if (!nuevoNombre.isEmpty()) {
                        huesped.setNombres(nuevoNombre);
                        b1 = true;
                    } else {
                        System.out.println("Se conservo el valor original");
                    }
                    break;
                case 2:
                    System.out.print("Apellido actual: " + huesped.getApellido() + "\nNuevo apellido: ");
                    String nuevoApellido = sc.nextLine().trim();
                    if (!nuevoApellido.isEmpty()) {
                        huesped.setApellido(nuevoApellido);
                        b2 = true;
                    } else {
                        System.out.println("Se conservo el valor original");
                    }
                    break;
                case 3:
                    System.out.print("Edad actual: " + huesped.getEdad() + "\nNueva edad: ");
                    String edadStr = sc.nextLine().trim();
                    if (!edadStr.isEmpty()) {
                        try {
                            int nuevaEdad = Integer.parseInt(edadStr);
                            huesped.setEdad(nuevaEdad);
                            b3 = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Edad inválida, se mantendra la edad actual.");
                        }
                    } else {
                        System.out.println("Se conservo el valor original");
                    }
                    break;
                case 4:
                    System.out.print("Tipo documento actual: " + huesped.getTipoDocumento() + "\nNuevo tipo (DNI, LE, LC, PASAPORTE, OTRO): ");
                    String tipoDocStr = sc.nextLine().trim().toUpperCase();
                    if (!tipoDocStr.isEmpty()) {
                        try {
                            TipoDoc nuevoTipoDoc = TipoDoc.valueOf(tipoDocStr);
                            huesped.setTipoDocumento(nuevoTipoDoc);
                            b4 = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Tipo de documento inválido, se mantiene el actual.");
                        }
                    } else {
                        System.out.println("Se conservo el valor original");
                    }
                    break;
                case 5:
                    System.out.print("Documento actual: " + huesped.getDocumentacion() + "\nNuevo documento: ");
                    String docStr = sc.nextLine().trim();
                    if (!docStr.isEmpty()) {
                        try {
                            int nuevoDoc = Integer.parseInt(docStr);
                            huesped.setDocumentacion(nuevoDoc);
                            b5 = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Documento inválido, se mantiene el actual.");
                        }
                    } else {
                        System.out.println("Se conservo el valor original");
                    }
                    break;
                case 6:
                    System.out.print("Nacionalidad actual: " + huesped.getNacionalidad() + "\nNueva nacionalidad: ");
                    String nuevaNacionalidad = sc.nextLine().trim();
                    if (!nuevaNacionalidad.isEmpty()) {
                        huesped.setNacionalidad(nuevaNacionalidad);
                        b6 = true;
                    } else {
                        System.out.println("Se conservo el valor original");
                    }
                    break;
                case 7:
                    System.out.print("Email actual: " + huesped.getEmail() + "\nNuevo email: ");
                    String nuevoEmail = sc.nextLine().trim();
                    if (!nuevoEmail.isEmpty()) {
                        huesped.setEmail(nuevoEmail);
                        b7 = true;
                    } else {
                        System.out.println("Se conservo el valor original");
                    }
                    break;
                case 8:
                    System.out.println("\n El huesped sera ingresado con los siguientes datos:");
                    System.out.println(huesped);
                    gestorH.modificarHuesped(huesped);
                    System.out.print("El huesped fue modificado exitosamente");
                    break;
                case 9:
                    System.out.println("Operacion cancelada");
                    break;
                default:
                    System.out.println("Seleccion invalida, intentelo de nuevo");
                    break;
            }
        } while (eleccion != 8 && eleccion != 9);

    }
}
