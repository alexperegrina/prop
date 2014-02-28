package Dominio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Marc Vila Gomez
 */
public class DriverControladorSoluciones {

    private static void menu() {
        System.out.println("Driver de ControladorSoluciones");
        System.out.println("Creadoras:");
        System.out.println("\t 1) ControladorSoluciones()");
        System.out.println("\t \t input: 1 ");
        System.out.println("Consultoras:");
        System.out.println("\t 2) consultarSoluciones()");
        System.out.println("\t \t input: 2 ");
        System.out.println("\t 3) nSoluciones()");
        System.out.println("\t \t input: 3 ");
        System.out.println("\t 4) consultarSolucion(Integer id)");
        System.out.println("\t \t input: 4 <int>");
        System.out.println("\t 5) existeSolucion(Integer id)");
        System.out.println("\t \t input: 5 <int> ");
        System.out.println("Modificadoras:");
        System.out.println("\t 6) anadirSolucion(Solucion solucion)");
        System.out.println("\t \t input: 6 \"<String>\" <double> <double> <tamaNo> <ArrayList<Integer>");
        System.out.println("\t 7) borrarSolucion(Integer id)");
        System.out.println("\t \t input: 7 <int>");
        System.out.println("\t 8) modificarSoluciones(ArrayList<Solucion> sMod)");
        System.out.println("\t \t input: (Leer Readme) 8 <int> \"<String>\" <double> <double> <tamaNo> <ArrayList<Integer>");
        System.out.println("\t 9) soluciones.guardarDatos() (Guarda datos en fichero)");
        System.out.println("\t \t input: 9");
        System.out.println("\t 10) soluciones.abrirDatos(); (Abrir datos en fichero)");
        System.out.println("\t \t input: 10");
        System.out.println("Otros:");
        System.out.println("\t 11) Salir");
    }

    public static void main(String[] args) {
        try {
            ControladorSoluciones soluciones = null;
            menu();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            boolean salir = false;
            String linea = "Primero";
            while (!salir && linea != null) {
                linea = reader.readLine();
                String[] line = linea.split(" ");
                try {
                    System.out.println("Opción " + line[0] + " seleccionada.");
                    switch (line[0]) {
                        case "1":
                            soluciones = new ControladorSoluciones();
                            break;
                            
                        case "2":
                            if (soluciones == null) {
                                throw new IllegalArgumentException("Debes crear un controlador antes.");
                            }
                            ArrayList<Solucion> nSol = soluciones.consultarSoluciones();
                            for (int i = 0; i < nSol.size(); i++) {
                                Solucion solucion = nSol.get(i);
                                System.out.println("ID: " + i + "\tNombre: " + solucion.nombre());
                                System.out.print("Tiempo: " + solucion.tiempo());
                                System.out.println("\tCoste: " + solucion.coste());
                                ArrayList<Integer> items = solucion.listarItems();
                                System.out.print("ID Tareas: ");
                                for (int j = 0; j < items.size(); ++j) {
                                    if (j < items.size() - 1) {
                                        System.out.print(items.get(j) + " - ");
                                    } else {
                                        System.out.println(items.get(j));
                                    }
                                }
                            }
                            break;

                        case "3":
                            if (soluciones == null) {
                                throw new IllegalArgumentException("Debes crear un controlador antes.");
                            }
                            System.out.println("Numero de Soluciones: " + soluciones.nSoluciones());
                            break;

                        case "4":
                            if (soluciones == null) {
                                throw new IllegalArgumentException("Debes crear un controlador antes.");
                            }
                            Solucion sol = soluciones.consultarSolucion(Integer.parseInt(line[1]));
                            System.out.println("ID: " + line[1] + "\tNombre: " + sol.nombre());
                            System.out.print("Tiempo: " + sol.tiempo());
                            System.out.println("\tCoste: " + sol.coste());
                            ArrayList<Integer> items = sol.listarItems();
                            System.out.print("ID Tareas: ");
                            for (int j = 0; j < items.size(); ++j) {
                                if (j < items.size() - 1) {
                                    System.out.print(items.get(j) + " - ");
                                } else {
                                    System.out.println(items.get(j));
                                }
                            }
                            break;

                        case "5":
                            if (soluciones == null) {
                                throw new IllegalArgumentException("Debes crear un controlador antes.");
                            }
                            if (soluciones.existeSolucion(Integer.parseInt(line[1]))) {
                                System.out.println("La solucion con esa id existe");
                            } else {
                                System.out.println("La solucion con esa id no existe");
                            }
                            break;

                        case "6":
                            if (soluciones == null) {
                                throw new IllegalArgumentException("Debes crear un controlador antes.");
                            }
                            line = sacarStrings(linea);
                            items = new ArrayList<>();
                            for (int i = 0; i < Integer.parseInt(line[4]); i++) {
                                items.add(Integer.parseInt(line[5 + i]));
                            }
                            Solucion s = new Solucion(line[1], Double.parseDouble(line[2]), Double.parseDouble(line[3]), items);
                            soluciones.anadirSolucion(s);
                            break;

                        case "7":
                            if (soluciones == null) {
                                throw new IllegalArgumentException("Debes crear un controlador antes.");
                            }
                            soluciones.borrarSolucion(Integer.parseInt(line[1]));
                            break;
                        case "8":
                            line = sacarStrings(linea);
                            ArrayList<Solucion> nSoluciones = new ArrayList<Solucion>();
                            int n = Integer.parseInt(line[1]);
                            for (int i = 0; i < n; i++) {
                                items = new ArrayList<>();
                                String[] tour = line[5 + 4*i].split(" ");
                                for (int j = 0; j < tour.length; j++) {
                                    items.add(Integer.parseInt(tour[j]));
                                }
                                s = new Solucion(line[2 + 4*i], Double.parseDouble(line[3 + 4*i]), Double.parseDouble(line[4 + 4*i]), items);
                                nSoluciones.add(s);
                            }
                            soluciones.modificarSoluciones(nSoluciones);
                            break;
                        case "9":
                            soluciones.guardarDatos();
                            break;
                        case "10":
                            soluciones.abrirDatos();
                            break;
                        case "11":
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
}
