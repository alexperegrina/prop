package Persistencia;

import Dominio.Relacion;
import Dominio.RelacionCoste;
import Dominio.RelacionTiempo;
import Dominio.ControladorTareas;
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
 * @author Alex Peregrina Cabrera
 */
public class ControladorDatosRelaciones {

    File archivo;
    FileReader fr;
    FileWriter fw;
    BufferedReader br;
    String path = "DatosRelacion.txt";

    /**
     * Constructor por defecto.
     */
    public ControladorDatosRelaciones() {
        archivo = null;
        fr = null;
        br = null;
    }

    /**
     * Función que abre el archivo de Relaciones
     */
    public void abrirArchivoRelaciones() throws IOException {
        archivo = new File(path);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
    }

    /**
     * Función que cierra el archivo de Relaciones
     */
    public void cerrarArchivoRelaciones() throws IOException {
        archivo = null;
        if (null != fr) {
            fr.close();
        }
        if (null != fw) {
            fw.close();
        }
    }

    /**
     * Función que añade una Relacion al archivo
     *
     * @param Relacion Un array de Strings que contiene los datos de la Relacion
     */
    public void anadirRelacion(String[] Relacion) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.print(generarLinea(Relacion));
        out.close();
    }

    /**
     * Función que añade varias Relaciones al archivo
     *
     * @param Relacion Un array de array de Strings que contiene los datos de
     * varias Relaciones
     */
    public void anadirRelaciones(String[][] Relacion) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        for (int i = 0; i < Relacion.length; ++i) {
            out.print(generarLinea(Relacion[i]));
        }
        out.close();
    }

    /**
     * Función que modifica el archivo de Relaciones
     *
     * @param Relacion Un array de array de Strings que contiene los datos de
     * varias Relaciones
     */
    public void modificarArchivo(String[][] Relacion) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fw = new FileWriter(archivo, false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        for (int i = 0; i < Relacion.length; ++i) {
            out.print(generarLinea(Relacion[i]));
        }
        out.close();
    }

    /**
     * Función que lee el archivo de Relaciones
     *
     * @return Un array de array de Strings que contiene los datos de varias
     * Relaciones
     */
    public String[][] leerRelaciones() throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fr = new FileReader(archivo);
        ArrayList<String[]> Relaciones = new ArrayList();
        BufferedReader in = new BufferedReader(fr);
        String linea;
        while ((linea = in.readLine()) != null) {
            Relaciones.add(linea.split(";"));
        }
        in.close();
        String[][] TareasArray = null;
        if (Relaciones.size() > 0) {
            TareasArray = new String[Relaciones.size()][Relaciones.get(0).length];
        }
        return Relaciones.toArray(TareasArray);
    }

    /**
     * Función que modifica una Relacion del archivo de Relaciones
     *
     * @param id El identificador de la Relacion a modificar
     * @param str Un array de Strings que contiene los datos de una Relacion
     */
    public void modificarRelacion(String id, String[] str) throws IOException {
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
     * Función que genera una línea del archivo de Relaciones
     *
     * @param str Un array de Strings que contiene los datos de una Relacion
     * @return Un String que contiene los datos de una Relacion para guardar en
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