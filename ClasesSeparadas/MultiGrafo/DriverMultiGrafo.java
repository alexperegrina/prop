/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Alex Peregrina Cabrera
 */
public class DriverMultiGrafo {
    
    public static void mostrarAristas(ArrayList<Arista> aristas) {
        for(int i = 0; i < aristas.size(); ++i) {
            System.out.print("Arista" + i +"\n");
            System.out.print(" origen:" + aristas.get(i).origen() );
            System.out.print(" destino:" + aristas.get(i).destino() );
            System.out.print(" peso:" + aristas.get(i).peso() +"\n");
            System.out.print(" --- ");
        }
    }
    public static void mostrarArista(Arista arista) {
        System.out.print("Arista\n");
        System.out.print("origen:" + arista.origen() );
        System.out.print(" destino:" + arista.destino() );
        System.out.print(" peso:" + arista.peso() +"\n");
        System.out.print(" --- ");
    }
    
    private static void menu() {
        System.out.println("Escoja una opción:");                     
        System.out.println("\t 1) MultiGrafo().");
        System.out.println("\t 2) MultiGrafo(Vertice<T> v).");
        System.out.println("\t 3) MultiGrafo(int n).");
        System.out.println("\t 4) MultiGrafo(MultiGrafo<T,N> original).");
        System.out.println("\t 5) MultiGrafo(Grafo<T,N> original).");
        System.out.println("\t 6) ArrayList<ArrayList<ArrayList<Arista<N>>>> matrizAdyacencias().");
        System.out.println("\t 7) int numVertices().");
        System.out.println("\t 8) int numAristas().");
        System.out.println("\t 9) boolean existeIdVertice(int id).");
        System.out.println("\t 10) boolean existeVertice(Vertice<T> v).");
        System.out.println("\t 11) int idVertice(Vertice<T> v).");
        System.out.println("\t 12) void anadirVertice(Vertice<T> v).");
        System.out.println("\t 13) void modificarVertice(int id, Vertice<T> v).");
        System.out.println("\t 14) Vertice<T> vertice(int id).");
        System.out.println("\t 15) boolean adyacentes(int idOrigen, int idDestino).");
        System.out.println("\t 16) boolean adyacentes(Vertice<T> origen, Vertice<T> destino).");
        System.out.println("\t 17) ArrayList<Arista<N>> aristas(int idOrigen, int idDestino).");
        System.out.println("\t 18) ArrayList<Arista<N>> aristas(Vertice<T> origen, Vertice<T> destino).");
        System.out.println("\t 19) boolean existeIdArista(int idOrigen, int idDestino, int id).");
        
        System.out.println("\t 20) boolean existeArista(int idOrigen, int idDestino, Arista<N> arista).");
        System.out.println("\t 21) boolean existeArista(Vertice<T> origen, Vertice<T> destino, Arista<N> arista).");
        System.out.println("\t 22) int numAristasEntreVertices(int idOrigen, int idDestino).");
        System.out.println("\t 23) int numAristasEntreVertices(Vertice<T> origen, Vertice<T> destino).");
        System.out.println("\t 24) Arista<N> arista(int idOrigen, int idDestino, int id).");
        System.out.println("\t 25) Arista<N> arista(Vertice<T> origen, Vertice<T> destino, int id).");
        System.out.println("\t 26) void anadirArista(Arista<N> arista).");
        System.out.println("\t 27) void modificarArista(Arista<N> arista, int id).");
        System.out.println("\t 28) void eliminarArista(int idOrigen, int idDestino, int id).");
        System.out.println("\t 0) Salir.");
    }
    
