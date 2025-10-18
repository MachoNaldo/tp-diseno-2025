package grupo26.tpdiseno.pantallas;
import grupo26.tpdiseno.entidades.Direccion;
import grupo26.tpdiseno.entidades.DocumentoUsadoException;
import grupo26.tpdiseno.gestores.GestorHuesped;
import grupo26.tpdiseno.entidades.HuespedDTO;
import grupo26.tpdiseno.entidades.TipoConsumidor;
import grupo26.tpdiseno.entidades.TipoDoc;
import grupo26.tpdiseno.entidades.TipoSexo;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class PantallaDarAltaHuesped {
    
    Scanner sc = new Scanner(System.in);
  
    public void DarAltaHuesped(){
         GestorHuesped gestorH = GestorHuesped.getInstancia();
         HuespedDTO huesped = this.generarHuespedDTO();
         try{
         gestorH.DarAltaHuesped(huesped);
         } catch(DocumentoUsadoException dc){
             System.out.println("\n El huesped con la documentacion ingresada ya existe, desea ingresarlo de todas formas? (SI/NO)");
             String respuesta = sc.nextLine().trim().toUpperCase();
             if (respuesta.equals("SI")) {
                 gestorH.forzarHuesped(huesped);
            }
         }
    }

    public HuespedDTO generarHuespedDTO(){
       
         System.out.println("Ingrese su Apellido: ");
         String unApellido = sc.nextLine();
         
         System.out.println("Ingrese su Nombre: ");
         String unNombre = sc.nextLine();
         
         System.out.println("Ingrese su Edad: ");
         int unaEdad = sc.nextInt();
         
        
        String tipoSexo;
        TipoSexo unTipoSexo = null;

        System.out.print("Ingrese el Sexo (M/F): ");
        do {
         tipoSexo = sc.nextLine();
        } while (tipoSexo.length() != 1 ||  (!tipoSexo.equals("M") && !tipoSexo.equals("F")));

        switch (tipoSexo) {
             case "M" -> unTipoSexo = TipoSexo.Masculino;
             case "F" -> unTipoSexo = TipoSexo.Femenino;
        }
        
        
        TipoDoc unTipoDoc;
        
        System.out.println("Ingrese tipo de documento: ");
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
            

         
         System.out.println("Ingrese su Documentacion: ");
         int unDocumento = sc.nextInt();

         System.out.println("Ingrese su fecha de nacimiento: ");
         System.out.println("Dia: ");
         int diaFecha = sc.nextInt();
         
         System.out.println("Mes: ");
         int mesFecha = sc.nextInt();
         
         System.out.println("Anio:");
         int anioFecha = sc.nextInt();
         
         LocalDate fechaLocal = LocalDate.of(anioFecha, mesFecha, diaFecha);
         
         Date fechaNacimiento = Date.from(fechaLocal.atStartOfDay(ZoneId.systemDefault()).toInstant());
        
        
         //CONSUMIDOR FINAL
        String eleccion;
        TipoConsumidor unTipoConsumidor = null;

        do {
         System.out.print("Ingrese tipo de consumidor final (A/B): ");
         eleccion = sc.nextLine();
        } while (eleccion.length() != 1 ||  (!eleccion.equals("A") && !eleccion.equals("B")));

        switch (eleccion) {
             case "A":
                    unTipoConsumidor = TipoConsumidor.A;
                break;
            case "B":
                    unTipoConsumidor = TipoConsumidor.B;
                break;
        }   

        System.out.println("Tipo seleccionado: " + unTipoConsumidor);

         
         System.out.println("Ingrese su email: "); //OPCIONAL
         String unEmail = sc.nextLine();
         
         System.out.println("Ingrese su CUIT: "); //OPCIONAL
         String unCuit = sc.nextLine();
         
         System.out.println("Ingrese nombre de calle: ");
         String unNombreDeCalle = sc.nextLine();
         
         System.out.println("Ingrese el numero de su domicilio: ");
         int numDeCalle = sc.nextInt();
         
         System.out.println("Ingrese piso: ");
         String unPiso = sc.nextLine();
         
         System.out.println("Ingrese un departamento: ");
         String unDepartamento = sc.nextLine();
         
         System.out.println("Ingrese Localidad: ");
         String unaLocalidad = sc.nextLine();
         
         System.out.println("Ingrese Cod. Postal: ");
         String unCodPostal = sc.nextLine();
         
         System.out.println("Ingrese Pais: ");
         String unPais = sc.nextLine();
         
         Direccion unaDireccion = new Direccion(unNombreDeCalle, numDeCalle, unCodPostal, unaLocalidad, unPais, unPiso, unDepartamento);
         
         System.out.println("Ingrese un num telefono: ");
         String unTelefono = sc.nextLine();
         
         System.out.println("Ingrese su nacionalidad: ");
         String unaNacionalidad = sc.nextLine();
         
          System.out.println("Ingrese su ocupacion: ");
         String unaOcupacion = sc.nextLine();
        
        //sc.close();
        
        
        
        HuespedDTO unDTO = new HuespedDTO(
                unCuit, unTelefono, unaNacionalidad, unaDireccion, unNombre, unApellido, unaEdad, unTipoSexo, unTipoDoc, 
                unDocumento, fechaNacimiento, unTipoConsumidor, unEmail, unaOcupacion);
        
        return unDTO;
    }
    
    
    
}
