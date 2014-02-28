
import java.util.*;
//import javax.swing.text.StyledEditorKit;

/**
 *
 * @author Alex Peregrina Cabrera
 */
public class ControladorTareas {
    
    private static String msg_tarea_nula = "Error en la tarea: Tarea nula";
    private static String msg_idtarea_no_valid = "Error en el id de la tarea: Id no valido.";
    private static String msg_idTarea1_no_valid = "Error en el id de la primera tarea: Id no valido.";
    private static String msg_idTarea2_no_valid = "Error en el id de la segunda tarea: Id no valido.";
    private static String msg_A_rel_A_no_valid = "Error, una relacion no puede relacionarse con ella misma";
    private static String msg_similitud_mayor_0 = "Error en la similitud: similitud > 0.";
    private static String msg_relacion_nula = "Error en la relacion: relacion nula";
    private static String msg_idRel_no_valid = "Error en el id de la relacion: Id no valido.";
    private static String msg_A_rel_B_no_exists = "Error en la relacion: La relacion idTarea1 <--> idTarea2, no existe.";
    
    //atributos para Tareas
    private HashMap<Integer,Tarea>  CjTareas;
    private Integer contIdTarea;
    
    
    /**
     * Solo hay un conjunto de relaciones  que puedes ser de coste o de tiempo
     * la indexacion para la optimizacion se hace con tareaRelacion
     */
    
    //atributos para Relaciones
    private HashMap<Integer,Relacion>  CjRelaciones;
    private Integer contIdRel;
    
    private HashMap<Integer,ArrayList<Integer>>  tareaRelacionCoste;
    private HashMap<Integer,ArrayList<Integer>>  tareaRelacionTiempo;
    
    /**
     * Constructor por defecto. Crea un ControladorTareas con los identificadores unicos a 0.
     */
    public ControladorTareas() {
        this.contIdTarea = 0;
        this.contIdRel = 0;
        CjTareas = new HashMap<Integer,Tarea>();
        CjRelaciones = new HashMap<Integer,Relacion>();
        tareaRelacionCoste = new HashMap<Integer,ArrayList<Integer>>();
        tareaRelacionTiempo = new HashMap<Integer,ArrayList<Integer>>();
    }
    // ------------ Gestion Tarea ------------ //
    
    /**
     * Añade una tare al conjunto, y se le assigna un idetificador unico en el conjunto tareas.
     * @param tarea Tarea a añadir en el conjunto.
     * @return Si se inserta la tarea en el conjunto devuelve el identificador asignado.
     * @throw NullPointerException.
     */
    private Integer anadirTarea(Tarea tarea) {
        if(tarea == null) 
            throw new NullPointerException(msg_tarea_nula);
        tarea.modificarId(this.contIdTarea);
        this.CjTareas.put(this.contIdTarea, tarea);
        this.tareaRelacionCoste.put(this.contIdTarea,new ArrayList<Integer>());
        this.tareaRelacionTiempo.put(this.contIdTarea,new ArrayList<Integer>());
        ++contIdTarea;
        return tarea.id();
    }
    
    /**
     * Añade una tare al conjunto.
     * @param nombre
     * @param tiempoProduccion
     * @param tiempoPreparacion
     * @param costeProduccion
     * @param costePreparacion
     * @param descripcion 
     */
    public void anadirTarea(String nombre, Double tiempoProduccion, 
            Double tiempoPreparacion, Double costeProduccion, Double costePreparacion, String descripcion) {
        Tarea t = new Tarea();
        t.modificarNombre(nombre);
        t.modificarTiempoProduccion(tiempoProduccion);
        t.modificarTiempoPreparacion(tiempoPreparacion);
        t.modificarCosteProduccion(costeProduccion);
        t.modificarCostePreparacion(costePreparacion);
        t.modificarDescripcion(descripcion);
        
        this.anadirTarea(t);
    }
    
