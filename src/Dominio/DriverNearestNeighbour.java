package Dominio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Rosas
 */
public class DriverNearestNeighbour {

    public static void main(String[] args) {
        NearestNeighbour NN = null;
        Grafo G = null;
        System.out.println("Driver NearestNeighbour");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String linea;
        boolean salir = false;
        linea = "Primero";
        while (linea != null && !salir) {
            try {
                System.out.println("Escoja una opción:");
                System.out.println("\t 1) obtenerPrimeraSolucion(Grafo G)");
                System.out.println("\t\t input: 1");
                System.out.println("\t 2) G = new Grafo(Integer n)");
                System.out.println("\t\t input: 2 <Integer>");
                System.out.println("\t 3) G.ponerArista(new Arista(int idOrigen, int idDestino, Double peso))");
                System.out.println("\t\t input: 3 <Integer> <Integer> <Double>");
                System.out.println("\t 0) Salir");

                linea = buffer.readLine();
                String[] line = linea.split(" ");

                switch (line[0]) {
                    case "1":
                        try {
                            NN = new NearestNeighbour();
                            System.out.println("Resultado: " + NN.obtenerPrimeraSolucion(G).toString());
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                    case "2":
                        try {
                            int n = Integer.parseInt(line[1]);
                            G = new Grafo(n);
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "3":
                        try {
                            G.ponerArista(new Arista(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Double.parseDouble(line[3])));
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "0":
                        salir = true;
                        break;
                    default:
                        System.out.println(linea);
                        break;
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }
}
