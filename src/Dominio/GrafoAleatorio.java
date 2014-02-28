/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author Alejandro
 */
public class GrafoAleatorio {

    public static void main(String[] args) {
        int N = 20;
        System.out.println("1");
        System.out.println("2 "+N);
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                double random = Math.random() * 10; // Max 10 relacion
                System.out.println("3 " + i + " " + j + " " + random);
            }
        }
        System.out.print("6 "+N);
        for (int j = 0; j < N; ++j) {
                System.out.print(" "+j);
        }

    }
}
