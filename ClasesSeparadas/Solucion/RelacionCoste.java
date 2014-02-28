import java.util.Vector;

/**
 *
 * @author Alex Peregrina Cabrera
 */
public class RelacionCoste extends Relacion {
    
    public RelacionCoste() {}
    
    public RelacionCoste(Integer tarea1, Integer tarea2, Double similitud) {
            super(tarea1,tarea2,similitud);
    }
    
    public RelacionCoste(String nombre, Integer tarea1, Integer tarea2, Double similitud) {
            super(nombre,tarea1,tarea2,similitud);
    }
    
    public Boolean tipo() {
        return Boolean.TRUE;
    }
}
