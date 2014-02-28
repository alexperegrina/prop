/**
 *
 * @author David Gracia Celemendi
 * @param <T> Tipo asociado a la información que contiene el vértice.
 */
public class Vertice<T> {
    private T informacion;
    
    /**
     *Constructor por defecto.
     */    
    public Vertice(){
        this.informacion = null;
    }

    /**
     *Constructor.
     * @param t Información T que contendrá el vértice creado.
     */     
    public Vertice(T t){
        this.informacion = t;
    }
    
    /**
     *Constructor copia.
     * @param original Vértice original que se va a copiar.
     */     
    public Vertice(Vertice<T> original){
        if(original.informacion == null) this.informacion = null;
        else this.informacion = original.informacion;
    }
    
    /**
     *Devuelve la información T del vértice.
     * @return La información del vértice.
     */     
    public T info() throws IllegalArgumentException {
        if(this.informacion == null) throw new IllegalArgumentException("Error al consultar informacion de un vértice: referencia nula.");
        return this.informacion;
    }
    
     /**
     *Modifica la información T del vértice.
     * @param nuevaInfo La nueva información T asociada al vértice.
     */     
    public void modificarInfo(T nuevaInfo){
        this.informacion = nuevaInfo;
    }
    
}
