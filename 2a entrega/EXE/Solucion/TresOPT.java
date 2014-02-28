

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Alejandro Rosas
 */
public class TresOPT extends Refinamiento {

    private int Soluciones;
    
    /**
     * Función que obtiene una Mejora del tour mediante el 3-opt. Implementacion
     * del algoritmo de 3-opt.
     *
     * @param g Un grafo completo
     * @param tour un ArrayList de identficadores de vertices del grafo g.
     * @return Un ArrayList de enteros con el Tour resultante del algoritmo
     */
    @Override
    ArrayList<Integer> mejorarSolucion(Grafo g, ArrayList<Integer> tour) {
        Integer[] n_tour = new Integer[tour.size()];
        n_tour = tour.toArray(n_tour);

        return new ArrayList<Integer>(Arrays.asList(tresopt(g, n_tour)));
    }
    
     /**
     * Constructor por defecto.
     */
    TresOPT()
    {
        Soluciones = 0;
    }
    
    /**
     * Consultor de Soluciones.
     *
     * @return Las soluciones consultadas.
     */
    public int soluciones() {
        return this.Soluciones;
    }

    /**
     * Función que obtiene una Mejora del tour mediante el 3-opt. Implementacion
     * del algoritmo de 3-opt.
     *
     * @param g Un grafo completo
     * @param tour un Array de identficadores de vertices del grafo g.
     * @return Un Array de enteros con el Tour resultante del algoritmo
     */
    private Integer[] tresopt(Grafo g, Integer[] tour) {
        if(!g.completo()) 
            throw new IllegalArgumentException("Error: El grafo debe ser completo.");
        boolean mejora;
        Soluciones = 0;
        double distancia = calcular_distancia(g, tour);
        do {
            mejora = false;
            for (int i = 0; i < tour.length - 3; ++i) {
                for (int j = i + 1; j < tour.length - 2; j++) {
                    for (int k = j + 1; k < tour.length - 1; k++) {
                        Integer[] nuevo_tour = new Integer[tour.length];

                        nuevo_tour = intercambio(tour, i, i + 1, j, k, j + 1, k + 1);
                        if (calcular_distancia(g, nuevo_tour) < distancia) {
                            tour = nuevo_tour;
                            distancia = calcular_distancia(g, nuevo_tour);
                            mejora = true;
                            ++Soluciones;
                        }

                        nuevo_tour = intercambio(tour, i, j, i + 1, j + 1, k, k + 1);
                        if (calcular_distancia(g, nuevo_tour) < distancia) {
                            tour = nuevo_tour;
                            distancia = calcular_distancia(g, nuevo_tour);
                            mejora = true;
                            ++Soluciones;
                        }

                        nuevo_tour = intercambio(tour, i, j, i + 1, k, j + 1, k + 1);
                        if (calcular_distancia(g, nuevo_tour) < distancia) {
                            tour = nuevo_tour;
                            distancia = calcular_distancia(g, nuevo_tour);
                            mejora = true;
                            ++Soluciones;
                        }

                        nuevo_tour = intercambio(tour, i, j + 1, k, i + 1, j, k + 1);
                        if (calcular_distancia(g, nuevo_tour) < distancia) {
                            tour = nuevo_tour;
                            distancia = calcular_distancia(g, nuevo_tour);
                            mejora = true;
                            ++Soluciones;
                        }

                        nuevo_tour = intercambio(tour, i, j + 1, k, j, i + 1, k + 1);
                        if (calcular_distancia(g, nuevo_tour) < distancia) {
                            tour = nuevo_tour;
                            distancia = calcular_distancia(g, nuevo_tour);
                            mejora = true;
                            ++Soluciones;
                        }

                        nuevo_tour = intercambio(tour, i, k, j + 1, i + 1, j, k + 1);
                        if (calcular_distancia(g, nuevo_tour) < distancia) {
                            tour = nuevo_tour;
                            distancia = calcular_distancia(g, nuevo_tour);
                            mejora = true;
                            ++Soluciones;
                        }

                        nuevo_tour = intercambio(tour, i, k, j + 1, j, i + 1, k + 1);
                        if (calcular_distancia(g, nuevo_tour) < distancia) {
                            tour = nuevo_tour;
                            distancia = calcular_distancia(g, nuevo_tour);
                            mejora = true;
                            ++Soluciones;
                        }
                    }
                }
            }
        } while (mejora);
        return tour;
    }

    
    /**
     * Función que calcula la distancia de un tour.
     * @param g Un grafo completo
     * @param tour un Array de identficadores de vertices del grafo g.
     * @return La distancia del recorrido del tour
     */
    private double calcular_distancia(Grafo g, Integer[] tour) {
        double distancia = (double) g.arista(tour[0], tour[tour.length - 1]).peso();
        for (int i = 1; i < tour.length; ++i) {
            distancia += (double) g.arista(tour[i - 1], tour[i]).peso();
        }
        return distancia;
    }

    
    /**
     * Función que obtiene una permutación del tour
     * @param tour un Array de identficadores de vertices del grafo g.
     * @param a una posición del array del tour.
     * @param b una posición del array del tour.
     * @param c una posición del array del tour.
     * @param d una posición del array del tour.
     * @param e una posición del array del tour.
     * @param f una posición del array del tour.
     * @return El tour resultante del intercambio
     */
    private Integer[] intercambio(Integer[] tour, int a, int b, int c, int d, int e, int f) {
        Integer[] nextPath = new Integer[tour.length];
        for (int i = 0; i < tour.length; ++i) {
            nextPath[i] = tour[i];
        }
        int offset = a + 1;
        nextPath[offset++] = tour[b];
        if (b < c) {
            for (int i = b + 1; i <= c; ++i) {
                nextPath[offset++] = tour[i];
            }
        } else {
            for (int i = b - 1; i >= c; --i) {
                nextPath[offset++] = tour[i];
            }
        }
        nextPath[offset++] = tour[d];
        if (d < e) {
            for (int i = d + 1; i <= e; ++i) {
                nextPath[offset++] = tour[i];
            }
        } else {
            for (int i = d - 1; i >= e; --i) {
                nextPath[offset++] = tour[i];
            }
        }
        nextPath[offset++] = tour[f];
        return nextPath;
    }

}
