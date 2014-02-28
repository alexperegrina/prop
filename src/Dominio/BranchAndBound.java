/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.util.ArrayList;
/**
 *
 * @author Alex Peregrina Cabrera
 */
public class BranchAndBound extends PrimeraSolucion{
    protected static String msg_G_not_null = "Error: G debe ser inicializado";
    protected Boolean debug = Boolean.FALSE;
    
    protected Grafo G;
    // Fila del vértice sin pareja.
    protected int fila;
    // Columna del vértice sin pareja
    protected int columna;
    // Almacena las soluciones parciales encontradas
    protected SolucionEmp sol;
    // Almacena la solución óptima encontrada.
    protected SolucionEmp solOptima;
    
    protected Long   timeInici; //contiene el tiempo del sistema
    protected Long timeFi; //indica el tiempo que deja para procesar la solucion
    
    protected Integer numSoluciones; //indica el numero de soluciones encontradas

    /**
     * Constructor por defecto. 
     */
    public BranchAndBound() {
        this.timeFi = Long.parseLong("2000000000000");;
        this.numSoluciones = 0;
    }

    @Override
    ArrayList<Integer> obtenerPrimeraSolucion(Grafo G) {
        this.numSoluciones = 0;
        return this.BranchAndBound(G);
    }
    
    /**
     * Metodo para indicar al algoritmo su tiempo de procesado de la solucion.
     * si buscando la solucion supera el tiempo establecido, continua asta obtener almenos
     * una solucion
     * @param timeFi tiempo expresado en segundo. 
     */
    public void indicarTiempoMaximo(Long timeFi) {
        this.timeFi = (timeFi*1000);
    }
    
    /**
     * Metodo para indicar al algoritmo el maximo tiempo predefinido.
     */
    public void ponerMaximoTiempo() {
        this.timeFi = Long.parseLong("2000000000000");
    }
    
    /**
     * Metodo para consultar el tiempo limite que tiene el algoritmo
     * @return Long expresado en milisegundo
     */
    public Long consultarTiempoMaximo() {
        return timeFi;
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
        // Candidato actualmente procesado, candidatos.verticesSinPareja[c.i]
        int i;
    }

    //
    /**
     * Funcion para comprovar si en sol puede contener una solucion
     * @return True -> si es una posible solucion, False -> si no es una solucion
     */
    private Boolean esSolucion() {
        if(debug) {
            System.out.println("**********************esSolucion");
            printSol(); 
        }
        
        return (this.sol.numVerticesEmparejados == (this.G.numVertices()-1));
    }
    
    /**
     * Comprueva si la solucion obtenida si es mejor que la mejor obtenida asta el momento,
     * si es asi la substituye.
     * @return True -> si es mejo, False -> si no es mejor
     */
    private Boolean esMejor() {
        if(debug) {
            System.out.println("**********************esMejor");
        }
        
        if(this.solOptima == null || (this.sol.pesoEmparejamiento < this.solOptima.pesoEmparejamiento)) return true;
        else return false;
    }
    
    /**
     * Substituye la solucion mejor obtenida asta el momenot por la solucion processada 
     */
    private void actualizaSolucion() {
        if(debug) {
            System.out.println("**********************actualizaSolucion");
            printSol();
        }
        
        this.numSoluciones++;  
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
        if(debug) {
            System.out.println("**********************nuevaEtapa");
        }
        
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
        if(debug) {
            System.out.println("**********************quedanCandidatos");
            System.out.println("c.i: " + c.i);
            System.out.println("c.verticesSinPareja.size(): " + c.verticesSinPareja.size());
        }
        
        if(c.verticesSinPareja.size() > 0 && c.i+1 < c.verticesSinPareja.size()) return true;
        else return false;
    }
    
