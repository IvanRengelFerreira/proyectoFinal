package presentacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import dominio.*;
/**
 * Esta es la clase Interfaz que contiene el menu de la aplicacion y la interaccion conn el usuario.
 *  */ 
public class Interfaz {
    /**
     * Esta es la variable superMercado que contiene un supermercado.
     */
    private SuperMercado superMercado = new SuperMercado("SuperMercado");
    /**
     * Esta es la variable sc que contiene un Scanner que usaremos para leer datos.
     */
    private Scanner sc = new Scanner(System.in);
    /**
     * Selecciona una seccion del supermercado
     * @return Seccion seleccionada
     */
    public Secciones seleccionarSecciones() {
     
        Secciones seccion = null;
         boolean salir = true;
         while( salir == true) {
             try {
            for (int i = 0; i < superMercado.size(); i++) {
                System.out.println((i + 1) + ". " + superMercado.getSecciones(i));
            }
            System.out.println("Seleccione una seccion (número): ");
            int eleccion = sc.nextInt() - 1;
            sc.nextLine(); 

            if (eleccion >= 0 && eleccion < superMercado.size()) {
               seccion = superMercado.getSecciones(eleccion);
               salir = false;
                
            } else {
                System.out.println("Selección inválida");
                
            }
        } catch (Exception e) {
            System.out.println("Error al seleccionar seccion");
            sc.nextLine();
          } 
        } 
          return seccion;
        }
/**
 * Añade un producto al supermercado
 * @return true si se ha añadido correctamente
* @return false si no se ha añadido correctamente
    */
    public boolean addProducto(){
    if (superMercado.size() == 0) {
        System.out.println("No hay secciones disponibles");
        System.out.println("¿Desea añadir una seccion? (si/no)");
        String peticion = sc.nextLine();
        if (peticion.equalsIgnoreCase("si")) {
            addSecciones();
            return true;
        } else if (peticion.equalsIgnoreCase("no")) {
            return false;
        } else {
            System.out.println("Peticion erronea");
            return true;
        }
        
    
    }else if (superMercado.size() != 0)  {
    System.out.println("Bienvenido al menu de añadir productos. En este apartado podra añadir productos a las distintas secciones del supermercado. \n Se le haran preguntas segun las caracteristicas de tu producto. \n Si necesita ayuda en cualquier momento escriba 'help'");
    System.out.println("Desea añadir un producto? (si/no))");
    String peticion = sc.nextLine();
    if (peticion.equalsIgnoreCase("si")) {
        System.out.println("¿Su producto tiene fecha de caducidad?");
        peticion = sc.nextLine();
        if (peticion.equalsIgnoreCase("si")) {
            System.out.println("¿Su producto es medible?");
            peticion = sc.nextLine();
            if (peticion.equalsIgnoreCase("si")) {
                addProductM();
            } else {
                addProductP();
            }
        } else if (peticion.equalsIgnoreCase("no")) {
             System.out.println("¿Su producto es medible?");
            peticion = sc.nextLine();
            if (peticion.equalsIgnoreCase("si")) {
                addProductNoP();
            } else if (peticion.equalsIgnoreCase("no")) {
                addProductSimp();
            }else {
               System.out.println("Peticion erronea");
               return true;
            }
        
        } else {
            System.out.println("Peticion erronea");
            return true;
        }
    } else if (peticion.equalsIgnoreCase("no")) {
         return false;
    } else {
        System.out.println("Peticion erronea");
        return true;     
    }
    }
    return true;
}
/**
 * Añade un producto simple sin fecha de caducidad y sin unidades de medida al supermercado
 */
    public void addProductSimp() {
        Secciones seccion = seleccionarSecciones();

        System.out.println("Nombre del Producto");
        String nombre = sc.nextLine();
        System.out.println("Precio por unidad del Producto");
        double precio = sc.nextDouble();
        sc.nextLine(); // consume newline
        System.out.println("Cantidad del Producto");
        int unidad = sc.nextInt();
        sc.nextLine(); // consume newline
      
        Producto a = new ProductoSimple(nombre, unidad,precio );
        seccion.addProducto(a);
    }
/** 
 * Añade un producto medible y que tiene fecha de caducidad al supermercado
 * */
    public void addProductM() {
        Secciones seccion = seleccionarSecciones();
    
        System.out.println("Nombre del Producto");
        String nombre = sc.nextLine();

        System.out.println("Precio por kilo del Producto");
        double precio = sc.nextDouble();
        sc.nextLine(); // consume newline

        System.out.println("Cantidad del Producto");
        int unidad = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.println("Masa del Producto (use un punto para decimales)");
        double masa = sc.nextDouble();
        sc.nextLine(); // consume newline

        System.out.println("Fecha de caducidad");
        System.out.println("Año:");
        int ano = sc.nextInt();
        System.out.println("Mes:");
        int mes = sc.nextInt();
        System.out.println("Dia:");
        int dia = sc.nextInt();
        sc.nextLine(); // consume newline

        LocalDate fechaCaducidad = LocalDate.of(ano, mes, dia);
            
        Producto a = new ProductoMedible(nombre, unidad, precio, masa, fechaCaducidad);
        seccion.addProducto(a);
    }
/**
 * Añade un producto  medible y que no tiene fecha de caducidad al supermercado
 * */

