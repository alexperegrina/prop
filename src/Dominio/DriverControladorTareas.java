package Dominio;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alex Peregrina Cabrera
 */
public class DriverControladorTareas {

    
    public static class ConjuntoOpciones {
    
        //I/O
        
        /*private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        private String letras;
        
        String linea;
        String[] line;*/
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
        

        // --- Gestion Tarea  

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

        public void anadirTareaNombre(String line[]) {
            try {
                this.nombre = line[1];
                this.controladorTareas.anadirTareaNombre(this.nombre);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }           
        }

        public void anadirTareaTiempo(String line[]) {            
            try {
                this.tiempoProduccion = Double.parseDouble(line[1]);
                this.tiempoPreparacion = Double.parseDouble(line[2]);
                this.controladorTareas.anadirTareaTiempo(this.tiempoProduccion, this.tiempoPreparacion);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } 
        }

        public void anadirTareaCoste(String line[]) {
            try {
                this.costeProduccion = Double.parseDouble(line[1]);
                this.costePreparacion = Double.parseDouble(line[2]);
                this.controladorTareas.anadirTareaCoste(this.costeProduccion, this.costePreparacion);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }  
        }

        private void mostrarTarea(Tarea t) {
            System.out.println("\tTareaID: "+t.id());
            System.out.println("\tNombre: "+t.nombre());
            System.out.println("\tTiempo de Produccion: " + t.tiempoProduccion());
            System.out.println("\tTiempo de Preparacion: " + t.tiempoPreparacion());
            System.out.println("\tCoste de Produccion: " + t.costeProduccion());
            System.out.println("\tCoste de Preparacion: " + t.costePreparacion());
            System.out.println("\tDescripcion: " + t.descripcion());
            System.out.println("\t  -----  ");
        }

        public void consultarIdTareas() {
            ArrayList<Integer> t = new ArrayList<>(this.controladorTareas.consultarIdTareas());
            System.out.print("\t");
            for(int i = 0; i < t.size(); ++i) {
                System.out.print(t.get(i) + " ");
            }
            System.out.print("\n");
        }

        public void consultarTareas() {
            ArrayList<Tarea> t = new ArrayList<>(this.controladorTareas.consultarTareas());
            for(int i = 0; i < t.size(); ++i) {
                mostrarTarea(t.get(i));
            }
        }

