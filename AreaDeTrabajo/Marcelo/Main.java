import java.util.ArrayList;
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

  public static void main(String[] args) {
      // Tamaño del tablero
      for (int i = 0; i < 1000000; i++) {
        System.out.println("Juego " + (i+1));
        Tablero tablero = new Tablero(10);
        // tablero.Imprimir();
        // ListaDisparos.add("y:x");
        Juego(tablero);
        ListaDeIntentos.add(intentos);
      }

      // Calcular promedio de intentos
      int suma = 0;
      for (Integer i : ListaDeIntentos) {
        suma += i;
      }

      System.out.println("Promedio de intentos: " + suma/ListaDeIntentos.size());
      
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

    // ListaDisparos.add("y:x");
    for (int i = 1; i<=100; i++) {
      // Generar numero random
      boolean par = false;
      intentos = t.ganar();
      // System.out.println("Disparo #" + ListaDisparos.size());
      if (intentos == 0)
      {
        int x, y;
        do {
          Random r = new Random();
          x = r.nextInt(10) + 1;
          y = r.nextInt(10) + 1;
          if (x%2 == 0 && y%2 == 1)
            par = true;
          if (y%2 == 0 && x%2 == 1)
            par = true;
          
        } while (oceanoDisparos[x-1][y-1] != '0' || par == false);
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
          // Iterator<Tipo> it = pilaObjetivo.iterator();
          // while (it.hasNext()) {
          //   modoCaza(oceanoDisparos, t, it.next());
          // }
          // for (Tipo o : pilaObjetivo) {
          for(int j=0; j<pilaObjetivo.size(); j++) {
            modoCaza(oceanoDisparos, t, pilaObjetivo.get(j));
            // j++;
            // System.out.println(">>> Modo caza activado: " + d);


            // System.out.println("--------------------");
            // for (char[] oD : oceanoDisparos) {
            //   for (char oD2 : oD) {
            //     System.out.print(oD2 + " ");
            //   }
            //   System.out.println();
            // }
          }
          pilaObjetivo.clear();
        }        
      }
      else
      {
        System.out.println("Ganaste en " + intentos + " intentos");
        // System.exit(0);
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
        do
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
            // System.out.println("Punto repetido " + (y-left+right) + ":" + (x-up+down));
            repetir = false;
          }
        while (repetir);
        up--;
      }

      if (up <= 0) {
        // Disparar Derecha
        right++;
        if (left>0)
          left = 0;
        do
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
        while (repetir);
        right--;
      }

      // Dispara Abajo
      if (right <= 0)
      {
        down++;
        if (up > 0)
          up = 0;
        do
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
        while (repetir);
        down--;
      }
      // Disparar Izquierda
      if (down <= 0)
      {
        left++;
        if (right > 0)
          right = 0;
        do
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
        while (repetir);
        left--;
      }

    }
  }
}
//   public static void modoCaza1(char[][] oceanoDisparos, int x, int y, Tablero t, Tipo objetivo) {
//     int i = 1;
//     while (true) {
//       // if (objetivo.largo <= 0) return;
//       if (objetivo.largo <= 0) return;

//       // Dispara arriba
//       if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
//       {
//         char d = t.disparo(x-i, y); // Dispara
//         if (d == '0') // Si falla
//         {
//           oceanoDisparos[x-i-1][y-1] = 'X';
//         }
//         else // Si acierta
//         {
//           oceanoDisparos[x-i-1][y-1] = d;
//           if (d == objetivo.tipo)// Si es el objetivo
//           {
//             objetivo.largo--;
//             i++;
//             if (objetivo.largo <= 0) return; // Si ya esta cazado
            
//             if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
//             {
//               d = t.disparo(x-i, y);
//               if (d == '0') // Si falla
//               {
//                 oceanoDisparos[x-i-1][y-1] = 'X';
//                 i--;
                
//                 if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
//                 {
//                   d = t.disparo(x-i, y);

//                   if (d == '0') // Si falla
//                   {
//                     oceanoDisparos[x-i-1][y-1] = 'X';
//                     i--;
//                   }
//                   else // Si acierta
//                   {
//                     if (d == objetivo.tipo) // Si es objetivo
//                     {
//                       objetivo.largo--;
//                       i--;
//                       if (objetivo.largo <= 0) return; // Si ya esta cazado
                      
//                       if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
//                       {
//                         d = t.disparo(x-i, y);

