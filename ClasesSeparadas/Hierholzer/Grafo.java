/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Grafo simple y ponderado genérico representado con matriz de adyacencias.
 *
 * T es el tipo de objetos asociado a los vértices, y N es el tipo numérico
 * asociado a las aristas para representar los pesos.
 *
 * Cada vértice tiene un id diferente. Estos id siempre están comprendidos entre
 * 0 y |V|-1 (ambos incluidos).
 *
 * Cada arista se puede identificar con los índices de la matriz de adyacencias.
 *
 * La forma de tratar un grafo sin ponderaciones es, simplemente, ignorar los
 * pesos asociados a las aristas.
 *
 * @author David Gracia Celemendi
 * @param <T> Tipo de objetos asociado a los vértices.
 * @param <N> Tipo numérico asociado a las aristas para representar los pesos.
 */
public class Grafo<T, N extends Number> {

    private ArrayList<ArrayList<Arista<N>>> matrizAdyacencias;
    private HashMap<Integer, ArrayList<Vertice<T>>> listasAdyacencias;
    private ArrayList<Vertice<T>> vertices;
    private int nAristas;
    private int nVertices;
    private ArrayList<Integer> grados;

    /**
     * Aumenta el grado de un vértice.
     *
     * @param id Identificador del vértice.
     */
    private void aumentarGrado(int id) throws IllegalArgumentException {
        if (!(this.existeIdVertice(id))) {
            throw new IllegalArgumentException("Error al aumentar grado: el vértice no existe.");
        }
        this.grados.set(id, this.grados.get(id) + 1);
    }

    /**
     * Disminuye el grado de un vértice.
     *
     * @param id Identificador del vértice.
     */
    private void disminuirGrado(int id) throws IllegalArgumentException {
        if (!(this.existeIdVertice(id))) {
            throw new IllegalArgumentException("Error al disminuir grado: el vértice no existe.");
        }
        this.grados.set(id, this.grados.get(id) - 1);
    }

    /**
     * Constructor por defecto. Crea un grafo con un vértice.
     */
    public Grafo() {
        this.matrizAdyacencias = new ArrayList<>();
        this.matrizAdyacencias.add(new ArrayList<Arista<N>>());
        this.listasAdyacencias = new HashMap<>();
        this.listasAdyacencias.put(0, new ArrayList<Vertice<T>>());
        this.vertices = new ArrayList<>();
        this.vertices.add(new Vertice());
        this.matrizAdyacencias.get(0).add(null);
        this.nVertices = 1;
        this.grados = new ArrayList<>();
        this.grados.add(0);
    }

//    /**
//     * Contructor. Crea un grafo con el vértice v.
//     * @param v El vértice con el que se va a crear el grafo.
//     */
//    public Grafo(Vertice v) {
//        this();
//        this.vertices.set(0, v);
//    }
    /**
     * Constructor. Crea un grafo con n vértices.
     *
     * @param n Número de vértices que se crearán en el grafo.
     * @throws IllegalArgumentException
     */
    public Grafo(int n) throws IllegalArgumentException {
        this();
        if (n < 1) {
            throw new IllegalArgumentException("Error al crear grafo: n < 1");
        }
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                this.vertices.add(new Vertice<T>());
                this.nVertices++;
                this.matrizAdyacencias.add(new ArrayList<Arista<N>>());
                this.listasAdyacencias.put(i, new ArrayList<Vertice<T>>());
                this.grados.add(0);
            }
            for (int j = 0; j < n; j++) {
                if (!(i == 0 && j == 0)) {
                    this.matrizAdyacencias.get(i).add(null);
                }
            }
        }
    }

    /**
     * Constructor copia.
     *
     * @param original Grafo que va a ser copiado.
     */
    public Grafo(Grafo original) {
        this();

        this.nAristas = original.nAristas;
        this.nVertices = original.nVertices;

        int n = original.nVertices;
        //Copiamos matriz de adyacencias
        for (int i = 0; i < n; i++) {
            Vertice v = new Vertice<>(original.vertices.get(i));
            Integer grado = new Integer((int) original.grados.get(i));
            if (i == 0) {
                this.vertices.set(0, v);
                this.grados.set(0, grado);
            } else {
                this.vertices.add(v);
                this.grados.add(grado);
                this.matrizAdyacencias.add(new ArrayList<Arista<N>>());
            }

            for (int j = 0; j < n; j++) {
                Arista a = null;
                if (i != j && original.adyacentes(i, j)) {
                    a = new Arista(original.arista(i, j));
                }
                if (i == 0 && j == 0) {
                    this.matrizAdyacencias.get(0).set(0, a);
                } else {
                    this.matrizAdyacencias.get(i).add(a);
                }
            }
        }
        //Copiamos listas de adyacencias
        for (int i = 0; i < this.nVertices; i++) {
            ArrayList<Vertice<T>> nuevaLista = new ArrayList<>();
            for (int j = 0; j < this.nVertices; j++) {
                if (i != j && this.adyacentes(i, j)) {
                    nuevaLista.add(this.vertice(j));
                }
            }
            this.listasAdyacencias.put(i, nuevaLista);
        }
    }

    /**
     * Devuelve la matriz de adyacencias del grafo.
     *
     * @return Devuelve la matriz de adyacencias del grafo.
     */
    public ArrayList<ArrayList<Arista<N>>> matrizAdyacencias() {
        return this.matrizAdyacencias;
    }

    /**
     * Devuelve las listas de adyacencias del grafo.
     *
     * @return Devuelve las listas de adyacencias del grafo.
     */
    public HashMap<Integer, ArrayList<Vertice<T>>> listasAdyacencias() {
        return this.listasAdyacencias;
    }

