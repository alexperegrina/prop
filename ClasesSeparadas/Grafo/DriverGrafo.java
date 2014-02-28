/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author David Gracia Celemendi
 */
public class DriverGrafo {

    private static ArrayList<ArrayList<Arista<Double>>> matrizAdyacencias(Grafo<Integer, Double> g) {
        System.out.print("IMPRESIÓN DE GRAFO:\nVértices: ");
        for (int i = 0; i < g.numVertices(); i++) {
            System.out.print(i + " ");
        }
        System.out.println("\nMatriz de adyacencias (i,j,peso): ");
        System.out.print("\n");
        for (int i = 0; i < g.numVertices(); i++) {
            for (int j = 0; j < g.numVertices(); j++) {
                if (i != j && g.adyacentes(i, j)) {
                    Double peso = (Double) g.arista(i, j).peso();
                    System.out.print("(" + i + "," + j + "," + peso + ")");
                } else {
                    System.out.print("  -----  ");
                }
                System.out.print("  ");
            }
            System.out.print("\n");
        }

        return g.matrizAdyacencias();
    }

    private static HashMap<Integer, ArrayList<Vertice<Integer>>> listasAdyacencias(Grafo<Integer, Double> g) {
        HashMap<Integer, ArrayList<Vertice<Integer>>> listas = g.listasAdyacencias();
        System.out.print("IMPRESIÓN DE GRAFO:\nVértices: ");
        for (int i = 0; i < listas.size(); i++) {
            System.out.print(i + " ");
        }
        System.out.println("\nListas de adyacencias: ");
        System.out.print("\n");
        for (int i = 0; i < listas.size(); i++) {
            System.out.print(i + " | ");
            ArrayList<Vertice<Integer>> lista = listas.get(i);
            for (int j = 0; j < lista.size(); j++) {                  
                System.out.print(g.idVertice(lista.get(j)) + " ");
            }
            System.out.print("\n");
        }

        return listas;
    }

    private static ArrayList<Arista> aristasIncidentes(Grafo<Integer, Double> g, int id) {
        ArrayList<Arista> aristas = g.aristasIncidentes(id);
        for (Arista a : aristas) {
            System.out.print("(" + a.origen() + "," + a.destino() + "),");
        }
        System.out.print("\n");
        return aristas;
    }

    private static ArrayList<Vertice> verticesAdyacentes(Grafo<Integer, Double> g, Vertice v) {
        return g.verticesAdyacentes(v);
    }

