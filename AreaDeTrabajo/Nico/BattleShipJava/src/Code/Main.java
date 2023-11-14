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
                return 1; // Ubicacion disponible

            } else {
                return 0; // Ubicacion no disponible 
            }
        } else {
            return 0; // Ubicacion no disponible
        }
    }

    

    public static void ModoCaza(char d, int filaActual, int columnaActual, Tablero t) {

        System.out.println("Entrando en modo caza para "+d);

        int fila = filaActual;
        int columna = columnaActual;
        char barcoinicial = d;
        char barcoActual = d;
        int numDisparosAcertados = 0;
        int numDisparosFallidos = 0;
        int tipoBarco = 0;
        int direccion = 0; // 0: derecha, 1: abajo, 2: izquierda, 3: arriba
        int filainicial = filaActual;
        int columnainicial = columnaActual;
    
        if (barcoActual == 'A') {tipoBarco = 5;}
        else if (barcoActual == 'B') {tipoBarco = 4;}
        else if (barcoActual == 'C' || barcoActual == 'S') {tipoBarco = 3;}
        else if (barcoActual == 'D') {tipoBarco = 2;}
    
        do {
            
            boolean seleccion = false;
            do {
                
                if (direccion == 0 && guardarUbicaciones(filainicial, columnainicial + 1) == 1) {
                    guardarUbicaciones(filainicial, columnainicial + 1);
                    columna = columnainicial + 1;
                    fila = filainicial;
                    seleccion = true;
                } else if (direccion == 1 && guardarUbicaciones(filainicial + 1, columnainicial) == 1) {
                    guardarUbicaciones(filainicial + 1, columnainicial);
                    fila = filainicial + 1;
                    columna = columnainicial;
                    seleccion = true;
                } else if (direccion == 2 && guardarUbicaciones(filainicial, columnainicial - 1) == 1) {
                    guardarUbicaciones(filainicial, columnainicial - 1);
                    columna = columnainicial - 1;
                    fila = filainicial;
                    seleccion = true;
                } else if (direccion == 3 && guardarUbicaciones(filainicial - 1, columnainicial) == 1) {
                    guardarUbicaciones(filainicial - 1, columnainicial);
                    fila = filainicial - 1;
                    columna = columnainicial;
                    seleccion = true;
                } else {
                    direccion ++;
                    if (direccion == 4) {
                        filainicial = filaActual;
                        columnainicial = columnaActual;
                        direccion = 0;    
                    }
                }

            } while (seleccion == false);
    
            barcoActual = t.disparo(fila, columna);
            if (barcoActual != '0' && barcoActual != barcoinicial) {
                ModoCaza(barcoActual,fila,columna,t);
            }
            System.out.println("Disparo en MC: ("+fila+","+columna+")"+"en: "+barcoActual);
            if ((barcoActual == 'A' && tipoBarco == 5) ||
                (barcoActual == 'B' && tipoBarco == 4) ||
                (barcoActual == 'S' && tipoBarco == 3) ||
                (barcoActual == 'C' && tipoBarco == 3) ||
                (barcoActual == 'D' && tipoBarco == 2)) {
                numDisparosAcertados++;
                numDisparosFallidos = 0;
                filainicial = fila;
                columnainicial = columna;
            } else {
                numDisparosFallidos++;
                direccion = (direccion + 1) % 4; // Cambiar de dirección
            }

            if (numDisparosFallidos > tipoBarco) {
                filainicial = filaActual;
                columnainicial = columnaActual;
            }

        } while (numDisparosAcertados != tipoBarco-1); // Ajuste de la condición de salida
        System.out.println("Saliendo de modo caza para " + d);
    }    

    public static void main(String[] args) {
        int rep = 50;
        int contPorcentaje = 0;
        int disparos = 0;
        do {

            Tablero t = new Tablero(10);
            Random random = new Random();
            int fila, columna;
            fila = 5;
            columna = 5;

            while (t.ganar() == 0) {

                boolean acierto = false;    
                guardarUbicaciones(fila, columna);
                char d = t.disparo(fila, columna);
                if (d == 'A' || d == 'B' || d == 'C' || d == 'S' || d == 'D') {
                    acierto = true;  
                }else{
                    acierto = false;
                }
                System.out.println("Disparo Rand ("+fila+","+columna+") en :" + d);
                if (acierto == true) {
                    ModoCaza(d, fila, columna, t);    
                }
                do {
                    fila = random.nextInt(10)+1;
                    columna = random.nextInt(10)+1;
                } while (guardarUbicaciones(fila, columna) == 0);
                // System.out.println("<<<<<"+fila+","+columna+">>>>>");
            }

            

            int cantDisparos = t.ganar();

            System.out.println("{" + cantDisparos + "}");
            System.out.println("------------------------------");

            disparos = cantDisparos + disparos;
            contPorcentaje++;

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    ubicacionLista[i][j] = 0;
                    //reseteo las ubicaciones
                }
            }

        } while (contPorcentaje != rep);
        
        System.out.println(">>>>>>>>>>>"+(disparos/rep)+"<<<<<<<<<<<");

    }
}
