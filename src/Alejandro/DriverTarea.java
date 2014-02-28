package Alejandro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DriverTarea {

    public static void main(String[] args) {
        try {
            Tarea tarea = null;

            System.out.println("Driver Tarea");
            System.out.println("Opciones:");
            System.out.println("\t 1) Creadora Tarea()");
            System.out.println("\t       input: 1");
            System.out.println("\t 2) Creadora Tarea(Integer id,String nombre,Integer tiempoProduccion,Integer tiempoPreparacion,Integer costeProduccion,Integer costePreparacion,String descripcion)");
            System.out.println("\t       input: 2 <int> <String sin espacios> <Integer> <Integer> <Integer> <Integer> <String sin espacios>");
            System.out.println("\t 3) Devolver Id()");
            System.out.println("\t       input: 3");
            System.out.println("\t 4) Devolver Nombre()");
            System.out.println("\t       input: 4");
            System.out.println("\t 5) Devolver TiempoProduccion()");
            System.out.println("\t       input: 5");
            System.out.println("\t 6) Devolver tiempoPreparacion()");
            System.out.println("\t       input: 6");
            System.out.println("\t 7) Devolver costeProduccion()");
            System.out.println("\t       input: 7");
            System.out.println("\t 8) Devolver costePreparacion()");
            System.out.println("\t       input: 8");
            System.out.println("\t 9) Devolver descripcion");
            System.out.println("\t       input: 9");
            System.out.println("\t 10) Modificar Id");
            System.out.println("\t       input: 10");//m
            System.out.println("\t 11) Modificar Nombre");
            System.out.println("\t       input: 11");//m
            System.out.println("\t 12) Modificar TiempoProduccion");
            System.out.println("\t       input: 12");//m
            System.out.println("\t 13) Modificar tiempoPreparacion");
            System.out.println("\t 14) Modificar costeProduccion");
            System.out.println("\t 15) Modificar costeProduccion");
            System.out.println("\t 16) Modificar costePreparacion");
            System.out.println("\t 17) Modificar descripcion");
            System.out.println("\t 20) Salir");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            String linea;
            boolean salir = false;
            linea = "Primero";
            while (linea != null && !salir) {
                linea = buffer.readLine();
                String[] line = linea.split(" ");

                switch (line[0]) {
                    case "1":
                        try {
                            tarea = new Tarea();
                        } catch (Exception e) {
                        }
                        break;
                    case "2":
                        // 1 t1 1 2 3 4 gg
                        try {
                            tarea = new Tarea(Integer.parseInt(line[1]), line[2], Integer.parseInt(line[3]), Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]), line[7]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        break;
                    case "3":
                        System.out.println(tarea.id());
                        break;
                    case "4":
                        System.out.println(tarea.nombre());
                        break;
                    case "5":
                        System.out.println(tarea.tiempoProduccion());
                        break;
                    case "6":
                        System.out.println(tarea.tiempoPreparacion());
                        break;
                    case "7":
                        System.out.println(tarea.costeProduccion());
                        break;
                    case "8":
                        System.out.println(tarea.costePreparacion());
                        break;
                    case "9":
                        System.out.println(tarea.descripcion());
                        break;
                    case "10":
                        try {
                            tarea.modificarId(Integer.parseInt(line[1]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "11":
                        try {
                            tarea.modificarNombre(line[1]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "12":
                        try {
                            tarea.modificarTiempoProduccion(Integer.parseInt(line[1]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "13":
                        try {
                            tarea.modificarTiempoPreparacion(Integer.parseInt(line[1]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "14":
                        try {
                            tarea.modificarCostePreparacion(Integer.parseInt(line[1]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "15":
                        try {
                            tarea.modificarCosteProduccion(Integer.parseInt(line[1]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "16":
                        try {
                            tarea.modificarCostePreparacion(Integer.parseInt(line[1]));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "17":
                        try {
                            tarea.modificarDescripcion(line[1]);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case "20":
                        salir = true;
                        break;
                    default:
                        System.out.println("La opción elegida no existe!");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
