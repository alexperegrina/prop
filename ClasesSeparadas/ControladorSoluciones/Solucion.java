

import java.util.ArrayList;

/**
 *
 * @author Marc Vila Gomez
 * @param <T> Tipo parametrizado asociado a la solución
 */
public class Solucion<T> {

    private String nombre;
    private double tiempo; //Tc.T1 + Tp.T2*Similitud(T1,T2) + Tc.T2 ..+ Tp.T3*Similitud(T2,T3)
    private double coste;
    private ArrayList<T> items; //De id de tareas

    private boolean posicionValida(int posicion) {
        return posicion >= 0 && posicion < this.items.size();
    }

    private void calcularTiempoyCoste(ControladorTareas cntrT, ArrayList<Integer> idTareas) {
        if (!items.isEmpty()) {
            int id = idTareas.get((Integer) items.get(0));
            tiempo = cntrT.tarea(id).tiempoProduccion() + cntrT.tarea(id).tiempoPreparacion();
            coste = cntrT.tarea(id).costeProduccion() + cntrT.tarea(id).costePreparacion();

            for (int i = 1; i < items.size(); i++) {
                Tarea t1 = cntrT.tarea(idTareas.get((Integer) items.get(i - 1)));
                Tarea t2 = cntrT.tarea(idTareas.get((Integer) items.get(i)));
                tiempo += t2.tiempoProduccion() + t2.tiempoPreparacion() * cntrT.relacionSimilitud(t1.id(), t2.id(), false);
                coste += t2.costeProduccion() + t2.costePreparacion() * cntrT.relacionSimilitud(t1.id(), t2.id(), true);
            }
        }
    }

    /**
     * Constructor por defecto.
     */
    public Solucion() {
        this.nombre = "";
        this.tiempo = Double.NaN;
        this.coste = Double.NaN;
        this.items = new ArrayList<>();
    }

    /**
     * Constructor completo.
     *
     * @param nombre Nombre de la solución.
     * @param tiempo Tiempo de producción de la solución.
     * @param coste Coste de producción de la solución.
     * @param items Conjunto de tareas que formarán la solución.
     */
    public Solucion(String nombre, double tiempo, double coste, ArrayList<T> items) throws IllegalArgumentException {
        if (items.isEmpty()) {
            throw new IllegalArgumentException("Error al crear una solución: conjunto de tareas vacío.");
        }
        this.nombre = nombre;
        this.tiempo = tiempo;
        this.coste = coste;
        this.items = items;
    }

    /**
     * Constructor copia.
     *
     * @param s Solucion original que se copiará.
     */
    public Solucion(Solucion s) {
        this.nombre = s.nombre;
        this.tiempo = s.tiempo;
        this.coste = s.coste;
        this.items = s.items;
    }

    /**
     * Devuelve el nombre de la solución.
     *
     * @return El nombre de la solución.
     */
    public String nombre() {
        return this.nombre;
    }

    /**
     * Modifica el nombre de la solución.
     *
     * @param nombre El nuevo nombre de la solución.
     */
    public void modificarNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el tiempo de produccion de la solución.
     *
     * @return El tiempo de produccion de la solución.
     */
    public double tiempo() {
        return this.tiempo;
    }

    /**
     * Modifica el tiempo de la solución.
     *
     * @param ntiempo El nuevo tiempo de la solución.
     */
    public void modificarTiempo(int ntiempo) {
        this.tiempo = ntiempo;
    }

    /**
     * Devuelve el coste de produccion de la solución.
     *
     * @return El coste de produccion de la solución.
     */
    public double coste() {
        return coste;
    }

    /**
     * Modifica el coste de la solución.
     *
     * @param ncoste El nuevo coste de la solución.
     */
    public void modificarCoste(int ncoste) {
        this.coste = ncoste;
    }

    /**
     * Añade una tarea a la solución.
     *
     * @param t La tarea que se va a añadir.
     */
    public void anadirItem(T t) {
        this.items.add(t);
    }
    
    /**
     * Devuelve el Array de items de la solucion
     *
     * @return El array de items de la solucion
     */
    public ArrayList<T> listarItems(){
        return this.items;
    }
    /**
     * Intercambia las posiciones de dos tareas.
     *
     * @param p1 Posición de una de las tareas que se va a intercambiar.
     * @param p2 Posición de una de las tareas que se va a intercambiar.
     */
    public void intercambiarPosicion(int p1, int p2) throws IllegalArgumentException {
        if (!(this.posicionValida(p1) && this.posicionValida(p2))) {
            throw new IllegalArgumentException("Error al intercambiar tareas: posición no válida.");
        }
        if (p1 == p2) {
            throw new IllegalArgumentException("Error al intercambiar tareas: posiciones repetidas.");
        }
        T tAux = this.items.get(p1);
        this.items.set(p1, this.items.get(p2));
        this.items.set(p2, tAux);
    }

    /**
     * Devuelve el número de tareas que contiene la solución.
     *
     * @return El número de tareas que contiene la solución.
     */
    public int numeroTareas() {
        return this.items.size();
    }

    /**
     * Ejecuta el algoritmo de un grafo G, con
     *
     * @param G Grafo para realizar el algoritmo
     * @param op1 String, que sera el primer algoritmo a ejecutar.
     * @param op2 String, que sera el refinamiento a ejecutar.
     */
    public void ejecutarTSP(Grafo G, String op1, String op2, ControladorTareas Cntrl, ArrayList<Integer> idTareas) {
        ControladorTSP cTSP = new ControladorTSP();
        ArrayList<Integer> idVs = cTSP.obtenerTour(G, op1, op2);
        for (int i : idVs) {
            Vertice v = G.vertice(i);
            items.add((T) v.info());
        }
        nombre = op1 + " + " + op2;
        calcularTiempoyCoste(Cntrl, idTareas);
    }
}
