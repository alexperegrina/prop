/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Alex;

import Dominio.MultiGrafo;
import Dominio.Arista;
import Dominio.ApareamientoPerfectoMinimo;
import Dominio.Grafo;
import Dominio.Kruskal;
import david.IOGrafo;
import java.io.File;

/**
 *
 * @author Alex Peregrina Cabrera
 */
public class PruevaGrafoGeneral {
    
     public static void main(String[] args) {
        IOMultiGrafo ioG = new IOMultiGrafo();
        IOGrafo io = new IOGrafo();
        
        String path = new File("").getAbsolutePath();
        path = path.concat("\\entradas\\entrada001.txt");
        
        Grafo<Integer,Double> R = new Grafo<>();
        io.leerGrafo(R, path);
        System.out.print("###GRAFO ORIGINAL###\n");
        io.imprimirGrafo(R);
        
        MultiGrafo<Integer,Double> G = new MultiGrafo<>(R);
        System.out.print("###GRAFO General###\n");
        ioG.imprimirGrafo(G);
        G.anadirArista(new Arista<>(0, 1, 1.2));
        ioG.imprimirGrafo(G);
        /*MultiGrafo<Integer,Double> G = new MultiGrafo<>();
        ioG.leerGrafo(G,path);
        ioG.imprimirGrafo(G);
        G.anadirArista(new Arista<>(0, 1, 1.2));
        ioG.imprimirGrafo(G);*/
        /*Grafo<Integer,Double> G = new Grafo<>();

        
       
        Kruskal K = new Kruskal();
        Grafo<Integer,Double> T = K.obtenerARM(G);
        System.out.print("\n###ARBOL RECUBRIDOR MINIMO###\n");
        io.imprimirGrafo(T);  
        
        
        ApareamientoPerfectoMinimo e = new ApareamientoPerfectoMinimo();
        e.apareamientoPerfectoMinimo(G, T);
        System.out.print("\nfin\n");*/

    }
    
}
