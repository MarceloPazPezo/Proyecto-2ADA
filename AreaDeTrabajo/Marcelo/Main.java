import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// import java.util.Iterator;
import java.util.Random;
// import java.util.Scanner;
import Java.Tablero;
import Java.Tipo;

// import java.util.ArrayList;

public class Main {
  public static ArrayList<Tipo> pilaObjetivo = new ArrayList<>();
  public static ArrayList<String> ListaDisparos = new ArrayList<>();
  public static Integer intentos;
  public static ArrayList<Integer> ListaDeIntentos = new ArrayList<>();
  public static Integer valorMin, valorMax;
  public static HashMap<Integer, Integer> mapa = new HashMap<>();

  public static void main(String[] args) {
      // Tamaño del tablero
      for (int i = 0; i < 10000; i++) {
        System.out.println("Juego " + (i+1));
        Tablero tablero = new Tablero(10);
        Juego(tablero);
        ListaDeIntentos.add(intentos);
      }

      // Calcular promedio de intentos
      int suma = 0;
      for (Integer i : ListaDeIntentos) {
        suma += i;
      }
      System.out.println("Promedio de intentos: " + suma/ListaDeIntentos.size());
      
      if (1 == 0) // Mostrar cosas innecesarias
      {
        // Recorrer la lista
        for (int valor : ListaDeIntentos) {
            // Si el mapa ya contiene el valor, incrementar su conteo
            if (mapa.containsKey(valor)) {
                mapa.put(valor, mapa.get(valor) + 1);
            } else {
                // Si el mapa no contiene el valor, agregarlo con un conteo de 1
                mapa.put(valor, 1);
            }
        }
        // Convertir el mapa en una lista de entradas
        List<Map.Entry<Integer, Integer>> lista = new ArrayList<>(mapa.entrySet());

        // Ordenar la lista de menor a mayor
        lista.sort(Map.Entry.comparingByKey());

        // Imprimir la lista ordenada
        for (Map.Entry<Integer, Integer> entrada : lista) {
            System.out.println("El valor " + entrada.getKey() + " aparece " + entrada.getValue() + " veces.");
        }
        
        System.out.println("Intentos minimos: " + valorMin);
        System.out.println("Intentos maximos: " + valorMax);
      }

      
  }

  public static void Juego(Tablero t) {
    char[][] oceanoDisparos = new char[10][10];
    
    // Llenar matriz de ceros
    for (int i = 0; i < oceanoDisparos.length; i++) {
      for (int j = 0; j < oceanoDisparos[i].length; j++) {
        oceanoDisparos[i][j] = '0';
      }
    }

    for (int i = 1; i<=100; i++) {
      // Generar numero random
      boolean par = false;
      intentos = t.ganar();
      if (intentos == 0)
      {
        int x, y;
        do {
          Random r = new Random();
          x = r.nextInt(10) + 1;
          y = r.nextInt(10) + 1;
          if (((x+y)%2 == 0) && oceanoDisparos[x-1][y-1] == '0')
            par = true;
        } while (par == false);
        if (i == 0)
        {
          x = 6;
          y = 6;
        }
        if (i == 1)
        {
          x = 7;
          y = 7;
        }
        char d = t.disparo(x, y);
        ListaDisparos.add(y + ":" + x);
        if (d == '0')
        {
          oceanoDisparos[x-1][y-1] = 'X';
        }
        else
        {
          oceanoDisparos[x-1][y-1] = d;
          Tipo objetivo = new Tipo(d, x, y);
          objetivo.largo--;
          pilaObjetivo.add(objetivo);

          for(int j=0; j<pilaObjetivo.size(); j++) {
            modoCaza(oceanoDisparos, t, pilaObjetivo.get(j));
          }
          pilaObjetivo.clear();
        }        
      }
      else
      {
        if (valorMin == null || valorMin > intentos)
          valorMin = intentos;
        if (valorMax == null || valorMax < intentos)
          valorMax = intentos;
        // System.out.println("Ganaste en " + intentos + " intentos");
        break;
      }
    }
  }

