
import java.util.ArrayList;

/**
 *
 * @author Alejandro Rosas
 */
public class ShortcutPaths extends CicloHamiltoniano{

    /**
     * Función que obtiene un ciclo Hamiltoniano
     * Implementacion del algoritmo de shortcutPaths para representar el Ciclo Hamiltoniano.
     * @param aristas Un ArrayList de Aristas ordenado de modo que sean adyacentes
     * @param g Un grafo conexo, no dirigido y ponderado
     * @return Un ArrayList de Aristas 
     */
    private ArrayList<Arista> shortcutPaths(ArrayList<Arista> aristas, Grafo g) {

        if(!g.completo()) 
            throw new IllegalArgumentException("Error: El grafo debe ser completo.");
        
        Arista primera_arista = aristas.get(0);
        Arista segunda_arista = aristas.get(1);
      
        int origen;

        int v1 = primera_arista.origen();
        int v2 = primera_arista.destino();

        if (segunda_arista.origen() == v1 || segunda_arista.destino() == v1) {
            origen = v1;
        }
        else if (segunda_arista.origen() == v2 || segunda_arista.destino() == v2){
            origen = v2;
        }
        else
        {
            throw new IllegalArgumentException("Error: El ArrayList de Aristas no estan ordenados correctamente.");
        }

        ArrayList<Arista> ciclo_hamiltoniano = new ArrayList<Arista>();
        ArrayList<Integer> vs = new ArrayList<Integer>();
        ArrayList<Integer> atajo_vs = new ArrayList<Integer>();

        vs.add(origen);
        int vertice_actual = origen;

        // Creamos una lista de Vertices en el orden que se han visitado
        for (Arista e : aristas) {
            //Obtenemos el siguiente vertice (de la arista) que no es el vertice actual
            int v_1 = e.origen();
            int v_2 = e.destino();
            if (vertice_actual == v_1) {
                vs.add(v_2);
                vertice_actual = v_2;
            }
            else {
                vs.add(v_1);
                vertice_actual = v_1;
            }
        }

        // Copiamos la lista de vertices pero sacando los nodos visitados
        for (Integer v : vs) {
            if (!(atajo_vs.contains(v))) {
                atajo_vs.add(v);
            }
        }

        // Creamos la lista de aristas en el Grafo Hamiltoniano 
        for (int i = 0; i < atajo_vs.size() - 1; i++) {
            ciclo_hamiltoniano.add(g.arista(atajo_vs.get(i), atajo_vs.get(i+1)));
        }

        // Añadimos la última arista para crear el ciclo
        ciclo_hamiltoniano.add(g.arista(atajo_vs.get(atajo_vs.size() - 1), origen));
        return ciclo_hamiltoniano;
    }

    @Override
    public ArrayList<Arista> obtenerCicloHamiltoniano(ArrayList<Arista> aristas, Grafo g) {
        return shortcutPaths(aristas, g);
    }
}
