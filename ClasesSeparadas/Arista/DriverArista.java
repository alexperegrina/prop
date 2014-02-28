/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author David Gracia Celemendi
 */
public class DriverArista {

    public static void main(String[] args) {
        String nombreClase = "Arista";
        System.out.println("Driver " + nombreClase + ": ejemplo con Double como tipo parametrizado");

        Arista<Double> A = new Arista<Double>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            boolean salir = false;
            while (!salir) {
                System.out.println("Escoge una opción:");
                System.out.println("\t 1) Arista()");
                System.out.println("\t 2) Arista(int idOrigen,int idDestino,T peso)");
                System.out.println("\t 3) Arista(Arista<T> original)");
                System.out.println("\t 4) int origen()");
                System.out.println("\t 5) void modificarOrigen(int idOrigen)");
                System.out.println("\t 6) int destino()");
                System.out.println("\t 7) void modificarDestino(int idDestino)");
                System.out.println("\t 8) T peso()");
                System.out.println("\t 9) void modificarPeso(N peso)");
                System.out.println("\t 10) int compareTo(Object o)");
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
                            A = new Arista<Double>();
                            break;
                        case "2":
                            Integer k = Integer.parseInt(palabras[1]);
                            Integer p = Integer.parseInt(palabras[2]);
                            Double d = Double.parseDouble(palabras[3]);
                            A = new Arista<Double>(k, p, d);
                            break;
                        case "3":
                            Arista<Double> B = new Arista<Double>(Integer.parseInt(palabras[1]), Integer.parseInt(palabras[2]), Double.parseDouble(palabras[3]));
                            A = new Arista<Double>(B);
                            break;
                        case "4":
                            System.out.println("Origen de la arista actual: " + A.origen() + ".");
                            break;
                        case "5":
                            A.modificarOrigen(Integer.parseInt(palabras[1]));
                            break;
                        case "6":
                            System.out.println("Destino de la arista actual: " + A.destino() + ".");
                            break;
                        case "7":
                            A.modificarDestino(Integer.parseInt(palabras[1]));
                            break;
                        case "8":
                            System.out.println("Peso del vértice actual: " + A.peso() + ".");
                            break;
                        case "9":
                            A.modificarPeso(Double.parseDouble(palabras[1]));
                            break;
                        case "10":
                            int res = A.compareTo(new Arista(Integer.parseInt(palabras[1]), Integer.parseInt(palabras[2]), Double.parseDouble(palabras[3])));
                            System.out.println("Resultado de la comparación: "+res);
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