  public static void modoCaza(char[][] oceanoDisparos, Tablero t, Tipo objetivo) {
    char d;
    int x = objetivo.marcax;
    int y = objetivo.marcay;
    int up = 0, down = 0, left = 0, right = 0;
    boolean repetir = true;
    while (objetivo.largo > 0) {
      // Dispara Arriba
      if (left<=0)
      { 
        up++;
        if (down>0)
          down = 0;
        do {
          if (((x-up+down) < 1 || (x-up+down) > 10) || (y-left+right) < 1 || (y-left+right) > 10)
          {
            repetir = false;
          }
          else if (oceanoDisparos[x-up+down-1][y-left+right-1] == '0') // Habilitado para disparar
          {
            d = t.disparo(x-up+down, y-left+right);
            ListaDisparos.add((y-left+right) + ":" + (x-up+down));
            if (d == '0')
            {
              oceanoDisparos[x-up+down-1][y-left+right-1] = 'X';
              repetir = false;
            }
            else
            {
              if (d == objetivo.tipo)
              {
                oceanoDisparos[x-up+down-1][y-left+right-1] = d;
  
                objetivo.largo--;
                if (objetivo.largo <= 0) return;
  
                repetir = true;
                up++;
              }
              else
              {
                oceanoDisparos[x-up+down-1][y-left+right-1] = d;

                Tipo o = new Tipo(d, x-up+down, y-left+right);
                o.largo--;
                pilaObjetivo.add(o);
                repetir = false;
              }
            }
          }
          else
          {
            repetir = false;
          }
        } while (repetir);
        up--;
      }

      if (up <= 0) {
        // Disparar Derecha
        right++;
        if (left>0)
          left = 0;
        do {
          if (((x-up+down) < 1 || (x-up+down) > 10) || (y-left+right) < 1 || (y-left+right) > 10)
          {
            repetir = false;
          }
          else if (oceanoDisparos[x-up+down-1][y-left+right-1] == '0') // Habilitado para disparar
          {
            d = t.disparo(x-up+down, y-left+right);
            ListaDisparos.add((y-left+right) + ":" + (x-up+down));
            if (d == '0')
            {
              oceanoDisparos[x-up+down-1][y-left+right-1] = 'X';
              repetir = false;
            }
            else
            {
              if (d == objetivo.tipo)
              {
                oceanoDisparos[x-up+down-1][y-left+right-1] = d;

                objetivo.largo--;
                if (objetivo.largo <= 0) return;

                repetir = true;
                right++;
              }
              else
              {
                oceanoDisparos[x-up+down-1][y-left+right-1] = d;
                Tipo o = new Tipo(d, x-up+down, y-left+right);
                o.largo--;
                pilaObjetivo.add(o);
                repetir = false;
              }
            }
          }
          else
            repetir = false;
        } while (repetir);
        right--;
      }

      // Dispara Abajo
      if (right <= 0)
      {
        down++;
        if (up > 0)
          up = 0;
        do {
          if (((x-up+down) < 1 || (x-up+down) > 10) || (y-left+right) < 1 || (y-left+right) > 10)
          {
            repetir = false;
          }
          else if (oceanoDisparos[x-up+down-1][y-left+right-1] == '0') // Habilitado para disparar
          {
            d = t.disparo(x-up+down, y-left+right);
            ListaDisparos.add((y-left+right) + ":" + (x-up+down));
            if (d == '0')
            {
              oceanoDisparos[x-up+down-1][y-left+right-1] = 'X';
              repetir = false;
            }
            else
            {
              if (d == objetivo.tipo)
              {
                oceanoDisparos[x-up+down-1][y-left+right-1] = d;

                objetivo.largo--;
                if (objetivo.largo <= 0) return;
                
                repetir = true;
                down++;
              }
              else
              {
                oceanoDisparos[x-up+down-1][y-left+right-1] = d;
                Tipo o = new Tipo(d, x-up+down, y-left+right);
                o.largo--;
                pilaObjetivo.add(o);
                repetir = false;
              }
            }
          }
          else
            repetir = false;
        } while (repetir);
        down--;
      }
      // Disparar Izquierda
      if (down <= 0)
      {
        left++;
        if (right > 0)
          right = 0;
        do {
          if (((x-up+down) < 1 || (x-up+down) > 10) || (y-left+right) < 1 || (y-left+right) > 10)
          {
            repetir = false;
          }
          else if (oceanoDisparos[x-up+down-1][y-left+right-1] == '0') // Habilitado para disparar
          {
            d = t.disparo(x-up+down, y-left+right);
            ListaDisparos.add((y-left+right) + ":" + (x-up+down));
            if (d == '0')
            {
              oceanoDisparos[x-up+down-1][y-left+right-1] = 'X';
              repetir = false;
            }
            else
            {
              if (d == objetivo.tipo)
              {
                oceanoDisparos[x-up+down-1][y-left+right-1] = d;

                objetivo.largo--;
                if (objetivo.largo <= 0) return;
                
                repetir = true;
                left++;
              }
              else
              {
                oceanoDisparos[x-up+down-1][y-left+right-1] = d;
                Tipo o = new Tipo(d, x-up+down, y-left+right);
                o.largo--;
                pilaObjetivo.add(o);
                repetir = false;
              }
            }
          }
          else
            repetir = false;
        } while (repetir);
        left--;
      }

    }
  }
}