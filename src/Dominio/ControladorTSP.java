
package Dominio;

import java.util.ArrayList;

/**
 *
 * @author David Gracia Celemendi
 */
public class ControladorTSP {

     /**
     * Obtiene un tour del grafo G.
     * @param G Un grafo completo del que se quiere obtener un tour.
     * @param op1 Algoritmo elegido para obtener una primera solución
     * @param op2 Algoritmo elegido para refinar la primera solución.
     * @return El tour obtenido (un ArrayList con los identificadores de los vértices en el grafo G).
     */
    public ArrayList<Integer> obtenerTour(Grafo G, String op1, String op2) throws IllegalArgumentException {
        if(!G.completo()) throw new IllegalArgumentException("Error al obtener tour: el grafo no es completo.");
        
        ArrayList<Integer> solucion = new ArrayList<Integer>();
        //Obtener primera solucion
        switch (op1) {
            case "Christofides":
                Christofides cris = new Christofides();
                solucion = cris.obtenerPrimeraSolucion(G);
                break;
            case "NearestNeighbour":
                NearestNeighbour NN = new NearestNeighbour();
                solucion = NN.obtenerPrimeraSolucion(G);
                break;
            case "BranchAndBound":
                BranchAndBound BaB = new BranchAndBound();
                solucion = BaB.obtenerPrimeraSolucion(G);
                break;
            default:
                throw new IllegalArgumentException("Error al obtener primera solución: el algoritmo no existe.");
        }
        
        ArrayList<Integer> solucionMejorada = new ArrayList<Integer>();
        //Refinar solucion
        switch (op2) {
            case "2OPT":
                DosOPT dosOpt = new DosOPT();
                solucionMejorada = dosOpt.mejorarSolucion(G,solucion);
                break;
            case "3OPT":
                TresOPT tresOpt = new TresOPT();
                solucionMejorada = tresOpt.mejorarSolucion(G,solucion);
                break;
            case "nada":    
                solucionMejorada = solucion;
                break;
            default:
                throw new IllegalArgumentException("Error al refinar la solución: el algoritmo no existe.");
        }
        
        return solucionMejorada;
    }
}
