import java.util.*;

public class Christofides extends PrimeraSolucion {

    @Override
    public ArrayList<Integer> obtenerPrimeraSolucion(Grafo g) {
        //El grafo g siempre será completo
        //Kruskal
        Kruskal K = new Kruskal();
        Grafo<Integer, Double> gK = K.obtenerARM(g);
        //Matching
        ApareamientoPerfectoMinimo e = new ApareamientoPerfectoMinimo();
        MultiGrafo R = e.apareamientoPerfectoMinimo(g, gK);
        //Tratar multigrafo a grafo
        Grafo G = MGrafoToGrafo(R);
        //Hierholzer
        Hierholzer Hz = new Hierholzer();
        Grafo GE = new Grafo(G);
        ArrayList<Arista> retC = Hz.obtenerCicloEuleriano(GE);
        //Hamilton
        ShortcutPaths SP = new ShortcutPaths();
        ArrayList<Arista> retH = SP.obtenerCicloHamiltoniano(retC, g);
        ArrayList<Integer> retI = new ArrayList<>();
        for (int i = 0; i < retH.size(); i++)
            retI.add(retH.get(i).origen());
        
        return retI;
    }
    private Grafo MGrafoToGrafo(MultiGrafo R)
    {
        int n = R.numVertices();
        Grafo G = new Grafo(n);
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if(R.adyacentes(i, j)){
                    Arista aMG = R.arista(i, j, 0);            
                    Arista nuevaAr = new Arista(i,j,(Double) aMG.peso());
                    G.ponerArista(nuevaAr);
                }
            }
        }
        return G;        
    }
}
