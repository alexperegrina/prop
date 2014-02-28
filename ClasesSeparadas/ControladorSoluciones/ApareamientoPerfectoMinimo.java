
import java.util.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Alex Peregrina Cabrera
 */
public class ApareamientoPerfectoMinimo {
    protected static String msg_G_M_no_sub = "M no es un subgrafo de G.";
    protected static String msg_G_no_completo = "Error! El grafo tiene que ser completo";
    protected Boolean debug = Boolean.FALSE;
    
    // Matriz de adyacencias del grafo.
    protected int[][] adyacencias;
    
    protected Grafo G;
    
    protected Boolean tipoEmparejamineto;//true = maximo, false = minimo
    // Fila del vértice sin pareja.
    protected int fila;
    // Columna del vértice sin pareja
    protected int columna;
    // Almacena las soluciones parciales encontradas
    protected SolucionEmp sol;
    // Almacena la solución óptima encontrada.
    protected SolucionEmp solOptima;
    
    /**
     * Constructor por defecto. 
     */
    public ApareamientoPerfectoMinimo() {}

    /**
     * Constructor para obtener el emparejamiento minimo o maximo
     * @param G Grafo
     */
    public ApareamientoPerfectoMinimo(Grafo<Integer, Double> G) {
        this.G = new Grafo(G);
    }
    
    class SolucionEmp {
        int[][] emparejamientos;
        /// Almacena el peso del emparejamiento.
        double pesoEmparejamiento;
        /// Número de vértices emparejados.
        int numVerticesEmparejados;
    }
    
    class Candidatos {
        ArrayList<Integer> verticesSinPareja;
        // Índice para iterar sobre el array de candidatos.
        int i;
        //indica el vertice ha emparejar
        int fila;
    }
    
    class Etapa {
        // Profundidad del árbol de expansión
        int k; //k como mucho sera nvertizes - 1
        // Candidato actualmente procesado, indice del vector verticesSinPareja
        int i;
    }

    //
    /**
     * Funcion para comprovar si en sol puede contener una solucion(todos o todos-1 vertizes estan emparejados)
     * @return True -> si es una posible solucion, False -> si no es una solucion
     */
    private Boolean esSolucion() {
        int size = this.sol.emparejamientos.length;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(this.sol.emparejamientos[i][j] == 0) return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }
    
    /**
     * Comprueva si la solucion obtenida si es mejor que la mejor obtenida asta el momento,
     * si es asi la substituye.
     * @return True -> si es mejo, False -> si no es mejor
     */
    private Boolean esMejor() {
        if(this.tipoEmparejamineto) {
            if(this.solOptima == null || (this.sol.pesoEmparejamiento > this.solOptima.pesoEmparejamiento)) return true;
            else return false;
        }
        else {
            if(this.solOptima == null || (this.sol.pesoEmparejamiento < this.solOptima.pesoEmparejamiento)) return true;
            else return false;
        }
    }
    
    /**
     * Substituye la solucion mejor obtenida asta el momenot por la solucion processada 
     */
    private void actualizaSolucion() {
        for(int i = 0; i < this.sol.emparejamientos.length; ++i) {
            for(int j = 0; j < this.sol.emparejamientos[i].length; ++j) {
                this.solOptima.emparejamientos[i][j] = this.sol.emparejamientos[i][j];
            }
        }
        this.solOptima.numVerticesEmparejados = this.sol.numVerticesEmparejados;
        this.solOptima.pesoEmparejamiento = this.sol.pesoEmparejamiento;
    }
    
    /**
     * Incrementamos el valor de la etapa actual,
     * @param e Etapa actual
     * @return Etapa siguiente
     */
    private Etapa nuevaEtapa(Etapa e) {
        Etapa ret = new Etapa();
        ret.i = e.i;
        ret.k = e.k+1;
        return ret;
    }
    
    /**
     * Se comprueba si el atributo “i” utilizado para iterar sobre 
     * el array “verticesSinpareja” que almacena los candidatos ha llegado al final del array
     * @param c Posibles candidatos
     * @return True -> si hay candidatos, False -> si no 
     */
    private Boolean quedanCandidatos(Candidatos c) {
        if(c.verticesSinPareja.size() > 0 && c.i+1 < c.verticesSinPareja.size()) return true;
        else return false;
    }
    
