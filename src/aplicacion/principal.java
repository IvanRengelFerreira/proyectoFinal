package aplicacion;

import presentacion.Interfaz;

public class Principal{
    public static void main(String[] args) {
        System.out.println("Bienvenido a la aplicación de gestión de supermercados.\nAca podra añadir secciones, productos y realizar operaciones con ellos.\nSi necesita ayuda con informacion sobre los comandos, introduzca 'help'");
        Interfaz interfaz = new Interfaz();
        String peticion;
        interfaz.procesarPeticion("help");
        do {
            peticion=interfaz.leerPeticion();
        } while (interfaz.procesarPeticion(peticion));
    }
}

