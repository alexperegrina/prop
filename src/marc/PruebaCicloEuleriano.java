package marc;

import Dominio.Grafo;
import Dominio.Hierholzer;
import Dominio.Arista;
import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Marc
 */
public class PruebaCicloEuleriano {
    public static void main(String[] args) 
    {
        IOGrafo io = new IOGrafo();
        
        String path = new File("").getAbsolutePath();
        path = path.concat("\\entradas\\entradaM.txt");

        Grafo<Integer,Double> G = new Grafo<>();
        io.leerGrafo(G, path);
        System.out.print("###GRAFO ORIGINAL###\n");
        io.imprimirGrafo(G);
        
        Hierholzer Hz = new Hierholzer();
        Grafo GE = new Grafo(G);
        ArrayList<Arista> retC = Hz.obtenerCicloEuleriano(GE);
        
        System.out.print("\n###Ciclo Euleriano###\n");
        io.imprimirCiclo(retC);   
    }
}