    /**
     * Añade una tare al conjunto.
     * @param tiempoProduccion
     * @param tiempoPreparacion
     */
    public void anadirTareaTiempo(Double tiempoProduccion, 
            Double tiempoPreparacion) {
        Tarea t = new Tarea();
        t.modificarTiempoProduccion(tiempoProduccion);
        t.modificarTiempoPreparacion(tiempoPreparacion);
        
        this.anadirTarea(t);
    }
    
    /**
     * Añade una tare al conjunto.
     * @param costeProduccion
     * @param costePreparacion
     */
    public void anadirTareaCoste(Double costeProduccion, Double costePreparacion) {
        Tarea t = new Tarea();
        t.modificarCosteProduccion(costeProduccion);
        t.modificarCostePreparacion(costePreparacion);
        
        this.anadirTarea(t);
    }
    
    /**
     * Añade una tarea al conjunto.
     * @param nombre
     */
    public void anadirTareaNombre(String nombre) {
        Tarea t = new Tarea();
        t.modificarNombre(nombre);
        
        this.anadirTarea(t);
    }

    /**
     * Metodo para consultar las tareas que hay en el conjunto.
     * @return Devuelve una lista de tareas.
     */
    public Collection<Tarea> consultarTareas() {
        return this.CjTareas.values();
    }
    
    /**
     * Metodo para consultar los identificadores de todas las tareas que hay en el conjunto.
     * @return Devuelve una conjunto identificadores de tareas.
     */
    public Set<Integer> consultarIdTareas() {
    	return this.CjTareas.keySet();
    }

    /**
     * Devuelve la tarea con identificador=id. El identificador debe cumplir: id>=0 y id este en conjunto.
     * @param idTarea El identificador de la tarea que va a ser consultada.
     * @return La tarea solicitada.
     */  
    public Tarea tarea(Integer idTarea) { 
        if(!this.existeTarea(idTarea))
            throw new IllegalArgumentException(msg_idtarea_no_valid);
        return this.CjTareas.get(idTarea);
    }

    /**
     * Modifica una Tarea del grafo que tenga como id el id de la tarea.
     * El identificador debe cumplir: tarea inicialicada, id>=0 y id valido en el conjunto.
     * @param tarea La tarea que sustituirá la original.
     * @throws IllegalArgumentException.
     */ 
    private void modificarTarea(Tarea tarea) {
        if(tarea == null) throw new NullPointerException(msg_tarea_nula);
        if(!this.existeTarea(tarea.id()))
            throw new IllegalArgumentException(msg_idtarea_no_valid);
        this.CjTareas.put(tarea.id(), tarea);
        
    }
    
    /**
     * Modifica una Tarea del grafo que tenga como id el id de la tarea.
     * El identificador debe cumplir: tarea inicialicada, id>=0 y id valido en el conjunto. 
     * @param idTarea Identificador de la tarea a modificar
     * @param nombre
     * @param tiempoProduccion
     * @param tiempoPreparacion
     * @param costeProduccion
     * @param costePreparacion
     * @param descripcion 
     */
    public void modificarTarea(Integer idTarea,String nombre, Double tiempoProduccion, 
            Double tiempoPreparacion, Double costeProduccion, Double costePreparacion, String descripcion) {
        if(!this.existeTarea(idTarea))
            throw new IllegalArgumentException(msg_idtarea_no_valid);
        Tarea t = this.tarea(idTarea);
        t.modificarNombre(nombre);
        t.modificarTiempoProduccion(tiempoProduccion);
        t.modificarTiempoPreparacion(tiempoPreparacion);
        t.modificarCosteProduccion(costeProduccion);
        t.modificarCostePreparacion(costePreparacion);
        t.modificarDescripcion(descripcion);
        this.modificarTarea(t);
    }
    