//     /**
//     * Devuelve la lista de adyacencias de un vértice.
//     * @param id El identificador del vértice
//     * @return Devuelve la lista de adyacencias de un vértice.
//     */        
//    public ArrayList<Vertice<T>> listaAdyacenciasVertice(int id) throws IllegalArgumentException {
//        if(!this.existeIdVertice(id)) throw new IllegalArgumentException("Error al consultar lista de adyacencias de un vértice: identificador no válido.");
//        return this.listasAdyacencias.get(id);   
//    }
    /**
     * Devuelve el número de vértices del grafo.
     *
     * @return El número de vértices del grafo.
     */
    public int numVertices() {
        return this.nVertices;
    }

    /**
     * Devuelve el número de arsitas del grafo.
     *
     * @return El número de aristas del grafo.
     */
    public int numAristas() {
        return this.nAristas;
    }

    /**
     * Determina si existe un vértice con identificador=id.
     *
     * @param id El identificador del vértice a comprobar.
     * @return Cierto si el grafo contiene un vértice con identificador=id,
     * falso en caso contrario.
     */
    private boolean existeIdVertice(int id) {
        return id >= 0 && id < this.nVertices;
    }

    /**
     * Determina si v está contenido en el grafo.
     *
     * @param v El vértice a comprobar.
     * @return Cierto si el grafo contiene v, falso en caso contrario.
     */
    private boolean existeVertice(Vertice v) {
        return this.vertices.contains(v);
    }

    /**
     * Devuelve el id de un vértice contenido en el grafo.
     *
     * @param v El vértice cuyo identificador queremos conocer.
     * @return El id de v contenido en el grafo.
     */
    public int idVertice(Vertice v) throws IllegalArgumentException {
        if (!this.vertices.contains(v)) {
            throw new IllegalArgumentException("Error al consultar el identificador del vértice: el vértice no existe.");
        }

        return this.vertices.indexOf(v);
    }

    /**
     * Devuelve el grado de un vértice contenido en el grafo.
     *
     * @param id El identificador del vértice cuyo grado queremos conocer.
     * @return El grado del vértice.
     */
    public int gradoVertice(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar grado vértice: identificador no válido.");
        }
        return this.grados.get(id);
    }

//     /**
//     * Devuelve el grado de un vértice contenido en el grafo.
//     * @param v El vértice cuyo grado queremos conocer.
//     * @return El grado del vértice.
//     */
//    public int gradoVertice(Vertice v) throws IllegalArgumentException {
//        if(!this.vertices.contains(v)) throw new IllegalArgumentException("Error al consultar grado vértice: vértice no existe.");
//        return this.gradoVertice(this.vertices.indexOf(v));
//    }
    /**
     * Devuelve las aristas incidentes en un vértice del grafo.
     *
     * @param id El identificador del vértice.
     * @return Las aristas incidentes en v.
     */
    public ArrayList<Arista> aristasIncidentes(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar aristas incidentes: id de vértice no existe.");
        }
        ArrayList<Arista> aristas = new ArrayList<>();
        for (int j = 0; j < this.nVertices; j++) {
            if (id != j && this.adyacentes(id, j)) {
                aristas.add(this.arista(id, j));
            }
        }
        return aristas;
    }

