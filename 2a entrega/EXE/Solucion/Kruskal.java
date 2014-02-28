import java.util.PriorityQueue;

/**
 *
 * @author David Gracia Celemendi
 */
public class Kruskal extends ArbolRecubridorMinimo {

    /**
     * Función que obtiene el arbol recubridor mínimo de un grafo.
     * Implementacion del algoritmo de Kruskal.
     * @param G Un grafo conexo, no dirigido y ponderado
     * @return El arbol recubridor mínimo de G
     */
    @Override
    public Grafo<Integer,Double> obtenerARM(Grafo<Integer,Double> G) throws IllegalArgumentException {
        if(!G.conexo()) throw new IllegalArgumentException("Error al generar arbol recubridor mínimo: el grafo no es conexo.");
        int n = G.numVertices();

        Grafo<Integer,Double> arbol = new Grafo<Integer,Double>(n);
        for (int i = 0; i < n; i++) {
            arbol.modificarVertice(i, new Vertice<Integer>(G.vertice(i)));
        }

        int m = G.numAristas();
        if (m > 0) {
            PriorityQueue<Arista> aristas = new PriorityQueue<>(m); //Contiene todas las aristas de G
            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (G.adyacentes(i, j)) {
                        aristas.add(G.arista(i, j));
                    }
                }
            }
            Arista<Double> aristaPesoMin = null;
            while (!aristas.isEmpty()) { 
                aristaPesoMin = aristas.poll();
                int vOrigen = aristaPesoMin.origen();
                int vDestino = aristaPesoMin.destino();
                if(arbol.camino(vOrigen, vDestino).isEmpty()){
                    arbol.ponerArista(new Arista<>(aristaPesoMin.origen(), aristaPesoMin.destino(), aristaPesoMin.peso()));
                }
            }
        }
        return arbol;
    }

}
