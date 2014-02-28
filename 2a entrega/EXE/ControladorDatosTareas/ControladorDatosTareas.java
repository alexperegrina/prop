

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
 * @author Alejandro Rosas García
 */
public class ControladorDatosTareas {

    File archivo;
    FileReader fr;
    FileWriter fw;
    BufferedReader br;
    String path = "DatosTarea.txt";

    /**
     * Constructor por defecto.
     */
    public ControladorDatosTareas() {
        archivo = null;
        fr = null;
        br = null;
    }

    /**
     * Función que abre el archivo de Tareas
     */
    public void abrirArchivoTareas() throws IOException {
        archivo = new File(path);
        if (!archivo.exists()) {
            archivo.createNewFile();
        }
    }

    /**
     * Función que cierra el archivo de Tareas
     */
    public void cerrarArchivoTareas() throws IOException {
        archivo = null;
        if (null != fr) {
            fr.close();

        }
        if (null != fw) {
            fw.close();

        }

    }

    /**
     * Función que añade una tarea al archivo
     * @param Tarea Un array de Strings que contiene los datos de la tarea
     */
    public void anadirTarea(String[] Tarea) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        out.print(generarLinea(Tarea));
        out.close();
    }

    /**
     * Función que añade varias tarea al archivo
     * @param Tarea Un array de array de Strings que contiene los datos de varias tareas
     */
    public void anadirTareas(String[][] Tarea) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fw = new FileWriter(archivo, true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        for (int i = 0; i < Tarea.length; ++i) {
            out.print(generarLinea(Tarea[i]));
        }
        out.close();

    }

    /**
     * Función que modifica el archivo de Tareas
     * @param Tarea Un array de array de Strings que contiene los datos de varias tareas
     */
    public void modificarArchivo(String[][] Tarea) throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fw = new FileWriter(archivo, false);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter out = new PrintWriter(bw);
        for (int i = 0; i < Tarea.length; ++i) {
            out.print(generarLinea(Tarea[i]));
        }
        out.close();
    }

    /**
     * Función que lee el archivo de Tareas
     * @return Un array de array de Strings que contiene los datos de varias tareas
     */
    public String[][] leerTareas() throws IOException {
        if (archivo == null) {
            throw new IllegalArgumentException("Error: No tienes ningun archivo abierto.");
        }
        fr = new FileReader(archivo);
        ArrayList<String[]> tareas = new ArrayList();
        BufferedReader in = new BufferedReader(fr);
        String linea;
        while ((linea = in.readLine()) != null) {
            tareas.add(linea.split(";"));
        }
        in.close();
        String[][] TareasArray = null;
        if (tareas.size() > 0) {
            TareasArray = new String[tareas.size()][tareas.get(0).length];
        }
        return tareas.toArray(TareasArray);
    }

    /**
     * Función que modifica una Tarea del archivo de Tareas
     * @param id El identificador de la tarea a modificar
     * @param Tarea Un array de Strings que contiene los datos de una tarea
     */
    public void modificarTarea(String id, String[] str) throws IOException {
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
     * Función que genera una línea del archivo de tareas
     * @param str Un array de Strings que contiene los datos de una tarea
     * @return Un String que contiene los datos de una Tarea para guardar en el archivo
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