//                         if (d == '0') // Si falla
//                         {
//                           oceanoDisparos[x-i-1][y-1] = 'X';
//                           i--;
//                         }
//                         else // Si acierta
//                         {
//                           if (d == objetivo.tipo) // Si es objetivo
//                           {
//                             objetivo.largo--;
//                             i--;
//                             if (objetivo.largo <= 0) return; // Si ya esta cazado
//                           }
//                           else if (d != '0') // Si no es objetivo
//                           {
//                             Tipo o = new Tipo(d);
//                             o.largo--;
//                             pilaObjetivo.add(o);
//                           }
//                         }
//                       }
//                     }
//                     else if (d != '0')
//                     {
//                       Tipo o = new Tipo(d);
//                       o.largo--;
//                       pilaObjetivo.add(o);
//                     }
//                   }
//                 }
//               }
//               else // Si acierta
//               {
//                 oceanoDisparos[x-i-1][y-1] = d;
//                 if (d == objetivo.tipo) // Si es el objetivo
//                 {
//                   objetivo.largo--;
//                   i++;
//                   if (objetivo.largo <= 0) return; // Si ya esta cazado
                  
//                   if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
//                   {
//                     d = t.disparo(x-i, y);
//                     System.out.println("Disparo en:" + y + ":" + (x-i) + " " + d);
//                     if (d == '0') // Si falla
//                     {
//                       oceanoDisparos[x-i-1][y-1] = 'X';
//                       i--;
//                       if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
//                       {
//                         d = t.disparo(x-i, y);

//                         if (d == '0') // Si falla
//                         {
//                           oceanoDisparos[x-i-1][y-1] = 'X';
//                           i--;
//                         }
//                         else // Si acierta
//                         {
//                           if (d == objetivo.tipo) // Si es objetivo
//                           {
//                             objetivo.largo--;
//                             i--;
//                             if (objetivo.largo <= 0) return; // Si ya esta cazado
                            
//                             if (oceanoDisparos[x-i-1][y-1] == '0') // Comprobar si el disparo es posible
//                             {
//                               d = t.disparo(x-i, y);

//                               if (d == '0') // Si falla
//                               {
//                                 oceanoDisparos[x-i-1][y-1] = 'X';
//                                 i--;
//                               }
//                               else // Si acierta
//                               {
//                                 if (d == objetivo.tipo) // Si es objetivo
//                                 {
//                                   objetivo.largo--;
//                                   i--;
//                                   if (objetivo.largo <= 0) return; // Si ya esta cazado
//                                 }
//                                 else if (d != '0') // Si no es objetivo
//                                 {
//                                   Tipo o = new Tipo(d);
//                                   o.largo--;
//                                   pilaObjetivo.add(o);
//                                 }
//                               }
//                             }
//                           }
//                           else if (d != '0')
//                           {
//                             Tipo o = new Tipo(d);
//                             o.largo--;
//                             pilaObjetivo.add(o);
//                           }
//                         }
//                       }
//                     }
//                     else
//                     {
//                       oceanoDisparos[x-i-1][y-1] = d;
//                       if (d == objetivo.tipo) // Si es el objetivo
//                       {
//                         objetivo.largo--;
//                         i++;
//                         if (objetivo.largo <= 0) return;
                        
//                         if (oceanoDisparos[x-i-1][y-1] == '0')
//                         {
//                           d = t.disparo(x-i, y);

//                           System.out.println("Disparo en:" + y + ":" + (x-i) + " " + d);
//                           if (d == '0') 
//                           {
//                             oceanoDisparos[x-i-1][y-1] = 'X';
//                             // i = 
//                           }
//                           else
//                           {
//                             oceanoDisparos[x-i-1][y-1] = d;
//                             if (d == objetivo.tipo) // Si es el objetivo
//                             {
//                               objetivo.largo--;
//                               i++;
//                               if (objetivo.largo <= 0) return;
                              
//                             }
//                             else if (d != '0')
//                             {
//                               Tipo o = new Tipo(d);
//                               o.largo--;
//                               pilaObjetivo.add(o);
//                             }
//                           }
//                         }
//                       }
//                       else if (d != '0')
//                       {
//                         Tipo o = new Tipo(d);
//                         pilaObjetivo.add(o);
//                       }
//                     }
//                   }
//                 }
//                 else if (d != '0')
//                 {
//                   Tipo o = new Tipo(d);
//                   pilaObjetivo.add(o);
//                 }
//               }
//             }
//           }
//           else if (d != '0')
//           {
//             Tipo o = new Tipo(d);
//             pilaObjetivo.add(o);
//           }
//         }
//       }          
//     }          
//   }
// }
