/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author David Gracia Celemendi
 * @param <X> Tipo asociado al primer elemento del par ordenado
 * @param <Y> Tipo asociado al segundo elemento del par ordenado
 */
public class ParOrdenado<X,Y> {
    private X primero;
    private Y segundo;
    
    /**
     * Constructor por defecto.
     */   
    public ParOrdenado(){
        this.primero = null;
        this.segundo = null;
    }
    
    /**
     * Constructor completo.
     * @param primero Primer elemento.
     * @param segundo Segundo elemento.
     */    
    public ParOrdenado(X primero,Y segundo) {
        this.primero = primero;
        this.segundo = segundo;
    }
    
     /**
     * Consultor del primer elemento.
     * @return El primer elemento.
     */  
    public X primero() {
        return this.primero;
    }

     /**
     * Modificador del primer elemento.
     * @param primero El elemento que va a sustituir al original.
     */
    public void modificarPrimero(X primero) {
        this.primero = primero;
    }

     /**
     * Consultor del segundo elemento.
     * @return El segundo elemento.
     */
    public Y segundo() {
        return this.segundo;
    }
    
     /**
     * Modificador del segundo elemento.
     * @param segundo El elemento que va a sustituir al original.
     */    
    public void modificarSegundo(Y segundo) {
        this.segundo = segundo;
    }
    
     /**
     * Devuelve cierto si el objeto implícito y p son iguales. Falso en contrario.
     * @param p El par ordenado con el que se quiere comparar.
     * @return Cierto si el objeto implícito y p son iguales. Falso en contrario.
     */
    public boolean igualA(ParOrdenado<X,Y> p){        
        return this.primero==p.primero && this.segundo==p.segundo;
    }
}