//     /**
//     * Devuelve las aristas incidentes en un vértice del grafo.
//     * @param v El vértice.
//     * @return Las aristas incidentes en v.
//     */
//    public ArrayList<Arista> aristasIncidentes(Vertice v) throws IllegalArgumentException {
//        if(!this.existeVertice(v)) throw new IllegalArgumentException("Error al consultar aristas incidentes: vértice no existe.");
//        return this.aristasIncidentes(this.idVertice(v));
//    }
    /**
     * Devuelve los vértices adyacentes a un vértice del grafo.
     *
     * @param id El identificador del vértice.
     * @return Los vértices adyacentes a v.
     */
    private ArrayList<Vertice> verticesAdyacentes(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar vértices adyacentes: id de vértice no existe.");
        }
        ArrayList<Vertice> vAd = new ArrayList<>();
        for (int j = 0; j < this.nVertices; j++) {
            if (id != j && this.adyacentes(id, j)) {
                vAd.add(this.vertice(j));
            }
        }
        return vAd;
    }

    /**
     * Devuelve los vértices adyacentes a un vértice del grafo.
     *
     * @param v El vértice.
     * @return Los vértices adyacentes a v.
     */
    public ArrayList<Vertice> verticesAdyacentes(Vertice v) throws IllegalArgumentException {
        if (!this.existeVertice(v)) {
            throw new IllegalArgumentException("Error al consultar vértices adyacentes: vértice no existe.");
        }
        return this.verticesAdyacentes(this.idVertice(v));
    }

    /**
     * Devuelve uno de los caminos posibles con menor longitud entre dos
     * vértices. El camino será un array con los indentificadores de los
     * vértices que forman el camino. Si no existe camino, devuelve un array
     * vacío.
     *
     * @param idOrigen Identificador de un vértice.
     * @param idDestino Identificador de un vértice.
     * @return Uno de los caminos posibles con menor longitud entre dos
     * vértices.
     */
    public ArrayList<Integer> camino(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error en camino(int,int): algún vértice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error en camino(int,int): deben ser identificadores diferentes.");
        }

        //BFS. Busco el camino de destino a origen para no tener que inveertir el array al final
        int[] predecesores = new int[this.nVertices];

        ArrayList<Vertice> Q = new ArrayList<>();
        boolean[] visitado = new boolean[this.nVertices];
        for (int i = 0; i < visitado.length; i++) {
            visitado[i] = false;
        }
        Vertice v = this.vertices.get(idDestino);
        Q.add(v);
        visitado[idDestino] = true;
        Vertice t = null;
        while (!Q.isEmpty() && idVertice(t = Q.get(0)) != idOrigen) {
            Q.remove(t);
            for (Vertice u : this.verticesAdyacentes(t)) {
                if (!visitado[this.idVertice(u)]) {
                    predecesores[this.idVertice(u)] = this.idVertice(t);
                    visitado[this.idVertice(u)] = true;
                    Q.add(u);
                }
            }
        }
        ArrayList<Integer> camino = new ArrayList<>();
        int id = idVertice(t);
        if (id == idOrigen) {
            while (id != idDestino) {
                camino.add(id);
                id = predecesores[id];
            }
            camino.add(id);
        }
        return camino;
    }

    /**
     * Devuelve cierto si las aristas conectan todos los vértices del grafo.
     * Falso en caso contrario.
     *
     * @param aristas Las aristas.
     * @return Cierto si las aristas conectan todos los vértices del grafo.
     * Falso en caso contrario.
     */
    public boolean conectaTodosVertices(ArrayList<Arista> aristas) throws IllegalArgumentException {
        ArrayList<Vertice> V = new ArrayList<>();
        int visitados = 0;
        for (int i = 0; i < aristas.size() && visitados < this.nVertices; i++) {
            Arista a = aristas.get(i);
            if (a == null) {
                throw new IllegalArgumentException("Error al comprobar arista: referencia nula a la arista.");
            }
            int idOrigen = a.origen();
            int idDestino = a.destino();
            if (idOrigen == idDestino) {
                throw new IllegalArgumentException("Error al comprobar arista: la arista tiene el mismo origen y destino.");
            }
            if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
                throw new IllegalArgumentException("Error al comprobar arista: algún vértice no existe.");
            }

            Vertice v1 = this.vertice(idOrigen);
            Vertice v2 = this.vertice(idDestino);
            if (!V.contains(v1)) {
                V.add(v1);
                visitados++;
            }
            if (!V.contains(v2)) {
                V.add(v2);
                visitados++;
            }
        }
        return visitados == this.nVertices;
    }

    /**
     * Devuelve cierto si el grafo es completo, falso en caso contrario.
     *
     * @return Cierto si el grafo es completo, falso en caso contrario.
     */
    public boolean completo() {
        boolean esCompleto = true;
        for (int i = 0; i < this.nVertices && esCompleto; i++) {
            for (int j = i + 1; j < this.nVertices; j++) {
                if (this.matrizAdyacencias.get(i).get(j) == null) {
                    esCompleto = false;
                }
            }
        }
        return esCompleto;
    }

    /**
     * Devuelve cierto si el grafo es conexo, falso en caso contrario.
     *
     * @return Cierto si el grafo es conexo, falso en caso contrario.
     */
    public boolean conexo() {
        //BFS
        ArrayList<Vertice> Q = new ArrayList<>();
        Q.add(this.vertices.get(0));
        boolean[] visitado = new boolean[this.nVertices];
        for (int i = 0; i < visitado.length; i++) {
            visitado[i] = false;
        }
        visitado[0] = true;
        int countVis = 1;
        while (!Q.isEmpty()) {
            Vertice t = Q.get(0);
            Q.remove(t);
            for (Vertice u : this.verticesAdyacentes(t)) {
                if (!visitado[this.idVertice(u)]) {
                    visitado[this.idVertice(u)] = true;
                    countVis++;
                    Q.add(u);
                }
            }
        }
        return countVis == this.nVertices;
    }

    /**
     * Devuelve cierto si el grafo es euleriano.
     *
     * @return Cierto si el grafo es euleriano. Falso en caso contrario.
     */
    public boolean euleriano() {
        for (int g : this.grados) {
            if (g % 2 == 1) {
                return false;
            }
        }
        return this.conexo();
    }

    /**
     * Añade un nuevo vértice al grafo. El vértice no puede estar ya contenido
     * en el grafo.
     *
     * @param v El vértice que se añadirá al grafo.
     */
    public void anadirVertice(Vertice v) throws IllegalArgumentException {
        if (this.vertices.contains(v)) {
            throw new IllegalArgumentException("Error al añadir vértice: el vértice ya existe.");
        }
        this.vertices.add(v);
        this.matrizAdyacencias.add(new ArrayList<Arista<N>>());
        this.grados.add(0);
        this.listasAdyacencias.put(this.nVertices, new ArrayList<Vertice<T>>());

        this.nVertices++;
        for (int i = 0; i < this.nVertices; i++) {
            int dif = this.nVertices - this.matrizAdyacencias.get(i).size();
            for (int j = 0; j < dif; j++) {
                this.matrizAdyacencias.get(i).add(null);
            }
        }
    }

    /**
     * Modifica un vértice del grafo. El identificador debe cumplir: id>=0 y
     * id menor que el número de vértices del grafo. 
     * @param id El identificador del vértice que va a ser sustituido.
     * @param v El vértice que sustituirá al original.
     */
    public void modificarVertice(int id, Vertice v) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al modificar vértice: identificador no válido.");
        }
        //Modificamos listas de adyacencias
        HashMap<Integer, ArrayList<Vertice<T>>> listas = this.listasAdyacencias;
        for (int i = 0; i < this.nVertices; i++) {
            ArrayList<Vertice<T>> lista = listas.get(i);
            if (i != id) {
                Vertice<T> w = this.vertice(id);
                if (lista.contains(w)) {
                    lista.set(lista.indexOf(w), v);
                } else {
                    lista.add(v);
                }
            }
        }
        //////
        this.vertices.set(id, v);
    }

    /**
     * Devuelve el vértice con identificador=id. El identificador debe cumplir:
     * id>=0 y id menor que el número de vértices del grafo.
     * @param id El identificador del vértice que va a ser consultado.
     * @return El vértice solicitado.
     */
    public Vertice vertice(int id) throws IllegalArgumentException {
        if (!this.existeIdVertice(id)) {
            throw new IllegalArgumentException("Error al consultar vértice: identificador no válido.");
        }
        return this.vertices.get(id);
    }

    /**
     * Devuelve cierto si los dos vértices con identificador idOrigen y
     * idDestino, respectivamente, son adyacentes. Falso en caso contrario.
     *
     * @param idOrigen El identificador de un vértice que existe en el grafo.
     * @param idDestino El identificador de un vértice que existe en el grafo.
     * @return Cierto si los dos vértices son adyacentes, falso en caso
     * contrario.
     */
    public boolean adyacentes(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error en adyacentes(int,int): algún vértice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error en adyacentes(int,int): deben ser identificadores diferentes.");
        }
        return this.matrizAdyacencias.get(idOrigen).get(idDestino) != null || this.matrizAdyacencias.get(idDestino).get(idOrigen) != null;
    }

