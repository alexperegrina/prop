package Dominio;

import Persistencia.ControladorDatosSolucion;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Marc Vila Gomez
 */
public class ControladorSoluciones {

    //private ControladorDades controladorDades;
    private ArrayList<Solucion> soluciones;

    /**
     * Crea una instancia del controlador
     *
     * @return Una instancia del controlador
     */
    public ControladorSoluciones() {
        this.soluciones = new ArrayList<Solucion>();
    }

    /**
     * Añade una solucion al conjunto de soluciones.
     *
     * @param solucion Solucion a añadir.
     */
    public void anadirSolucion(Solucion solucion) throws IllegalArgumentException {
        if (solucion == null) {
            throw new IllegalArgumentException("Error: Solucion es null");
        }
        this.soluciones.add(solucion);
    }

    /**
     * Elimina una solucion
     *
     * @param solucion Solucion que se va a eliminar
     */
    public void borrarSolucion(int solucion) throws IllegalArgumentException {
        if (!existeSolucion(solucion)) {
            throw new IllegalArgumentException("Error: Solucion/Soluciones es null");
        }
        this.soluciones.remove(solucion); //Elimina primera instancia de solucion
    }

    /**
     * Devuelve la lista de Soluciones del conjunto
     *
     * @return La lista de soluciones
     */
    public ArrayList<Solucion> consultarSoluciones() throws IllegalArgumentException {
        if (soluciones == null) {
            throw new IllegalArgumentException("Error: Soluciones es null");
        }
        return this.soluciones;
    }

    /**
     * Devuelve el numero de soluciones
     *
     * @return El numero de soluciones
     */
    public int nSoluciones() throws IllegalArgumentException {
        if (soluciones == null) {
            throw new IllegalArgumentException("Error: Soluciones es null");
        }
        return this.soluciones.size();
    }

    /**
     * Sobreescribe el array de soluciones por uno nuevo
     *
     * @param sMod Lista de soluciones a escribir.
     */
    public void modificarSoluciones(ArrayList<Solucion> sMod) {
        this.soluciones = sMod;
    }

    /**
     * Devuelve una solucion a traves de un id
     *
     * @param id ID de la solucion a retornar
     * @return La solucion con ID = parametro
     */
    public Solucion consultarSolucion(Integer id) throws IllegalArgumentException {
        if (soluciones == null) {
            throw new IllegalArgumentException("Error: Soluciones es null");
        }
        return this.soluciones.get(id);
    }

    /**
     * Devuelve booleano sobre si existe solucion
     *
     * @param id ID de la solucion a consultar
     * @return Bool sobre si existe o no la solucion con ID = parametro
     */
    public boolean existeSolucion(Integer id) throws IllegalArgumentException {
        if (soluciones == null) {
            throw new IllegalArgumentException("Error: Soluciones es null");
        }
        if (nSoluciones() > id) {
            return true;
        }
        return false;
    }

    /**
     * Carga los datos de fichero en memoria
     *
     */
    public void abrirDatos() throws IOException {
        ControladorDatosSolucion CntrlDS = new ControladorDatosSolucion();
        CntrlDS.abrirArchivoSoluciones();

        String[][] ret = CntrlDS.leerSoluciones();
        soluciones = new ArrayList<Solucion>();
        for (int i = 0; i < ret.length; i++) {
            String[] sitems = ret[i][3].split(" ");
            ArrayList<Integer> items = new ArrayList<Integer>();
            for (int j = 0; j < sitems.length; j++) {
                items.add(Integer.parseInt(sitems[j]));
            }
            Solucion sol = new Solucion(ret[i][0], Double.parseDouble(ret[i][1]), Double.parseDouble(ret[i][2]), items);
            soluciones.add(sol);
        }
        CntrlDS.cerrarArchivoSoluciones();
    }

    /**
     * Guarda la memoria de datos en fichero de salida
     *
     */
    public void guardarDatos() throws IOException {
        ControladorDatosSolucion CntrlDS = new ControladorDatosSolucion();
        String[][] ret = new String[soluciones.size()][4];
        for (int i = 0; i < soluciones.size(); i++) {
            ret[i][0] = soluciones.get(i).nombre();
            ret[i][1] = ((Double) soluciones.get(i).tiempo()).toString();
            ret[i][2] = ((Double) soluciones.get(i).coste()).toString();
            if (soluciones.get(i).listarItems().size() > 0) {
                ret[i][3] = soluciones.get(i).listarItems().get(0).toString();
            }
            for (int j = 1; j < soluciones.get(i).listarItems().size(); j++) {
                ret[i][3] += " " + soluciones.get(i).listarItems().get(j);
            }
        }
        CntrlDS.abrirArchivoSoluciones();
        CntrlDS.modificarArchivo(ret);
        CntrlDS.cerrarArchivoSoluciones();
    }
}
