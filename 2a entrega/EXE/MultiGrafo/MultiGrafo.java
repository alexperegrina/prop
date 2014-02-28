/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 * Multigrafo dirigido y ponderado genérico representado con matriz de
 * adyacencias. Esta clase también puede representar los casos particulares, es
 * decir, grafos que no sean multigrafos, grafos no dirigidos y grafos no
 * ponderados.
 *
 * T es el tipo de objetos asociado a los vértices, y N es el tipo numérico
 * asociado a las aristas para representar los pesos.
 *
 * Cada vértice tiene un id diferente. Estos id siempre están comprendidos entre
 * 0 y |V|-1 (ambos incluidos).
 *
 * Las aristas entre cada par de vértices son un conjunto de aristas. Su índice
 * en la posición del conjunto es la forma de identificarlas dentro de cada
 * conjunto de aristas incidentes en dos vértices. Este identificador nos las
 * distingue entre todas las aristas del grafo. Para distinguirlas globalmente
 * se necesitan los vértices origen y destino (o sus identificadores) y el
 * identificador de la arista dentro del conjunto (origen,destino).
 *
 * Si se quiere tratar el grafo como uno que no es un multigrafo, la forma de
 * hacerlo es sólo accediendo a la primera posición de cada conjunto de aristas
 * incidentes en dos vértices.
 *
 * Si se quiere tratar el grafo como uno no dirigido, toda modificación que se
 * haga en el conjunto de aristas (u,v) debe hacerse en el conjunto de aristas
 * (v,u).
 *
 * La forma de tratar un grafo sin ponderaciones es, simplemente, ignorar los
 * pesos asociados a las aristas.
 *
 * @author David Gracia Celemendi
 * @param <T> Tipo de objetos asociado a los vértices.
 * @param <N> Tipo numérico asociado a las aristas para representar los pesos.
 */
public class MultiGrafo<T,N extends Number> {

    private ArrayList<ArrayList<ArrayList<Arista<N>>>> matrizAdyacencias;
    private ArrayList<Vertice<T>> vertices;
    private int nAristas;
    private int nVertices;

    /**
     * Constructor por defecto. Crea un grafo con un vértice.
     */
    public MultiGrafo() {
        this.matrizAdyacencias = new ArrayList<>();
        this.matrizAdyacencias.add(new ArrayList<ArrayList<Arista<N>>>());
        this.matrizAdyacencias.get(0).add(new ArrayList<Arista<N>>());
        this.vertices = new ArrayList<>();
        this.vertices.add(new Vertice<T>());
        this.nVertices = 1;
    }

    /**
     * Contructor. Crea un grafo con el vértice v.
     * @param v El vértice con el que se va a crear el grafo.
     */
    public MultiGrafo(Vertice<T> v) {
        this();
        this.vertices.set(0, v);
    }

