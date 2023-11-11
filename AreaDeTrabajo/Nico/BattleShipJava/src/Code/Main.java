
//package Code;
/**
 * @author nicolas
 */
import java.util.Random;

public class Main {

    private static int[][] ubicacionLista = new int[10][10];

    public static int guardarUbicaciones(int x, int y) {
        
        if (ubicacionLista[x-1][y-1] != 1) {
            ubicacionLista[x-1][y-1] = 1;
            return 1;
        } else {
            return 0;
        }
    }

    public static void ModoCaza(char TipoBarco, int filaActual, int columnaActual, boolean aciertoActual, Tablero t){
        
        int fila = filaActual;
        int columna = columnaActual;
        boolean acierto = aciertoActual;
        char barcoActual = TipoBarco;

        int direccion = 0; // 0: derecha, 1: abajo, 2: izquierda, 3: arriba
            while (acierto) {
                do {
                    int numDisparos = 0;
                    if (barcoActual == 'A') {
                        do {
                            if (direccion == 0 && guardarUbicaciones(fila, columna+1) == 0) {
                                columna++;
                            } else if (direccion == 1 && guardarUbicaciones(fila+1, columna) == 0) {
                                fila++;
                            } else if (direccion == 2 && guardarUbicaciones(fila, columna-1) == 0) {
                                columna--;
                            } else if (direccion == 3 && guardarUbicaciones(fila-1, columna) == 0) {
                                fila--;
                            } else {
                                direccion = (direccion + 1) % 4; // Cambiar de dirección
                            }
                            
                            System.out.println("Disparo en: (" + fila + "," + columna + ")");
                            barcoActual = t.disparo(fila, columna);
                            if (barcoActual == 'X') {
                                acierto = true;
                            } else {
                                acierto = false;
                                direccion = (direccion + 1) % 4; // Cambiar de dirección
                            }
                            t.Imprimir();
                            System.out.println(">>> " + t.ganar());
                            numDisparos++;

                        } while (numDisparos<5);
                    }
                    if (barcoActual == 'B') {
                        do {
                            if (direccion == 0 && guardarUbicaciones(fila, columna+1) == 0) {
                                columna++;
                            } else if (direccion == 1 && guardarUbicaciones(fila+1, columna) == 0) {
                                fila++;
                            } else if (direccion == 2 && guardarUbicaciones(fila, columna-1) == 0) {
                                columna--;
                            } else if (direccion == 3 && guardarUbicaciones(fila-1, columna) == 0) {
                                fila--;
                            } else {
                                direccion = (direccion + 1) % 4; // Cambiar de dirección
                            }
                            
                            System.out.println("Disparo en: (" + fila + "," + columna + ")");
                            barcoActual = t.disparo(fila, columna);
                            if (barcoActual == 'X') {
                                acierto = true;
                            } else {
                                acierto = false;
                                direccion = (direccion + 1) % 4; // Cambiar de dirección
                            }
                            t.Imprimir();
                            System.out.println(">>> " + t.ganar());
                            numDisparos++;

                        } while (numDisparos<4);
                    }
                    if (barcoActual == 'C') {
                        do {
                            if (direccion == 0 && guardarUbicaciones(fila, columna+1) == 0) {
                                columna++;
                            } else if (direccion == 1 && guardarUbicaciones(fila+1, columna) == 0) {
                                fila++;
                            } else if (direccion == 2 && guardarUbicaciones(fila, columna-1) == 0) {
                                columna--;
                            } else if (direccion == 3 && guardarUbicaciones(fila-1, columna) == 0) {
                                fila--;
                            } else {
                                direccion = (direccion + 1) % 4; // Cambiar de dirección
                            }
                            
                            System.out.println("Disparo en: (" + fila + "," + columna + ")");
                            barcoActual = t.disparo(fila, columna);
                            if (barcoActual == 'X') {
                                acierto = true;
                            } else {
                                acierto = false;
                                direccion = (direccion + 1) % 4; // Cambiar de dirección
                            }
                            t.Imprimir();
                            System.out.println(">>> " + t.ganar());
                            numDisparos++;

                        } while (numDisparos<3);
                    }
                    if (barcoActual == 'S') {
                        do {
                            if (direccion == 0 && guardarUbicaciones(fila, columna+1) == 0) {
                                columna++;
                            } else if (direccion == 1 && guardarUbicaciones(fila+1, columna) == 0) {
                                fila++;
                            } else if (direccion == 2 && guardarUbicaciones(fila, columna-1) == 0) {
                                columna--;
                            } else if (direccion == 3 && guardarUbicaciones(fila-1, columna) == 0) {
                                fila--;
                            } else {
                                direccion = (direccion + 1) % 4; // Cambiar de dirección
                            }
                            
                            System.out.println("Disparo en: (" + fila + "," + columna + ")");
                            barcoActual = t.disparo(fila, columna);
                            if (barcoActual == 'X') {
                                acierto = true;
                            } else {
                                acierto = false;
                                direccion = (direccion + 1) % 4; // Cambiar de dirección
                            }
                            t.Imprimir();
                            System.out.println(">>> " + t.ganar());
                            numDisparos++;

                        } while (numDisparos<3);
                    }
                    if (barcoActual == 'D') {
                        do {
                            if (direccion == 0 && guardarUbicaciones(fila, columna+1) == 0) {
                                columna++;
                            } else if (direccion == 1 && guardarUbicaciones(fila+1, columna) == 0) {
                                fila++;
                            } else if (direccion == 2 && guardarUbicaciones(fila, columna-1) == 0) {
                                columna--;
                            } else if (direccion == 3 && guardarUbicaciones(fila-1, columna) == 0) {
                                fila--;
                            } else {
                                direccion = (direccion + 1) % 4; // Cambiar de dirección
                            }
                            
                            System.out.println("Disparo en: (" + fila + "," + columna + ")");
                            barcoActual = t.disparo(fila, columna);
                            if (barcoActual == 'X') {
                                acierto = true;
                            } else {
                                acierto = false;
                                direccion = (direccion + 1) % 4; // Cambiar de dirección
                            }
                            t.Imprimir();
                            System.out.println(">>> " + t.ganar());
                            numDisparos++;

                        } while (numDisparos<2);
                    }
                } while (guardarUbicaciones(fila, columna) == 1);

                // System.out.println("Disparo en: (" + fila + "," + columna + ")");
                // disparoActual = t.disparo(fila, columna);
                // if (disparoActual == 'X') {
                //     acierto = true;
                // } else {
                //     acierto = false;
                //     direccion = (direccion + 1) % 4; // Cambiar de dirección
                // }
                // t.Imprimir();
                // System.out.println(">>> " + t.ganar());
            }
    }

    public static void main(String[] args) {
        Tablero t = new Tablero(10);
        Random random = new Random();
        
        char disparoActual;


        while (t.ganar() == 0) {
            int fila, columna;
            boolean acierto = false;
            do {
                fila = random.nextInt(10)+1;
                columna = random.nextInt(10)+1;
            } while (guardarUbicaciones(fila, columna) != 1);

            // Realizar el disparo y mostrar el resultado
            System.out.println("Disparo en: (" + fila + "," + columna + ")");
            disparoActual = t.disparo(fila, columna);
            if (disparoActual != '0') {
                acierto = true;
            }
            t.Imprimir();
            System.out.println(">>> " + t.ganar());

            ModoCaza(disparoActual, fila, columna, acierto, t);// Modo caza: si acierta, seguir disparando en la misma dirección hasta eliminar el barco
        }
        System.out.println(">>> " + t.ganar());
    }
}
