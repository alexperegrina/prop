

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ListIterator;
import java.util.Set;
import java.util.Stack;

/**
 *
 * @author Marc Vila Gomez
 */
public class Hierholzer extends CicloEuleriano{
    
    @Override
    public ArrayList<Arista> obtenerCicloEuleriano(Grafo G) throws IllegalArgumentException {
        if(!G.euleriano()) throw new IllegalArgumentException("Error: El grafo no es euleriano");
        Vertice v = G.vertice((int) Math.random()*G.numVertices());
        // Inicializar el ciclo (con vertice inicial aleatorio) y el siguiente para ser Merge
        ArrayList<Arista> ciclo = crearCiclo(G, v);
        ArrayList<Arista> siguiente_ciclo = ciclo;
        // Mientras que el ciclo no contenga todos los vertices de G
        while (!G.conectaTodosVertices(ciclo)) {
            // Encontrar Arista colindante en el ciclo
            Arista vecina = encontrarAristaVecina(G, ciclo);
            int id1 = vecina.origen(); // Averiguar que vertice vecino está en el ciclo
            int id2 = vecina.destino();
            Vertice v1 = new Vertice();

            for (Arista e : ciclo) {
                if ((e.origen() == id1) || (e.destino() == id1)) {
                    v1 = G.vertice(vecina.origen());
                    break;
                } else {
                    if ((e.origen() == id2) || (e.destino() == id2)) {
                        v1 = G.vertice(vecina.destino());
                        break;
                    }
                }
            }

            // Eliminar las aristas y vertices del ciclo para evitar pasar por ellos de nuevo
            if (!(ciclo == null)) {
                for (Arista a : siguiente_ciclo) {
                    G.eliminarArista(a.origen(), a.destino());
                }
            }
            
            // Continuar construyendo el circuito euleriano
            siguiente_ciclo = crearCiclo(G, v1);
            ciclo = mergeTours(G, ciclo, siguiente_ciclo);            
        }
        return ciclo;
    }

    private Arista encontrarAristaVecina(Grafo G, ArrayList<Arista> ciclo) throws IllegalArgumentException{
        // Encontrar un vertice en el ciclo que sea incidente con una arista sin visitar.
        ArrayList<Arista> vecinos;
        for (Arista e : ciclo) {
            Vertice[] arr = new Vertice[2];
            arr[0] = G.vertice(e.origen());
            arr[1] = G.vertice(e.destino());
            for (int i = 0; i < 2; ++i) {
                vecinos = G.aristasIncidentes(G.idVertice(arr[i]));
                if (vecinos != null) {
                    for (Arista vecino : vecinos) {
                        if (!(ciclo.contains(vecino))) {
                            return vecino;
                        }
                    }
                }
            }
        }
        throw new IllegalArgumentException("Fallo al encontrar una Arista que no este en el ciclo");
    }
    