    /**
     * Modifica el tiempo de una Tarea del grafo que tenga como id el id de la tarea.
     * El identificador debe cumplir: tarea inicialicada, id>=0 y id valido en el conjunto. 
     * @param idTarea Identificador de la tarea a modificar
     * @param tiempoProduccion
     * @param tiempoPreparacion
     */
    public void modificarTareaTiempo(Integer idTarea,Double tiempoProduccion, Double tiempoPreparacion) {
        if(!this.existeTarea(idTarea))
            throw new IllegalArgumentException(msg_idtarea_no_valid);
        Tarea t = this.tarea(idTarea);
        t.modificarTiempoProduccion(tiempoProduccion);
        t.modificarTiempoPreparacion(tiempoPreparacion);
        this.modificarTarea(t);
    }
    
    /**
     * Modifica el coste de una Tarea del grafo que tenga como id el id de la tarea.
     * El identificador debe cumplir: tarea inicialicada, id>=0 y id valido en el conjunto. 
     * @param idTarea Identificador de la tarea a modificar
     * @param costeProduccion
     * @param costePreparacion
     */
    public void modificarTareaCoste(Integer idTarea, Double costeProduccion, Double costePreparacion) {
        if(!this.existeTarea(idTarea))
            throw new IllegalArgumentException(msg_idtarea_no_valid);
        Tarea t = this.tarea(idTarea);
        t.modificarCosteProduccion(costeProduccion);
        t.modificarCostePreparacion(costePreparacion);
        this.modificarTarea(t);
    }
    
    /**
     * Modifica el nombre de una Tarea del grafo que tenga como id el id de la tarea.
     * El identificador debe cumplir: tarea inicialicada, id>=0 y id valido en el conjunto. 
     * @param idTarea Identificador de la tarea a modificar
     * @param nombre
     */
    public void modificarTareaNombre(Integer idTarea,String nombre) {
        if(!this.existeTarea(idTarea))
            throw new IllegalArgumentException(msg_idtarea_no_valid);
        Tarea t = this.tarea(idTarea);
        t.modificarNombre(nombre);
        this.modificarTarea(t);
    }
    
    /**
     * Modifica la descripcion de una Tarea del grafo que tenga como id el id de la tarea.
     * El identificador debe cumplir: tarea inicialicada, id>=0 y id valido en el conjunto. 
     * @param idTarea Identificador de la tarea a modificar
     * @param descripcion 
     */
    public void modificarTareaDescripcion(Integer idTarea,String descripcion) {
        if(!this.existeTarea(idTarea))
            throw new IllegalArgumentException(msg_idtarea_no_valid);
        Tarea t = this.tarea(idTarea);
        t.modificarDescripcion(descripcion);
        this.modificarTarea(t);
    }
    
    
    
    /**
     * Elimina una Tarea del grafo que tenga como id = idTarea.
     * El identificador debe cumplir: id>=0 y id valido en el conjunto.
     * @param idTarea El identificador de la tarea que va a ser elliminada.
     * @throws IllegalArgumentException
     */ 
    public void eliminarTarea(Integer idTarea) {
        if(!this.existeTarea(idTarea))
            throw new IllegalArgumentException(msg_idtarea_no_valid);
        
        
        /*
        Codigo para eliminar todas las relaciones que tenga como miembro la tarea sera eliminada la relacion
        */
        for(int i = 0; i < this.tareaRelacionCoste.get(idTarea).size(); ++i) {
            Relacion r = this.relacion(this.tareaRelacionCoste.get(idTarea).get(i));
            this.eliminarRelacion(r.tarea1(), r.tarea2(), r.tipo());
        }
        for(int i = 0; i < this.tareaRelacionCoste.get(idTarea).size(); ++i) {
            Relacion r = this.relacion(this.tareaRelacionTiempo.get(idTarea).get(i));
            this.eliminarRelacion(r.tarea1(), r.tarea2(), r.tipo());
        }
        this.tareaRelacionTiempo.remove(idTarea);
        this.tareaRelacionCoste.remove(idTarea);
        this.CjTareas.remove(idTarea);
    }
    
