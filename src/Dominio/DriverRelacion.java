/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author Alex Peregrina Cabrera
 */
public class DriverRelacion {
    
    private static String msg_error_number = "Error, Los datos Introduccidos no son del formato correcto.";
    private static String msg_error_general = "Error.";
    
    
    private static String[] sacarStrings(String str) {
        List<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(str);
        while (m.find()) {
            list.add(m.group(1).replace("\"", ""));
        }
        String[] ret = new String[list.size()];
        return list.toArray(ret);
    }
    
    public static void menu(Boolean iniciada2,Boolean iniciada3) {
        System.out.println("Escoja una opción:");                     
        System.out.println("\t 1) RelacionTiempo()");
        System.out.println("\t 2) RelacionTiempo(Integer tarea1, Integer tarea2, Double similitud)");
        System.out.println("\t 3) RelacionTiempo(String nombre, Integer tarea1, Integer tarea2, Double similitud)");
        System.out.println("\t 4) RelacionCoste()");
        System.out.println("\t 5) RelacionCoste(Integer tarea1, Integer tarea2, Double similitud)");
        System.out.println("\t 6) RelacionCoste(String nombre, Integer tarea1, Integer tarea2, Double similitud)");

        if(iniciada3) {
            System.out.println("*RelacionCoste");
            System.out.println("\t 7) Integer id()");
            System.out.println("\t 8) String nombre()");
            System.out.println("\t 9) Double similitud()");
            System.out.println("\t 10) Integer tarea1()");
            System.out.println("\t 11) Integer tarea2()");
            System.out.println("\t 12) Boolean tipo()");
            System.out.println("\t 13) void modificarId(int id)");
            System.out.println("\t 14) void modificarNombre(String nombre)");
            System.out.println("\t 15) void modificarSimilitud(Double similitud)");
            System.out.println("\t 16) void modificarTarea1(int tarea1)");
            System.out.println("\t 17) void modificarTarea2(int tarea2)");
            System.out.println("\t 18) void modificarTareas(Integer tarea1, Integer tarea2)");
            
        }
        if(iniciada2) {
            System.out.println("*RelacionTiempo");
            System.out.println("\t 19) Integer id()");
            System.out.println("\t 20) String nombre()");
            System.out.println("\t 21) Double similitud()");
            System.out.println("\t 22) Integer tarea1()");
            System.out.println("\t 23) Integer tarea2()");
            System.out.println("\t 24) Boolean tipo()");
            System.out.println("\t 25) void modificarId(int id)");
            System.out.println("\t 26) void modificarNombre(String nombre)");
            System.out.println("\t 27) void modificarSimilitud(Double similitud)");
            System.out.println("\t 28) void modificarTarea1(int tarea1)");
            System.out.println("\t 29) void modificarTarea2(int tarea2)");
            System.out.println("\t 30) void modificarTareas(Integer tarea1, Integer tarea2)");
            
        }
        System.out.println("\t 0) Salir");
    }
    