    // Construye un ciclo en el grafo G empezando por v_inicio
    private ArrayList<Arista> crearCiclo(Grafo G, Vertice v_inicio) throws IllegalArgumentException {
        if (G.numVertices() < 2) throw new IllegalArgumentException("El grafo no es valido: No se puede encontrar un ciclo con un solo nodo");
        HashMap<Integer, ArrayList<Vertice>> adjListv = G.listasAdyacencias();
        HashMap<Integer, ArrayList<Arista>> adjList = new HashMap<>();
        for (int i = 0; i < adjListv.size(); ++i)
        {
            ArrayList<Arista> tempa;
            Vertice tempv = G.vertice(i);
            tempa = G.aristasIncidentes(G.idVertice(tempv));
            adjList.put(i, tempa);       
        }
        // Contiene la lista definitiva de Aristas del ciclo
        ArrayList<Arista> llistaArista = new ArrayList<>();

        // Realiza un seguimiento de los Vertices ya visitados
        Set<Integer> vs = new HashSet<>();

        // Contiene las aristas que aún estamos autorizados a atravesar (guarda dos copias de cada arista)
        ArrayList<Arista> es = new ArrayList<>();
        for (ArrayList<Arista> arista : adjList.values()) {
            es.addAll(arista);
        }


        // Hacer DFS hasta que volvamos al vertice inicial con seguimiento del orden visitado
        Stack<Vertice> s = new Stack<>();
        s.push(v_inicio);

        // Marcar V_inicio como visitado
        vs.add(G.idVertice(v_inicio));

        // Mientras que la pila no sea vacia.
        outer:
        while (!s.empty()) {
            Vertice v = s.pop();
            vs.add(G.idVertice(v));  //Marcar v como visitado

            // Si estamos ya en el vertice inicia, retornamos el ciclo
            if (v == v_inicio && llistaArista.size() > 1)
                return llistaArista;
            // para todas las Aristas "e" adyacentes a "v"
            
            for (Arista e : (ArrayList<Arista>) G.aristasIncidentes(G.idVertice(v))) {
                // Si la arista e ya estaba, pasar a la siguiente
                if (!es.contains(e)) continue;
                Vertice[] arr = new Vertice[2];
                arr[0] = G.vertice(e.origen());
                arr[1] = G.vertice(e.destino());
                Vertice w;
                // Poner w al vertice de la arista que no es v
                if (G.idVertice(arr[0]) == G.idVertice(v)) w = arr[1];
                else w = arr[0];

                // Si estamos en el vertice de inicio, retornamos el ciclo
                if (llistaArista.size() > 1 && (arr[0] == v_inicio || arr[1] == v_inicio)) {
                    llistaArista.add(e);
                    return llistaArista;
                }

                // Si vertice w no estaba visitado y no es el v_inicio
                if (!vs.contains(G.idVertice(w))) {//Original era !vs.contains(w)
                    
                    vs.add(G.idVertice(w)); // Marcar w como visitado
                    // Sacar las dos copias de e de la lista de Aristas
                    es.remove(e); es.remove(e);
                    // Push w en el stack y e a la lista de aristas final
                    s.push(w);
                    llistaArista.add(e);
                    continue outer;
                }
            }
        }
        // Si tenemos una sola Arista, la duplicamos
        if (llistaArista.size() < 2) 
            llistaArista.add(llistaArista.get(0));
        return llistaArista;
    }

    // Merges ciclos aNadiendo Aristas de la lista 2 en la lista1 
    private ArrayList<Arista> mergeTours(Grafo G, ArrayList<Arista> list1, ArrayList<Arista> list2) throws IllegalArgumentException {
        ArrayList<Arista> result = list1;

        // Cojer el vertice del ciclo que estamos aNadiendo, que tambien esta en el primer ciclo
        Vertice[] arr = new Vertice[2];
        arr[0] = G.vertice(list2.get(0).origen());
        arr[1] = G.vertice(list2.get(0).destino());
        int insert = list2.get(0).origen();
        int index = -100;

        // Indica por que indice queremos empezar a añadir vertices 
        ListIterator<Arista> iterator = list1.listIterator(0);

        // Iterar a traves de aristas del primer ciclo
        while (iterator.hasNext()) {
            Arista e = iterator.next();

            // Si tenemos un primer match
            int idA1 = e.origen();
            int idA2 = e.destino();
            if (idA1 == insert || idA2 == insert) {
                if (iterator.hasNext()) {
                    e = iterator.next();
                    idA1 = e.origen();
                    idA2 = e.destino();
                    // Si tenemos un segundo match, guardamos el indice
                    if (idA1 == insert || idA2 == insert)
                        index = list1.indexOf(e) - 1;
                } else {
                    // Estamos en el ultimo vertice del indice el cual rodea al primero
                    index = list1.indexOf(e);
                }
            }
        }
        if (index < 0) {
            // Probamos de nuevo con el otro vertice
            insert = G.idVertice(arr[1]);            
            ListIterator<Arista> iterator2 = list1.listIterator(0);

            while (iterator2.hasNext()) {
                Arista e = iterator2.next();
                int idA1 = e.origen();
                int idA2 = e.destino();
                if (idA1 == insert || idA2 == insert) {
                    if (iterator2.hasNext()) {
                        e = iterator2.next();
                        idA1 = e.origen();
                        idA2 = e.destino();
                        if (idA1 == insert || idA2 == insert) 
                            index = list1.indexOf(e) - 1;
                    } 
                    else index = list1.indexOf(e);
                }
            }
        }
        if (index < 0) throw new IllegalArgumentException("Couldn't figure out where to insert");

        result.addAll(index + 1, list2);
        return result;
    }
}