    /**
     * Calcula los Candidatos porsibles para una etapa en concreto
     * @param e Etapa a comprovar si quedan candidatos
     * @return candidatos en esa etapa
     */
    private Candidatos calculaCandidatos(Etapa e) {
        if(debug) {
            System.out.println("**********************calculaCandidatos");
            System.out.println("e.i: " + e.i);
        }
        
        Candidatos c = new Candidatos();
        if(e.k <= this.G.numVertices()) {
            //crear un objeto candidato y rellenarlo para esta etapa         
            c.i = -1; //no apuntamos a ninguna pos porque en la funcion seleccionaCandidato ya incrementamos este valor
            //buscar el primer vertize sin emparejar
            int size = this.sol.emparejamientos.length;
            
            c.fila = e.i;
            
            //añadimos los candidatos
            c.verticesSinPareja = new ArrayList<>();
            for(int k = 0; k < size; ++k) {
                if(this.sol.emparejamientos[c.fila][k] == 0) {
                    if(debug) {
                        System.out.print(" candidato: " + k+ " ");
                    }
                    c.verticesSinPareja.add(k);
                }
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
        
        
        if(debug) {
            System.out.println("**********************selecionacandidato");
            System.out.println("rc.fila: "+rc.fila);
            System.out.println("rc.i: "+ rc.i);
            System.out.println("verticesSinPareja.size: "+c.verticesSinPareja.size() );
            System.out.println("this.fila: "+this.fila);
            System.out.println("this.columna: "+ this.columna);
        }
        
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
            System.out.println("**********************anotaSolucion");
            printSol();
        }
        //marcar la pareja en la matriz        
        this.sol.numVerticesEmparejados += 1;     
        this.sol.pesoEmparejamiento += (Double)this.G.arista(this.fila, this.columna).peso();
        //marcar las no validas
        int size = this.sol.emparejamientos.length;
        for(int i = 0; i < size; ++i) {
            for(int j = 0; j < size; ++j) {
                if(i != j && this.sol.emparejamientos[i][j] == 0 && 
                        (j == this.columna || j == this.fila) ) this.sol.emparejamientos[i][j] = -e.k;
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
        if(debug) {
            System.out.println("**********************cancelaAnotacion");
        }
        
        this.fila = c.fila;
        this.columna = c.verticesSinPareja.get(c.i);
        
        this.sol.emparejamientos[this.fila][this.columna] = 0;
        this.sol.pesoEmparejamiento -= (Double)this.G.arista(this.fila, this.columna).peso();
        this.sol.numVerticesEmparejados -= 1;
        
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
     *  ·Comprovamos si son adyacentes en el grafo G.
     *  ·Comprovamos si a transurrido el tiempo maximo indicado, pero si aun no se a encontrado una solucion
     *   continua asta encontrar una.
     *  ·Comprvamos si la mejor solucion obtenida asta el momento es mejor que la solucion parcial, 
     *    realicamos la poda del arbol. 
     * @return Si la ramaificacion escojida es optima para la solucion
     */
    private boolean esPrometedor() {
        
        if(debug) {
            System.out.println("**********************esPrometedor");
            System.out.println("this.solOptima.pesoEmparejamiento: " + this.solOptima.pesoEmparejamiento);
            System.out.println("this.sol.pesoEmparejamiento: " + this.sol.pesoEmparejamiento);
            System.out.println("this.timeInici: " + this.timeInici);
            System.out.println("this.timeFi: " + this.timeFi);
            System.out.println("this.timeInici+this.timeFi: " + (this.timeInici+this.timeFi));
            System.out.println("System.currentTimeMillis(): " + System.currentTimeMillis());
            System.out.println("numSoluciones: " + this.numSoluciones);
        }

        if(!this.G.adyacentes(this.fila, this.columna) 
                || ((((this.timeInici+this.timeFi) < System.currentTimeMillis()) && this.numSoluciones > 0)  
                || (this.solOptima.pesoEmparejamiento < this.sol.pesoEmparejamiento))) {
            return false;
        }
        else return true;
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
            xsig = par.segundo();
        }
    }
    
    /**
     * Metodo general para obtener las aristas de la mejor solucion
     * @param tipo Si True --> MaximoEmparejamiento, False --> MinimoEmparejamiento
     * @return ArrayList<Arista> las aristas obtenidas
     */
    //private ArrayList<Arista> solucion() {
    private Arista[] solucion() {
        // preparar variables y estructuras y llamar al metodo de backtracking
        this.sol = new SolucionEmp();
        this.inicializarSolucion();
        this.solOptima = new SolucionEmp();
        this.solOptima.emparejamientos = new int[this.sol.emparejamientos.length][this.sol.emparejamientos.length];
        this.solOptima.pesoEmparejamiento = Integer.MAX_VALUE;
        Etapa e = new Etapa();
        e.i = 0;
        e.k = 0;
        this.timeInici = System.currentTimeMillis();
        //backtracking
        this.btOptimo2(e);
        return obtenerAristas();
    }
    
    /**
     * Metodo para obtener las aristas de la mejor solucion
     * @return 
     */
    //private ArrayList<Arista> obtenerAristas() {
    private Arista[] obtenerAristas() {
        Arista ret[] = new Arista[this.G.numVertices()];
        if(this.solOptima != null) {
            int size = this.solOptima.emparejamientos.length;
            for(int i = 0; i < size; ++i) {
                for(int j = 0; j < size; ++j) {
                    int val = this.solOptima.emparejamientos[i][j]; 
                    if(val > 0 && val != Integer.MIN_VALUE && val != Integer.MAX_VALUE) {
                        ret[val] = new Arista(G.arista(i, j));
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
                    this.sol.emparejamientos[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }
    
    /**
     * Metodo para obtener una primera solucion optima, mediante el algoritmo
     * de BranchAndBound
     * @param G es un grafo completo
     * @return ArrayList<Integer> que contiene la solucion obtenida.
     */
    private ArrayList<Integer> BranchAndBound(Grafo G) {
        if(G == null)
            throw new NullPointerException(msg_G_not_null);
        this.G = new Grafo(G);
        Arista aristas[] = this.solucion();
        ArrayList<Integer> camino = this.obtenerCamino(aristas);
        return camino;
    }
    
    /**
     * Con el conjunto de aristas obtenido por solucion, obtenemos el camino indicado por los id
     * @param insert Array de aristas, comenza acontar a partir de la posicion 1 
     * ya que las etapas comienzan a contar apartir del 1
     * @return ArrayList<Integer> id's ordenados ascendetemente del camino obtenido
     */
    private ArrayList<Integer> obtenerCamino(Arista[] insert) {
        ArrayList<Integer> insert2 = new ArrayList<>();
        Integer size;
        //mostrarAristas(insert);
        for(int i = 1; i < insert.length; ++i) {
            if(i != insert.length-1) {
                insert2.add(insert[i].origen());
            }
            else {
                insert2.add(insert[i].origen());
                insert2.add(insert[i].destino());
            }
        }
        if(debug) {
            System.out.println("CaminoAristas ");
            for(int i = 0; i < insert2.size(); ++i) {
                System.out.print(" "+insert2.get(i));
            }
            System.out.println(" ");
        }
        return insert2;
    }

    public static void mostrarAristas(ArrayList<Arista> aristas) {
        for(int i = 0; i < aristas.size(); ++i) {
            System.out.print("Arista" + i +"\n");
            System.out.print("origen:" + aristas.get(i).origen() );
            System.out.print("destino:" + aristas.get(i).destino() );
            System.out.print("peso:" + aristas.get(i).peso() +"\n");
            System.out.print(" --- ");
        }
    }
    
    public static void mostrarArista(Arista a) {
            System.out.print("Arista \n");
            System.out.print("origen:" + a.origen() );
            System.out.print("destino:" + a.destino() );
            System.out.print("peso:" + a.peso() +"\n");
            System.out.print(" --- ");

    }
    
    private void printMejor() {
        System.out.print("+++++++++++++++++Mejor\n");
        System.out.print("solOptima.numVérticesEmparejados "+ this.solOptima.numVerticesEmparejados+"\n");
        System.out.print("solOptima.pesoEmparejamiento "+ this.solOptima.pesoEmparejamiento+"\n");
        System.out.print("solOptima.lenght "+ this.solOptima.emparejamientos.length+"\n");
        printMat(this.solOptima.emparejamientos);
        System.out.print("+++++++++++++++++\n");
    }
    
    private void printSol() {
        System.out.print("+++++++++++++++++Sol\n");
        System.out.print("sol.numVérticesEmparejados "+ this.sol.numVerticesEmparejados+"\n");
        System.out.print("sol.pesoEmparejamiento "+ this.sol.pesoEmparejamiento+"\n");
        System.out.print("sol.lenght "+ this.sol.emparejamientos.length+"\n");
        printMat(this.sol.emparejamientos);
        System.out.print("+++++++++++++++++\n");
    }
    
    private void printMat(int[][] m) {
        System.out.print("****************** printMat\n");
        for(int i = 0; i < m.length; ++i) {
            for(int j = 0; j < m.length; ++j) System.out.print(" " + m[i][j] + " ");
            System.out.print("\n");
        }
    }
    private void printAristas(Arista[] m) {
        System.out.print("****************** printAristas\n");
        System.out.println("m.length: " + m.length);
        for(int i = 1; i < m.length; ++i) {
            mostrarArista(m[i]);
        }
    }
}