    /**
     * Devuelve cierto si la tarea está presente en el conjunto. Falso en caso contrario.
     * @param idTarea Identificador de la tarea a consultar.
     * @return Cierto si la tarea está presente en el conjunto. Falso en caso contrario.
     */  
    public boolean existeTarea(Integer idTarea) {
        return this.CjTareas.containsKey(idTarea);
    }
    
    // ------------ Fi Gestion Tarea ------------ //
    

    // ------------ Gestion Relacion ------------ //
    
    /**
     * Metodo para consultar las relaciones que hay en el conjunto.
     * @return Devuelve una lista de relaciones.
     */
    public Collection<Relacion> consultarRelaciones() {
        return this.CjRelaciones.values();
    }
    
    /**
     * Metodo para consultar los identificadores de todas las relaciones que hay en el conjunto.
     * @return Devuelve una conjunto identificadores de relaciones.
     */
    public Set<Integer> consultarIdRelaciones() {
    	return this.CjRelaciones.keySet();
    }
    
    /**
     * Relaciona dos tareas agregando una similitud entre ellas en el proceso de manufacturacion,
     * que reducira el coste o el tiempo en el procesado.
     * Una tarea no puede relacionarse con si misma ya que se supone que la similitud sera 100%
     * @param idTarea1 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param idTarea2 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param similitud porcentaje del la similitud entre dos tareas: similitud > 0
     * @param tipo Si tipo == True --> tipo = coste, Si tipo == False --> tipo = tiempo
     * @param nombre 
     * @throws IllegalArgumentException
     */
    public void anadirRelacion(Integer idTarea1, Integer idTarea2, Double similitud, Boolean tipo, String nombre) {
        if(!this.existeTarea(idTarea1))
            throw new IllegalArgumentException(msg_idTarea1_no_valid);
        if(!this.existeTarea(idTarea2))
            throw new IllegalArgumentException(msg_idTarea2_no_valid);
        if(idTarea1 == idTarea2)
            throw new IllegalArgumentException(msg_A_rel_A_no_valid);
        if(similitud < 0)
            throw new IllegalArgumentException(msg_similitud_mayor_0);

        Integer id;
        Relacion r;
        
        if(tipo) 
            r = new RelacionCoste(nombre, idTarea1,idTarea2, similitud);
        else
            r = new RelacionTiempo(nombre, idTarea1,idTarea2, similitud);    
        
        id = this.anadirRelacion(r);
        this.unirTareasRelacion(idTarea1, idTarea2, id, tipo);
    }
    
    /**
     * Relaciona dos tareas agregando una similitud entre ellas en el proceso de manufacturacion,
     * que reducira el coste o el tiempo en el procesado.
     * Una tarea no puede relacionarse con si misma ya que se supone que la similitud sera 100%
     * @param idTarea1 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param idTarea2 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param similitud porcentaje del la similitud entre dos tareas: similitud > 0
     * @param tipo Si tipo == True --> tipo = coste, Si tipo == False --> tipo = tiempo
     * @throws IllegalArgumentException
     */
    public void anadirRelacion(Integer idTarea1, Integer idTarea2, Double similitud, Boolean tipo) {
        if(!this.existeTarea(idTarea1))
            throw new IllegalArgumentException(msg_idTarea1_no_valid);
        if(!this.existeTarea(idTarea2))
            throw new IllegalArgumentException(msg_idTarea2_no_valid);
        if(idTarea1 == idTarea2)
            throw new IllegalArgumentException(msg_A_rel_A_no_valid);
        if(similitud < 0)
            throw new IllegalArgumentException(msg_similitud_mayor_0);
 
        Integer id;
        Relacion r;
        
        if(tipo) 
            r = new RelacionCoste(idTarea1,idTarea2, similitud);
        else
            r = new RelacionTiempo(idTarea1,idTarea2, similitud);    
        
        id = this.anadirRelacion(r);
        this.unirTareasRelacion(idTarea1, idTarea2, id, tipo);   
    }

