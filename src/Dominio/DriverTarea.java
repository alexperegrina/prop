package Dominio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriverTarea {

    private static void menu() {
        System.out.println("Driver Tarea");
        System.out.println("Opciones:");
        System.out.println("\t 1) Creadora Tarea()");
        System.out.println("\t       input: 1");
        System.out.println("\t 2) Creadora Tarea(int id, String nombre, double tiempoProduccion, double tiempoPreparacion, double costeProduccion, double costePreparacion, String descripcion)");
        System.out.println("\t       input: 2 <int> \"<String>\" <double> <double> <double> <double> \"<String>\"");
        System.out.println("\t Consultoras");
        System.out.println("\t\t 3) id()");
        System.out.println("\t\t       input: 3");
        System.out.println("\t\t 4) nombre()");
        System.out.println("\t\t       input: 4");
        System.out.println("\t\t 5) tiempoProduccion()");
        System.out.println("\t\t       input: 5");
        System.out.println("\t\t 6) tiempoPreparacion()");
        System.out.println("\t\t       input: 6");
        System.out.println("\t\t 7) costeProduccion()");
        System.out.println("\t\t       input: 7");
        System.out.println("\t\t 8) costePreparacion()");
        System.out.println("\t\t       input: 8");
        System.out.println("\t\t 9) descripcion()");
        System.out.println("\t\t       input: 9");
        System.out.println("\t Modificadoras");
        System.out.println("\t\t 10) modificarId(int id)");
        System.out.println("\t          input: 10 <int>");
        System.out.println("\t\t 11) modificarNombre(String nombre)");
        System.out.println("\t          input: 11 \"<String>\"");
        System.out.println("\t\t 12) modificarTiempoProduccion(double tiempoProduccion)");
        System.out.println("\t          input: 12 <double>");
        System.out.println("\t\t 13) modificarTiempoPreparacion(double tiempoProduccion)");
        System.out.println("\t          input: 13 <double>");
        System.out.println("\t\t 14) modificarCosteProduccion(double tiempoPreparacion)");
        System.out.println("\t          input: 14 <double>");
        System.out.println("\t\t 15) modificarCostePreparacion(double costePreparacion)");
        System.out.println("\t          input: 15 <double>");
        System.out.println("\t\t 16) modificarDescripcion(String descripcion)");
        System.out.println("\t          input: 16 \"<String>\"");
        System.out.println("\t 0) Salir");
        System.out.println("\t          input: 0");

    }

    public static void main(String[] args) {
        try {
            Tarea tarea = null;
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            String linea;
            boolean salir = false;
            linea = "Primero";
            while (linea != null && !salir) {
                menu();
                linea = buffer.readLine();
                String[] line = linea.split(" ");
                switch (line[0]) {
                    case "1":
                        try {
                            tarea = new Tarea();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "2":
                        // 2 1 "t1 22" 1 2 3 4 gg
                        try {
                            line = sacarStrings(linea);
                            tarea = new Tarea(Integer.parseInt(line[1]), line[2], Double.parseDouble(line[3]), Double.parseDouble(line[4]), Double.parseDouble(line[5]), Double.parseDouble(line[6]), line[7]);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case "3":
                        try {
                            System.out.println(tarea.id());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "4":
                        try {
                            System.out.println(tarea.nombre());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "5":
                        try {
                            System.out.println(tarea.tiempoProduccion());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "6":
                        try {
                            System.out.println(tarea.tiempoPreparacion());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "7":
                        try {
                            System.out.println(tarea.costeProduccion());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "8":
                        try {
                            System.out.println(tarea.costePreparacion());
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "9":
                        System.out.println(tarea.descripcion());
                        break;
                    case "10":
                        try {
                            tarea.modificarId(Integer.parseInt(line[1]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "11":
                        try {
                            line = sacarStrings(linea);
                            tarea.modificarNombre(line[1]);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "12":
                        try {
                            tarea.modificarTiempoProduccion(Double.parseDouble(line[1]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "13":
                        try {
                            tarea.modificarTiempoPreparacion(Double.parseDouble(line[1]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "14":
                        try {
                            tarea.modificarCosteProduccion(Double.parseDouble(line[1]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "15":
                        try {
                            tarea.modificarCostePreparacion(Double.parseDouble(line[1]));
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "16":
                        try {
                            line = sacarStrings(linea);
                            tarea.modificarDescripcion(line[1]);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    case "0":
                        salir = true;
                        break;
                    default:
                        System.out.println(linea);
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
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