//     /**
//     * Devuelve cierto si los vértices son adyacentes. Falso en caso contrario.
//     * @param origen Un vértice que existe en el grafo.
//     * @param destino Un vértice que existe en el grafo.
//     * @return Cierto si los dos vértices son adyacentes, falso en caso contrario.
//     */   
//    public boolean adyacentes(Vertice origen, Vertice destino) throws IllegalArgumentException {
//        if(!(this.vertices.contains(origen) && this.vertices.contains(destino))) 
//            throw new IllegalArgumentException("Error en adyacentes(Vertice,Vertice): algún vértice no existe.");
//        if(this.vertices.indexOf(origen)==this.vertices.indexOf(destino)) 
//            throw new IllegalArgumentException("Error en adyacentes(Vertice,Vertice): deben ser vértices diferentes.");
//        int idOrigen = this.vertices.indexOf(origen);
//        int idDestino = this.vertices.indexOf(destino);
//        return this.adyacentes(idOrigen, idDestino);
//    }
//     /**
//     * Devuelve cierto si la arista está presente en el grafo. Falso en caso contrario.
//     * @param idOrigen Identificador del vértice origen de la arista.
//     * @param idDestino Identificador del vértice destino de la arista.
//     * @return Cierto si la arista está presente en el grafo. Falso en caso contrario.
//     */     
//    public boolean existeArista(int idOrigen, int idDestino) throws IllegalArgumentException {
//        if(!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) 
//            throw new IllegalArgumentException("Error al comprobar arista: algún vértice no existe.");
//        if(idOrigen==idDestino) throw new IllegalArgumentException("Error al comprobar arista: deben ser identificadores diferentes.");
//        return this.matrizAdyacencias.get(idOrigen).get(idDestino)!=null || this.matrizAdyacencias.get(idDestino).get(idOrigen)!=null;
//    }
//     /**
//     * Devuelve cierto si la arista está presente en el grafo. Falso en caso contrario.
//     * @param origen Vértice origen de la arista.
//     * @param destino Vértice destino de la arista.
//     * @return Cierto si la arista está presente en el grafo. Falso en caso contrario.
//     */  
//    public boolean existeArista(Vertice origen, Vertice destino) throws IllegalArgumentException {
//        if(!(this.existeVertice(origen)&& this.existeVertice(destino))) 
//            throw new IllegalArgumentException("Error al comprobar arista: algún vértice no existe.");
//        if(this.vertices.indexOf(origen)==this.vertices.indexOf(destino)) 
//            throw new IllegalArgumentException("Error al comprobar arista: deben ser vértices diferentes.");
//        int idOrigen = this.vertices.indexOf(origen);
//        int idDestino = this.vertices.indexOf(destino);
//        return existeArista(idOrigen, idDestino);
//    }
    /**
     * Devuelve la arista incidente en los vértices con idOrigen y idDestino.
     *
     * @param idOrigen Indentificador del vértice origen incidente en la arista.
     * @param idDestino Indentificador del vértice destino incidente en la
     * arista.
     * @return Arista incidente en origen y destino.
     */
    public Arista arista(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error al consultar una arista: algún vértice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error al consultar una arista: deben ser identificadores diferentes.");
        }
        if (!this.adyacentes(idOrigen, idDestino)) {
            throw new IllegalArgumentException("Error al consultar una arista: los vértices no son adyacentes.");
        }

        Arista a = this.matrizAdyacencias.get(idOrigen).get(idDestino);
        return a;
    }

