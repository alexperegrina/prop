package david;


import Dominio.Arista;
import Dominio.Grafo;
import Dominio.Vertice;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author David
 */
public class IOGrafo {

    public void leerGrafo(Grafo<Integer, Double> G, BufferedReader br) {
        //File archivo = null;
        //FileReader fr = null;
        //BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            //archivo = new File(path);
            //fr = new FileReader(archivo);
            //br = new BufferedReader(fr);

            System.out.print("Lectura de grafo\n");
            // Lectura del fichero
            String linea = br.readLine();
            String e1[] = linea.split(" ");
            int n = Integer.parseInt(e1[0]);
            int m = Integer.parseInt(e1[1]);
            for (int i = 0; i < n; i++) {
                Vertice<Integer> v = new Vertice<>();
                if (i == 0) {
                    G.modificarVertice(i, v);
                } else {
                    G.anadirVertice(v);
                }
            }
            System.out.print("Vértices leidos\n");

            for(int i=0;i<m;i++){
                linea = br.readLine();
                String e2[] = linea.split(" ");
                Arista<Double> a = new Arista<>(Integer.parseInt(e2[0]), Integer.parseInt(e2[1]), Double.parseDouble(e2[2]));
                G.ponerArista(a);
            }
            System.out.print("Aristas leidas\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
               // if (null != fr) {
               //     fr.close();
                //}
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void imprimirGrafo(Grafo<Integer, Double> G) {
        System.out.print("IMPRESIÓN DE GRAFO:\nVértices: ");
        for (int i = 0; i < G.numVertices(); i++) {
            System.out.print(i + " ");
        }
        System.out.println("\nMatriz de adyacencias (i,j,peso): ");
        System.out.print("\n");
        for (int i = 0; i < G.numVertices(); i++) {
            for (int j = 0; j < G.numVertices(); j++) {
                if (i!=j && G.adyacentes(i, j)) {
                    Double peso = (Double)G.arista(i, j).peso();
                    System.out.print("(" + i + "," + j + "," + peso + ")");
                } else {
                    System.out.print("  -----  ");
                }
                System.out.print("  ");
            }
            System.out.print("\n");
        }
    }

}