    public static void main(String[] args) { 
        System.out.println("Driver Relacion");
        IOMultiGrafo ioM = new IOMultiGrafo();
        IOGrafo ioG = new IOGrafo();
        boolean salir = false;
        
        Grafo G = null;
        MultiGrafo M,O = null;
        ArrayList<Arista> aristas;
        Arista arista = null;
        Vertice vertice = null;
        
        String linea;
        String[] line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (!salir) {
            try {
                menu();
                linea = reader.readLine();
                line = linea.split(" ");
                switch (line[0]) {
                    case "1":
                        M = new MultiGrafo();
                        break;
                    case "2":
                        try {
                            vertice = new Vertice(line[1]);
                            M = new MultiGrafo(vertice);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                            
                        break;
                    case "3":
                        try {
                            M = new MultiGrafo(Integer.parseInt(line[1]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "4":
                        try {
                            //ioM.leerMultiGrafoManual(O);
                            //M = new MultiGrafo(O);
                            M = new MultiGrafo(new MultiGrafo(Integer.parseInt(line[1])));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "5":
                        try {
                            //ioG.leerGrafo(G, reader);
                            //M = new MultiGrafo(G);
                            M = new MultiGrafo(new Grafo(Integer.parseInt(line[1])));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "6":
                        try {
                            ioM.imprimirGrafo(O);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "7":
                        System.out.println(O.numVertices());
                        break;
                    case "8":
                        System.out.println(O.numAristas());
                        break;
                    case "9":
                        try {
                            System.out.println(O.existeIdVertice(Integer.parseInt(line[1])));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "10":
                        try {
                            System.out.println(O.existeVertice(new Vertice(Integer.parseInt(line[1]))));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "11":
                        try {
                            System.out.println(O.idVertice(new Vertice(Integer.parseInt(line[1]))));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "12":
                        try {
                            O.anadirVertice(new Vertice(Integer.parseInt(line[1])));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "13":
                        try {
                            O.modificarVertice(Integer.parseInt(line[1]), 
                                    new Vertice(Integer.parseInt(line[2])));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "14":
                        try {
                            vertice = O.vertice(Integer.parseInt(line[1]));
                            System.out.println("Informacion: " + vertice.info());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "15":
                        try {
                            System.out.println(O.adyacentes(Integer.parseInt(line[1]), 
                                    Integer.parseInt(line[2])));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "16":
                        try {
                            System.out.println(O.adyacentes(new Vertice(Integer.parseInt(line[1])),
                                    new Vertice(Integer.parseInt(line[2]))));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "18":
                        try {
                            aristas = O.aristas(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                            mostrarAristas(aristas);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "19":
                        try {
                            aristas = O.aristas(new Vertice(Integer.parseInt(line[1])),
                                    new Vertice(Integer.parseInt(line[2])));
                            mostrarAristas(aristas);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "20":
                        try {
                            System.out.println(O.existeArista(Integer.parseInt(line[1]), Integer.parseInt(line[2]),
                                    new Arista(Integer.parseInt(line[1]),Integer.parseInt(line[2]), Integer.parseInt(line[3]))));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "21":
                        try {
                            System.out.println(O.existeArista(new Vertice(Integer.parseInt(line[1])),
                                    new Vertice(Integer.parseInt(line[2])),
                                    new Arista(Integer.parseInt(line[1]),
                                    Integer.parseInt(line[2]), Integer.parseInt(line[3]))));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "22":
                        try {
                            System.out.println(O.numAristasEntreVertices(
                                    Integer.parseInt(line[1]), Integer.parseInt(line[2])));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "23":
                        try {
                            System.out.println(O.numAristasEntreVertices(new Vertice(Integer.parseInt(line[1])),
                                    new Vertice(Integer.parseInt(line[2]))));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "24":
                        try {
                            arista = O.arista(Integer.parseInt(line[1]),Integer.parseInt(line[2]), Integer.parseInt(line[3]));
                            mostrarArista(arista);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "25":
                        try {
                            arista = O.arista(new Vertice(Integer.parseInt(line[1])),
                                    new Vertice(Integer.parseInt(line[2])), Integer.parseInt(line[3]));
                            mostrarArista(arista);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "26":
                        try {
                            O.anadirArista(new Arista(Integer.parseInt(line[1]),
                                    Integer.parseInt(line[2]), Integer.parseInt(line[3])));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "27":
                        try {
                            O.modificarArista(new Arista(Integer.parseInt(line[1]),
                                    Integer.parseInt(line[2]), Integer.parseInt(line[3])), Integer.parseInt(line[4]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "28":
                        try {
                            O.eliminarArista(Integer.parseInt(line[1]),Integer.parseInt(line[2]), Integer.parseInt(line[3]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "0":
                        salir = Boolean.TRUE;
                        break;
                    default:
                        System.out.println("La opción elegida no existe!");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
    }
}