    /**
     * Calcula los Candidatos porsibles para una etapa en concreto
     * @param e Etapa a comprovar si quedan candidatos
     * @return candidatos en esa etapa
     */
    private Candidatos calculaCandidatos(Etapa e) {
        if(debug)
            System.out.println("************************calculaCandidatos");
        Candidatos c = new Candidatos();
        if(e.k < this.G.numVertices()) {
            //crear un objeto candidato y rellenarlo para esta etapa         
            c.i = -1; //no apuntamos a ninguna pos porque en la funcion seleccionaCandidato ya incrementamos este valor
            //buscar el primer vertize sin emparejar
            int size = this.sol.emparejamientos.length;
            int i = 0,j = 0;
            boolean trobat = Boolean.FALSE;
            while(i < size && !trobat) {
                j = 0;
                while(j < size && !trobat) {
                    if(i != j && this.G.adyacentes(i, j) &&
                        this.sol.emparejamientos[i][j] == 0) {
                        trobat = Boolean.TRUE;
                        c.fila = i;
                    }
                    ++j;
                }
                ++i;
            }
            //añadimos los candidatos
            c.verticesSinPareja = new ArrayList<>();
            for(int k = 0; k < size; ++k) {
                if(this.sol.emparejamientos[c.fila][k] == 0) c.verticesSinPareja.add(k);
            }
        }
        else {
            c.verticesSinPareja = new ArrayList<>();
        }
        return c;
    }
    
    /**
     * actualizamos los atributos “fila” y “columna” del
     * vértice no emparejado. Además se incrementa el índice del candidato usado
     * para iterar sobre el array que almacena los vértices sin pareja y asignamos
     * dicho valor al atributo que indica en la etapa el candidato que se esta
     * procesando actualmente
     * @param c Candidatos
     * @param e Etapa
     * @return Candidato y Etapa modificados
     */
    private ParOrdenado<Candidatos,Etapa> selecionaCandidato(Candidatos c, Etapa e) {
        if(debug)
            System.out.println("************************selecionaCandidato");
        Candidatos rc = new Candidatos();
        Etapa re = new Etapa();
        rc.fila = c.fila;
        rc.i = c.i+1;
        rc.verticesSinPareja = c.verticesSinPareja;
        re.i = c.verticesSinPareja.get(rc.i);
        re.k = e.k;        
        ParOrdenado<Candidatos,Etapa> ret = new ParOrdenado<>(rc,re);
        this.fila = c.fila;
        this.columna = c.verticesSinPareja.get(rc.i);
        return ret;
    }
    
