import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 *
 * @author Alejandro Rosas
 */
public class DriverShortcutPaths {

    public static void main(String[] args) {
        ArrayList<Arista> aristas = new ArrayList<Arista>();
        Grafo G = null;
        ArrayList<Integer> tour = null;
        System.out.println("Driver TresOPT");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        String linea;
        boolean salir = false;
        linea = "Primero";
        while (linea != null && !salir) {
            try {
                System.out.println("Escoja una opción:");
                System.out.println("\t 1) obtenerCicloHamiltoniano(ArrayList<Arista> aristas, Grafo g)");
                System.out.println("\t\t input: 1");
                System.out.println("\t 2) G = new Grafo(Integer n)");
                System.out.println("\t\t input: 2 <Integer>");
                System.out.println("\t 3) G.ponerArista(new Arista(int idOrigen, int idDestino, Double peso))");
                System.out.println("\t\t input: 3 <Integer> <Integer> <Double>");
                System.out.println("\t 4) aristas.add(new Arista(int idOrigen, int idDestino, Double peso))");
                System.out.println("\t\t input: 4 <Integer> <Integer> <Double>");
                System.out.println("\t 0) Salir");

                linea = buffer.readLine();
                String[] line = linea.split(" ");

                switch (line[0]) {
                    case "1":
                        try {
                            ShortcutPaths S = new ShortcutPaths();
                            ArrayList<Arista> ret = S.obtenerCicloHamiltoniano(aristas, G);
                            for (int i = 0; i < ret.size(); ++i) {
                                System.out.print(ret.get(i).origen() + "," + ret.get(i).destino() + ".");
                            }
                            System.out.print("\n");
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "2":
                        try {
                            int n = Integer.parseInt(line[1]);
                            G = new Grafo(n);
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "3":
                        try {
                            G.ponerArista(new Arista(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Double.parseDouble(line[3])));
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "4":
                        try {
                            aristas.add(new Arista(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Double.parseDouble(line[3])));
                        } catch (Exception E) {
                            System.out.println(E.getMessage());
                        }
                        break;
                    case "0":
                        salir = true;
                        break;
                    default:
                        System.out.println(linea);
                        break;
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }

        }

    }
}