    /**
     * Añade una relacion al conjunto, y se le assigna un idetificador unico en el conjunto relaciones.
     * @param relacion Relacion a añadir en el conjunto.
     * @return Si se inserta la relacion en el conjunto devuelve el identificador asignado.
     * @throw NullPointerException.
     */
    private Integer anadirRelacion(Relacion relacion) {
        if(relacion == null) throw new NullPointerException(msg_relacion_nula);
        relacion.modificarId(this.contIdRel);
        this.CjRelaciones.put(this.contIdRel, relacion);
        ++contIdRel;
        return relacion.id();
    }
    

    /**
     * Modifica una Relacion del grafo que tenga como identificador el id de la tarea.
     * La relacion debe cumplir: relacion inicialicada y que la relacion exista previamente.
     * @param relacion La relacion que sustituirá la original.
     * @throws IllegalArgumentException.
     */
    private void modificarRelacion(Relacion relacion) {
        if(relacion == null) 
            throw new NullPointerException(msg_relacion_nula);
        if(!this.existeRelacion(relacion.id()))
            throw new IllegalArgumentException(msg_idRel_no_valid);
        this.CjRelaciones.put(relacion.id(), relacion);
        
    }
    
    /*private void mostrarRelacion(Relacion r) {
            System.out.println("\tRelacionID: "+r.id());
            System.out.println("\tNombre: "+r.nombre());
            System.out.println("\tSimilitud: " + r.similitud());
            System.out.println("\tTarea1: " + r.tarea1());
            System.out.println("\tTarea2: " + r.tarea2());
            if(r instanceof RelacionCoste || r instanceof RelacionTiempo)
                System.out.println("\tTipo: " + r.tipo());
            System.out.println("\t  -----  ");
        }*/
    
    /**
     * Modifica la relacion que tenga como tareas relacionada (tarea1, tarea2), modifica la similitud.
     * @param idTarea1 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param idTarea2 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param similitud porcentaje del la similitud entre dos tareas: similitud > 0
     * @param tipo Si tipo == True --> tipo = coste, Si tipo == False --> tipo = tiempo
     * @throws IllegalArgumentException
     */
    public void modificarRelacion(Integer idTarea1, Integer idTarea2, Double similitud,Boolean tipo) {
        if(!this.existeTarea(idTarea1))
            throw new IllegalArgumentException(msg_idTarea1_no_valid);
        if(!this.existeTarea(idTarea2))
            throw new IllegalArgumentException(msg_idTarea2_no_valid);
        if(!this.existeRelacion(idTarea1, idTarea2, tipo))
            throw new IllegalArgumentException(msg_A_rel_B_no_exists);
        if(similitud < 0)
            throw new IllegalArgumentException(msg_similitud_mayor_0);
        
        Relacion r = this.relacion(idTarea1, idTarea2, tipo);
        r.modificarSimilitud(similitud);
        this.modificarRelacion(r);
    }
    
    /**
     * Modifica la relacion que tenga como tareas relacionada (tarea1, tarea2), modifica el nombre.
     * @param idTarea1 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param idTarea2 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param tipo Si tipo == True --> tipo = coste, Si tipo == False --> tipo = tiempo
     * @param nombre  cadena de caracteres que se agrega a una relacion
     * @throws IllegalArgumentException
     */
    public void modificarRelacion(Integer idTarea1, Integer idTarea2, Boolean tipo, String nombre) {
        if(!this.existeTarea(idTarea1))
            throw new IllegalArgumentException(msg_idTarea1_no_valid);
        if(!this.existeTarea(idTarea2))
            throw new IllegalArgumentException(msg_idTarea2_no_valid);
        if(!this.existeRelacion(idTarea1, idTarea2, tipo))
            throw new IllegalArgumentException(msg_A_rel_B_no_exists);
        
        Relacion r = this.relacion(idTarea1, idTarea2, tipo);
        r.modificarNombre(nombre);
        this.modificarRelacion(r);
    }
    
