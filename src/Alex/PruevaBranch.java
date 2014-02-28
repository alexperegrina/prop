/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Alex;

import Dominio.*;
import david.IOGrafo;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Alex Peregrina Cabrera
 */
public class PruevaBranch {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        IOGrafo io = new IOGrafo();
        IOMultiGrafo ior = new IOMultiGrafo();
        
        String path = new File("").getAbsolutePath();
        path = path.concat("\\classesSeparadas\\BranchAndBound\\inPruevaBranch.txt");

        Grafo<Integer,Double> G = new Grafo<>();

        io.leerGrafo(G, reader);
        //ior.leerGrafoPath(G, path);
        System.out.print("###GRAFO ORIGINAL###\n");
        io.imprimirGrafo(G);
   
        BranchAndBound branch = new BranchAndBound();
        ArrayList<Integer> camino = branch.BranchAndBound(G);
        System.out.println("CaminoAristas ");
        for(int i = 0; i < camino.size(); ++i) {
            System.out.print(" "+camino.get(i));
        }
        System.out.println(" ");
    }
}
