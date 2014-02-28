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
public class DriverBranchAndBound {
    
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
    
    public static void printCamino(ArrayList<Integer> camino) {
        System.out.println("CaminoAristas ");
        for(int i = 0; i < camino.size(); ++i) {
            System.out.print(" "+camino.get(i));
        }
        System.out.println(" ");
    }
    
    public static void menu() {
        System.out.println("Escoja una opción:");   
        System.out.println("\t 1) (G)Grafo(int n).");
        System.out.println("\t 2) (G)ponerArista(Arista arista).");
        System.out.println("\t 3) (G)Mostrar Grafo.");
        System.out.println("\t 4) ArrayList<Integer> BranchAndBound(Grafo G).");
        System.out.println("\t 5) void indicarTiempoMaximo(Long timeFi).");
        System.out.println("\t 6) Long consultarTiempoMaximo().");
        System.out.println("\t 7) void ponerMaximoTiempo().");
        System.out.println("\t 0) Salir.");
    }
    
    public static void main(String[] args) {
        System.out.println("Driver Relacion");
        boolean salir = false;
        Grafo G = null;
        BranchAndBound bab = null;
        ArrayList<Integer> camino;
        bab = new BranchAndBound();
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
                        System.out.println("nvertizes: "+G.numVertices());
                        System.out.println("naristas: "+G.numAristas());
                        matrizAdyacencias(G);
                        break;
                    case "4":
                        try {
                            camino = bab.obtenerPrimeraSolucion(G);
                            printCamino(camino);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "5":
                        try {
                            bab.indicarTiempoMaximo(Long.parseLong(line[1]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "6":
                        System.out.println(bab.consultarTiempoMaximo());
                        break;
                    case "7":
                        bab.ponerMaximoTiempo();
                        break;
                    case "0":
                        salir = Boolean.TRUE;
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
