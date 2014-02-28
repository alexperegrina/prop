package david;

public class GrafoAleatorio {

    public static void main(String[] args) {
        
        int N = 200;
        System.out.print(N);
        int k = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                ++k;
            }
        }
        System.out.print(" " + k);
        for (int i = 0; i < N; ++i) {
            for (int j = i + 1; j < N; ++j) {
                Double random = Math.random() * 10; // Max 10 relacion
                System.out.print(" " + i + " " + j + " " + random);
                //System.out.println(j + " " + i + " " + random); //Funciona igual shurmano
            }
        }

    }
}
