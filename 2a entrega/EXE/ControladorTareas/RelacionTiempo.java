

/**
 *
 * @author Alex Peregrina Cabrera
 */
public class RelacionTiempo extends Relacion {
    
    public RelacionTiempo() {}
    
    public RelacionTiempo(Integer tarea1, Integer tarea2, Double similitud) {
            super(tarea1,tarea2,similitud);
    }
    
    public RelacionTiempo(String nombre, Integer tarea1, Integer tarea2, Double similitud) {
            super(nombre,tarea1,tarea2,similitud);
    }
    
    public Boolean tipo() {
        return Boolean.FALSE;
    }
}