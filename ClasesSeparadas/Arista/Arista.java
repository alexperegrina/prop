

/**
 *
 * @author David Gracia Celemendi
 * @param <N> Tipo numérico que representa el peso de una arista.
 */
public class Arista<N extends Number> implements Comparable {

    private int origen;
    private int destino;
    private N peso;

    /**
     * Constructor por defecto. Los extremos de la arista están inicializados
     * con identificadores menor que 0. El peso es null.
     */
    public Arista() {
        this.origen = -1;
        this.destino = -2;
        this.peso = null;
    }

//    /**
//    *Constructor.
//    * @param n El peso de la arista.
//    */    
//    public Arista(N n){
//        this();
//        this.peso = n;
//    }
    /**
     * Constructor completo.
     *
     * @param idOrigen Extremo origen de la arista.
     * @param idDestino Extremo destino de la arista.
     * @param peso Peso de la arista.
     */
    public Arista(int idOrigen, int idDestino, N peso) {
        this.origen = idOrigen;
        this.destino = idDestino;
        this.peso = peso;
    }

    /**
     * Constructor copia.
     *
     * @param original Arista original que se va a copiar.
     */
    public Arista(Arista<N> original) {
        this.origen = original.origen;
        this.destino = original.destino;
        if (original.peso == null) {
            this.peso = null;
        } else {
            this.peso = original.peso;
        }
    }

//    /**
//    * Modificadora completa.
//    * @param idOrigen Extremo origen de la arista.
//    * @param idDestino Extremo destino de la arista.
//    * @param peso Peso de la arista.
//    */    
//    public void modificar(int idOrigen,int idDestino,N peso){
//        this.origen=idOrigen;
//        this.destino=idDestino;
//        this.peso=peso;
//    }
    /**
     * Devuelve el extremo origen de la arista.
     *
     * @return El extremo origen de la arista.
     */
    public int origen() {
        return this.origen;
    }

    /**
     * Modifica el extremo origen de la arista.
     *
     * @param idOrigen El nuevo extremo origen de la arista.
     */
    public void modificarOrigen(int idOrigen) {
        this.origen = idOrigen;
    }

    /**
     * Devuelve el extremo destino de la arista.
     *
     * @return El extremo destino de la arista.
     */
    public int destino() {
        return this.destino;
    }

    /**
     * Modifica el extremo destino de la arista.
     *
     * @param idDestino El nuevo extremo destino de la arista.
     */
    public void modificarDestino(int idDestino) {
        this.destino = idDestino;
    }

    /**
     * Devuelve el peso de la arista.
     *
     * @return El peso de la arista.
     */
    public N peso() throws IllegalArgumentException {
        if (this.peso == null) {
            throw new IllegalArgumentException("Error al consultar peso de una arista: referencia nula.");
        }
        return this.peso;
    }

    /**
     * Modifica el peso de la arista.
     *
     * @param peso El nuevo peso de tipo N de la arista.
     */
    public void modificarPeso(N peso) {
        this.peso = peso;
    }

//    @Override
//    public int compareTo(Object o) {
//        Arista<N> a = (Arista<N>)o;
//        int res = 0;
//        Double peso1 = (Double) this.peso;
//        Double peso2 = (Double) a.peso();
//        if (peso1 < peso2) {
//            res = -1;
//        } else if (peso1 == peso2) {
//            res = 0;
//        } else if (peso1 > peso2) {
//            res = 1;
//        }
//        return res;
//    }

     /**
     * Compara dos aristas.
     * @param o La arista con la que se quiere comparar
     * @return -1 si la arista implícita tiene menor peso, 0 si tienen el mismo peso, 1 si tiene mayor peso
     */
    @Override
    public int compareTo(Object o) {
        Arista<N> a = (Arista<N>)o;
        int res = 0;
        Double peso1 = (Double) this.peso();
        Double peso2 = (Double) a.peso();
        if (peso1 < peso2) {
            res = -1;
        } else if (peso1 == peso2) {
            res = 0;
        } else if (peso1 > peso2) {
            res = 1;
        }
        return res;
    }
}
