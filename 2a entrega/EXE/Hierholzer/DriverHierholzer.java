import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Marc Vila Gomez
 */
public class DriverHierholzer {

    private static void menu() {
        System.out.println("Driver de Hierholzer");
        System.out.println("\t 1) obtenerCicloEuleriano(Grafo G)");
        System.out.println("\t \t input: Leer Leeme para introducir el grafo");
        System.out.println("\t 0) Salir");
    }

    public static void main(String[] args) {
        try {
            menu();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            boolean salir = false;
            String linea = "Primero";
            Hierholzer Hz = new Hierholzer();
            Grafo G;
            while (!salir && linea != null) {
                System.out.println("\nSelecciona una opcion: ");
                linea = reader.readLine();
                String[] line = linea.split(" ");
                try {
                    System.out.println("Opción " + line[0] + " seleccionada.");
                    switch (line[0]) {
                        case "1":
                            int n = Integer.parseInt(line[1]);
                            G = new Grafo(n);
                            int m = Integer.parseInt(line[2]);
                            if (n == 1 && m == 1) throw new IllegalArgumentException("Error: no puede haber un solo vertice con una arista");
                            for (int i = 0; i < m; i++) {
                                G.ponerArista(new Arista(Integer.parseInt(line[3 + 3 * i]), Integer.parseInt(line[4 + 3 * i]), Double.parseDouble(line[5 + 3 * i])));
                            }
                            ArrayList<Arista> retC = Hz.obtenerCicloEuleriano(G);
                            System.out.print("Impresión del Grafo original");
                            matrizAdyacencias(G);
                            imprimirCiclo(retC);
                            break;
                        case "0":
                            salir = true;
                            break;
                        default:
                            System.out.println("La opción elegida no existe");
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void matrizAdyacencias(Grafo g) {
        System.out.println("\nMatriz de adyacencias (i,j,peso): \n");
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
    }

    private static void imprimirCiclo(ArrayList<Arista> retC) {
        System.out.println();
        System.out.println("IMPRESIÓN DEl Ciclo Euleriano:");
        for (int i = 0; i < retC.size(); ++i) {
            System.out.println(retC.get(i).origen() + " -> " + retC.get(i).destino());
        }
    }
}
