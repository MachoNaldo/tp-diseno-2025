/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo26.tpdiseno.entidades;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Frasco
 */
public class PantallaModificarHuesped{
     Scanner sc = new Scanner(System.in);

    public void ModificarHuesped(String huesped){
       
         String tipoSexo,unTipoDoc;
         TipoSexo unTipoSexo = null;
         String eleccion;
         TipoConsumidor unTipoConsumidor = null;
         boolean llave=true;
         boolean llave2=true;
         int dmenu, selector;
          GestorHuesped gestorH = GestorHuesped.getInstancia();
         
         while(llave){
         selector = sc.nextInt();
         switch(selector){
            case 1: 
                System.out.println("Ingrese su Apellido: ");
               String unApellido = sc.nextLine();
                 gestorH.modificarHuesped(1,huesped,unApellido);
                break;
            case 2: 
                System.out.println("Ingrese su Nombre: ");
                String unNombre = sc.nextLine();
                gestorH.modificarHuesped(2,huesped,unNombre);
                break;
            case 3: 
                System.out.println("Ingrese su Edad: ");
                int unaEdad = sc.nextInt();
                gestorH.modificarHuesped(3,huesped,String.valueOf(unaEdad));
                break;
            case 4: 
                System.out.print("Ingrese el Sexo (M/F): ");
                
                do {
                  tipoSexo = sc.nextLine();
                  } while (tipoSexo.length() != 1 ||  (!tipoSexo.equals("M") && !tipoSexo.equals("F")));
                 
                gestorH.modificarHuesped(4,huesped,tipoSexo); 
                break;
            case 5: 
                  while(llave2){
                    System.out.println("Ingrese tipo de documento: ");
                    System.out.println("1 - DNI");
                    System.out.println("2 - LE");
                    System.out.println("3 - LC");
                    System.out.println("4 - Pasaporte");
                    System.out.println("5 - Otro");
                   System.out.println("Ingrese su Documentacion: ");
                int unDocumento = sc.nextInt();
                switch(sc.nextInt()){
                case 1: 
                unTipoDoc = "DNI";
                break;
                case 2: 
                unTipoDoc = "LE";
                break;
                case 3: 
                unTipoDoc = "LC";
                break;
                case 4: 
                unTipoDoc = "PASAPORTE";
                break;
                case 5: 
                unTipoDoc = "OTRO";
                break;
                default:
                System.out.println("Caracter invalido ingresado, ingrese un caracter correcto");
                llave2=false;
                     }
                
                  }
                  
                  gestorH.modificarHuesped(5,huesped,unTipoDoc);
                    break;
            case 6: 
                System.out.println("Ingrese su fecha de nacimiento: ");
                System.out.println("Dia: ");
                int diaFecha = sc.nextInt();
         
                System.out.println("Mes: ");
                int mesFecha = sc.nextInt();
         
                System.out.println("Anio:");
                int anioFecha = sc.nextInt();
         
                LocalDate fechaLocal = LocalDate.of(anioFecha, mesFecha, diaFecha);
         
                Date fechaNacimiento = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
                
                gestorH.modificarHuesped(6,huesped,String.valueOf(fechaNacimiento));//SE PUEDE SIMPLIFICAR CODIGO SI SE ARMA LA CONCATENACION DE ENTEROS CON UN FORMATO EN UN STRING, PERO NO SE QUE FORMATO VAMOS A USAR; CAMBIAR LUEGO

                break;
            case 7: 
                Scanner sc = new Scanner(System.in);
                do {
                   System.out.print("Ingrese tipo de consumidor final (A/B): ");
                   eleccion = sc.nextLine();
                } while (eleccion.length() != 1 ||  (!eleccion.equals("A") && !eleccion.equals("B")));

                gestorH.modificarHuesped(7,huesped,eleccion);
                break;
            case 8: 
                System.out.println("Ingrese su email: "); //OPCIONAL
                String unEmail = sc.nextLine();
                gestorH.modificarHuesped(8,huesped,unEmail);
                break;
            case 9: 
                System.out.println("Ingrese su CUIT: "); //OPCIONAL
                String unCuit = sc.nextLine();
                gestorH.modificarHuesped(9,huesped,unCuit);
                break;
            case 10: 
                dmenu = sc.nextInt();
                switch(dmenu){
                    case 1:
                            System.out.println("Ingrese nombre de calle: ");
                            String unNombreDeCalle = sc.nextLine();
                            gestorH.modificarHuesped(10,huesped,unNombreDeCalle);
                            break;
                
                
                    case 2:
                            System.out.println("Ingrese numero de calle: ");
                            int numDeCalle = sc.nextInt();
                            gestorH.modificarHuesped(11,huesped,String.valueOf(numDeCalle));
                            break;
                    case 3:
                            System.out.println("Ingrese piso: ");
                            String unPiso = sc.nextLine();
                            gestorH.modificarHuesped(12,huesped,unPiso);
                            break;
                    case 4:
                            System.out.println("Ingrese un departamento: ");
                            String unDepartamento = sc.nextLine();
                            gestorH.modificarHuesped(13,huesped,unDepartamento);
                            break;
                    case 5:
                            System.out.println("Ingrese Localidad: ");
                            String unaLocalidad = sc.nextLine();
                            gestorH.modificarHuesped(14,huesped,unaLocalidad);
                            break;
                    case 6:
                            System.out.println("Ingrese Cod. Postal: ");
                            String unCodPostal = sc.nextLine();
                            gestorH.modificarHuesped(15,huesped,unCodPostal);
                            break;
                    case 7:
                            System.out.println("Ingrese Pais: ");
                            String unPais = sc.nextLine();
                            gestorH.modificarHuesped(16,huesped,unPais);
                            break;
                            }
            case 11:
                System.out.println("Ingrese un num telefono: ");
                String unTelefono = sc.nextLine();
                gestorH.modificarHuesped(17,huesped,unTelefono);
                 break;
            case 12:
                System.out.println("Ingrese su nacionalidad: ");
                String unaNacionalidad = sc.nextLine();
                gestorH.modificarHuesped(18,huesped,unaNacionalidad);
                break;
            case 13:
                 System.out.println("Ingrese su ocupacion: ");
                 String unaOcupacion = sc.nextLine();
                 gestorH.modificarHuesped(19,huesped,unaOcupacion);
                break;
            case 14:
                 llave=false;
                break;
             
            default:
             System.out.println("Caracter invalido ingresado, ingrese un caracter correcto");
         
         }
          sc.close();
         
        
       
        
       

        
    }

    
}
}
