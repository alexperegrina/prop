package Alejandro;

/**
 *
 * @author Alejandro Rosas
 */
public class Tarea {

    private int id;
    private String nombre;
    private double tiempoProduccion;
    private double tiempoPreparacion;
    private double costeProduccion;
    private double costePreparacion;
    private String descripcion;
    //public Vector myRelacionTiempo;
    //public Vector myRelacionCoste;

    /**
     * Constructor por defecto.
     */
    public Tarea() {
        this.id = -1;
        this.nombre = "";
        this.tiempoProduccion = Double.NaN;
        this.tiempoPreparacion = Double.NaN;
        this.costeProduccion = Double.NaN;
        this.costePreparacion = Double.NaN;
        this.descripcion = "";
    }

    /**
     * Constructor copia.
     *
     * @param t Tarea original que se va a copiar.
     */
    public Tarea(Tarea t) {
        this.id = t.id;
        this.nombre = t.nombre;
        this.tiempoProduccion = t.tiempoProduccion;
        this.tiempoPreparacion = t.tiempoPreparacion;
        this.costeProduccion = t.costeProduccion;
        this.costePreparacion = t.costePreparacion;
        this.descripcion = t.descripcion;
    }

    /**
     * Constructor completo.
     *
     * @param id Identificador de la tarea.
     * @param nombre Nombre de la tarea.
     * @param tiempoProduccion Tiempo de producción de la tarea.
     * @param tiempoPreparacion Tiempo de preparación de la tarea.
     * @param costeProduccion Coste de producción de la tarea.
     * @param costePreparacion Coste de preparación de la tarea.
     * @param descripcion Descripción de la tarea.
     */
    public Tarea(int id, String nombre, double tiempoProduccion, double tiempoPreparacion, double costeProduccion, double costePreparacion, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.tiempoPreparacion = tiempoPreparacion;
        this.tiempoProduccion = tiempoProduccion;
        this.costePreparacion = costePreparacion;
        this.costeProduccion = costeProduccion;
        this.descripcion = descripcion;
    }

    /**
     * Consultor del identificador.
     *
     * @return El identificador de la tarea.
     */
    public int id() {
        return this.id;
    }

    /**
     * Modificador del identificador.
     *
     * @param id Nuevo identificador.
     */
    public void modificarId(int id) {
        this.id = id;
    }

    /**
     * Consultor del nombre.
     *
     * @return El nombre de la tarea.
     */
    public String nombre() {
        return nombre;
    }

    /**
     * Modificador del nombre.
     *
     * @param nombre Nuevo nombre.
     */
    public void modificarNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Consultor del tiempo de produccion.
     *
     * @return El tiempo de producción de la tarea.
     */
    public double tiempoProduccion() {
        return this.tiempoProduccion;
    }

    /**
     * Modificador del tiempo de producción.
     *
     * @param tiempoProduccion Nuevo tiempo de producción.
     */
    public void modificarTiempoProduccion(double tiempoProduccion) {
        this.tiempoProduccion = tiempoProduccion;
    }

    /**
     * Consultor del tiempo de preparación.
     *
     * @return El tiempo de preparación de la tarea.
     */
    public double tiempoPreparacion() {
        return this.tiempoPreparacion;
    }

    /**
     * Modificador del tiempo de preparación.
     *
     * @param tiempoPreparacion Nuevo tiempo de preparación.
     */
    public void modificarTiempoPreparacion(double tiempoPreparacion) {
        this.tiempoPreparacion = tiempoPreparacion;
    }

    /**
     * Consultor del coste de producción.
     *
     * @return El coste de producción de la tarea.
     */
    public double costeProduccion() {
        return this.costeProduccion;
    }

    /**
     * Modificador del coste de producción.
     *
     * @param costeProduccion Nuevo coste de producción.
     */
    public void modificarCosteProduccion(double costeProduccion) {
        this.costeProduccion = costeProduccion;
    }

    /**
     * Consultor del coste de preparación.
     *
     * @return El coste de preparación de la tarea.
     */
    public double costePreparacion() {
        return this.costePreparacion;
    }

    /**
     * Modificador del coste de preparación.
     *
     * @param costePreparacion Nuevo coste de preparación.
     */
    public void modificarCostePreparacion(double costePreparacion) {
        this.costePreparacion = costePreparacion;
    }

    /**
     * Consultor de la descripción.
     *
     * @return La descripción de la tarea.
     */
    public String descripcion() {
        return this.descripcion;
    }

    /**
     * Modificador de la descripción.
     *
     * @param descripcion Nueva descripción.
     */
    public void modificarDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /*
     public Lista ConsultarRelaciones() {
     return null;
     }

     public Relacion CogerRelacion(Integer id) {
     return null;
     }
     */
}
