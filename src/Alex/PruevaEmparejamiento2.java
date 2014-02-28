/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Alex;

import Dominio.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class PruevaEmparejamiento2 {
    
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
    
    private static void menu() {
        System.out.println("Escoja una opción:");      
        System.out.println("\t 1) (G)Grafo(int n).");
        System.out.println("\t 2) (G)ponerArista(Arista arista).");
        System.out.println("\t 3) (G)Mostrar Grafo.");
        System.out.println("\t 4) Kruskal Obtener arbol recubridor mínimo.");
        System.out.println("\t 5) (M)Mostrar Grafo.");
        System.out.println("\t 6) MultiGrafo apareamientoPerfectoMinimo(Grafo G, Grafo M).");
        System.out.println("\t 7) (O)Mostrar MultiGrafo.");
        System.out.println("\t 0) Salir.");
    }
    
    public static void main(String[] args) throws IOException { 
        IOMultiGrafo ioM = new IOMultiGrafo();
        boolean salir = false;
        ApareamientoPerfectoMinimo emparejamiento = new ApareamientoPerfectoMinimo();
        Kruskal K = new Kruskal();
        Grafo G = null,T = null;
        MultiGrafo O = null;
        ArrayList<Arista> aristas;
        
        String linea;
        String[] line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!salir) {
            menu();
                linea = reader.readLine();
                line = linea.split(" ");
                switch (line[0]) {
                    case "1":
                        try {
                            G = new Grafo(Integer.parseInt(line[1]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "2":
                        try {
                            Arista a = new Arista(Integer.parseInt(line[1]),Integer.parseInt(line[2]),Double.parseDouble(line[3]));
                            G.ponerArista(a);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "3":
                        matrizAdyacencias(G);
                        break;
                    case "4":
                        T = K.obtenerARM(G);
                        break;
                    case "5":
                        matrizAdyacencias(T);
                        break;
                    case "6":
                        O = emparejamiento.apareamientoPerfectoMinimo(G, T);
                        break;
                    case "7":
                        ioM.imprimirGrafo(O);
                        break;
                    case "0":
                        
                        break;
                }
            
        }
    }
}
