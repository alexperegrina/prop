package Dominio;

import java.util.ArrayList;

/**
 *
 * @author Alejandro Rosas
 */
public abstract class CicloHamiltoniano {
    public abstract ArrayList<Arista> obtenerCicloHamiltoniano(ArrayList<Arista> aristas, Grafo g);
}
