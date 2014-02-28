package Dominio;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DriverTarea_old {

    public static void main(String[] args) {
        System.out.println("Driver Tarea");

        Tarea tarea = null;

        boolean salir = false;
        while (!salir) {
            System.out.println("Escoja una opción:");
            System.out.println("\t 1) Creadora Tarea()");
            System.out.println("\t 2) Creadora Tarea(Integer id,String nombre,Integer tiempoProduccion,Integer tiempoPreparacion,Integer costeProduccion,Integer costePreparacion,String descripcion)");
            System.out.println("\t 3) Devolver Id");
            System.out.println("\t 4) Devolver Nombre");
            System.out.println("\t 5) Devolver TiempoProduccion");
            System.out.println("\t 6) Devolver tiempoPreparacion");
            System.out.println("\t 7) Devolver costeProduccion");
            System.out.println("\t 8) Devolver costePreparacion");
            System.out.println("\t 9) Devolver descripcion");
            System.out.println("\t 10) Modificar Id");
            System.out.println("\t 11) Modificar Nombre");
            System.out.println("\t 12) Modificar TiempoProduccion");
            System.out.println("\t 13) Modificar tiempoPreparacion");
            System.out.println("\t 14) Modificar costeProduccion");
            System.out.println("\t 15) Modificar costeProduccion");
            System.out.println("\t 16) Modificar costePreparacion");
            System.out.println("\t 17) Modificar descripcion");
            System.out.println("\t 20) Salir");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String opcion = null;
            try {
                opcion = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (opcion) {
                case "1":
                    tarea = new Tarea();
                    break;
                case "2":
                    reader = new BufferedReader(new InputStreamReader(System.in));
                    String valores = null;
                    try {
                        valores = reader.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String[] resultado = valores.split(" ");
                    // 1 t1 1 2 3 4 gg
                    tarea = new Tarea(Integer.parseInt(resultado[0]), resultado[1], Integer.parseInt(resultado[2]), Integer.parseInt(resultado[3]), Integer.parseInt(resultado[4]), Integer.parseInt(resultado[5]), resultado[6]);
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
                        tarea.modificarId(Integer.parseInt(reader.readLine()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "11":
                    try {
                        tarea.modificarNombre(reader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "12":
                    try {
                        tarea.modificarTiempoProduccion(Integer.parseInt(reader.readLine()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "13":
                    try {
                        tarea.modificarTiempoPreparacion(Integer.parseInt(reader.readLine()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "14":
                    try {
                        tarea.modificarCostePreparacion(Integer.parseInt(reader.readLine()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "15":
                    try {
                        tarea.modificarCosteProduccion(Integer.parseInt(reader.readLine()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "16":
                    try {
                        tarea.modificarCostePreparacion(Integer.parseInt(reader.readLine()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "17":
                    try {
                        tarea.modificarDescripcion(reader.readLine());
                    } catch (IOException e) {
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
    }
}