//     /**
//     * Devuelve una arista incidente en los vértices origen y destino. 
//     * @param origen Vértice origen incidente en la arista.
//     * @param destino Vértice destino incidente en la arista.
//     * @return Arista incidente en origen y destino.
//     */
//    public Arista arista(Vertice origen, Vertice destino) throws IllegalArgumentException {
//        if(!(this.existeVertice(origen) && this.existeVertice(destino))) 
//            throw new IllegalArgumentException("Error al consultar una arista: algún vértice no existe.");
//        if(this.vertices.indexOf(origen)==this.vertices.indexOf(destino)) 
//            throw new IllegalArgumentException("Error al consultar una arista: deben ser vértices diferentes.");
//        if(!this.adyacentes(origen, destino)) 
//            throw new IllegalArgumentException("Error al consultar una arista: los vértices no son adyacentes.");
//        return this.arista(this.idVertice(origen), this.idVertice(destino));
//    }
    /**
     * Pone una arista en el grafo que incidirá en los vértices idOrigen y
     * idDestino. Si ya hay una, la sustituye.
     *
     * @param idOrigen Identificador de un vértice.
     * @param idDestino Identificador de un vértice.
     * @param arista Arista que se va a añadir al grafo.
     */
    private void ponerArista(int idOrigen, int idDestino, Arista arista) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error al poner una arista: algún vértice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error al poner una arista: deben ser identificadores diferentes.");
        }
        if (!this.adyacentes(idOrigen, idDestino)) {
            this.aumentarGrado(idOrigen);
            this.aumentarGrado(idDestino);
        }
        //Actualizar matriz de adyacencia
        arista.modificarOrigen(idOrigen);
        arista.modificarDestino(idDestino);
        this.matrizAdyacencias.get(idOrigen).set(idDestino, arista);
        Arista inv = new Arista(arista);
        inv.modificarOrigen(idDestino);
        inv.modificarDestino(idOrigen);
        this.matrizAdyacencias.get(idDestino).set(idOrigen, inv);
        //Actualizar listas de adyacencias
        ArrayList<Vertice<T>> listAdyOri = this.listasAdyacencias.get(idOrigen);
        ArrayList<Vertice<T>> listAdyDest = this.listasAdyacencias.get(idDestino);
        Vertice origen = this.vertice(idOrigen);
        Vertice destino = this.vertice(idDestino);
        if (!listAdyOri.contains(destino)) {
            listAdyOri.add(destino);
        }
        if (!listAdyDest.contains(origen)) {
            listAdyDest.add(origen);
        }

        this.nAristas++;
    }

    /**
     * Pone una arista en el grafo que incidirá en los vértices arista.origen()
     * y arista.destino(). Si ya hay una, la sustituye.
     *
     * @param arista Arista que se va a añadir al grafo.
     */
    public void ponerArista(Arista arista) throws IllegalArgumentException {
        if (!(this.existeIdVertice(arista.origen()) && this.existeIdVertice(arista.destino()))) {
            throw new IllegalArgumentException("Error al poner una arista: algún vértice no existe.");
        }
        if (arista.origen() == arista.destino()) {
            throw new IllegalArgumentException("Error al poner una arista: deben ser identificadores diferentes.");
        }
        this.ponerArista(arista.origen(), arista.destino(), arista);
    }

    /**
     * Eliminina una arista del grafo.
     *
     * @param idOrigen Identificador del extremo origen de la arista.
     * @param idDestino Identificador del extremo destino de la arista.
     */
    public void eliminarArista(int idOrigen, int idDestino) throws IllegalArgumentException {
        if (!(this.existeIdVertice(idOrigen) && this.existeIdVertice(idDestino))) {
            throw new IllegalArgumentException("Error al eliminar una arista: algún vértice no existe.");
        }
        if (idOrigen == idDestino) {
            throw new IllegalArgumentException("Error al eliminar una arista: deben ser identificadores diferentes.");
        }
        if (!this.adyacentes(idOrigen, idDestino)) {
            throw new IllegalArgumentException("Error al eliminar una arista: la arista no existe.");
        }
        if (this.adyacentes(idOrigen, idDestino)) {
            this.disminuirGrado(idOrigen);
            this.disminuirGrado(idDestino);
        }
        //Actualizar matriz de adyacencia
        this.matrizAdyacencias.get(idOrigen).set(idDestino, null);
        this.matrizAdyacencias.get(idDestino).set(idOrigen, null);
        //Actualizar listas de adyacencias
        ArrayList<Vertice<T>> listAdyOri = this.listasAdyacencias.get(idOrigen);
        ArrayList<Vertice<T>> listAdyDest = this.listasAdyacencias.get(idDestino);
        Vertice origen = this.vertice(idOrigen);
        Vertice destino = this.vertice(idDestino);
        listAdyOri.remove(destino);
        listAdyDest.remove(origen);

        this.nAristas--;
    }
}
