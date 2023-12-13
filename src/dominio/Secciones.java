package dominio;
import java.util.ArrayList; 
import java.io.Serializable; 
public class Secciones implements Serializable {
    
    private String nombre;
    private ArrayList<Producto> productos;

    public Secciones(String nombre){
        this.nombre = nombre;
        this.productos = new ArrayList<Producto>();
    }

    public String getNombre(){
        return this.nombre;
    }

    public ArrayList<Producto> getProductos(){
        return this.productos;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setproductos(ArrayList<Producto> productos){
        this.productos = productos;
    }

    public void addProducto(Producto producto){
        this.productos.add(producto);
    }

    public void removeProducto(Producto p){
        this.productos.remove(p);
    }

    public int size(){
        return this.productos.size();
    }

    public Producto getproductos(int i){
        return this.productos.get(i);
    }

    public void mostrarproductos(){
        for(Producto p: productos){
            System.out.println(p);
        }
        
        }
        public String toString() {
            String result = nombre + "\n";
            for (Producto p : productos) {
                result += p.toString() + "\n";
            }
            return result;
        }

        public boolean equals(Object o){
            Secciones s = (Secciones)o;
            return nombre.equals(s.nombre);
        }

        public Producto buscarProducto(String nombre) {
            for (Producto p : productos) {
                if (p.getNombre().equalsIgnoreCase(nombre)) {
                    return p;
                }
            }
            return null; 
        }

        public int IndexOf(Producto p) {
            return productos.indexOf(p);
        }


    }

      
 
      
        
    

    