    /**
     * Modifica la relacion que tenga como tareas relacionada (tarea1, tarea2), modifica el nombre.
     * @param idTarea1 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param idTarea2 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param similitud porcentaje del la similitud entre dos tareas: similitud > 0
     * @param tipo Si tipo == True --> tipo = coste, Si tipo == False --> tipo = tiempo
     * @param nombre  cadena de caracteres que se agrega a una relacion
     * @throws IllegalArgumentException
     */
    public void modificarRelacion(Integer idTarea1, Integer idTarea2, Double similitud, Boolean tipo, String nombre) {
        if(!this.existeTarea(idTarea1))
            throw new IllegalArgumentException(msg_idTarea1_no_valid);
        if(!this.existeTarea(idTarea2))
            throw new IllegalArgumentException(msg_idTarea2_no_valid);
        if(!this.existeRelacion(idTarea1, idTarea2,tipo)) 
            throw new IllegalArgumentException(msg_A_rel_B_no_exists);
        if(similitud < 0)
            throw new IllegalArgumentException(msg_similitud_mayor_0);
        
        Relacion r = this.relacion(idTarea1, idTarea2, tipo);
        r.modificarSimilitud(similitud);
        r.modificarNombre(nombre);
        
        this.modificarRelacion(r);
    }

    /**
     * Elimina una Relacion del conjunto que tenga como id = idRelacion.
     * El identificador debe cumplir: id>=0 y id< contIdRel(contador Interno).
     * @param idRelacion El identificador de la relacion que va a ser elliminada.
     * @throws IllegalArgumentException
     */ 
    private void eliminarRelacion(Integer idRelacion) {    
         if(!this.existeRelacion(idRelacion))
            throw new IllegalArgumentException(msg_idRel_no_valid);
         this.CjRelaciones.remove(idRelacion);
    }
    
    /**
     * Elimina la relacion que tenga como tareas relacionada (tarea1, tarea2) 
     * @param idTarea1 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param idTarea2 identificador de la tarea a relacionar: idtarea1 > 0 y idtarea valido en el conjunto
     * @param tipo Si tipo == True --> tipo = coste, Si tipo == False --> tipo = tiempo
     * @throws IllegalArgumentException
     */
    public void eliminarRelacion(Integer idTarea1, Integer idTarea2, Boolean tipo) {
        if(!this.existeTarea(idTarea1))
            throw new IllegalArgumentException(msg_idTarea1_no_valid);
        if(!this.existeTarea(idTarea2))
            throw new IllegalArgumentException(msg_idTarea2_no_valid);
        if(!this.existeRelacion(idTarea1, idTarea2, tipo)) 
            throw new IllegalArgumentException(msg_A_rel_B_no_exists);
        
        Relacion r = this.relacion(idTarea1, idTarea2, tipo);
        this.separarTareasRelacion(idTarea1, idTarea2, r.id(), tipo);
        this.eliminarRelacion(r.id()); 
    }
    
    //post: si la tarea no esta null
    /**
     * Devuelve la relacion con identificador=id. El identificador debe cumplir: id>=0 y id este en conjunto.
     * @param idRelacion El identificador de la relacion que va a ser consultada.
     * @return La relacion solicitada.
     */
    private Relacion relacion(Integer idRelacion) {
        if (!this.existeRelacion(idRelacion)) 
            throw new IllegalArgumentException(msg_idRel_no_valid);

        return CjRelaciones.get(idRelacion);
    }
    