    public static void main(String[] args) {   
        System.out.println("Driver Relacion");
        boolean salir = false;
        boolean iniciada2 = false;
        boolean iniciada3 = false;

        //atributos Relacion
        Integer id;
        String nombre;
        Integer tarea1;
        Integer tarea2;
        double similitud;

        HashMap<String,Relacion> Relaciones = new HashMap<String,Relacion>();

        String linea;
        String[] line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        while (!salir) {
            try {
                menu(iniciada2, iniciada3);
                linea = reader.readLine();
                line = linea.split(" ");
                line = sacarStrings(linea);

                switch (line[0]) {
                    case "1":
                        iniciada2 = true;
                        Relaciones.put("RelacionTiempo", new RelacionTiempo());
                        break;
                    case "2":
                        iniciada2 = true;
                        tarea1 = Integer.parseInt(line[1]);
                        tarea2 = Integer.parseInt(line[2]);
                        similitud = Double.parseDouble(line[3]);
                        Relaciones.put("RelacionTiempo", new RelacionTiempo(tarea1, tarea2, similitud));
                        break;
                    case "3":
                        iniciada2 = true;
                        nombre = line[1];  
                        tarea1 = Integer.parseInt(line[2]);
                        tarea2 = Integer.parseInt(line[3]);
                        similitud = Double.parseDouble(line[4]);
                        Relaciones.put("RelacionTiempo", new RelacionTiempo(nombre, tarea1, tarea2, similitud));
                    break;
                    case "4":
                        iniciada2 = true;
                        Relaciones.put("RelacionCoste", new RelacionCoste());
                        break;
                    case "5":
                        nombre = reader.readLine();
                        tarea1 = Integer.parseInt(line[1]);
                        tarea2 = Integer.parseInt(line[2]);
                        similitud = Double.parseDouble(line[3]);
                        Relaciones.put("RelacionCoste", new RelacionCoste(nombre, tarea1, tarea2, similitud));
                        break;
                    case "6":
                        nombre = line[1];  
                        tarea1 = Integer.parseInt(line[2]);
                        tarea2 = Integer.parseInt(line[3]);
                        similitud = Double.parseDouble(line[4]);
                        Relaciones.put("RelacionCoste", new RelacionCoste(nombre, tarea1, tarea2, similitud));
                        break;
                    case "7":
                        System.out.println(""+Relaciones.get("RelacionCoste").id());
                        break;
                    case "8":
                        System.out.println(""+Relaciones.get("RelacionCoste").nombre());
                        break;
                    case "9":
                        System.out.println(""+Relaciones.get("RelacionCoste").similitud());
                        break;
                    case "10":
                        System.out.println(""+Relaciones.get("RelacionCoste").tarea1());
                        break;
                    case "11":
                        System.out.println(""+Relaciones.get("RelacionCoste").tarea2());
                        break;
                    case "12":
                        System.out.println(""+Relaciones.get("RelacionCoste").tipo());
                        break;
                    case "13":
                        id = Integer.parseInt(line[1]);
                        Relaciones.get("RelacionCoste").modificarId(id);
                        break;
                    case "14":
                        Relaciones.get("RelacionCoste").modificarNombre(line[1]);
                        break;
                    case "15":
                        similitud = Double.parseDouble(line[1]);
                        Relaciones.get("RelacionCoste").modificarSimilitud(similitud);
                        break;
                    case "16":
                        tarea1 = Integer.parseInt(line[1]);
                        Relaciones.get("RelacionCoste").modificarTarea1(tarea1);
                        break;
                    case "17":
                        tarea2 = Integer.parseInt(line[1]);
                        Relaciones.get("RelacionCoste").modificarTarea2(tarea2);
                        break;
                    case "18":
                        tarea1 = Integer.parseInt(line[1]);
                        tarea2 = Integer.parseInt(line[2]);
                        Relaciones.get("RelacionCoste").modificarTareas(tarea1, tarea2);
                        break;
                    case "19":
                        System.out.println(""+Relaciones.get("RelacionTiempo").id());
                        break;
                    case "20":
                        System.out.println(""+Relaciones.get("RelacionTiempo").nombre());
                        break;
                    case "21":
                        System.out.println(""+Relaciones.get("RelacionTiempo").similitud());
                        break;
                    case "22":
                        System.out.println(""+Relaciones.get("RelacionTiempo").tarea1());
                        break;
                    case "23":
                        System.out.println(""+Relaciones.get("RelacionTiempo").tarea2());
                        break;
                    case "24":
                        System.out.println(""+Relaciones.get("RelacionTiempo").tipo());
                        break;  
                    case "25":
                        id = Integer.parseInt(line[1]);
                        Relaciones.get("RelacionTiempo").modificarId(id);
                        break;
                    case "26":
                        Relaciones.get("RelacionTiempo").modificarNombre(line[1]);
                        break;
                    case "27":
                        similitud = Double.parseDouble(line[1]);
                        Relaciones.get("RelacionTiempo").modificarSimilitud(similitud);
                        break;
                    case "28":
                        tarea1 = Integer.parseInt(line[1]);
                        Relaciones.get("RelacionTiempo").modificarTarea1(tarea1);
                        break;
                    case "29":
                        tarea2 = Integer.parseInt(line[1]);
                        Relaciones.get("RelacionTiempo").modificarTarea2(tarea2);
                        break;
                    case "30":
                        tarea1 = Integer.parseInt(line[1]);
                        tarea2 = Integer.parseInt(line[2]);
                        Relaciones.get("RelacionTiempo").modificarTareas(tarea1, tarea2);
                        break;
                    case "0":
                        salir = true;
                        break;
                    default:
                        System.out.println("La opción elegida no existe!");
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(DriverRelacion.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NumberFormatException e) {
                System.out.println(msg_error_number);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
