/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author David Gracia Celemendi
 */
public class DriverParOrdenado {

    public static void main(String[] args) throws IOException {
        String nombreClase = "ParOrdenado";
        System.out.println("Driver " + nombreClase + ": ejemplo con <Integer,Integer> como tipos parametrizado");

        ParOrdenado<Integer,Integer> P = new ParOrdenado<Integer,Integer>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            boolean salir = false;
            while (!salir) {
                System.out.println("Escoge una opción:");
                System.out.println("\t 1) ParOrdenado()");
                System.out.println("\t 2) ParOrdenado(X primero,Y segundo)");
                System.out.println("\t 3) X primero()");
                System.out.println("\t 4) void modificarPrimero(X primero)");
                System.out.println("\t 5) Y segundo()");
                System.out.println("\t 6) void modificarSegundo(Y segundo)");
                System.out.println("\t 7) boolean igualA(ParOrdenado<X,Y> p)");                
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
                            P = new ParOrdenado<Integer,Integer>();
                            break;
                        case "2":
                            P = new ParOrdenado<Integer,Integer>(Integer.parseInt(palabras[1]),Integer.parseInt(palabras[2]));
                            break;
                        case "3":
                            System.out.println("Información del primer elemento: "+P.primero());
                            break;
                        case "4":
                            P.modificarPrimero(Integer.parseInt(palabras[1]));
                            break;
                        case "5":
                            System.out.println("Información del segundo elemento: "+P.segundo());
                            break;
                        case "6":
                            P.modificarSegundo(Integer.parseInt(palabras[1]));
                            break;
                        case "7":
                            if(P.igualA(new ParOrdenado<Integer,Integer>(Integer.parseInt(palabras[1]),Integer.parseInt(palabras[2]))))
                                System.out.println("Sí son iguales");
                            else System.out.println("No son iguales");
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
