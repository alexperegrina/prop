
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Alejandro Rosas
 */
public class NearestNeighbour extends PrimeraSolucion {
    
    
    @Override
     /**
     * Función que obtiene una Primera solución mediante el algoritmo NearestNeighbour.
     * Implementacion del algoritmo de NearestNeighbour.
     * @param g Un grafo completo
     * @return Un ArrayList de enteros con el Tour resultante del algoritmo
     */
    ArrayList<Integer> obtenerPrimeraSolucion(Grafo G) {
        return new ArrayList<Integer>(Arrays.asList(NearestNeighbour(G)));
    }
    
    
    /**
     * Función que obtiene una Primera solución mediante el algoritmo NearestNeighbour.
     * Implementacion del algoritmo de NearestNeighbour.
     * @param g Un grafo completo
     * @return Un Array de enteros con el Tour resultante del algoritmo
     */
    private Integer[] NearestNeighbour(Grafo g) {
        if(!g.completo()) 
            throw new IllegalArgumentException("Error: El grafo debe ser completo.");
        
        boolean[] visitado = new boolean[g.numVertices()];
        Integer tour[] = new Integer[g.numVertices()];
        int actual = (int) (Math.random() * (g.numVertices()));

        tour[0] = actual;
        visitado[actual] = true;

        int visitados = 1; // 
        while (visitados < g.numVertices()) {
            int id_mejor = 0;
            double mejor = 0.;
            boolean primero = true;
            for (int i = 0; i < g.numVertices(); i++) {
                if (!visitado[i]) {
                    if ((double) g.arista(actual, i).peso() < mejor || primero) {
                        id_mejor = i;
                        mejor = (double) g.arista(actual, i).peso();
                        primero = false;
                    }
                }
            }
            visitado[id_mejor] = true; //Actualizamos Visitado
            actual = id_mejor; // Actualizamos el vértice actual
            tour[visitados] = actual;
            ++visitados;
        }

        return tour;
    }

}
