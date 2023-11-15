// import java.util.Random;

// public class PruebasProbabilidadYDemas {
//     public static char[][] Oceano = new char[10][10];
//     public static void main(String[] args) {
//         int size = 10; // Tamaño de la matriz
//         double sigma = size / 3.0; // Desviación estándar (ajusta este valor para cambiar cuánto se prioriza el centro)
//         Random rand = new Random();

//         for (int i = 0; i<10; i++){
//             for (int j = 0; j<10; j++){
//                 Oceano[i][j] = '0';
//             }
//         }

//         Oceano[5][5] = '1';
//         for (int i = 0; i < 4; i++) {
//             int randomX = getGaussianRandom(rand, size / 2.0, sigma);
//             int randomY = getGaussianRandom(rand, size / 2.0, sigma);
//             System.out.println(Oceano[randomX][randomY]);
//         }
//     }

//     // Genera un número aleatorio siguiendo una distribución normal (gaussiana)
//     public static int getGaussianRandom(Random rand, double mean, double sigma) {
//         return (int) Math.round(Math.max(0, Math.min((rand.nextGaussian() * sigma + mean), mean * 2 - 1)));
//     }
// }

import java.util.ArrayList;
import java.util.Random;

public class PruebasProbabilidadYDemas {
    public static char[][] oceano = new char[10][10];
    public static void main(String[] args) {
        boolean par = false;
        int x, y;

        // LLenar oceano de ceros
        for (int i = 0; i<10; i++){
            for (int j = 0; j<10; j++){
                oceano[i][j] = '0';
            }
        }

        for (int i = 0; i<50; i++)
        {
            par = false;
            do {
                Random r = new Random();
                x = r.nextInt(10) + 1;
                y = r.nextInt(10) + 1;
                // if (x%2== 0 && y%2 == 0)
                //     par = true;
                // if ((x+y)%2 == 0 && oceano[x-1][y-1] == '0')
                //     par = true;
                if (((x%2== 0 && y%2 == 1) ||(y%2 == 0 && x%2 == 1)) && oceano[x-1][y-1] == '0')
                    par = true;
                
            } while (par == false);
            oceano[x-1][y-1] = 'X';
        }

        for (int i = 0; i<10; i++){
            for (int j = 0; j<10; j++){
                System.out.print(oceano[i][j] + " ");
            }
            System.out.println();
        }
        
    }
}