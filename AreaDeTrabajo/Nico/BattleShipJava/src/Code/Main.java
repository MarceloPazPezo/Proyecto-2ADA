//package Code;
/**
 * @author nicolas
 */
import java.util.Random;

public class Main {

    private static int[][] ubicacionLista = new int[10][10];

    public static int guardarUbicaciones(int x, int y) {

        if (x >= 1 && x <= 10 && y >= 1 && y <= 10) {
            if (ubicacionLista[x - 1][y - 1] != 5) {
                ubicacionLista[x - 1][y - 1] = 5;
                
                System.out.println("return_1");
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        System.out.print("{"+ubicacionLista[x - 1][y - 1]+"}");
                    }
                    System.out.println();
                }
                
                return 1;
            } else {

                System.out.println("return_0");
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        System.out.print("{"+ubicacionLista[x - 1][y - 1]+"}");
                    }
                    System.out.println();
                }

                return 0;
            }
        } else {
            return 0;
        }
    }

    

    public static void ModoCaza(char d, int filaActual, int columnaActual, boolean aciertoActual, Tablero t) {

        int fila = filaActual;
        int columna = columnaActual;
        boolean acierto = aciertoActual;
        char barcoActual = d;
        int numDisparos = 0;
        int tipoBarco = 0;

        int direccion = 0; // 0: derecha, 1: abajo, 2: izquierda, 3: arriba
            
        if (barcoActual == 'A')tipoBarco = 5;
        else if(barcoActual == 'B')tipoBarco = 4;
        else if(barcoActual == 'C')tipoBarco = 3;
        else if(barcoActual == 'S')tipoBarco = 3;
        else if(barcoActual == 'D')tipoBarco = 2;

        do{
            numDisparos = 0;

            // if (barcoActual == 'A')tipoBarco = 5;
            // else if(barcoActual == 'B')tipoBarco = 4;
            // else if(barcoActual == 'C')tipoBarco = 3;
            // else if(barcoActual == 'S')tipoBarco = 3;
            // else if(barcoActual == 'D')tipoBarco = 2;

            do {
                for (int i = 1; i < tipoBarco+1; i++) {
                    System.out.println("[]"+i+"[]");
                    if (direccion == 0 && guardarUbicaciones(fila, columna + i) == 1) {
                        // guardarUbicaciones(fila, columna + i);
                        System.out.println(barcoActual+"<>Disparo Abajo");
                        columna++;
                    } else if (direccion == 1 && guardarUbicaciones(fila + i, columna) == 1) {
                        // guardarUbicaciones(fila + i, columna);
                        System.out.println(barcoActual+"<>Disparo Derecha");
                        fila++;
                    } else if (direccion == 2 && guardarUbicaciones(fila, columna - i) == 1) {
                        // guardarUbicaciones(fila, columna-i);
                        System.out.println(barcoActual+"<>Disparo Arriba");
                        columna--;
                    } else if (direccion == 3 && guardarUbicaciones(fila - i, columna) == 1) {
                        // guardarUbicaciones(fila - i, columna);
                        System.out.println(barcoActual+"<>Disparo Izquierda");
                        fila--;
                    } else {
                        System.out.println(barcoActual+"<>Cambia Direccion");
                        direccion = (direccion + 1) % 4; // Cambiar de dirección
                    }
                }

                System.out.println("Disparo en: (" + fila + "," + columna + ")");
                barcoActual = t.disparo(fila, columna);
                System.out.println("----"+barcoActual);
                if (barcoActual != 'X' || barcoActual != '0') {
                    numDisparos++; 
                    acierto = true;
                } else {
                    acierto = false;
                    //direccion = (direccion + 1) % tipoBarco; // Cambiar de dirección
                }
                t.Imprimir();
                System.out.println(">>> " + t.ganar());

            } while (numDisparos < tipoBarco  );

        }while (numDisparos != 0);

        System.out.println("Disparo en: (" + fila + "," + columna + ")");
        barcoActual = t.disparo(fila, columna);
        if (barcoActual == 'X') {
            acierto = true;
        } else {
            acierto = false;
            //direccion = (direccion + 1) % 4; // Cambiar de dirección
        }
        t.Imprimir();
        System.out.println(">>> " + t.ganar());
    }

    public static void main(String[] args) {
        Tablero t = new Tablero(10);
        Random random = new Random();
        int fila, columna;
        fila = 5;
        columna = 5;

        while (t.ganar() == 0) {

            boolean acierto = false;    
            guardarUbicaciones(fila, columna);
            char d = t.disparo(fila, columna);
            System.out.println("......"+d); 
            if (d == 'A' || d == 'B' || d == 'C' || d == 'S' || d == 'D') {
                acierto = true;  
            }else{
                acierto = false;
            }
            System.out.println("-----"+acierto);

            if (acierto == true) {
                ModoCaza(d, fila, columna, acierto, t);    
            }
            //ModoCaza(fila, columna, acierto, t); // Modo caza: si acierta, seguir disparando en la misma dirección hasta eliminar el barco
            
            do {
                fila = random.nextInt(10)+1;
                columna = random.nextInt(10)+1;
            } while (guardarUbicaciones(fila, columna) == 0);
            System.out.println("<<<<<"+fila+","+columna+">>>>>");
        }
        System.out.println("}" + t.ganar());
    }
}