    /**
     * Devuelve la relacion que tiene como relacionados idTarea1 <--> idTarea2,
     * La relacion debe de existir
     * @param idTarea1 identificador de la tarea1
     * @param idTarea2 identificador de la tarea2
     * @param tipo Si tipo == True --> tipo = coste, Si tipo == False --> tipo = tiempo 
     * @return La relacion solicitada
     * @throws IllegalArgumentException
     */
    public Relacion relacion(Integer idTarea1, Integer idTarea2, Boolean tipo) {
        if(!this.existeRelacion(idTarea1, idTarea2, tipo)) 
            throw new IllegalArgumentException(msg_A_rel_B_no_exists);
        
        Relacion r = null;   
        int i = 0;
        if(tipo) {
            while(i < this.tareaRelacionCoste.get(idTarea1).size()) {
                r = relacion(this.tareaRelacionCoste.get(idTarea1).get(i));
                if(r.tarea1() == idTarea2 || r.tarea2() == idTarea2) return r;
                ++i;
            }
        }
        else {
            while(i < this.tareaRelacionTiempo.get(idTarea1).size()) {
                r = relacion(this.tareaRelacionTiempo.get(idTarea1).get(i));
                if(r.tarea1() == idTarea2 || r.tarea2() == idTarea2) return r;
                ++i;
            }
        } 
        return r;
    }
    
    /**
     * Consulta la similitud entre dos tareas, la relacion deve de existir
     * @param idTarea1 identificador de la tarea1
     * @param idTarea2 identificador de la tarea2
     * @param tipo Si tipo == True --> tipo = coste, Si tipo == False --> tipo = tiempo 
     * @return similitud entre las dos tareas
     * @throws IllegalArgumentException
     */
    public Double relacionSimilitud(Integer idTarea1, Integer idTarea2, Boolean tipo) {
        if(!this.existeRelacion(idTarea1, idTarea2, tipo)) 
            throw new IllegalArgumentException(msg_A_rel_B_no_exists);
        return this.relacion(idTarea1, idTarea2, tipo).similitud();
    }
    
    /**
     * Determina si existe una relacion een el conjunto con identiificador = idrel
     * @param idRel identificador de la relacion
     * @param idTarea2 identificador de la tarea2
     * @return Cierto si el grafo contiene un vértice con identificador=id, falso en caso contrario.
     */
    public Boolean existeRelacion(Integer idRel) {
        return CjRelaciones.containsKey(idRel);
    }

    /**
     * Determina si existe una relacion entre idTarea1 y idTarea2
     * @param idTarea1 identificador de la tarea1
     * @param idTarea2 identificador de la tarea2
     * @return Cierto si el grafo contiene un vértice con identificador=id, falso en caso contrario.
     * @throws IllegalArgumentException
     */
    public Boolean existeRelacion(Integer idTarea1, Integer idTarea2, Boolean tipo) {
        if(!this.existeTarea(idTarea1))
            throw new IllegalArgumentException(msg_idTarea1_no_valid);
        if(!this.existeTarea(idTarea2))
            throw new IllegalArgumentException(msg_idTarea2_no_valid);
        
        int i = 0;
        Relacion r;
        if(tipo) {
            while(i < this.tareaRelacionCoste.get(idTarea1).size()) {
                r = relacion(this.tareaRelacionCoste.get(idTarea1).get(i));
                if(r.tarea1() == idTarea2 || r.tarea2() == idTarea2) return true;
                ++i;
            }
        }
        else {
            while(i < this.tareaRelacionTiempo.get(idTarea1).size()) {
                r = relacion(this.tareaRelacionTiempo.get(idTarea1).get(i));
                if(r.tarea1() == idTarea2 || r.tarea2() == idTarea2) return true;
                ++i;
            }
        } 
        return false;
    }
    
    // ------------ Fi Gestion Relacion ------------ //
    
