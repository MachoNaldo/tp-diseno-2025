package grupo26.tpdiseno.gestores;

import grupo26.tpdiseno.entidades.Direccion;
import grupo26.tpdiseno.entidades.DocumentoUsadoException;
import grupo26.tpdiseno.entidades.FechaFunciones;
import grupo26.tpdiseno.entidades.FiltroBusquedaHuesped;
import grupo26.tpdiseno.entidades.Huesped;
import grupo26.tpdiseno.entidades.HuespedDTO;
import grupo26.tpdiseno.entidades.RegistroNoEncontradoException;
import grupo26.tpdiseno.entidades.SinConcordanciaException;
import grupo26.tpdiseno.entidades.TipoConsumidor;
import grupo26.tpdiseno.entidades.TipoDoc;
import grupo26.tpdiseno.entidades.TipoSexo;
import grupo26.tpdiseno.pantallas.PantallaDarAltaHuesped;
import grupo26.tpdiseno.pantallas.PantallaBuscarHuesped;
import grupo26.tpdiseno.servicios.HuespedDAOJSON;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GestorHuesped implements FechaFunciones {

    private static GestorHuesped instancia;
    private final HuespedDAOJSON hDAO;

    GestorHuesped() {
        this.hDAO = HuespedDAOJSON.getInstancia();
    }

    public static GestorHuesped getInstancia() {
        if (instancia == null) {
            instancia = new GestorHuesped();
        }
        return instancia;
    }

    public void DarAltaHuesped(HuespedDTO unHuespedDTO) throws DocumentoUsadoException {

        Huesped h1 = new Huesped(unHuespedDTO);

        hDAO.agregarHuesped(h1, false);

        System.out.println("El huesped se cargo con exito en el sistema");
    }

    /**
     * Fuerza el registro de un huésped en el sistema, incluso si ya existe otro
     * huésped con el mismo documento.
     *
     * <p>
     * Este método existe ya que el enunciado del trabajo práctico
     * se especifica que el conserje puede confirmar y forzar el alta de
     * un huésped cuyo documento ya fue registrado previamente.</p>
     *
     * <p>
     * Internamente, convierte el {@link HuespedDTO} recibido en una entidad
     * {@link Huesped} y lo agrega al sistema mediante el {@code hDAO},
     * utilizando el parámetro de control {@code true} para indicar que se omita
     * la validación de unicidad del documento.</p>
     *
     * @param unHuespedDTO El objeto de transferencia que contiene los datos del
     * huésped a registrar.
     *
     * @throws DocumentoUsadoException Como es el mismo metodo recibe de igual manera el
     * DocumentoUsadoException aunque en realidad nunca pasaria
     *
     */
    public void forzarHuesped(HuespedDTO unHuespedDTO) {

        try {
            Huesped h1 = new Huesped(unHuespedDTO);

            hDAO.agregarHuesped(h1, true);

            System.out.println("El huesped se cargo con exito en el sistema");
        } catch (DocumentoUsadoException ex) {

        }
    }

    public List<HuespedDTO> buscarHuesped(FiltroBusquedaHuesped unFiltro) {
        try {
            return hDAO.buscarHuespedDTO(unFiltro);
        } catch (SinConcordanciaException e) {
            System.out.println(e);
            System.out.println("Pasando a Dar Alta de Huesped...");
            new PantallaDarAltaHuesped().DarAltaHuesped();
        }
        return null;
    }

    public void modificarHuesped(HuespedDTO unDTO) {
        //PantallaBuscarHuesped pantallaB = new PantallaBuscarHuesped();
        //PantallaDarAltaHuesped pantallaA = new PantallaDarAltaHuesped();
        //Se selecciona el huesped a modificar
        //    String huespedOriginal = pantallaB.seleccionarHuesped(pantallaB.buscarHuesped());
        Huesped huespedModificado = new Huesped(unDTO);
        hDAO.modificarHuesped(huespedModificado);
    }

    public void eliminarHuesped(String id) throws RegistroNoEncontradoException {
        //PantallaBuscarHuesped pantallaB = new PantallaBuscarHuesped();
        //Se selecciona el huesped a eliminar
        //  String huesped = pantallaB.seleccionarHuesped(pantallaB.buscarHuesped());
        hDAO.eliminarHuesped(id);
    }

    public HuespedDTO generarHuespedDTO() {
        Scanner sc = new Scanner(System.in);
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
        } while (tipoSexo.length() != 1 || (!tipoSexo.equals("M") && !tipoSexo.equals("F")));

        switch (tipoSexo) {
            case "M" ->
                unTipoSexo = TipoSexo.Masculino;
            case "F" ->
                unTipoSexo = TipoSexo.Femenino;
        }

        TipoDoc unTipoDoc;

        System.out.println("Ingrese tipo de documento: ");
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
        } while (eleccion.length() != 1 || (!eleccion.equals("A") && !eleccion.equals("B")));

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