    public void addProductNoP() {
        Secciones seccion = seleccionarSecciones();
        System.out.println("Nombre del Producto");
        String nombre = sc.nextLine();

        System.out.println("Precio por kilo del Producto");
        double precioKilo = sc.nextDouble();
        sc.nextLine(); // consume newline

        System.out.println("Cantidad del Producto");
        int unidad = sc.nextInt();
        sc.nextLine(); // consume newline
    
        System.out.println("Masa del Producto (use un punto para decimales)");
        double masa = sc.nextDouble();
        sc.nextLine(); // consume newline
    
        Producto a = new ProductoMedible(nombre, unidad, precioKilo,masa);
        seccion.addProducto(a);
    }
/**
 * Añade un producto perecedero sin unidades de medida al supermercado
 * */
    public void addProductP() {


        Secciones seccion = seleccionarSecciones();
        System.out.println("Nombre del Producto");
        String nombre = sc.nextLine();

        System.out.println("Precio por unidad del Producto");
        int precioUnidad = sc.nextInt();
        sc.nextLine(); // consume newline
        
        System.out.println("Cantidad del Producto");
        int unidad = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.println("Fecha de caducidad");
        System.out.println("Año:");
        int ano = sc.nextInt();
        System.out.println("Mes:");
        int mes = sc.nextInt();
        System.out.println("Dia:");
        int dia = sc.nextInt();
        sc.nextLine(); // consume newline

        LocalDate fechaCaducidad = LocalDate.of(ano, mes, dia);

        Producto a = new ProductoSimple(nombre, unidad, precioUnidad,fechaCaducidad);
        seccion.addProducto(a);
    }
/**
 * Añade una seccion al supermercado
 * */
    public void addSecciones() {
        System.out.println("Nombre de la Seccion");
        String nombre = sc.nextLine();
        Secciones s = new Secciones(nombre);
        superMercado.addSeccion(s);
    }
/**
 * Muestra el menu de la aplicacion
 * */
    public void menu(){
        System.out.println("--------------------Menu--------------------");
        System.out.println("1.Añadir Secciones");
        System.out.println("2.Añadir Producto");
        System.out.println("3.Mostrar Secciones");
        System.out.println("4.Borrar seccion");
        System.out.println("5.Borrar producto");
        System.out.println("6.Actualizar producto");
        System.out.println("Para salir de la interfaz escriba 'exit'");
        System.out.println("Si necesita ayuda en cualquier momento escriba 'help'");
    
    }
/**
 * Procesa la peticion del usuario
 * @param peticion Peticion del usuario
 * @return true si se ha procesado correctamente
 * @return false si no se ha procesado correctamente
 * */
    public boolean procesarPeticion(String peticion) {
        String[] p = peticion.trim().split("\\s+");

        if (p.length > 0) {

            if (p[0].equalsIgnoreCase("1")) {
                addSecciones();
            } else if (p[0].equalsIgnoreCase("2")) {
                addProducto();
            }else if (p[0].equalsIgnoreCase("3")) {
                mostrarSecciones();
            } else if (p[0].equalsIgnoreCase("4")) {
                borrarSeccion();
            }else if (p[0].equalsIgnoreCase("5")) {
                borrarProducto();
            }else if (p[0].equalsIgnoreCase("6")) {
                actualizarProducto();
            }else if (p[0].equalsIgnoreCase("help")) {
                menu();
            } else if (p[0].equalsIgnoreCase("exit")) {
                grabar();
                return false;
            } else {
                System.out.println("Peticion erronea");
            }
        } else {
            System.out.println("Peticion erronea");
        }
        // Mostrar ayuda si hubo un error en la petición.
        System.out.println("Introduzca 'help' para obtener una lista de comandos validos.");
        return true;
    }

    public void mostrarSecciones() {
        superMercado.mostrarSecciones();
    }