    /**
     * Vincula dos tareas con una relacino.
     * El identificador debe cumplir: idTarea>=0 y idTarea< contIdTarea(contador Interno).
     * El identificador debe cumplir: idRelacion>=0 y idRelacion< contIdRel(contador Interno).
     * @param idTarea1 El identificador de la tarea1 que va a ser vinculada.
     * @param idTarea2 El identificador de la tarea2 que va a ser vinculada.
     * @param idRelacion El identificador de la relacion que va a ser vinculada.
     * @param tipo Si tipo == True --> tipo = coste, Si tipo == False --> tipo = tiempo
     */
    private void unirTareasRelacion(Integer idTarea1, Integer idTarea2, Integer idRelacion, Boolean tipo) {
        if(!existeTarea(idTarea1))
            throw new IllegalArgumentException(msg_idTarea1_no_valid);
        if(!existeTarea(idTarea2))
            throw new IllegalArgumentException(msg_idTarea2_no_valid);
        if(!existeRelacion(idRelacion))
            throw new IllegalArgumentException(msg_idRel_no_valid);
        
        if(tipo) {
            this.tareaRelacionCoste.get(idTarea1).add(idRelacion); 
            this.tareaRelacionCoste.get(idTarea2).add(idRelacion); 
        }
        else {
            this.tareaRelacionTiempo.get(idTarea1).add(idRelacion);
            this.tareaRelacionTiempo.get(idTarea2).add(idRelacion);
        }      

    }
    
    /**
     * Desvincula dos tareas con una relacion.
     * las tareas y la relacion deben de existir
     * @param idTarea1 El identificador de la tarea1 que va a ser desvinculada.
     * @param idTarea2 El identificador de la tarea2 que va a ser desvinculada.
     * @param idRelacion El identificador de la relacion que va a ser desvinculada.
     * @param tipo  Si tipo == 0 -> tipo = coste, si tipo == 1 -> tipo = tiempo
     * @throws IllegalArgumentException
     */
    private void separarTareasRelacion(Integer idTarea1, Integer idTarea2, Integer idRelacion,Boolean tipo) {
        if(!existeTarea(idTarea1))
            throw new IllegalArgumentException(msg_idTarea1_no_valid);
        if(!existeTarea(idTarea2))
            throw new IllegalArgumentException(msg_idTarea2_no_valid);
        if(!existeRelacion(idRelacion))
            throw new IllegalArgumentException(msg_idRel_no_valid);
        
        int i = 0;
        Boolean trobat = Boolean.FALSE;
        if(tipo) {
            while(i < this.tareaRelacionCoste.get(idTarea1).size() && !trobat) {
                if(idRelacion == this.tareaRelacionCoste.get(idTarea1).get(i)) {
                    trobat = Boolean.TRUE;
                    this.tareaRelacionCoste.get(idTarea1).remove(i);
                }
                ++i;
            }
            i = 0;
            trobat = Boolean.FALSE;
            while(i < this.tareaRelacionCoste.get(idTarea2).size() && !trobat) {
                if(idRelacion == this.tareaRelacionCoste.get(idTarea2).get(i)) {
                    trobat = Boolean.TRUE;
                    this.tareaRelacionCoste.get(idTarea2).remove(i);
                }
                ++i;
            }
        }
        else {
            while(i < this.tareaRelacionTiempo.get(idTarea1).size() && !trobat) {
                if(idRelacion == this.tareaRelacionTiempo.get(idTarea1).get(i)) {
                    trobat = Boolean.TRUE;
                    this.tareaRelacionTiempo.get(idTarea1).remove(i);
                }
                ++i;
            }
            i = 0;
            trobat = Boolean.FALSE;
            while(i < this.tareaRelacionTiempo.get(idTarea2).size() && !trobat) {
                if(idRelacion == this.tareaRelacionTiempo.get(idTarea2).get(i)) {
                    trobat = Boolean.TRUE;
                    this.tareaRelacionTiempo.get(idTarea2).remove(i);
                }
                ++i;
            }
        } 
    }
}
