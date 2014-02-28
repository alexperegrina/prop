import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author marc.vila.gomez
 */
public class DriverChristophides {

    public static void main(String[] args) {
        String nombreClase = "Christophides";
        System.out.println("Driver " + nombreClase);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            Christofides Cris = new Christofides();

            Grafo<Integer, Double> G = new Grafo();
            boolean salir = false;
            while (!salir) {
                System.out.println("\nSelecciona una opcion: ");
                System.out.println("\t 1) Realizar Christophides.");
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
                            if (n == 1 && m == 1) throw new IllegalArgumentException("Error: no puede haber un solo vertice con una arista");
                            for (int i = 0; i < m; i++) {
                                G.ponerArista(new Arista(Integer.parseInt(palabras[3 + 3 * i]), Integer.parseInt(palabras[4 + 3 * i]), Double.parseDouble(palabras[5 + 3 * i])));
                            }
                            System.out.print("Impresión del Grafo original");
                            matrizAdyacencias(G);
                            ArrayList<Integer> retC = Cris.obtenerPrimeraSolucion(G);
                            System.out.println("Christophides finalizado");
                            imprimirChristo(retC);
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

    private static void imprimirChristo(ArrayList<Integer> retC) {
        System.out.print("IMPRESIÓN DE Christophides: ");
        for (int i = 0; i < retC.size(); ++i) {
            if (i < retC.size()-1) System.out.print(retC.get(i) + " -> ");
            else System.out.print(retC.get(i));
        }
    }
}
