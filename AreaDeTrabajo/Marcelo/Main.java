import java.util.Scanner;
import Java.Tablero;

public class Main {
  public static void main(String[] args) {
    // Crear un objeto de la clase Tablero
    int n;
    Scanner sc = new Scanner(System.in);
    System.out.print("Ingrese el numero de tableros a generar: ");
    n = sc.nextInt();
    sc.close();
    for (int i = 0; i < n; i++) {
      Tablero tablero = new Tablero(10);
      tablero.Imprimir();
      System.out.println();
    }
  }
}
