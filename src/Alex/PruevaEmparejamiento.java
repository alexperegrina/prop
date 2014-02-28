package Alex;


import Dominio.MultiGrafo;
import Dominio.*;
import david.IOGrafo;
import java.io.File;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex Peregrina Cabrera
 */
public class PruevaEmparejamiento {
    
    public static void main(String[] args) {
        IOGrafo io = new IOGrafo();
        IOMultiGrafo ior = new IOMultiGrafo();
        
        String path = new File("").getAbsolutePath();
        path = path.concat("\\entradas\\entrada001.txt");

        Grafo<Integer,Double> G = new Grafo<>();

        io.leerGrafo(G, path);
        System.out.print("###GRAFO ORIGINAL###\n");
        io.imprimirGrafo(G);
       
        Kruskal K = new Kruskal();
        Grafo<Integer,Double> T = K.obtenerARM(G);
        System.out.print("\n###ARBOL RECUBRIDOR MINIMO###\n");
        io.imprimirGrafo(T);  
        
        
        ApareamientoPerfectoMinimo e = new ApareamientoPerfectoMinimo();
        MultiGrafo R = e.apareamientoPerfectoMinimo(G, T);
        System.out.print("\n###MultiGrafo###\n");
        ior.imprimirGrafo(R);
        /*ApareamientoPerfectoMinimo e = new ApareamientoPerfectoMinimo(G);
        ArrayList<Arista> a = e.obtenerParejasMaximas();
        
        
        
        for(int i = 0; i < a.size(); ++i) {
            System.out.print("Arista" + i +"\n");
            System.out.print("origen:" + a.get(i).origen() );
            System.out.print("destino:" + a.get(i).destino() );
            System.out.print("peso:" + a.get(i).peso() +"\n");
        }*/
    }
}
