package Alex;


import Dominio.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alex Peregrina Cabrera
 */
public class TestGrafoExtens {
    
    private static void leerGrafoExtens(GrafoExtens G, String path) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea = br.readLine();
            String e1[] = linea.split(" ");
            int n = Integer.parseInt(e1[0]);

            for (int i = 0; i < n; i++) {
                if (!G.existeVertice(i)) {
                    G.anadirVertice(i);
                }
            }
            System.out.print("Vertices leidos\n");

            while ((linea = br.readLine()) != null) {
                String e2[] = linea.split(" ");
                Integer or = Integer.parseInt(e2[0]);
                Integer dest = Integer.parseInt(e2[1]);
                Double peso = Double.parseDouble(e2[2]);

                G.anadirArista(or,dest,peso);
                G.anadirArista(dest,or,peso);
                System.out.print("Arista leida\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    
    
    
    private static void imprimirGrafo(GrafoExtens G) {
        System.out.print("IMPRESIÓN DE GRAFOAlias:\nVértices: ");
        System.out.print("size(): " + G.matrizAdyacenciaAlias().size() + "\n");
        Iterator it = G.matrizAdyacenciaAlias().keySet().iterator();
        while(it.hasNext()) System.out.print(" " + it.next() + " ");
        
        System.out.println("\nAristas(i,j,peso): ");
        System.out.print("\n");
        
        it = G.matrizAdyacenciaAlias().keySet().iterator();
        while(it.hasNext()) {
            Integer key = (Integer)it.next();
            for(int i = 0; i < G.matrizAdyacenciaAlias().get(key).size(); ++i) {
                ParOrdenado<Boolean, Double> par = G.matrizAdyacenciaAlias().get(key).get(i);
                if (key != i && par.primero()) {
                    Double peso = par.segundo();
                    System.out.print("(" + key + "," + i + "," + peso + ")");
                } else {
                    System.out.print("  -----  ");
                }
                System.out.print("  ");
            }
            System.out.print("\n");
        }
        
        System.out.print("IMPRESIÓN DE GRAFO:\nVértices: ");
        for (int i = 0; i < G.matrizAdyacencia().size(); i++) {
            System.out.print(i + " ");
        }
        System.out.println("\nAristas(i,j,peso): ");
        System.out.print("\n");
        for (int i = 0; i < G.matrizAdyacencia().size(); i++) {
            for (int j = 0; j < G.matrizAdyacencia().get(i).size(); j++) {
                ParOrdenado<Boolean, Double> par = G.matrizAdyacencia().get(i).get(j);
                if (i != j && par.primero()) {
                    Double peso = par.segundo();
                    System.out.print("(" + i + "," + j + "," + peso + ")");
                } else {
                    System.out.print("  -----  ");
                }
                System.out.print("  ");
            }
            System.out.print("\n");
        }
    }
    
    public static void main(String[] args) {
        String path = "C:\\Users\\Alex\\workspace\\Prop\\entradas\\entradaEmp.txt";
        GrafoExtens G = new GrafoExtens();
        leerGrafoExtens(G, path);
        G.pasMatAAlias();
        imprimirGrafo(G);
        ApareamientoPerfectoMinimo emp = new ApareamientoPerfectoMinimo();
        emp.añadirMatrizAdjacencias(G);
        ArrayList<Arista> ret = emp.obtenerParejas(Boolean.FALSE);
        
        System.out.print("\n");
        System.out.print("Print Arista nuevas\n");
        for(int i = 0; i < ret.size(); ++i) {
            System.out.print("Arista" + i +"\n");
            System.out.print("origen:" + ret.get(i).origen() );
            System.out.print("destino:" + ret.get(i).destino() );
            System.out.print("peso:" + ret.get(i).peso() +"\n");
        }
        
    }
    
}