    /**
     * se marca la fila y columna de los vértices
     * emparejados con el valor “-k” de la etapa. Por otra parte posición de la matriz
     * “emparejamientos” correspondiente al emparejamiento se marca con el valor
     * “k” de la etapa indicando el orden en el que se ha encontrado la pareja.
     * También incrementamos en dos el número de vértices emparejados y
     * actualizamos el peso del emparejamiento parcialmente construido con el peso
     * de la nueva arista considerada 
     * @param e Etapa actual
     */
    private void anotaSolucion(Etapa e) {
        if(debug) {
            System.out.println("************************anotaSolucion");
            System.out.println("this.fila: " + this.fila);
            System.out.println("this.columna: " + this.columna);
        }
        //marcar la pareja en la matriz        
        this.sol.numVerticesEmparejados += 2;   
        this.sol.pesoEmparejamiento += (Double)this.G.arista(this.fila, this.columna).peso();
        //marcar las no validas
        int size = this.sol.emparejamientos.length;
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                if(i != j && this.sol.emparejamientos[i][j] == 0 && (i == this.fila || j == this.columna 
                        || i == this.columna || j == this.fila)) this.sol.emparejamientos[i][j] = -e.k;
            }
        }
        this.sol.emparejamientos[this.fila][this.columna] = e.k;
    }
    
    /**
     * decrementamos el valor “k” y
     * restauramos el valor del candidato actualmente de la etapa. Además se
     * restaura el valor de la fila y columna, se desmarcan la fila y columnas de los
     * últimos vértices emparejados. También restauramos el valor del peso del
     * emparejamiento y vértice emparejados con el valor que tenían antes de
     * considerar la nueva pareja
     * @param c Candidatos
     * @param e Etapa
     */
    private void cancelaAnotacion(Candidatos c, Etapa e) {
        if(debug)
            System.out.println("************************cancelaAnotacion");
        this.fila = c.fila;
        this.columna = c.verticesSinPareja.get(c.i);
        this.sol.emparejamientos[this.fila][this.columna] = 0;
        this.sol.pesoEmparejamiento -= (Double)this.G.arista(this.fila, this.columna).peso();
        this.sol.numVerticesEmparejados -= 2;
        
        Etapa ret = new Etapa();
        ret.k = e.k-1;
        ret.i = e.i;

        //marcar las no validas
        int size = this.sol.emparejamientos.length;
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                if(i != j && (this.sol.emparejamientos[i][j] == -e.k 
                        || this.sol.emparejamientos[i][j] == e.k)) this.sol.emparejamientos[i][j] = 0;
            }
        }
    }
    
    /**
     * metodo para tener una euristica mas fina en el recorrido del arbol
     * aqui comprovamos para hacer la poda en el arbol
     * @return Si la ramaificacion escojida es optima para la solucion
     */
    private boolean esPrometedor() {
        if(this.G.adyacentes(this.fila, this.columna)) {
            if(!this.tipoEmparejamineto && (this.solOptima.pesoEmparejamiento < this.sol.pesoEmparejamiento))
                return false;
            else return true;
        }
        else return false;
    }
    
    /**
     * Algoritmo backtracking
     * @param x Etapa
     */
    private void btOptimo2(Etapa x) {
        Etapa xsig;
        Candidatos cand;
        
        if(esSolucion()) {
            if(esMejor()) {
                actualizaSolucion();
            }
        }           
            
        xsig = nuevaEtapa(x);
        cand = calculaCandidatos(x);
        while(quedanCandidatos(cand)) {
            ParOrdenado<Candidatos,Etapa> par = selecionaCandidato(cand, xsig);
            if(esPrometedor()) {
                anotaSolucion(par.segundo());
                btOptimo2(par.segundo());
                cancelaAnotacion(par.primero(), par.segundo());
            }
            cand = par.primero();
        }
    }
    
    /**
     * Metodo para obtener las parejas minimas del grafo con el cual se a inicializado la clase ApareamientoPerfectoMinimo
     * @return ArrayList<Arista> las aristas obtenidas
     */
    public ArrayList<Arista> obtenerParejasMaximas() {
        if(G == null) 
            throw new NullPointerException("Error al obtener las parejas: Debe inicializar Emparejamiento con un Grafo");
        if(G.completo())
            throw new IllegalArgumentException(msg_G_no_completo);
        
        return this.obtenerParejas(Boolean.TRUE);
    }
    
    /**
     * Metodo para obtener las parejas minimas del grafo con el cual se a inicializado la clase ApareamientoPerfectoMinimo
     * @return ArrayList<Arista> las aristas obtenidas
     */
    public ArrayList<Arista> obtenerParejasMinimas() {
        if(G == null) 
            throw new NullPointerException("Error al obtener las parejas: Debe inicializar Emparejamiento con un Grafo");
        if(!G.completo())
            throw new IllegalArgumentException(msg_G_no_completo);
        return this.obtenerParejas(Boolean.FALSE);
    }
    
    /**
     * Metodo general para obtener las aristas de la mejor solucion
     * @param tipo Si True --> MaximoEmparejamiento, False --> MinimoEmparejamiento
     * @return ArrayList<Arista> las aristas obtenidas
     */
    private ArrayList<Arista> obtenerParejas(Boolean tipo) {
        if(debug)
            System.out.println("************************obtenerParejas");
        this.tipoEmparejamineto = tipo;
        // preparar variables y estructuras y llamar al metodo de backtracking
        this.sol = new SolucionEmp();
        this.inicializarSolucion();
        this.solOptima = new SolucionEmp();
        this.solOptima.emparejamientos = new int[this.sol.emparejamientos.length][this.sol.emparejamientos.length];
        if(tipo) {
            this.solOptima.pesoEmparejamiento = Integer.MIN_VALUE; 
        }
        else {
            this.solOptima.pesoEmparejamiento = Integer.MAX_VALUE;
        }
        Etapa e = new Etapa();
        e.i = 0;
        e.k = 0;
        //backtracking
        this.btOptimo2(e);
        return obtenerAristas();
    }
    
    /**
     * Metodo para obtener las aristas de la mejor solucion
     * @return 
     */
    private ArrayList<Arista> obtenerAristas() {
        if(debug)
            System.out.println("************************obtenerAristas");
        ArrayList<Arista> ret = new ArrayList<>();
        if(this.solOptima != null) {
            int size = this.solOptima.emparejamientos.length;
            for(int i = 0; i < size; ++i) {
                for(int j = 0; j < size; ++j) {
                    int val = this.solOptima.emparejamientos[i][j]; 
                    if(val > 0 && val != Integer.MIN_VALUE && val != Integer.MAX_VALUE) {
                        Arista a = new Arista(G.arista(i, j));
                        ret.add(a);
                    }
                }
            }
            return ret;
        }
        else return null;
    }
    
    /**
     * Inicializa la matriz de emparejamiento de solucion
     */
    private void inicializarSolucion() {
        this.sol.numVerticesEmparejados = 0;
        this.sol.pesoEmparejamiento = 0;
        int size = this.G.numVertices();
        this.sol.emparejamientos = new int[size][size];
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                if(i != j) this.sol.emparejamientos[i][j] = 0;
                else {
                    if(this.tipoEmparejamineto) this.sol.emparejamientos[i][j] = Integer.MIN_VALUE;
                    else this.sol.emparejamientos[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }
    
    //G es el grafo completo, M es el arbol recubridor minimo
    /**
     * Metodo para obtener un multigrafo de M, que contiene el grafo M y el emparejamiento minimo
     * entre los vertizes de grado impar.
     * @param G es un grafo completo
     * @param M es grafo, y M es un subgrafo de G
     * @return MultiGrafo de M con la aristas minimas
     */
    public MultiGrafo apareamientoPerfectoMinimo(Grafo G, Grafo M) {
        if(G.numVertices() != M.numVertices()) 
            throw new IllegalArgumentException(msg_G_M_no_sub);
        //if(!G.completo())
        //    throw new IllegalArgumentException(msg_G_no_completo);
        ParOrdenado<Grafo,ArrayList<Integer>> grafoEmp;
        grafoEmp = this.verticesImpar(G, M);
        ApareamientoPerfectoMinimo emparejamiento = new ApareamientoPerfectoMinimo(grafoEmp.primero());
        ArrayList<Arista> insert =  emparejamiento.obtenerParejas(Boolean.FALSE);
        
        ArrayList<Arista> insert2 = new ArrayList<>();
        for(int i = 0; i < insert.size(); ++i) {
            Arista r = new Arista();
            r.modificarOrigen(grafoEmp.segundo().get(insert.get(i).origen()));
            r.modificarDestino(grafoEmp.segundo().get(insert.get(i).destino()));
            r.modificarPeso(insert.get(i).peso());
            insert2.add(r);
        }
        
        MultiGrafo R = new MultiGrafo(M);
        for(int i = 0; i < insert.size(); ++i) {
            R.anadirArista(insert2.get(i));
        }
        return R;
    }
    
    //Pre G,M not null y G completo
    //Post: O que es el grafo completo G talque en M tiene grado impar
    /**
     * Metodo que obtiene todos los vertices de grado impar de M, con todas las arista de G
     * @param G Grafo completo
     * @param M M grafo, subgrafo de G
     * @return Primero = Grafo completo de los vertizes de grado impar de M, 
     * ArrayList<Integer> relaciones de los nuevos identificadores con los identificadores de M. 
     */
    private ParOrdenado<Grafo,ArrayList<Integer>> verticesImpar(Grafo G, Grafo M) {
        Grafo ret = new Grafo();
        ArrayList<Integer> relacion = new ArrayList<>();
        Boolean primero = true;
        for(int i = 0; i < M.numVertices(); ++i) {
            if(M.gradoVertice(i)%2 != 0) {                
                if(!primero) ret.anadirVertice(new Vertice(i));
                else primero = false;
                relacion.add(i);
            }
        }
        for(int i = 0; i < relacion.size(); ++i) {
            ArrayList<Arista> a = G.aristasIncidentes(relacion.get(i));
            for(int j = 0; j < a.size(); ++j) {
                int k = 0;
                boolean trobat = Boolean.FALSE;
                while(k < relacion.size() && !trobat) {
                    if(relacion.get(k) == a.get(j).destino()) {
                        Arista s = new Arista(i, k, a.get(j).peso());
                        ret.ponerArista(s);
                        trobat = Boolean.TRUE;
                    }
                    ++k;
                }
            }
        }
        return new ParOrdenado<>(ret, relacion);
    }
}
