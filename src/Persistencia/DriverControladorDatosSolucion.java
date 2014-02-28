package Persistencia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Marc
 */
public class DriverControladorDatosSolucion {
    private static void menu() {
        System.out.println("Driver ControladorDatosSolucion");
        System.out.println("Opciones:");
        System.out.println("\t 1) ControladorDatosSolucion()");
        System.out.println("\t       input: 1");
        System.out.println("\t 2) abrirArchivoSoluciones()");
        System.out.println("\t       input: 2 <String>");
        System.out.println("\t 3) cerrarArchivoSoluciones()");
        System.out.println("\t       input: 3");
        System.out.println("\t 4) anadirSolucion(String[] Solucion)");
        System.out.println("\t       input: 4 \"<String>\" <double> <double> <integers idtareastour> ");
        System.out.println("\t 5) anadirSoluciones(String[][] Solucion)");
        System.out.println("\t       input: 5 <int: N> N veces: {\"<String>\" <double> <double> <integer idtareastour>}");
        System.out.println("\t 6) modificarArchivo(String[][] Solucion)");
        System.out.println("\t       input: 6 <int: N> N veces: {\"<String>\" <double> <double> <integer idtareastour>}");
        System.out.println("\t 7) leerSoluciones()");
        System.out.println("\t       input: 7");
        System.out.println("\t 8) modificarSolucion(String id, String[] str)");
        System.out.println("\t       input: 8 <int> \"<String>\" <double> <double> <integer idtareastour>");

    }

    public static void main(String[] args) {
        try {
            ControladorDatosSolucion datossol = null;
            int tamano_linea = 4;
            BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
            String linea;
            boolean salir = false;
            linea = "Primero";
            menu();
            while (linea != null && !salir) {
                linea = buffer.readLine();
                try {
                    String[] line;
                    line = sacarStrings(linea);
                    switch (line[0]) {
                        case "1":
                            try {
                                datossol = new ControladorDatosSolucion();
                            } catch (Exception e) {
                                System.out.print(e.getMessage());
                            }
                            break;
                        case "2":
                            try {
                                datossol.abrirArchivoSoluciones();
                            } catch (Exception e) {
                                System.out.print(e.getMessage());
                            }
                            break;
                        case "3":
                            try {
                                datossol.cerrarArchivoSoluciones();
                            } catch (Exception e) {
                                System.out.print(e.getMessage());
                            }
                            break;
                        case "4":
                            try {
                                datossol.anadirSolucion(Arrays.copyOfRange(line, 1, tamano_linea + 1));
                            } catch (Exception e) {
                                System.out.print(e.getMessage());
                            }
                            break;
                        case "5":
                            try {
                                String[][] str = new String[Integer.parseInt(line[1])][tamano_linea];
                                for (int i = 0; i < Integer.parseInt(line[1]); ++i) {
                                    str[i] = Arrays.copyOfRange(line, i * tamano_linea + 2, i * tamano_linea + tamano_linea + 2);
                                }
                                datossol.anadirSoluciones(str);
                            } catch (Exception e) {
                                System.out.print(e.getMessage());
                            }
                            break;
                        case "6":
                            try {
                                String[][] str = new String[Integer.parseInt(line[1])][tamano_linea];
                                for (int i = 0; i < Integer.parseInt(line[1]); ++i) {
                                    str[i] = Arrays.copyOfRange(line, i * tamano_linea + 2, i * tamano_linea + tamano_linea + 2);
                                }
                                datossol.modificarArchivo(str);
                            } catch (Exception e) {
                                System.out.print(e.getMessage());
                            }
                            break;
                        case "7":
                            try {
                                String[][] aa = datossol.leerSoluciones();
                                for (int i = 0; i < aa.length; ++i) {
                                    for (int j = 0; j < aa[i].length; ++j) {
                                        if (aa[i][j] != null) {
                                            System.out.print(aa[i][j] + " ");
                                        }
                                    }
                                    System.out.print("\n");
                                }

                            } catch (Exception e) {
                                System.out.print(e.getMessage());
                            }
                            break;
                        case "8":
                            try {
                                datossol.modificarSolucion(line[1], Arrays.copyOfRange(line, 2, tamano_linea + 2));
                            } catch (Exception e) {
                                System.out.print(e.getMessage());
                            }
                            break;
                        case "0":
                            salir = true;
                            break;
                        default:
                            if (linea == null) {
                                salir = true;
                            }
                            else System.out.println(linea);
                            break;
                    }
                } catch (Exception e) {
                    System.out.print(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.print(e.getMessage());
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
