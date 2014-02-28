

import java.util.Comparator;

/**
 *
 * @author Alex Peregrina Cabrera
 */
public abstract class Relacion implements Comparable<Relacion>{
        
        private Integer id;
	private String nombre;
	private Integer tarea1;
        private Integer tarea2;
        private double similitud;
	
	public Relacion() {
	}
        
        public Relacion(Integer tarea1, Integer tarea2, Double similitud) {
            this.nombre = nombre;
            this.similitud = similitud;
            this.tarea1 = tarea1; 
            this.tarea2 = tarea2;
	}
        
	public Relacion(String nombre, Integer tarea1, Integer tarea2, Double similitud) {
            this.nombre = nombre;
            this.similitud = similitud;
            this.tarea1 = tarea1; 
            this.tarea2 = tarea2;        
	}
        public Integer id() {
            return id;
	}

	public String nombre() {
            return nombre;
	}
        
        public Double similitud() {
            return similitud;
	}
        
        public Integer tarea1() {
            return tarea1;
	}
        
        public Integer tarea2() {
            return tarea2;
	}

        public void modificarId(int id) {
            this.id = id;
	}

	public void modificarNombre(String nombre) {
            this.nombre = nombre;
	}

	public void modificarSimilitud(Double similitud) {
            this.similitud = similitud;
	}

	public void modificarTarea1(int tarea1) {
            this.tarea1 = tarea1;
	}
        
        public void modificarTarea2(int tarea2) {
            this.tarea2 = tarea2;
	}
        
        public void modificarTareas(Integer tarea1, Integer tarea2) {
            this.tarea1 = tarea1;
            this.tarea2 = tarea2;
        }

        @Override
        public int compareTo(Relacion o) {
            return this.id.compareTo(o.id);
        }
        
        //para usarlo el comparator cuando añadimos algo al ArrayList x hacemos:
        //Collections.sort(x, new ComparadorSimilitud());
        
        class ComparadorSimilitud implements Comparator {
            @Override
            public int compare(Object o1, Object o2) {
                Relacion r1 = (Relacion)o1;
                Relacion r2 = (Relacion)o2;
                if(r1.similitud > r2.similitud) return 1;
                else if(r1.similitud < r2.similitud) return -1;
                else return 0;
            }
        }
 
        abstract public Boolean tipo();
        
}
