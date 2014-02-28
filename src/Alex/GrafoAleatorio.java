/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Alex;

import Dominio.*;

/**
 *
 * @author Alejandro
 */
public class GrafoAleatorio {
    
    public static double Redondear(double numero)
    {
        return Math.rint(numero*100)/100;
    }

    public static void main(String[] args) {
        int N = 12;
        int opc = 2;
        System.out.println("1 "+N);
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                double random = Math.random() * 10; // Max 10 relacion
                System.out.println( opc + " " + i + " " + j + " " + Redondear(random));
            }
        }
        /*System.out.println("6 "+N);
        for (int j = 0; j < N; ++j) {
                System.out.print(" "+j);
        }*/

    }
}
