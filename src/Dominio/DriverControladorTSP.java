/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author David Gracia Celemendi
 */
public class DriverControladorTSP {

    public static void main(String[] args) {
        String nombreClase = "ControladorTSP";
        System.out.println("Driver " + nombreClase);
        ControladorTSP ctrlTSP = new ControladorTSP();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            boolean salir = false;
            while (!salir) {

                System.out.println("Escoge una opción:");
                System.out.println("\t 1) ArrayList<Integer> obtenerTour(Grafo G, String op1, String op2)");
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
                            String primSol = palabras[1];
                            String refSol = palabras[2];
                            int n = Integer.parseInt(palabras[3]);
                            Grafo<Integer, Double> G = new Grafo<Integer, Double>(n);
                            int m = Integer.parseInt(palabras[4]);
                            for (int i = 0; i < m; i++) {
                                Arista<Double> a = new Arista<Double>(Integer.parseInt(palabras[5 + 3 * i]), Integer.parseInt(palabras[6 + 3 * i]), Double.parseDouble(palabras[7 + 3 * i]));
                                G.ponerArista(a);
                            }
                            ArrayList<Integer> tour = ctrlTSP.obtenerTour(G, primSol, refSol);

                            System.out.println("Tour obtenido (idVertice peso idVertice ...):");
                            Double pesoTotal = 0.0;
                            Integer a;
                            Integer b;
                            for (int i = 0; i < tour.size(); i++) {
                                a = tour.get(i);
                                Double peso;
                                if (i < tour.size() - 1) {
                                    b = tour.get(i + 1);
                                    peso = (Double) G.arista(a, b).peso();
                                    pesoTotal += peso;
                                    System.out.print("v" + a + " " + peso + " ");
                                } else {
                                    System.out.print("v" + a + " ");
                                }
                            }
                            if (tour.size() > 1) {
                                System.out.print(G.arista(tour.get(tour.size() - 1), tour.get(0)).peso() + " v" + tour.get(0));
                            }
                            System.out.print("\n");
                            System.out.println("Peso total: " + pesoTotal);
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
