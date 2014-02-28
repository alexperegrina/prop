/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Alex;

/**
 *
 * @author Alex
 */
public class Libreria {
    
    //STATICA
    public static boolean isNumeric(String string)
    {
        if (string == null) throw new NullPointerException("The string must not be null!");
        final int len = string.length();
        if (len == 0) return false;
        for (int i = 0; i < len; ++i) {
            if (!Character.isDigit(string.charAt(i))) return false;
        }
        return true;
    }
    
    //Metodos para debugar la clase emparejamiento
    /*private void printMejor() {
        System.out.print("+++++++++++++++++Mejor\n");
        System.out.print("solOptima.numVérticesEmparejados "+ this.solOptima.numVérticesEmparejados+"\n");
        System.out.print("solOptima.pesoEmparejamiento "+ this.solOptima.pesoEmparejamiento+"\n");
        System.out.print("solOptima.lenght "+ this.solOptima.emparejamientos.length+"\n");
        printMat(this.solOptima.emparejamientos);
        System.out.print("+++++++++++++++++\n");
    }*/
    
    private void printMat(int[][] m) {
        System.out.print("****************** printMat\n");
        for(int i = 0; i < m.length; ++i) {
            for(int j = 0; j < m.length; ++j) System.out.print(" " + m[i][j] + " ");
            System.out.print("\n");
        }
    }
    
    //estas funciones si que tiene que estar en el projecto
       //STATICA
    public static boolean isNumericInteger(String string)
    {
        if (string == null) throw new NullPointerException("The string must not be null!");
        final int len = string.length();
        if (len == 0) return false;
        for (int i = 0; i < len; ++i) {
            if (!Character.isDigit(string.charAt(i))) return false;
        }
        return true;
    }
    
    public static boolean isNumericDouble(String string)
    {
        if (string == null) throw new NullPointerException("The string must not be null!");
        final int len = string.length();
        if (len == 0) return false;
        for (int i = 0; i < len; ++i) {
            if (string.charAt(i) != '.' && !Character.isDigit(string.charAt(i))) return false;
        }
        return true;
    }
}