    /**
     * Constructor. Crea un grafo con n vértices.
     * @param n Número de vértices que se crearán en el grafo.
     * @throws IllegalArgumentException
     */
    public MultiGrafo(int n) throws IllegalArgumentException {
        this();
        if (n < 1) throw new IllegalArgumentException("Error al crear grafo: n < 1");
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                this.vertices.add(new Vertice<T>());
                this.nVertices++;
                this.matrizAdyacencias.add(new ArrayList<ArrayList<Arista<N>>>());
            }
            for (int j = 0; j < n; j++) {
                if (!(i == 0 && j == 0)) {
                    this.matrizAdyacencias.get(i).add(new ArrayList<Arista<N>>());
                }
            }
        }
    }

    /**
     *Constructor copia.
     * @param original Grafo que va a ser copiado.
     */
    public MultiGrafo(MultiGrafo<T,N> original) {
        this();

        this.nAristas = original.nAristas;
        this.nVertices = original.nVertices;

        int n = original.nVertices;

        for (int i = 0; i < n; i++) {
            Vertice<T> v = new Vertice<>(original.vertices.get(i));
            if (i == 0) {
                this.vertices.set(i, v);
            } else {
                this.vertices.add(v);
            }
        }

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                this.matrizAdyacencias.add(new ArrayList<ArrayList<Arista<N>>>());
            }

            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    this.matrizAdyacencias.get(0).set(0, new ArrayList<Arista<N>>());
                } else {
                    this.matrizAdyacencias.get(i).add(new ArrayList<Arista<N>>());
                }

                int m = original.matrizAdyacencias.get(i).get(j).size();
                for (int k = 0; k < m; k++) {
                    Arista<N> a = new Arista<>(original.matrizAdyacencias.get(i).get(j).get(k));
                    this.matrizAdyacencias.get(i).get(j).add(a);
                }
            }
        }
    }
    
    /**
     *Constructor copia.
     * @param original Grafo que va a ser copiado.
     */
    public MultiGrafo(Grafo<T,N> original) {
        this();

        this.nAristas = original.numAristas();
        this.nVertices = original.numVertices();

        int n = original.numVertices();

        for (int i = 0; i < n; i++) {
            Vertice<T> v = (Vertice<T>)new Vertice<>(i);
            if (i == 0) {
                this.vertices.set(i, v);
            } else {
                this.vertices.add(v);
            }
        }

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                this.matrizAdyacencias.add(new ArrayList<ArrayList<Arista<N>>>());
            }

            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    this.matrizAdyacencias.get(0).set(0, new ArrayList<Arista<N>>());
                } else {
                    this.matrizAdyacencias.get(i).add(new ArrayList<Arista<N>>());
                }
                
                /*Arista a = null;
                if(i!=j && original.adyacentes(i, j)) a = new Arista(original.arista(i, j));
                if(i==0 && j==0) {
                    this.matrizAdyacencias.get(i).get(i).set(i, a)
                    this.matrizAdyacencias.get(i).get(j).set(0, a);
                }
                else this.matrizAdyacencias.get(i).get(j).add(a);*/
                
                if(i!=j && original.adyacentes(i, j)) {
                    Arista a = new Arista(original.matrizAdyacencias().get(i).get(j));
                    this.matrizAdyacencias.get(i).get(j).add(a);
                }
            }
        }
    }

    /**
     *Devuelve la matriz de adyacencias del grafo.
     * @return Devuelve la matriz de adyacencias del grafo.
     */
    public ArrayList<ArrayList<ArrayList<Arista<N>>>> matrizAdyacencias() {
        return this.matrizAdyacencias;
    }

    /**
     *Devuelve el número de vértices del grafo.
     * @return El número de vértices del grafo.
     */
    public int numVertices() {
        return this.nVertices;
    }
    
    /**
     *Devuelve el número de arsitas del grafo.
     * @return El número de aristas del grafo.
     */
    public int numAristas() {
        return this.nAristas;
    }
    
     /**
     *Determina si existe un vértice con identificador=id.
     * @param id El identificador del vértice a comprobar.
     * @return Cierto si el grafo contiene un vértice con identificador=id, falso en caso contrario.
     */
    public boolean existeIdVertice(int id){
        return id>=0 && id<this.nVertices;
    }

     /**
     *Determina si v está contenido en el MultiGrafo.
     * @param v El vértice a comprobar.
     * @return Cierto si el grafo contiene v, falso en caso contrario.
     */
    public boolean existeVertice(Vertice<T> v) {
        return this.vertices.contains(v);
    }
    
     /**
     *Devuelve el id de un vértice contenido en el grafo.
     * @param v El vértice cuyo identificador queremos conocer.
     * @return El id de v contenido en el grafo.
     */
    public int idVertice(Vertice<T> v) throws IllegalArgumentException {
        if(!this.vertices.contains(v)) throw new IllegalArgumentException("Error al consultar el identificador del vértice: el vértice no existe.");
        
        return this.vertices.indexOf(v);
    }

     /**
     *Añade un nuevo vértice al grafo. El vértice no puede estar ya contenido en el grafo.
     * @param v El vértice que se añadirá al grafo.
     */    
    public void anadirVertice(Vertice<T> v) throws IllegalArgumentException {
        if(this.vertices.contains(v)) throw new IllegalArgumentException("Error al añadir vértice: el vértice ya existe.");
        this.vertices.add(v);
        this.matrizAdyacencias.add(new ArrayList<ArrayList<Arista<N>>>());
        this.nVertices++;

        for (int i = 0; i < this.nVertices; i++) {
            int dif = this.nVertices - this.matrizAdyacencias.get(i).size();

            for (int k = 0; k < dif; k++) {
                this.matrizAdyacencias.get(i).add(new ArrayList<Arista<N>>());
            }
        }
    }

     /**
     *Modifica un vértice del grafo. El identificador debe cumplir: id>=0 y id<"número de vértices del grafo".
     * @param id El identificador del vértice que va a ser sustituido.
     * @param v El vértice que sustituirá al original.
     */       
    public void modificarVertice(int id, Vertice<T> v) throws IllegalArgumentException {
        if(!this.existeIdVertice(id)) throw new IllegalArgumentException("Error al modificar vértice: identificador no válido.");
        this.vertices.set(id, v);
    }

     /**
     *Devuelve el vértice con identificador=id. El identificador debe cumplir: id>=0 y id<"número de vértices del grafo".
     * @param id El identificador del vértice que va a ser consultado.
     * @return El vértice solicitado.
     */  
    public Vertice<T> vertice(int id) throws IllegalArgumentException {
        if(!this.existeIdVertice(id)) throw new IllegalArgumentException("Error al consultar vértice: identificador no válido.");
        return this.vertices.get(id);
    }
    
     /**
     *Devuelve cierto si los dos vértices con identificador idOrigen y idDestino, respectivamente, son adyacentes. Falso en caso contrario.
     * @param idOrigen El identificador de un vértice que existe en el grafo.
     * @param idDestino El identificador de un vértice que existe en el grafo.
     * @return Cierto si los dos vértices son adyacentes, falso en caso contrario. 
     */      
    public boolean adyacentes(int idOrigen, int idDestino) throws IllegalArgumentException {
        if(!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) 
            throw new IllegalArgumentException("Error en adyacentes(int,int): algún vértice no existe.");
        return !this.matrizAdyacencias.get(idOrigen).get(idDestino).isEmpty() || !this.matrizAdyacencias.get(idDestino).get(idOrigen).isEmpty();
    }

     /**
     *Devuelve cierto si los vértices son adyacentes. Falso en caso contrario.
     * @param origen Un vértice que existe en el grafo.
     * @param destino Un vértice que existe en el grafo.
     * @return Cierto si los dos vértices son adyacentes, falso en caso contrario.
     */   
    public boolean adyacentes(Vertice<T> origen, Vertice<T> destino) throws IllegalArgumentException {
        if(!(this.vertices.contains(origen) && this.vertices.contains(destino))) 
            throw new IllegalArgumentException("Error en adyacentes(Vertice,Vertice): algún vértice no existe.");
        int idOrigen = this.vertices.indexOf(origen);
        int idDestino = this.vertices.indexOf(destino);
        return this.adyacentes(idOrigen, idDestino);
    }

     /**
     *Devuelve un ArrayList de aristas que corresponde al conjunto de aristas (idOrigen,idDestino).
     * @param idOrigen Identificador del extremo origen de la arista.
     * @param idDestino Identificador del extremo destino de la arista.
     * @return Un ArrayList de aristas que corresponde al conjunto de aristas (idOrigen,idDestino).
     */ 
    public ArrayList<Arista<N>> aristas(int idOrigen, int idDestino) throws IllegalArgumentException {
        if(!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) 
            throw new IllegalArgumentException("Error al consultar aristas: algún vértice no existe.");
        return this.matrizAdyacencias.get(idOrigen).get(idDestino);
    }

     /**
     *Devuelve un ArrayList de aristas que corresponde al conjunto de aristas (origen,destino).
     * @param origen Vértice origen de la arista.
     * @param destino Vértice destino de la arista.
     * @return Un ArrayList de aristas que corresponde al conjunto de aristas (origen,destino).
     */ 
    public ArrayList<Arista<N>> aristas(Vertice<T> origen, Vertice<T> destino) throws IllegalArgumentException {
        if(!(this.existeVertice(origen)&&this.existeVertice(destino))) 
            throw new IllegalArgumentException("Error al consultar aristas: algún vértice no existe.");
        return this.aristas(this.vertices.indexOf(origen), this.vertices.indexOf(destino));
    }
    
     /**
     *Devuelve cierto si existe una arista en el conjunto (origen,destino) con identificador=id. Falso en caso contrario.
     * @param idOrigen Identificador del vértice origen de la arista.
     * @param idDestino Identificador del vértice destino de la arista.
     * @param id Identificador de la arista, dentro del conjunto (origen,destino), que se va a comprobar si existe o no.
     * @return Cierto si existe una arista dentro del conjunto (origen,destino) con identificador=id. Falso en caso contrario.
     */     
    public boolean existeIdArista(int idOrigen, int idDestino, int id) throws IllegalArgumentException {
        if(!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) 
            throw new IllegalArgumentException("Error al comprobar identificador de arista: algún vértice no existe.");
        return id>=0 && id<this.matrizAdyacencias.get(idOrigen).get(idDestino).size();
    }

     /**
     *Devuelve cierto si la arista está presente en el grafo. Falso en caso contrario.
     * @param idOrigen Identificador del vértice origen de la arista.
     * @param idDestino Identificador del vértice destino de la arista.
     * @param arista Arista que se va a comprobar si existe o no.
     * @return Cierto si la arista está presente en el grafo. Falso en caso contrario.
     */     
    public boolean existeArista(int idOrigen, int idDestino, Arista<N> arista) throws IllegalArgumentException {
        if(!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) 
            throw new IllegalArgumentException("Error al comprobar arista: algún vértice no existe.");
        return this.matrizAdyacencias.get(idOrigen).get(idDestino).contains(arista);
    }

     /**
     *Devuelve cierto si la arista está presente en el grafo. Falso en caso contrario.
     * @param origen Vértice origen de la arista.
     * @param destino Vértice destino de la arista.
     * @param arista Arista que se va a comprobar si existe o no.
     * @return Cierto si la arista está presente en el grafo. Falso en caso contrario.
     */  
    public boolean existeArista(Vertice<T> origen, Vertice<T> destino, Arista<N> arista) throws IllegalArgumentException {
        if(!(this.existeVertice(origen)&& this.existeVertice(destino))) 
            throw new IllegalArgumentException("Error al comprobar arista: algún vértice no existe.");
        int idOrigen = this.vertices.indexOf(origen);
        int idDestino = this.vertices.indexOf(destino);
        return existeArista(idOrigen, idDestino, arista);
    }

     /**
     *Devuelve el número de aristas presentes entre los vértices con idOrigen y idDestino.
     * @param idOrigen Indetificador del vértice origen de la arista.
     * @param idDestino Indentificador del vértice destino de la arista.
     * @return Número de aristas presentes entre los vértices con idOrigen y idDestino.
     */
    public int numAristasEntreVertices(int idOrigen, int idDestino) throws IllegalArgumentException {
        if(!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) 
            throw new IllegalArgumentException("Error al consultar número de aristas entre vértices: algún vértice no existe.");
        return this.matrizAdyacencias.get(idOrigen).get(idDestino).size();
    }

     /**
     *Devuelve el número de aristas presentes entre los vértices origen y destino.
     * @param origen Vértice origen de la arista.
     * @param destino Vértice destino de la arista.
     * @return Número de aristas presentes entre los vértices origen y destino.
     */
    public int numAristasEntreVertices(Vertice<T> origen, Vertice<T> destino) throws IllegalArgumentException {
        if(!(this.existeVertice(origen) && this.existeVertice(destino))) 
            throw new IllegalArgumentException("Error al consultar número de aristas entre vértices: algún vértice no existe.");
        int idOrigen = this.vertices.indexOf(origen);
        int idDestino = this.vertices.indexOf(destino);
        return this.numAristasEntreVertices(idOrigen, idDestino);
    }

     /**
     *Devuelve una arista incidente en los vértices con idOrigen y idDestino. 
     * @param idOrigen Indentificador del vértice origen incidente en la arista con identificador=id dentro del conjunto (origen,destino).
     * @param idDestino Indentificador del vértice destino incidente en la arista con identificador=id dentro del conjunto (origen,destino).
     * @param id Identificador de la arista dentro del conjunto de aristas (origen,destino)
     * @return Arista incidente en origen y destino, y con identificador=id.
     */
    public Arista<N> arista(int idOrigen, int idDestino, int id) throws IllegalArgumentException {
        if(!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) 
            throw new IllegalArgumentException("Error al consultar una arista: algún vértice no existe.");
        if(!this.adyacentes(idOrigen, idDestino)) 
            throw new IllegalArgumentException("Error al consultar una arista: los vértices no son adyacentes.");
        if(!this.existeIdArista(idOrigen, idDestino, id)) 
            throw new IllegalArgumentException("Error al consultar una arista: la arista con identificador = "+ id + 
                    " no existe dentro del conjunto ("+idOrigen+","+idDestino+").");
        return this.matrizAdyacencias.get(idOrigen).get(idDestino).get(id);
    }

     /**
     *Devuelve una arista incidente en los vértices origen y destino. 
     * @param origen Vértice origen incidente en la arista con identificador=id dentro del conjunto (origen,destino).
     * @param destino Vértice destino incidente en la arista con identificador=id dentro del conjunto (origen,destino).
     * @param id Identificador de la arista dentro del conjunto de aristas (origen,destino)
     * @return Arista incidente en origen y destino, y con identificador=id.
     */
    public Arista<N> arista(Vertice<T> origen, Vertice<T> destino, int id) throws IllegalArgumentException {
        if(!(this.existeVertice(origen) && this.existeVertice(destino))) 
            throw new IllegalArgumentException("Error al consultar una arista: algún vértice no existe.");
        if(!this.adyacentes(origen, destino)) 
            throw new IllegalArgumentException("Error al consultar una arista: los vértices no son adyacentes.");
        if(!this.existeIdArista(this.idVertice(origen),this.idVertice(destino), id)) 
            throw new IllegalArgumentException("Error al consultar una arista: la arista con identificador = "+ id + 
                    " no existe dentro del conjunto ("+this.idVertice(origen)+","+this.idVertice(destino)+").");
        return this.arista(this.idVertice(origen), this.idVertice(destino), id);
    }

     /**
     *Añade una arista al grafo. 
     * @param arista Arista que se va a añadir al grafo.
     */
    public void anadirArista(Arista<N> arista) throws IllegalArgumentException {
        if(!(this.existeIdVertice(arista.origen()) && this.existeIdVertice(arista.destino()))) 
            throw new IllegalArgumentException("Error al añadir una arista: algún vértice no existe.");
        if(this.matrizAdyacencias.get(arista.origen()).get(arista.destino()).contains(arista)) 
            throw new IllegalArgumentException("Error al añadir una arista: la arista ya existe.");
        int idOrigen = arista.origen();
        int idDestino = arista.destino();
        
        this.matrizAdyacencias.get(idOrigen).get(idDestino).add(arista);
        this.matrizAdyacencias.get(idDestino).get(idOrigen).add(arista);
        this.nAristas++;
    }

     /**
     *Mofifica una arista del grafo. 
     * @param arista Arista que se va a añadir al grafo.
     */
    public void modificarArista(Arista<N> arista, int id) throws IllegalArgumentException {
        if(!(this.existeIdVertice(arista.origen()) && this.existeIdVertice(arista.destino()))) 
            throw new IllegalArgumentException("Error al modificar una arista: algún vértice no existe.");
        if(!this.existeIdArista(arista.origen(), arista.destino(), id)) 
            throw new IllegalArgumentException("Error al modificar una arista: la arista no existe.");
        this.matrizAdyacencias.get(arista.origen()).get(arista.destino()).set(id, arista);
    }

     /**
     *Eliminina una arista del grafo. 
     * @param idOrigen Identificador del extremo origen de la arista.
     * @param idDestino Identificador del extremo destino de la arista.
     * @param id Posición de la arista a eliminar dentro del conjunto (idOrigen,idDestino)
     */
    public void eliminarArista(int idOrigen, int idDestino, int id) throws IllegalArgumentException {
        if(!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) 
            throw new IllegalArgumentException("Error al eliminar una arista: algún vértice no existe.");
        if(!this.existeIdArista(idOrigen, idDestino, id)) 
            throw new IllegalArgumentException("Error al eliminar una arista: la arista no existe.");
        this.matrizAdyacencias.get(idOrigen).get(idDestino).remove(id);
        this.nAristas--;
    }
}
