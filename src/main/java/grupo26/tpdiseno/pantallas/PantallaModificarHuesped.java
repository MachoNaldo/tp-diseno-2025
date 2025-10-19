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

        System.out.println("\nPresione ENTER para mantener el valor actual, o escriba el nuevo valor.\n");

        System.out.print("Nombre actual: " + huesped.getNombres() + "\nNuevo nombre: ");
        String nuevoNombre = sc.nextLine().trim();
        if (!nuevoNombre.isEmpty()) {
            huesped.setNombres(nuevoNombre);
        }

        System.out.print("Apellido actual: " + huesped.getApellido() + "\nNuevo apellido: ");
        String nuevoApellido = sc.nextLine().trim();
        if (!nuevoApellido.isEmpty()) {
            huesped.setApellido(nuevoApellido);
        }

        System.out.print("Edad actual: " + huesped.getEdad() + "\nNueva edad: ");
        String edadStr = sc.nextLine().trim();
        if (!edadStr.isEmpty()) {
            try {
                int nuevaEdad = Integer.parseInt(edadStr);
                huesped.setEdad(nuevaEdad);
            } catch (NumberFormatException e) {
                System.out.println("Edad inválida, se mantendra la edad actual.");
            }
        }

        System.out.print("Tipo documento actual: " + huesped.getTipoDocumento() + "\nNuevo tipo (DNI, LE, LC, PASAPORTE, OTRO): ");
        String tipoDocStr = sc.nextLine().trim().toUpperCase();
        if (!tipoDocStr.isEmpty()) {
            try {
                TipoDoc nuevoTipoDoc = TipoDoc.valueOf(tipoDocStr);
                huesped.setTipoDocumento(nuevoTipoDoc);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo de documento inválido, se mantiene el actual.");
            }
        }

        System.out.print("Documento actual: " + huesped.getDocumentacion() + "\nNuevo documento: ");
        String docStr = sc.nextLine().trim();
        if (!docStr.isEmpty()) {
            try {
                int nuevoDoc = Integer.parseInt(docStr);
                huesped.setDocumentacion(nuevoDoc);
            } catch (NumberFormatException e) {
                System.out.println("Documento inválido, se mantiene el actual.");
            }
        }

        System.out.print("Nacionalidad actual: " + huesped.getNacionalidad() + "\nNueva nacionalidad: ");
        String nuevaNacionalidad = sc.nextLine().trim();
        if (!nuevaNacionalidad.isEmpty()) {
            huesped.setNacionalidad(nuevaNacionalidad);
        }

        System.out.print("Email actual: " + huesped.getEmail() + "\nNuevo email: ");
        String nuevoEmail = sc.nextLine().trim();
        if (!nuevoEmail.isEmpty()) {
            huesped.setEmail(nuevoEmail);
        }

        System.out.println("\n El huesped sera ingresado con los siguientes datos:");
        System.out.println(huesped);
        gestorH.modificarHuesped(huesped);
    }
}
