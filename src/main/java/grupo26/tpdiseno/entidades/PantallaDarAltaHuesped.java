package grupo26.tpdiseno.entidades;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PantallaDarAltaHuesped {
    
    Scanner sc = new Scanner(System.in);

    public HuespedDTO generarDTO(){
        
         System.out.println("Ingrese su Apellido: ");
         String unApellido = sc.nextLine();
         
         System.out.println("Ingrese su Nombre: ");
         String unNombre = sc.nextLine();
         
         System.out.println("Ingrese su Edad: ");
         int unaEdad = sc.nextInt();
         
        
        String tipoSexo;
        TipoSexo unTipoSexo = null;

        do {
         System.out.print("Ingrese el Sexo (M/F): ");
         tipoSexo = sc.nextLine();
        } while (tipoSexo.length() != 1 ||  (!tipoSexo.equals("M") && !tipoSexo.equals("F")));

        switch (tipoSexo) {
             case "M":
                    unTipoSexo = TipoSexo.Masculino;
                break;
            case "F":
                    unTipoSexo = TipoSexo.Femenino;
                break;
}
    
         
         System.out.println("Ingrese su Documentacion: ");
         int unDocumento = sc.nextInt();
         
         System.out.println("Ingrese su Fecha: ");
         String textoFecha = sc.nextLine(); //Deberia ser DATE
         DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate unaFecha = LocalDate.parse(textoFecha, formato);
         
         //CONSUMIDOR FINAL
        String eleccion;
        Scanner sc = new Scanner(System.in);
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
         
         System.out.println("Ingrese numero de calle: ");
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
        
        sc.close();
        
        
        
        HuespedDTO unDTO = new HuespedDTO(
                unCuit, unTelefono, unaNacionalidad, unaDireccion, unNombre, unApellido, unaEdad, unTipoSexo, 
                unDocumento, unaFecha, unTipoConsumidor, unEmail, unaOcupacion);
        
        return unDTO;
        
    }

    
}
