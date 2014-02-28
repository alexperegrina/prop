package Dominio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Marc Vila Gomez
 */
public class DriverSolucion {

    public static class ConjuntoOpciones {

        private int numeros;
        private ControladorTareas controladorTareas;
        //atributos Tarea
        private int id;
        private String nombre;
        private double tiempoProduccion;
        private double tiempoPreparacion;
        private double costeProduccion;
        private double costePreparacion;
        private String descripcion;
        //atributos relacion
        private Integer tarea1;
        private Integer tarea2;
        private double similitud;

        public void crearControlador() {
            this.controladorTareas = new ControladorTareas();
        }

        private void anadirTareaCompleta(String line[]) {
            try {
                this.nombre = line[1];
                this.tiempoProduccion = Double.parseDouble(line[2]);
                this.tiempoPreparacion = Double.parseDouble(line[3]);
                this.costeProduccion = Double.parseDouble(line[4]);
                this.costePreparacion = Double.parseDouble(line[5]);
                this.descripcion = line[6];
                this.controladorTareas.anadirTarea(this.nombre, this.tiempoProduccion, this.tiempoPreparacion,
                        this.costeProduccion, this.costePreparacion, this.descripcion);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public void anadirRelacionCompleta(String line[]) {
            try {
                Boolean tipo;
                this.tarea1 = Integer.parseInt(line[1]);
                this.tarea2 = Integer.parseInt(line[2]);
                this.similitud = Double.parseDouble(line[3]);
                this.numeros = Integer.parseInt(line[4]);
                if (this.numeros == 1) {
                    tipo = Boolean.TRUE;
                } else {
                    tipo = Boolean.FALSE;
                }

                this.nombre = line[5];
                this.controladorTareas.anadirRelacion(this.tarea1, this.tarea2, this.similitud, tipo, this.nombre);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public Set<Integer> consultarIdTareas() {
            return this.controladorTareas.consultarIdTareas();
        }
    }

    public static double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
    }

    private static void menu() {
        System.out.println("Driver de Solución");
        System.out.println("Creadoras:");
        System.out.println("\t 1) Solución()");
        System.out.println("\t \t input: 1 ");
        System.out.println("\t 2) Solución(String nombre, double tiempo, double coste, int tamItems, ArrayList<T> items)");
        System.out.println("\t \t input: 2 \"<String>\" <double> <double> <tamaNo> <ArrayList<Integer>");
        System.out.println("\t 3) Solución(Solucion)");
        System.out.println("\t \t input: 3 \"<String>\" <double> <double> <tamaNo> <ArrayList<Integer>");
        System.out.println("Consultoras:");
        System.out.println("\t 4) nombre()");
        System.out.println("\t \t input: 4 ");
        System.out.println("\t 5) tiempo()");
        System.out.println("\t \t input: 5 ");
        System.out.println("\t 6) coste()");
        System.out.println("\t \t input: 6 ");
        System.out.println("\t 7) numeroTareas()");
        System.out.println("\t \t input: 7");
        System.out.println("\t 8) listarItems()");
        System.out.println("Modificadoras");
        System.out.println("\t 9) modificarNombre(String nombre)");
        System.out.println("\t \t input: 9 \"<String>\"");
        System.out.println("\t 10) modificarTiempo(int ntiempo)");
        System.out.println("\t \t input: 10 <int> ");
        System.out.println("\t 11) modificarCoste(int ncoste)");
        System.out.println("\t \t input: 11 <int> ");
        System.out.println("\t 12) anadirItem(T t)");
        System.out.println("\t \t input: 12 <int> ");
        System.out.println("\t 13) intercambiarPosicion(int p1, int p2)");
        System.out.println("\t \t input: 13 <int> <int>");
        System.out.println("\t 14) CrearControlador()");
        System.out.println("\t \t input: 14");
        System.out.println("\t 15) anadir tareas");
        System.out.println("\t \t input: leer Leeme");
        System.out.println("\t 16) anadir relaciones");
        System.out.println("\t \t input: leer Leeme");
        System.out.println("\t 17) ejecutarTSP(Grafo G, String op1, String op2)");
        System.out.println("\t \t input: 17 \"<String>\" \"<String>\"");
        System.out.println("Otros:");
        System.out.println("\t 18) Salir");
        System.out.println("\t Si necesitas más ayuda para introducir parametros, consulta el Readme.");
    }

    public static void main(String[] args) {
        try {
            ConjuntoOpciones ConjOp = new ConjuntoOpciones();
            Solucion solucion = null;
            menu();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            boolean salir = false;
            String linea = "Primero";
            while (!salir && linea != null) {
                linea = reader.readLine();
                String[] line = linea.split(" ");
                line = sacarStrings(linea);
                try {
                    System.out.println("Opción " + line[0] + " seleccionada.");
                    switch (line[0]) {
                        case "1":
                            solucion = new Solucion();
                            break;
                        case "2":
                            ArrayList<Integer> items = new ArrayList<>();
                            for (int i = 0; i < Integer.parseInt(line[4]); i++) {
                                items.add(Integer.parseInt(line[5 + i]));
                            }
                            solucion = new Solucion(line[1], Double.parseDouble(line[2]), Double.parseDouble(line[3]), items);
                            break;
                        case "3":
                            items = new ArrayList<>();
                            for (int i = 0; i < Integer.parseInt(line[4]); i++) {
                                items.add(Integer.parseInt(line[5 + i]));
                            }
                            Solucion sol = new Solucion(line[1], Double.parseDouble(line[2]), Double.parseDouble(line[3]), items);
                            solucion = new Solucion(sol);
                            break;
                        case "4":
                            if (solucion == null) {
                                throw new IllegalArgumentException("Debes crear una solucion antes.");
                            }
                            System.out.println(solucion.nombre());
                            break;
                        case "5":
                            if (solucion == null) {
                                throw new IllegalArgumentException("Debes crear una solucion antes.");
                            }
                            System.out.println(solucion.tiempo());
                            break;
                        case "6":
                            if (solucion == null) {
                                throw new IllegalArgumentException("Debes crear una solucion antes.");
                            }
                            System.out.println(solucion.coste());
                            break;
                        case "7":
                            if (solucion == null) {
                                throw new IllegalArgumentException("Debes crear una solucion antes.");
                            }
                            System.out.println(solucion.numeroTareas());
                            break;
                        case "8":
                            if (solucion == null) {
                                throw new IllegalArgumentException("Debes crear una solucion antes.");
                            }
                            items = solucion.listarItems();
                            for (int i = 0; i < items.size(); ++i) {
                                System.out.println(items.get(i) + " - ");
                            }
                            break;

                        case "9":
                            if (solucion == null) {
                                throw new IllegalArgumentException("Debes crear una solucion antes.");
                            }
                            solucion.modificarNombre(line[1]);
                            break;
                        case "10":
                            if (solucion == null) {
                                throw new IllegalArgumentException("Debes crear una solucion antes.");
                            }
                            solucion.modificarTiempo(Integer.parseInt(line[1]));
                            break;
                        case "11":
                            if (solucion == null) {
                                throw new IllegalArgumentException("Debes crear una solucion antes.");
                            }
                            solucion.modificarCoste(Integer.parseInt(line[1]));
                            break;
                        case "12":
                            if (solucion == null) {
                                throw new IllegalArgumentException("Debes crear una solucion antes.");
                            }
                            int n = Integer.parseInt(line[1]);
                            for (int i = 0; i < n; i++) {
                                solucion.anadirItem(Integer.parseInt(line[2 + i]));
                            }
                            break;
                        case "13":
                            if (solucion == null) {
                                throw new IllegalArgumentException("Debes crear una solucion antes.");
                            }
                            solucion.intercambiarPosicion(Integer.parseInt(line[1]), Integer.parseInt(line[2]));
                            break;
                        case "14":
                            ConjOp.crearControlador();
                            break;
                        case "15":
                            ConjOp.anadirTareaCompleta(line);
                            break;
                        case "16":
                            ConjOp.anadirRelacionCompleta(line);
                            break;
                        case "17":
                            if (solucion == null) {
                                throw new IllegalArgumentException("Debes crear una solucion antes.");
                            }
                            ArrayList<Integer> idTareas = new ArrayList<>(ConjOp.consultarIdTareas());
                            Grafo G = new Grafo(idTareas.size());
                            
                            for (int i = 0; i < idTareas.size(); ++i) {
                                for (int j = i + 1; j < idTareas.size(); ++j) {
                                    double random = Math.random() * 10; // Max 10 relacion
                                    G.ponerArista(new Arista(i, j, Redondear(random)));
                                }
                                G.modificarVertice(i, new Vertice(idTareas.get(i)));
                            }
                            matrizAdyacencias(G);
                            solucion.ejecutarTSP(G, line[1], line[2], ConjOp.controladorTareas, idTareas);
                            System.out.println("Terminado");
                            break;
                        case "18":
                            salir = true;
                            break;

                        default:
                            System.out.println("La opción elegida no existe");
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String[] sacarStrings(String str) {
        List<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(str);
        while (m.find()) {
            list.add(m.group(1).replace("\"", ""));
        }
        String[] ret = new String[list.size()];
        return list.toArray(ret);
    }

    private static void matrizAdyacencias(Grafo g) {
        System.out.print("IMPRESIÓN DE GRAFO:\nVértices: ");
        for (int i = 0; i < g.numVertices(); i++) {
            System.out.print(i + " ");
        }
        System.out.println("\nMatriz de adyacencias (i,j,peso): ");
        System.out.print("\n");
        for (int i = 0; i < g.numVertices(); i++) {
            for (int j = 0; j < g.numVertices(); j++) {
                if (i != j && g.adyacentes(i, j)) {
                    Double peso = (Double) g.arista(i, j).peso();
                    System.out.print("(" + i + "," + j + "," + peso + ")");
                } else {
                    System.out.print("  -----  ");
                }
                System.out.print("  ");
            }
            System.out.print("\n");
        }
    }
}
