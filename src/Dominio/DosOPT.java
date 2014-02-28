package Dominio;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Alejandro Rosas
 */
public class DosOPT extends Refinamiento {
    private int Soluciones;
    
    /**
     * Función que obtiene una Mejora del tour mediante el 2-opt.
     * Implementacion del algoritmo de 2-opt.
     * @param g Un grafo completo
     * @param tour un ArrayList de identficadores de vertices del grafo g.
     * @return Un ArrayList de enteros con el Tour resultante del algoritmo
     */
    @Override
    ArrayList<Integer> mejorarSolucion(Grafo g, ArrayList<Integer> tour) {
        Integer[] n_tour = new Integer[tour.size()];
        n_tour = tour.toArray(n_tour);

        return new ArrayList<Integer>(Arrays.asList(dosopt(g, n_tour)));
    }
    
    
    /**
     * Constructor por defecto.
     */
    DosOPT()
    {
        Soluciones = 0;
    }
    
    /**
     * Consultor de Soluciones.
     *
     * @return Las soluciones consultadas
     */
    public int soluciones() {
        return this.Soluciones;
    }
    
    /**
     * Función que obtiene una Mejora del tour mediante el 2-opt.
     * Implementacion del algoritmo de 2-opt.
     * @param g Un grafo completo
     * @param tour un Array de identficadores de vertices del grafo g.
     * @return Un Array de enteros con el Tour resultante del algoritmo
     */
    private Integer[] dosopt(Grafo g, Integer[] tour) {
        if(!g.completo()) 
            throw new IllegalArgumentException("Error: El grafo debe ser completo.");
        boolean mejora;
        Soluciones = 0;
        do {
            double distancia = calcular_distancia(g, tour);
            mejora = false;
            for (int i = 0; i < tour.length - 1; ++i) {
                for (int k = i + 1; k < tour.length; k++) {
                    Integer[] nuevo_tour = intercambio(tour, i, k);
                    double nueva_distancia = calcular_distancia(g, nuevo_tour);
                    if (nueva_distancia < distancia) {
                        tour = nuevo_tour;
                        mejora = true;
                        ++Soluciones;
                    }
                }
            }
        } while (mejora);
        return tour;
    }

    /**
     * Función que obtiene una permutación del tour
     * @param tour un Array de identficadores de vertices del grafo g.
     * @param i una posición del array del tour.
     * @param k una posición del array del tour.
     * @return El tour resultante del intercambio
     */
    private Integer[] intercambio(Integer[] tour, int i, int k) {
        Integer[] nuevo_tour = tour.clone();
        Integer[] revert = new Integer[k-i];
        System.arraycopy(tour,i,revert,0,k-i);
        reverse(revert);
        System.arraycopy(revert,0,nuevo_tour,i,revert.length);
        return nuevo_tour;
    }

    
    /**
     * Función que calcula la distancia de un tour.
     * @param g Un grafo completo
     * @param tour un Array de identficadores de vertices del grafo g.
     * @return La distancia del recorrido del tour
     */
    private double calcular_distancia(Grafo g, Integer[] tour) {
        double distancia = (double) g.arista(tour[0], tour[tour.length-1]).peso();
        for (int i = 1; i < tour.length; ++i) {
            distancia += (double) g.arista(tour[i - 1], tour[i]).peso();
        }
        return distancia;
    }
    
    
    /**
     * Función que invierte un array de enteros
     * @param v un array de enteros.
     */
    private void reverse (Integer[] v)
    {
        for(int i = 0; i < v.length / 2; i++)
        {
            Integer temp = v[i];
            v[i] = v[v.length - i - 1];
            v[v.length - i - 1] = temp;
        }
    }

}
