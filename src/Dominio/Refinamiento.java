/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dominio;

import java.util.ArrayList;

/**
 *
 * @author 
 */
abstract class Refinamiento {
    abstract ArrayList<Integer> mejorarSolucion(Grafo g, ArrayList<Integer> tour);
}