    public Interfaz() {
       leer();
    }

    public void leer() {
         File f = new File("Supermercado.dat");
        try {
            ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
            try {
                superMercado = (SuperMercado) obj.readObject();
                obj.close();
            } catch (Exception e) {
                System.out.println("Error al leer el fichero");
                superMercado = new SuperMercado("Mi supermercado");
            }
          
        } catch (Exception e) {
            superMercado = new SuperMercado("Mi supermercado");
        }
    }
        
    public String leerPeticion() {
        System.out.print("?>");
        String cadena = sc.nextLine();
        return cadena;
    }

  

 
    public void grabar() {
        File f = new File("SuperMercado.dat");
        try {
            ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(f));
            obj.writeObject(superMercado);
            obj.close();
            System.out.println("Grabado correctamente");
        } catch (Exception e) {
            System.out.println("Error al grabar");
        }
    }
   
    public void borrarSeccion() {
        Secciones seccion = seleccionarSecciones();
        superMercado.removeSeccion(seccion);
        System.out.println("La seccion ha sido borrada con exito");
    }
    
    public void borrarProducto() {
        Secciones seccion = seleccionarSecciones();
        System.out.println("Nombre del Producto");
        String nombre = sc.nextLine();
        Producto p = new ProductoMedible(nombre);
        seccion.removeProducto(p);
        System.out.println("El producto ha sido borrado con exito");
    }

    public void actualizarProducto(){
        if (superMercado.size() == 0) {
            System.out.println("No hay secciones disponibles");
        }else{
         Secciones seccion = seleccionarSecciones();
        System.out.println("Nombre del Producto");
        String nombre = sc.nextLine();
        Producto p = seccion.buscarProducto(nombre);
            if (p == null) {
            System.out.println("El producto no existe");
            }else{
            menuActualizar(p);
            System.out.println("El producto ha sido actualizado con exito");
            }
        }
    }

    public boolean menuActualizar(Producto p){
        System.out.println("¿Que desea cambiar?");
        System.out.println("1.Nombre del producto");
        System.out.println("2.Precio por kilo del Producto");
        System.out.println("3.Cantidad del Producto");
        System.out.println("4.Masa del Producto (use un punto para decimales)");
        System.out.println("5.Fecha de caducidad");
        System.out.println("6.Salir");
        String peticion = sc.nextLine();
        if (peticion.equalsIgnoreCase("1")) {
            System.out.println("Nombre nuevo del Producto");
            String nombreNuevo = sc.nextLine();
            p.setNombre(nombreNuevo);
        } else if (peticion.equalsIgnoreCase("2")) {
            if (p instanceof ProductoMedible) {
            System.out.println("Precio por kilo del Producto");
            double nuevoPrecio = sc.nextDouble();
            sc.nextLine(); // consume newline
            ((ProductoMedible)p).setPrecioKilo(nuevoPrecio);
            }
        } else if (peticion.equalsIgnoreCase("3")) {
            System.out.println("Cantidad del Producto");
            int nuevaUnidad = sc.nextInt();
            sc.nextLine(); // consume newline
            p.setUnidad(nuevaUnidad);
        } else if (peticion.equalsIgnoreCase("4")) {
            if (p instanceof ProductoMedible) {
            System.out.println("Masa del Producto (use un punto para decimales)");
            double nuevaMasa = sc.nextDouble();
            sc.nextLine(); // consume newline
             ((ProductoMedible)p).setMasa(nuevaMasa);
            }
        } else if (peticion.equalsIgnoreCase("5")) {
            try {
            System.out.println("Fecha de caducidad");
            System.out.println("Año:");
            int nuevoAno = sc.nextInt();
            System.out.println("Mes:");
            int nuevoMes = sc.nextInt();
            System.out.println("Dia:");
            int nuevoDia = sc.nextInt();
            sc.nextLine(); // consume newline
            LocalDate nuevaFechaCaducidad = LocalDate.of(nuevoAno, nuevoMes, nuevoDia);
            if (p instanceof ProductoMedible) {
            ((ProductoMedible)p).setFechaCaducidad(nuevaFechaCaducidad);
                
            }else if (p instanceof ProductoSimple) {
                ((ProductoSimple)p).setFechaCaducidad(nuevaFechaCaducidad);
            }
        }catch(Exception e){
            System.out.println("Error al introducir la fecha o este producto no admite fecha de caducidad");
            sc.nextLine();
        }
        } else if (peticion.equalsIgnoreCase("6")) {
           return false;
        } else {
            System.out.println("Peticion erronea");
        }
       
        return true;

    }
    
    }
    

