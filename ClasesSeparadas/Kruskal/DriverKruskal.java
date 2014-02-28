/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author David Gracia Celemendi
 */
public class DriverKruskal {

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

    public static void main(String[] args) {
        String nombreClase = "Kruskal";
        System.out.println("Driver " + nombreClase);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Kruskal K = new Kruskal();

            Grafo<Integer, Double> G = new Grafo();
            boolean salir = false;
            while (!salir) {
                System.out.println("Escoge una opción:");
                System.out.println("\t 1) Obtener arbol recubridor mínimo.");
                System.out.println("\t 0) Salir");

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
                            int n = Integer.parseInt(palabras[1]);
                            G = new Grafo(n);
                            int m = Integer.parseInt(palabras[2]);
                            for (int i = 0; i < m; i++) {
                                G.ponerArista(new Arista(Integer.parseInt(palabras[3 + 3 * i]), Integer.parseInt(palabras[4 + 3 * i]), Double.parseDouble(palabras[5 + 3 * i])));
                            }
                            System.out.println("Grafo original");
                            matrizAdyacencias(G);
                            G = K.obtenerARM(G);
                            System.out.println("Arbol recubridor mínimo obtenido.");
                            matrizAdyacencias(G);
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
