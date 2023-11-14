import java.util.ArrayList;
import java.util.Random;
// import java.util.Scanner;
import Java.Tablero;
import Java.Tipo;
// import java.util.ArrayList;

public class Main {
  public static ArrayList<Tipo> pilaObjetivo = new ArrayList<>();
  // public char[][] oceanoDisparos;
  
  public static void main(String[] args) {
      // Tamaño del tablero
      Tablero tablero = new Tablero(10);
      tablero.Imprimir();
      Juego(tablero);
      
  }

  public static void Juego(Tablero t) {
    char[][] oceanoDisparos = new char[10][10];

    // Llenar matriz de ceros
    for (int i = 0; i < oceanoDisparos.length; i++) {
      for (int j = 0; j < oceanoDisparos[i].length; j++) {
        oceanoDisparos[i][j] = '0';
      }
    }
    // float[][] oceanoProbabilidad = new float[10][10];

    for (int i = 1; i<=100; i++) {
      // Generar numero random
      if (t.ganar() == 0)
      {
        int x, y;
        do {
          Random r = new Random();
          x = r.nextInt(9) + 1;
          y = r.nextInt(9) + 1;
        } while (oceanoDisparos[x-1][y-1] == 'X'  /*||  (0 < y-1 && y-1 < 11) || (0 < x-1 && x-1 < 11)*/);
        char d = t.disparo(x, y);
        System.out.println("Disparo en:" + y + ":" + x + " " + d);
        if (d == '0')
        {
          // System.out.println("Disparo fallido");
          oceanoDisparos[x-1][y-1] = 'X';
        }
        else
        {
          oceanoDisparos[x-1][y-1] = d;
          // System.out.print("Acierto en " + d);
          Tipo objetivo = new Tipo(d);
          objetivo.largo--;
          pilaObjetivo.add(objetivo);
          for (Tipo o : pilaObjetivo) {
            System.out.println(" Modo caza activado");
            modoCaza(oceanoDisparos, x, y, t, o);
            for (char[] oD : oceanoDisparos) {
              for (char oD2 : oD) {
                System.out.print(oD2 + " ");
              }
              System.out.println();
            }
          }
          pilaObjetivo.clear();
        }        
      }
    }
  }
  public static void modoCaza(char[][] oceanoDisparos, int x, int y, Tablero t, Tipo objetivo) {
    char d;
    int up = 0, down = 0, left = 0, right = 0;
    while (objetivo.largo > 0) {
      // Dispara arriba
      up++;
      if (oceanoDisparos[x-up+down-1][y-left+right-1] == '0') // Habilitado para disparar
      {
        d = t.disparo(x-up+down, y-left+right); // Dispara
        System.out.println((y-left+right) + ":" + (x-up+down) + " " + d);
        if (d == '0') // Si falla
        {
          oceanoDisparos[x-up+down-1][y-left+right-1] = 'X';
        }
        else // Si acierta
        {
          oceanoDisparos[x-up+down-1][y-left+right-1] = d;
          if (d == objetivo.tipo)// Si es el objetivo
          {
            objetivo.largo--;
            if (objetivo.largo <= 0) return; // Si ya esta cazado

            while (objetivo.largo > 0) {
              up++;
            }
          }
          else if (d != '0')
          {
            Tipo o = new Tipo(d);
            o.largo--;
            pilaObjetivo.add(o);
          }
        } 
      }
      up--;

      // Dispara Abajo
      down++;
      if (oceanoDisparos[x-up+down-1][y-left+right-1] == '0') // Habilitado para disparar
      {
        d = t.disparo(x-up+down, y-left+right); // Dispara
        System.out.println((y-left+right) + ":" + (x-up+down) + " " + d);
        if (d == '0') // Si falla
        {
          oceanoDisparos[x-up+down-1][y-left+right-1] = 'X';
        }
        else // Si acierta
        {
          oceanoDisparos[x-up+down-1][y-left+right-1] = d;
          if (d == objetivo.tipo)// Si es el objetivo
          {
            objetivo.largo--;
            if (objetivo.largo <= 0) return; // Si ya esta cazado

            down++;
            right--;
            left--;
          }
          else if (d != '0')
          {
            Tipo o = new Tipo(d);
            o.largo--;
            pilaObjetivo.add(o);
          }
        } 
      }
      down--;

      // Disparar Derecha
      right++;
      if (oceanoDisparos[x-up+down-1][y-left+right-1] == '0') // Habilitado para disparar
      {
        d = t.disparo(x-up+down, y-left+right); // Dispara
        System.out.println((y-left+right) + ":" + (x-up+down) + " " + d);
        if (d == '0') // Si falla
        {
          oceanoDisparos[x-up+down-1][y-left+right-1] = 'X';
        }
        else // Si acierta
        {
          oceanoDisparos[x-up+down-1][y-left+right-1] = d;
          if (d == objetivo.tipo)// Si es el objetivo
          {
            objetivo.largo--;
            if (objetivo.largo <= 0) return; // Si ya esta cazado

            right++;
          }
          else if (d != '0')
          {
            Tipo o = new Tipo(d);
            o.largo--;
            pilaObjetivo.add(o);
          }
        } 
      }
      right--;

      // Disparar Izquierda
      left++;
      if (oceanoDisparos[x-up+down-1][y-left+right-1] == '0') // Habilitado para disparar
      {
        d = t.disparo(x-up+down, y-left+right); // Dispara
        System.out.println((y-left+right) + ":" + (x-up+down) + " " + d);
        if (d == '0') // Si falla
        {
          oceanoDisparos[x-up+down-1][y-left+right-1] = 'X';
        }
        else // Si acierta
        {
          oceanoDisparos[x-up+down-1][y-left+right-1] = d;
          if (d == objetivo.tipo)// Si es el objetivo
          {
            objetivo.largo--;
            if (objetivo.largo <= 0) return; // Si ya esta cazado

            left++;
          }
          else if (d != '0')
          {
            Tipo o = new Tipo(d);
            o.largo--;
            pilaObjetivo.add(o);
          }
        } 
      }
      left--;
    }
  }
  public static void modoCaza1(char[][] oceanoDisparos, int x, int y, Tablero t, Tipo objetivo) {
    int i = 1;
    while (true) {
      // if (objetivo.largo <= 0) return;
      if (objetivo.largo <= 0) return;

      // Dispara arriba
      if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
      {
        char d = t.disparo(x-i, y); // Dispara
        if (d == '0') // Si falla
        {
          oceanoDisparos[x-i-1][y-1] = 'X';
        }
        else // Si acierta
        {
          oceanoDisparos[x-i-1][y-1] = d;
          if (d == objetivo.tipo)// Si es el objetivo
          {
            objetivo.largo--;
            i++;
            if (objetivo.largo <= 0) return; // Si ya esta cazado
            
            if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
            {
              d = t.disparo(x-i, y);
              if (d == '0') // Si falla
              {
                oceanoDisparos[x-i-1][y-1] = 'X';
                i--;
                
                if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
                {
                  d = t.disparo(x-i, y);

                  if (d == '0') // Si falla
                  {
                    oceanoDisparos[x-i-1][y-1] = 'X';
                    i--;
                  }
                  else // Si acierta
                  {
                    if (d == objetivo.tipo) // Si es objetivo
                    {
                      objetivo.largo--;
                      i--;
                      if (objetivo.largo <= 0) return; // Si ya esta cazado
                      
                      if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
                      {
                        d = t.disparo(x-i, y);

                        if (d == '0') // Si falla
                        {
                          oceanoDisparos[x-i-1][y-1] = 'X';
                          i--;
                        }
                        else // Si acierta
                        {
                          if (d == objetivo.tipo) // Si es objetivo
                          {
                            objetivo.largo--;
                            i--;
                            if (objetivo.largo <= 0) return; // Si ya esta cazado
                          }
                          else if (d != '0') // Si no es objetivo
                          {
                            Tipo o = new Tipo(d);
                            o.largo--;
                            pilaObjetivo.add(o);
                          }
                        }
                      }
                    }
                    else if (d != '0')
                    {
                      Tipo o = new Tipo(d);
                      o.largo--;
                      pilaObjetivo.add(o);
                    }
                  }
                }
              }
              else // Si acierta
              {
                oceanoDisparos[x-i-1][y-1] = d;
                if (d == objetivo.tipo) // Si es el objetivo
                {
                  objetivo.largo--;
                  i++;
                  if (objetivo.largo <= 0) return; // Si ya esta cazado
                  
                  if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
                  {
                    d = t.disparo(x-i, y);
                    System.out.println("Disparo en:" + y + ":" + (x-i) + " " + d);
                    if (d == '0') // Si falla
                    {
                      oceanoDisparos[x-i-1][y-1] = 'X';
                      i--;
                      if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
                      {
                        d = t.disparo(x-i, y);

                        if (d == '0') // Si falla
                        {
                          oceanoDisparos[x-i-1][y-1] = 'X';
                          i--;
                        }
                        else // Si acierta
                        {
                          if (d == objetivo.tipo) // Si es objetivo
                          {
                            objetivo.largo--;
                            i--;
                            if (objetivo.largo <= 0) return; // Si ya esta cazado
                            
                            if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
                            {
                              d = t.disparo(x-i, y);

                              if (d == '0') // Si falla
                              {
                                oceanoDisparos[x-i-1][y-1] = 'X';
                                i--;
                              }
                              else // Si acierta
                              {
                                if (d == objetivo.tipo) // Si es objetivo
                                {
                                  objetivo.largo--;
                                  i--;
                                  if (objetivo.largo <= 0) return; // Si ya esta cazado
                                }
                                else if (d != '0') // Si no es objetivo
                                {
                                  Tipo o = new Tipo(d);
                                  o.largo--;
                                  pilaObjetivo.add(o);
                                }
                              }
                            }
                          }
                          else if (d != '0')
                          {
                            Tipo o = new Tipo(d);
                            o.largo--;
                            pilaObjetivo.add(o);
                          }
                        }
                      }
                    }
                    else
                    {
                      oceanoDisparos[x-i-1][y-1] = d;
                      if (d == objetivo.tipo) // Si es el objetivo
                      {
                        objetivo.largo--;
                        i++;
                        if (objetivo.largo <= 0) return;
                        
                        if (oceanoDisparos[x-i-1][y-1] == '0')
                        {
                          d = t.disparo(x-i, y);

                          System.out.println("Disparo en:" + y + ":" + (x-i) + " " + d);
                          if (d == '0') 
                          {
                            oceanoDisparos[x-i-1][y-1] = 'X';
                            // i = 
                          }
                          else
                          {
                            oceanoDisparos[x-i-1][y-1] = d;
                            if (d == objetivo.tipo) // Si es el objetivo
                            {
                              objetivo.largo--;
                              i++;
                              if (objetivo.largo <= 0) return;
                              
                            }
                            else if (d != '0')
                            {
                              Tipo o = new Tipo(d);
                              o.largo--;
                              pilaObjetivo.add(o);
                            }
                          }
                        }
                      }
                      else if (d != '0')
                      {
                        Tipo o = new Tipo(d);
                        pilaObjetivo.add(o);
                      }
                    }
                  }
                }
                else if (d != '0')
                {
                  Tipo o = new Tipo(d);
                  pilaObjetivo.add(o);
                }
              }
            }
          }
          else if (d != '0')
          {
            Tipo o = new Tipo(d);
            pilaObjetivo.add(o);
          }
        }
      }          
    }          
  }
}
