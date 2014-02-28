/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Alex Peregrina Cabrera
 */
public class DriverApareamientoPerfectoMinimo {
    
    private static ArrayList<ArrayList<Arista<Double>>> matrizAdyacencias(Grafo g) {
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
    
    public static void mostrarAristas(ArrayList<Arista> aristas) {
        for(int i = 0; i < aristas.size(); ++i) {
            System.out.print("Arista" + i +"\n");
            System.out.print("origen:" + aristas.get(i).origen() );
            System.out.print("destino:" + aristas.get(i).destino() );
            System.out.print("peso:" + aristas.get(i).peso() +"\n");
            System.out.print(" --- ");
        }
    }
    
    public static void menu() {
        System.out.println("Escoja una opción:");                     
        System.out.println("\t 1) Emparejamiento().");
        System.out.println("\t 2) Emparejamiento(Grafo<Integer, Double> G).");
        System.out.println("\t 3) ArrayList<Arista> obtenerParejasMaximas().");
        System.out.println("\t 4) ArrayList<Arista> obtenerParejasMinimas().");
        System.out.println("\t 5) MultiGrafo apareamientoPerfectoMinimo(Grafo G, Grafo M).");
        System.out.println("\t 6) (G)Grafo(int n).");
        System.out.println("\t 7) (G)ponerArista(Arista arista).");
        System.out.println("\t 8) (G)Mostrar Grafo.");
        System.out.println("\t 9) (T)Grafo(int n).");
        System.out.println("\t 10) (T)ponerArista(Arista arista).");
        System.out.println("\t 11) (T)Mostrar Grafo.");
        System.out.println("\t 12) (O)Mostrar Multigrafo.");
        System.out.println("\t 0) Salir.");
    }
    
    public static void main(String[] args) { 
        System.out.println("Driver Relacion");
        IOMultiGrafo ioM = new IOMultiGrafo();
        boolean salir = false;
        ApareamientoPerfectoMinimo emparejamiento = null;
        Grafo G = null,T = null;
        MultiGrafo O = null;
        ArrayList<Arista> aristas;
        
        String linea;
        String[] line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!salir) {
            try {
                menu();
                linea = reader.readLine();
                line = linea.split(" ");
                switch (line[0]) {
                    case "1":
                        emparejamiento = new ApareamientoPerfectoMinimo();
                        break;
                    case "2":
                        emparejamiento = new ApareamientoPerfectoMinimo(G);
                        break;
                    case "3":
                        try {
                            aristas = emparejamiento.obtenerParejasMaximas();
                            mostrarAristas(aristas);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }  
                        break;
                    case "4":
                        try {
                            aristas = emparejamiento.obtenerParejasMinimas();
                            mostrarAristas(aristas);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }                    
                        break;
                    case "5":
                        try {
                            O = emparejamiento.apareamientoPerfectoMinimo(G, T);
                            ioM.imprimirGrafo(O);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "6":
                        try {
                            G = new Grafo(Integer.parseInt(line[1]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "7":
                        try {
                            Arista a = new Arista(Integer.parseInt(line[1]),Integer.parseInt(line[2]),Double.parseDouble(line[3]));
                            G.ponerArista(a);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "8":
                        matrizAdyacencias(G);
                        break;
                    case "9":
                        try {
                            T = new Grafo(Integer.parseInt(line[1]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "10":
                        try {
                            Arista a = new Arista(Integer.parseInt(line[1]),Integer.parseInt(line[2]),Double.parseDouble(line[3]));
                            T.ponerArista(a);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "11":
                        matrizAdyacencias(T);
                        break;
                    case "12":
                        ioM.imprimirGrafo(O);
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
    }   
}