        public void consultarTarea(String line[]) { 
            try {
                this.id = Integer.parseInt(line[1]);
                Tarea t = controladorTareas.tarea(this.id);
                mostrarTarea(t);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public void modificarTareaCompleta(String line[]) {
            try {
                this.nombre = line[1];
                this.tiempoProduccion = Double.parseDouble(line[2]);
                this.tiempoPreparacion = Double.parseDouble(line[3]);
                this.costeProduccion = Double.parseDouble(line[4]);
                this.costePreparacion = Double.parseDouble(line[5]);
                this.descripcion = line[6];
                controladorTareas.modificarTarea(this.id,this.nombre,this.tiempoProduccion,this.tiempoPreparacion,
                        this.costeProduccion,this.costePreparacion,this.descripcion);
            } catch (Exception e) {
                System.out.println(e.getMessage());   
            }
        }

        public void modificarTareaNombre(String line[]) {
            try {
                this.id = Integer.parseInt(line[1]);
                this.nombre = line[2];
                controladorTareas.modificarTareaNombre(this.id, this.nombre);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }

        public void modificarTareaTiempo(String line[]) {
            try {
                this.id = Integer.parseInt(line[1]);
                this.tiempoProduccion = Double.parseDouble(line[2]);
                this.tiempoPreparacion = Double.parseDouble(line[3]);
                this.controladorTareas.modificarTareaTiempo(this.id, this.tiempoProduccion, this.tiempoPreparacion);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public void modificarTareaCoste(String line[]) {
            try {
                this.id = Integer.parseInt(line[1]);
                this.costeProduccion = Double.parseDouble(line[2]);
                this.costePreparacion = Double.parseDouble(line[3]);
                this.controladorTareas.modificarTareaCoste(this.id, this.costeProduccion, this.costePreparacion);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        public void modificarTareaDescripcion(String line[]) {
            try {
                this.id = Integer.parseInt(line[1]);
                this.descripcion = line[2];
                controladorTareas.modificarTareaDescripcion(this.id, this.descripcion);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public void eliminarTarea(String line[]) {      
            try {
                this.id = Integer.parseInt(line[1]);
                controladorTareas.eliminarTarea(this.id);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        public void existeTarea(String line[]) {
            try {
                this.id = Integer.parseInt(line[1]);
                System.out.println(""+controladorTareas.existeTarea(this.id));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            
        }
        // ---Fin Gestion Tarea



        // ---Gestion Relacion
        private void mostrarRelacion(Relacion r) {
            System.out.println("\tRelacionID: "+r.id());
            System.out.println("\tNombre: "+r.nombre());
            System.out.println("\tSimilitud: " + r.similitud());
            System.out.println("\tTarea1: " + r.tarea1());
            System.out.println("\tTarea2: " + r.tarea2());
            if(r instanceof RelacionCoste || r instanceof RelacionTiempo)
                System.out.println("\tTipo: " + r.tipo());
            System.out.println("\t  -----  ");
        }
        
        public void anadirRelacion4Parametros(String line[]) {
            try {
                Boolean tipo;
                this.tarea1 = Integer.parseInt(line[1]);
                this.tarea2 = Integer.parseInt(line[2]);
                this.similitud = Double.parseDouble(line[3]);
                this.numeros = Integer.parseInt(line[4]);
                if(this.numeros == 1) 
                    tipo = Boolean.TRUE;
                else 
                    tipo = Boolean.FALSE;
                this.controladorTareas.anadirRelacion(this.tarea1, this.tarea2, this.similitud, tipo);
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
                if(this.numeros == 1) 
                    tipo = Boolean.TRUE;
                else 
                    tipo = Boolean.FALSE;

                this.nombre = line[5];
                this.controladorTareas.anadirRelacion(this.tarea1, this.tarea2, this.similitud, tipo, this.nombre);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public void modificarRelacionCompleta(String line[]) {
            try {
                Boolean tipo;
                this.tarea1 = Integer.parseInt(line[1]);
                this.tarea2 = Integer.parseInt(line[2]);
                this.similitud = Double.parseDouble(line[3]);
                this.numeros = Integer.parseInt(line[4]);
                if(this.numeros == 1) 
                    tipo = Boolean.TRUE;
                else 
                    tipo = Boolean.FALSE;

                this.nombre = line[5];
                controladorTareas.modificarRelacion(this.tarea1, this.tarea2, this.similitud, tipo, this.nombre);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        public void modificarRelacionSimilitud(String line[]) {
            try {
                Boolean tipo;
                this.tarea1 = Integer.parseInt(line[1]);
                this.tarea2 = Integer.parseInt(line[2]);
                this.similitud = Double.parseDouble(line[3]);
                this.numeros = Integer.parseInt(line[4]);
                if(this.numeros == 1) 
                    tipo = Boolean.TRUE;
                else 
                    tipo = Boolean.FALSE;
                controladorTareas.modificarRelacion(this.tarea1, this.tarea2, this.similitud, tipo);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        public void modificarRelacionNombre(String line[]) {
            try {
                Boolean tipo;
                this.tarea1 = Integer.parseInt(line[1]);
                this.tarea2 = Integer.parseInt(line[2]);
                this.numeros = Integer.parseInt(line[3]);
                if(this.numeros == 1) 
                    tipo = Boolean.TRUE;
                else 
                    tipo = Boolean.FALSE;

                this.nombre = line[4];
                controladorTareas.modificarRelacion(this.tarea1, this.tarea2,tipo, this.nombre);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        public void consultarRelaciones() {
            ArrayList<Relacion> t = new ArrayList<>(this.controladorTareas.consultarRelaciones());
            for(int i = 0; i < t.size(); ++i) {
                mostrarRelacion(t.get(i));
            }
        }
        
        public void consultarIdRelaciones() {
            ArrayList<Integer> t = new ArrayList<>(this.controladorTareas.consultarIdRelaciones());
            System.out.print("\t");
            for(int i = 0; i < t.size(); ++i) {
                System.out.print(t.get(i) + " ");
            }
            System.out.print("\n");
        }
        
        public void eliminarRelacion(String line[]) {
            try {
                Boolean tipo;
                this.tarea1 = Integer.parseInt(line[1]);
                this.tarea2 = Integer.parseInt(line[2]);
                this.numeros = Integer.parseInt(line[3]);
                if(this.numeros == 1) 
                    tipo = Boolean.TRUE;
                else 
                    tipo = Boolean.FALSE;

                controladorTareas.eliminarRelacion(this.tarea1, this.tarea2,tipo);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        public void consultarRelacion(String line[]) {
            try {
                Boolean tipo;
                this.tarea1 = Integer.parseInt(line[1]);
                this.tarea2 = Integer.parseInt(line[2]);
                this.numeros = Integer.parseInt(line[3]);
                if(this.numeros == 1) 
                    tipo = Boolean.TRUE;
                else 
                    tipo = Boolean.FALSE;

                Relacion r = controladorTareas.relacion(this.tarea1, this.tarea2, tipo);
                mostrarRelacion(r);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        public void existeRelacionId(String line[]) {
            this.id = Integer.parseInt(line[1]);
            System.out.println(""+controladorTareas.existeRelacion(this.id));
        }
        
        public void existeRelacion(String line[]) {
            Boolean tipo;
            this.tarea1 = Integer.parseInt(line[1]);
            this.tarea2 = Integer.parseInt(line[2]);
            this.numeros = Integer.parseInt(line[3]);
            if(this.numeros == 1) 
                tipo = Boolean.TRUE;
            else 
                tipo = Boolean.FALSE;
            
            try {
                System.out.println(""+controladorTareas.existeRelacion(this.tarea1, this.tarea2, tipo));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        
        public void cargarTareas() {
            try {
                this.controladorTareas.cargarDatosTareas();
            } catch (IOException ex) {
                Logger.getLogger(DriverControladorTareas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        public void guardarTareas() {
            try {
                this.controladorTareas.guardarDatosTareas();
            } catch (IOException ex) {
                Logger.getLogger(DriverControladorTareas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    // ---Fin Gestion Relacion
    
    //STATICAS
    private static String[] sacarStrings(String str) {
        List<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(str);
        while (m.find()) {
            list.add(m.group(1).replace("\"", ""));
        }
        String[] ret = new String[list.size()];
        return list.toArray(ret);
    }
    
    public static void menu() {
        System.out.println("Escoge una opción:");
        System.out.println("\t 01) ControladorTareas().");
        System.out.println("\t 02) void anadirTarea(String nombre, Double tiempoProduccion,"
                + "Double tiempoPreparacion, Double costeProduccion, Double costePreparacion, String descripcion.");
        System.out.println("\t 03) void anadirTareaTiempo(Double tiempoProduccion, Double tiempoPreparacion).");
        System.out.println("\t 04) void anadirTareaCoste(Double costeProduccion, Double costePreparacion).");                
        System.out.println("\t 05) void anadirTareaNombre(String nombre).");
        System.out.println("\t 06) Collection<Tarea> consultarTareas().");
        System.out.println("\t 07) Set<Integer> consultarIdTareas().");
        System.out.println("\t 08) Tarea tarea(Integer idTarea).");
        System.out.println("\t 09) void modificarTarea(Integer idTarea,String nombre, Double tiempoProduccion,"
                + " Double tiempoPreparacion, Double costeProduccion, Double costePreparacion, String descripcion).");
        System.out.println("\t 10) void modificarTareaTiempo(Integer idTarea,Double tiempoProduccion, Double tiempoPreparacion).");
        System.out.println("\t 11) void modificarTareaCoste(Integer idTarea, Double costeProduccion, Double costePreparacion).");
        System.out.println("\t 12) void modificarTareaNombre(Integer idTarea,String nombre).");
        System.out.println("\t 13) void modificarTareaDescripcion(Integer idTarea,String descripcion).");
        System.out.println("\t 14) void eliminarTarea(Integer idTarea).");
        System.out.println("\t 15) boolean existeTarea(Integer idTarea).");
        System.out.println("\t 16) Collection<Relacion> consultarRelaciones().");
        System.out.println("\t 17) Set<Integer> consultarIdRelaciones().");
        System.out.println("\t 18) void anadirRelacion(Integer idTarea1, Integer idTarea2, "
                + "Double similitud, Boolean tipo, String nombre).");
        System.out.println("\t 19) void anadirRelacion(Integer idTarea1, Integer idTarea2, Double similitud, Boolean tipo).");
        System.out.println("\t 20) void modificarRelacion(Integer idTarea1, Integer idTarea2, Double similitud, Boolean tipo, String nombre).");
        System.out.println("\t 21) void modificarRelacion(Integer idTarea1, Integer idTarea2, Double similitud,Boolean tipo).");
        System.out.println("\t 22) void modificarRelacion(Integer idTarea1, Integer idTarea2, Boolean tipo, String nombre).");     
        System.out.println("\t 23) void eliminarRelacion(Integer idTarea1, Integer idTarea2, Boolean tipo).");
        System.out.println("\t 24) Relacion relacion(Integer idTarea1, Integer idTarea2, Boolean tipo).");
        System.out.println("\t 25) Boolean existeRelacion(Integer idRel).");
        System.out.println("\t 26) Boolean existeRelacion(Integer idTarea1, Integer idTarea2, Boolean tipo).");
        System.out.println("\t 27) void cargarTareas() ");
        System.out.println("\t 28) void guardarTareas()");
        System.out.println("\t 0) Salir");
    }
    
    public static void main(String[] args) {
        String nombreClase = "ConjuntoTareas";
        System.out.println("Driver "+nombreClase);
        String nombreEntrada = "in" + "Driver" + nombreClase + ".txt";

        String path = new File("").getAbsolutePath();
        path = path.concat("\\ClasesSeparadas\\");
        path = path.concat(nombreClase).concat("\\" + nombreEntrada);
        
        String linea;
        String[] line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        ConjuntoOpciones conjuntoOpciones = new ConjuntoOpciones();
        
        try {          
            Grafo<Integer,Double> G = new Grafo();

            boolean salir = false;
            boolean lista = true;
            while (!salir) {
                try {
                    menu();
                    linea = reader.readLine();
                    line = linea.split(" ");
                    line = sacarStrings(linea);
                    switch (line[0]) {
                        case "1":
                            conjuntoOpciones.crearControlador();
                            break;
                        case "2":
                            conjuntoOpciones.anadirTareaCompleta(line);
                            break;
                        case "3":
                            conjuntoOpciones.anadirTareaTiempo(line);
                            break;
                        case "4":
                            conjuntoOpciones.anadirTareaCoste(line);
                            break;    
                        case "5":
                            conjuntoOpciones.anadirTareaNombre(line);
                            break;
                        case "6":
                            conjuntoOpciones.consultarTareas();
                            break;    
                        case "7":
                            conjuntoOpciones.consultarIdTareas();
                            break;                            
                        case "8":
                            conjuntoOpciones.consultarTarea(line);
                            break;
                        case "9":
                            conjuntoOpciones.modificarTareaCompleta(line);
                            break;
                        case "10":
                            conjuntoOpciones.modificarTareaTiempo(line);
                            break;      
                        case "11":
                            conjuntoOpciones.modificarTareaCoste(line);
                            break;   
                        case "12":
                            conjuntoOpciones.modificarTareaNombre(line);
                            break;    
                        case "13":
                            conjuntoOpciones.modificarTareaNombre(line);
                            break;     
                        case "14":
                            conjuntoOpciones.eliminarTarea(line);
                            break;    
                        case "15":
                            conjuntoOpciones.existeTarea(line);
                            break;     
                        case "16":
                            conjuntoOpciones.consultarRelaciones();
                        case "17":
                            conjuntoOpciones.consultarIdRelaciones();
                            break;
                        case "18":
                            conjuntoOpciones.anadirRelacionCompleta(line);
                            break;
                        case "19":
                            conjuntoOpciones.anadirRelacion4Parametros(line);
                            break;
                        case "20":
                            conjuntoOpciones.modificarRelacionCompleta(line);
                            break;
                        case "21":
                            conjuntoOpciones.modificarRelacionSimilitud(line);
                            break;          
                        case "22":
                            conjuntoOpciones.modificarRelacionNombre(line);
                            break;
                        case "23":
                            conjuntoOpciones.eliminarRelacion(line);
                            break;
                        case "24":
                            conjuntoOpciones.consultarRelacion(line);
                            break;        
                        case "25":
                            conjuntoOpciones.existeRelacionId(line);
                            break;                            
                        case "26":
                            conjuntoOpciones.existeRelacion(line);
                            break;  
                        case "27":
                            conjuntoOpciones.cargarTareas();
                            break;
                        case "28":
                            conjuntoOpciones.guardarTareas();
                            break;
                        case "0":
                            salir = true;
                            break;
                        default:
                            System.out.println("La opción elegida no existe!");
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
