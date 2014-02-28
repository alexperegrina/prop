/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author David Gracia Celemendi
 */
public class DriverVertice {

    public static void main(String[] args) throws IOException {
        String nombreClase = "Vertice";
        System.out.println("Driver " + nombreClase + ": ejemplo con Integer como tipo parametrizado");

        Vertice<Integer> V = new Vertice<Integer>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            boolean salir = false;
            while (!salir) {
                System.out.println("Escoge una opción:");
                System.out.println("\t 1) Vertice()");
                System.out.println("\t 2) Vertice(T t)");
                System.out.println("\t 3) Vertice(Vertice<T> original)");
                System.out.println("\t 4) Integer info()");
                System.out.println("\t 5) void modificarInfo(T nuevaInfo)");
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
                            V = new Vertice<Integer>();
                            break;
                        case "2":
                            V = new Vertice<Integer>(Integer.parseInt(palabras[1]));
                            break;
                        case "3":
                            Vertice<Integer> W = new Vertice<Integer>(Integer.parseInt(palabras[1]));
                            V = new Vertice<Integer>(W);
                            break;
                        case "4":
                            System.out.println("Información del vértice actual: " + V.info() + ".");
                            break;
                        case "5":
                            V.modificarInfo(Integer.parseInt(palabras[1]));
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
