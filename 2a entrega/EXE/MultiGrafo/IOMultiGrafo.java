
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alex
 */
public class IOMultiGrafo {
    
    public void leerMultiGrafoManual(MultiGrafo<Integer, Double> G) {
        /*File archivo = null;
        FileReader fr = null;*/
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String letras;
        int numeros;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            /*archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);*/

            System.out.print("Lectura de grafo\n");
            System.out.print("numero de vertices: \n");
            letras = reader.readLine();
           
            int n = Integer.parseInt(letras);

            for (int i = 0; i < n; i++) {
                Vertice<Integer> v = new Vertice<>();
                if (i == 0) {
                    G.modificarVertice(i, v);
                } else {
                    G.anadirVertice(v);
                }
            }
            System.out.print("Vértices leidos\n");
            System.out.print("Numero aristas: \n");
            letras = reader.readLine();
           
            n = Integer.parseInt(letras);
            
            System.out.print("aristas: \n");
            for (int i = 0; i < n; i++) {
                int or = Integer.parseInt(reader.readLine());
                int des = Integer.parseInt(reader.readLine());
                Double peso = Double.parseDouble(reader.readLine());
                Arista<Double> a = new Arista<>(or, des, peso);
                G.anadirArista(a);
            }
            System.out.print("Aristas leidas\n");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta 
            // una excepcion.
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void leerMultiGrafo(MultiGrafo<Integer, Double> G, String path) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(path);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            System.out.print("Lectura de grafo\n");
            // Lectura del fichero
            String linea = br.readLine();
            String e1[] = linea.split(" ");
            int n = Integer.parseInt(e1[0]);

            for (int i = 0; i < n; i++) {
                Vertice<Integer> v = new Vertice<>();
                if (i == 0) {
                    G.modificarVertice(i, v);
                } else {
                    G.anadirVertice(v);
                }
            }
            System.out.print("Vértices leidos\n");

            while ((linea = br.readLine()) != null) {
                String e2[] = linea.split(" ");
                Arista<Double> a = new Arista<>(Integer.parseInt(e2[0]), Integer.parseInt(e2[1]), Double.parseDouble(e2[2]));
                G.anadirArista(a);
            }
            System.out.print("Aristas leidas\n");
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

    public void imprimirGrafo(MultiGrafo<Integer, Double> G) {
        System.out.print("IMPRESIÓN DE GRAFO:\nVértices: ");
        for (int i = 0; i < G.numVertices(); i++) {
            System.out.print(i + " ");
        }
        System.out.println("\nMatriz de adyacencias (i,j,peso): ");
        System.out.print("\n");
        for (int i = 0; i < G.numVertices(); i++) {
            for (int j = 0; j < G.numVertices(); j++) {
                if (i!=j && G.adyacentes(i, j)) {
                    ArrayList<Arista<Double>> l = G.aristas(i, j);
                    for(int k = 0; k<l.size(); k++) {
                        Double peso = (Double)G.arista(i, j,k).peso();
                        System.out.print("(" + i + "," + j + "," + peso + ")");
                    }
                    if(l.size() == 0) System.out.print("  -----  ");
                    
                } else {
                    System.out.print("  -----  ");
                }
                System.out.print("  ");
            }
            System.out.print("\n");
        }
    }
}
