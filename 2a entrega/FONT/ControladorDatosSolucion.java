


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Marc Vila Gomez
 */
public class ControladorDatosSolucion {

    File archivo;
    FileReader fr;
    FileWriter fw;
    BufferedReader br;
    String path = "DatosSolucion.txt";

    /**
     * Constructor por defecto.
     */
    public ControladorDatosSolucion() {
        archivo = null;
        fr = null;
        br = null;
    }

    /**
     * Función que abre el archivo de Soluciones
     */
    public void abrirArchivoSoluciones() throws IOException {
        archivo = new File(path);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
    }

    /**
     * Función que cierra el archivo de Soluciones
     */
    public void cerrarArchivoSoluciones() throws IOException {
        archivo = null;
        if (null != fr) {
            fr.close();
        }
        if (null != fw) {
            fw.close();
        }
    }

    /**
     * Función que añade una solucion al archivo
     *
     * @param Solucion Un array de Strings que contiene los datos de la solucion
     */
    public void anadirSolucion(String[] Solucion) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.print(generarLinea(Solucion));
        out.close();
    }

    /**
     * Función que añade varias soluciones al archivo
     *
     * @param Solucion Un array de array de Strings que contiene los datos de
     * varias soluciones
     */
    public void anadirSoluciones(String[][] Solucion) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        for (int i = 0; i < Solucion.length; ++i) {
            out.print(generarLinea(Solucion[i]));
        }
        out.close();
    }

    /**
     * Función que modifica el archivo de Soluciones
     *
     * @param Solucion Un array de array de Strings que contiene los datos de
     * varias soluciones
     */
    public void modificarArchivo(String[][] Solucion) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fw = new FileWriter(archivo, false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        for (int i = 0; i < Solucion.length; ++i) {
            out.print(generarLinea(Solucion[i]));
        }
        out.close();
    }

    /**
     * Función que lee el archivo de Soluciones
     *
     * @return Un array de array de Strings que contiene los datos de varias
     * Soluciones
     */
    public String[][] leerSoluciones() throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fr = new FileReader(archivo);
        ArrayList<String[]> Soluciones = new ArrayList();
        BufferedReader in = new BufferedReader(fr);
        String linea;
        while ((linea = in.readLine()) != null) {
            Soluciones.add(linea.split(";"));
        }
        in.close();
        String[][] TareasArray = null;
        if (Soluciones.size() > 0) {
            TareasArray = new String[Soluciones.size()][Soluciones.get(0).length];
        }
        return Soluciones.toArray(TareasArray);
    }

    /**
     * Función que modifica una Solucion del archivo de Soluciones
     *
     * @param id El identificador de la solucion a modificar
     * @param str Un array de Strings que contiene los datos de una Solucion
     */
    public void modificarSolucion(String id, String[] str) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        ArrayList<String[]> lineas = new ArrayList();
        fr = new FileReader(archivo);
        BufferedReader in = new BufferedReader(fr);
        String s;
        while ((s = in.readLine()) != null) {
            String[] linea = s.split(";");
            if (linea[0].equals(id)) {
                lineas.add(str);
            } else {
                lineas.add(linea);
            }
        }
        in.close();
        String[][] n_lineas = new String[lineas.size()][lineas.get(0).length];
        n_lineas = lineas.toArray(n_lineas);
        modificarArchivo(n_lineas);
    }

    /**
     * Función que genera una línea del archivo de soluciones
     *
     * @param str Un array de Strings que contiene los datos de una solucion
     * @return Un String que contiene los datos de una Solucion para guardar en
     * el archivo
     */
    private String generarLinea(String[] str) {
        String res = "";
        int n = str.length;
        res += str[0];
        for (int i = 1; i < n; ++i) {
            res += ";" + str[i];
        }
        res += "\n";
        return res;
    }
}