    public static void main(String[] args) throws IOException {
        String nombreClase = "Grafo";
        System.out.println("Driver " + nombreClase + ": ejemplo con <Integer,Double> como tipos parametrizados");

        try {

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Grafo<Integer, Double> G = new Grafo<Integer, Double>();

            boolean salir = false;
            boolean lista = true;
            while (!salir) {
                if (lista) {
                    System.out.println("Escoge una opción:");
                    System.out.println("\t 1) Grafo()");
                    System.out.println("\t 2) Grafo(int n).");
                    System.out.println("\t 3) Grafo(Grafo original)");
                    System.out.println("\t 4) ArrayList<ArrayList<Arista<N>>> matrizAdyacencias()");
                    System.out.println("\t 5) HashMap<Integer,ArrayList<Vertice<T>>> listasAdyacencias()");
                    System.out.println("\t 6) int numVertices()");
                    System.out.println("\t 7) int numAristas()");
                    System.out.println("\t 8) int idVertice(Vertice v)");
                    System.out.println("\t 9) int gradoVertice(int id)");
                    System.out.println("\t 10) ArrayList<Arista> aristasIncidentes(int id)");
                    System.out.println("\t 11) ArrayList<Vertice> verticesAdyacentes(Vertice v)");
                    System.out.println("\t 12) boolean conectaTodosVertices(ArrayList<Arista> aristas)");
                    System.out.println("\t 13) boolean completo()");
                    System.out.println("\t 14) boolean conexo()");
                    System.out.println("\t 15) boolean euleriano()");
                    System.out.println("\t 16) void anadirVertice(Vertice v)");
                    System.out.println("\t 17) void modificarVertice(int id, Vertice v)");
                    System.out.println("\t 18) Vertice vertice(int id)");
                    System.out.println("\t 19) boolean adyacentes(int idOrigen, int idDestino)");
                    System.out.println("\t 20) Arista arista(int idOrigen, int idDestino)");
                    System.out.println("\t 21) void ponerArista(Arista arista)");
                    System.out.println("\t 22) void eliminarArista(int idOrigen, int idDestino)");
                    System.out.println("\t 23) ArrayList<Integer> camino(int idOrigen, int idDestino)");
                    System.out.println("\t 24) Ocultar menú.");
                    System.out.println("\t 25) Mostrar menú.");
                    System.out.println("\t 0) Salir");
                } else {
                    System.out.println("Lista ocultada, introduce 25 para mostrar.");
                }

                String linea;
                String palabras[];
                String opcion;

                linea = br.readLine();
                palabras = linea.split(" ");
                opcion = palabras[0];

                try {
                    System.out.println("Opción " + opcion + " seleccionada.");
                    switch (opcion) {
                        case "1":
                            G = new Grafo<Integer, Double>();
                            break;
                        case "2":
                            G = new Grafo<Integer, Double>(Integer.parseInt(palabras[1]));
                            break;
                        case "3":
                            int n = Integer.parseInt(palabras[1]);
                            Grafo<Integer, Double> H = new Grafo(n);
                            int m = Integer.parseInt(palabras[2]);
                            for (int i = 0; i < m; i++) {
                                H.ponerArista(new Arista(Integer.parseInt(palabras[3 + 3 * i]), Integer.parseInt(palabras[4 + 3 * i]), Double.parseDouble(palabras[5 + 3 * i])));
                            }
                            G = new Grafo<Integer, Double>(H);
                            break;
                        case "4":
                            matrizAdyacencias(G);
                            break;
                        case "5":                          
                            listasAdyacencias(G);
                            break;
                        case "6":
                            System.out.println("Número de vértices: " + G.numVertices() + ".");
                            break;
                        case "7":
                            System.out.println("Número de aristas: " + G.numAristas() + ".");
                            break;
                        case "8":
                            System.out.println("Identificador del vértice en el grafo actual: " + G.idVertice(new Vertice(Integer.parseInt(palabras[1]))) + ".");
                            break;
                        case "9":
                            System.out.println("Grado del vértice en el grafo actual: " + G.gradoVertice(Integer.parseInt(palabras[1])) + ".");
                            break;
                        case "10":
                            aristasIncidentes(G, Integer.parseInt(palabras[1]));
                            break;
                        case "11":
                            verticesAdyacentes(G, new Vertice(Integer.parseInt(palabras[1])));
                            break;
                        case "12":
                            ArrayList<Arista> aristas = new ArrayList<>();
                            for (int i = 0; i < Integer.parseInt(palabras[1]); i++) {
                                aristas.add(new Arista(Integer.parseInt(palabras[2 + 3 * i]), Integer.parseInt(palabras[3 + 3 * i]), Double.parseDouble(palabras[4 + 3 * i])));
                            }
                            boolean b = G.conectaTodosVertices(aristas);
                            if (b) {
                                System.out.println("Las aristas SÍ conectan todos los vértices del grafo.");
                            } else {
                                System.out.println("Las aristas NO conectan todos los vértices del grafo.");
                            }
                            break;
                        case "13":
                            if (G.completo()) {
                                System.out.println("El grafo SÍ es completo.");
                            } else {
                                System.out.println("El grafo NO es completo.");
                            }
                            break;
                        case "14":
                            if (G.conexo()) {
                                System.out.println("El grafo SÍ es conexo.");
                            } else {
                                System.out.println("El grafo NO es conexo.");
                            }
                            break;
                        case "15":
                            if (G.euleriano()) {
                                System.out.println("El grafo SÍ es euleriano.");
                            } else {
                                System.out.println("El grafo NO es euleriano.");
                            }
                            break;
                        case "16":
                            G.anadirVertice(new Vertice(Integer.parseInt(palabras[1])));
                            break;
                        case "17":
                            G.modificarVertice(Integer.parseInt(palabras[1]), new Vertice(Integer.parseInt(palabras[2])));
                            break;
                        case "18":
                            Vertice v = G.vertice(Integer.parseInt(palabras[1]));
                            System.out.println("Información del vértice obtenido: " + v.info());
                            break;
                        case "19":
                            if (G.adyacentes(Integer.parseInt(palabras[1]), Integer.parseInt(palabras[2]))) {
                                System.out.println("SÍ son adyacentes.");
                            } else {
                                System.out.println("NO son adyacentes.");
                            }
                            break;
                        case "20":
                            Arista a = G.arista(Integer.parseInt(palabras[1]), Integer.parseInt(palabras[2]));
                            System.out.println("Arista obtenida: (" + a.origen() + "," + a.destino() + ")");
                            break;
                        case "21":
                            G.ponerArista(new Arista(Integer.parseInt(palabras[1]), Integer.parseInt(palabras[2]), Double.parseDouble(palabras[3])));
                            break;
                        case "22":
                            G.eliminarArista(Integer.parseInt(palabras[1]), Integer.parseInt(palabras[2]));
                            break;
                        case "23":
                            ArrayList<Integer> camino = G.camino(Integer.parseInt(palabras[1]), Integer.parseInt(palabras[2]));
                            if (camino.isEmpty()) {
                                System.out.println("No hay camino posible.");
                            } else {
                                System.out.print("Camino obtenido: ");
                                for (Integer i : camino) {
                                    System.out.print(i + ",");
                                }
                                System.out.print("\n");
                            }
                            break;
                        case "24":
                            lista = false;
                            break;
                        case "25":
                            lista = true;
                            break;
                        case "0":
                            salir = true;
                            break;
                        default:
                            System.out.println("La opción elegida no existe!");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Fin del driver");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
