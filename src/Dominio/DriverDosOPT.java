package Dominio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Rosas
 */
public class DriverDosOPT {

    public static void main(String[] args) {
        DosOPT t = null;
        Grafo G = null;
        ArrayList<Integer> tour = null;
        System.out.println("Driver DosOPT");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String linea;
        boolean salir = false;
        linea = "Primero";
        while (linea != null && !salir) {
            try {
                System.out.println("Escoja una opción:");
                System.out.println("\t 1) DosOPT()");
                System.out.println("\t\t input: 1");
                System.out.println("\t 2) mejorarSolucion(Grafo g, ArrayList<Integer> tour)");
                System.out.println("\t\t input: 2");
                System.out.println("\t 3) soluciones()");
                System.out.println("\t\t input: 3");
                System.out.println("\t 4) G = new Grafo(Integer n)");
                System.out.println("\t\t input: 4 <Integer>");
                System.out.println("\t 5) G.ponerArista(new Arista(int idOrigen, int idDestino, Double peso))");
                System.out.println("\t\t input: 5 <Integer> <Integer> <Double>");
                System.out.println("\t 6) Crear Tour: N veces tour.add(Integer i)");
                System.out.println("\t\t input: 6 <Integer: N> <Integer 1> ..  <Integer N>");
                System.out.println("\t 0) Salir");

                linea = buffer.readLine();
                String[] line = linea.split(" ");

                switch (line[0]) {
                    case "1":
                        try {
                            t = new DosOPT();
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "2":
                        try {
                            System.out.println("Tour Inicial: " + tour.toString());
                            System.out.println("Resultado: " + t.mejorarSolucion(G, tour).toString());
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "3":
                        try {
                            System.out.println(t.soluciones());
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "4":
                        try {
                            int n = Integer.parseInt(line[1]);
                            G = new Grafo(n);
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "5":
                        try {
                            G.ponerArista(new Arista(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Double.parseDouble(line[3])));
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "6":
                        try {
                            tour = new ArrayList<Integer>();
                            int num = Integer.parseInt(line[1]);
                            for (int i = 0; i < num; i++) {
                                tour.add(Integer.parseInt(line[2 + i]));
                            }
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
