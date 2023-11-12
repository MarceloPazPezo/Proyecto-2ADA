#include <iostream> // Para usar "cout" y "cin"
#include <utility> // Para usar la clase "pair"

using namespace std;

// Definir la matriz como una variable global
int matriz[9][9];

int Sample1[9][9] = {
    {0,2,3,4,5,6,7,8,0},
    {4,5,6,7,8,9,1,2,3},
    {7,8,9,1,2,3,4,5,6},
    {2,1,4,3,6,5,8,9,7},
    {3,6,5,8,9,7,2,1,4},
    {8,9,7,2,1,4,3,6,5},
    {5,3,1,6,4,2,9,7,8},
    {6,4,2,9,7,8,5,3,1},
    {0,7,8,5,3,1,6,4,0}
};

int Sample2[9][9] = {
    {0,4,6,3,0,0,0,0,0},
    {0,0,0,9,7,0,0,5,0},
    {0,0,9,0,0,5,1,2,0},
    {3,5,0,0,0,0,6,4,2},
    {0,6,2,1,0,3,0,7,0},
    {7,9,0,0,6,4,0,0,1},
    {1,0,0,7,8,0,0,6,5},
    {9,0,7,0,0,6,0,0,0},
    {0,3,0,4,0,1,8,0,0}
};

int Sample3[9][9] = {
    {3,0,0,4,0,6,0,0,9},
    {0,6,0,7,0,9,1,0,5},
    {0,9,8,0,0,0,0,4,6},
    {2,0,3,0,0,0,9,8,7},
    {5,0,0,0,9,0,3,0,1},
    {0,0,9,2,3,1,0,0,0},
    {9,8,0,0,4,0,0,6,0},
    {0,0,4,6,0,3,7,0,0},
    {6,3,5,9,0,0,4,0,0}
};

int Sample4[9][9] = {
    {0,5,0,0,2,1,7,9,8},
    {0,0,0,6,5,4,1,0,0},
    {0,2,3,0,0,0,4,0,5},
    {2,1,4,7,9,8,0,0,0},
    {3,0,0,0,0,2,0,7,0},
    {8,0,7,5,6,0,2,4,1},
    {6,0,0,1,0,5,0,8,7},
    {5,0,1,8,7,9,0,2,4},
    {9,0,8,0,0,0,5,0,3}
};

int Sample5[9][9] = {
    {0,0,3,6,4,2,0,0,8},
    {0,9,7,0,3,0,6,0,0},
    {0,0,0,0,0,8,5,0,1},
    {7,0,0,0,1,0,3,0,5},
    {4,2,0,0,6,0,0,9,0},
    {5,0,0,0,9,0,2,0,0},
    {0,0,0,0,0,6,7,8,9},
    {0,0,8,1,0,3,0,5,0},
    {6,4,5,7,0,0,0,0,3}
};

// Ejemplo de Sudoku extremo
int Sample6[9][9] = {
    {0,0,0,1,0,0,0,2,0},
    {4,0,6,0,0,0,0,0,0},
    {0,0,0,0,0,0,7,8,9},
    {8,0,7,0,0,0,0,0,0},
    {0,0,0,9,0,0,2,0,1},
    {0,3,0,0,5,0,0,0,7},
    {0,4,2,0,0,3,9,0,0},
    {6,5,0,0,0,8,0,4,0},
    {0,7,0,0,0,2,6,0,0}
};

// Funciones de backtracking

/**
 * La función "reject" verifica si una celda determinada viola las reglas de Sudoku y devuelve verdadero si lo hace,
 * y falso de lo contrario.
 */
bool reject(pair<int, int> nodo) {
    // Acepta si la celda está vacía
    if (matriz[nodo.first][nodo.second] == 0) return false;
    
    // Verificar la fila y la columna
    for(int i = 0; i < 9; i++) {
        if(i != nodo.second && matriz[nodo.first][i] == matriz[nodo.first][nodo.second]) {
            return true; // Rechazar si hay un valor duplicado en la fila
        }
        if(i != nodo.first && matriz[i][nodo.second] == matriz[nodo.first][nodo.second]) {
            return true; // Rechazar si hay un valor duplicado en la columna
        }
    }

    // Verificar la subcuadrícula 3x3
    for(int i = 0; i < 3; i++) {    
        for(int j = 0; j < 3; j++) {
            if(((nodo.first - nodo.first % 3) + i != nodo.first || (nodo.second - nodo.second % 3) + j != nodo.second) && matriz[(nodo.first - nodo.first % 3) + i][(nodo.second - nodo.second % 3) + j] == matriz[nodo.first][nodo.second]) {
                return true; // Rechazar si hay un valor duplicado en la subcuadrícula
            }
        }
    }

    // Aceptar si no se viola ninguna regla
    return false;
}

/**
 * La función "accept" comprueba si el sudoku se ha llenado de forma completa y correcta.
 */

bool accept(pair<int, int> nodo) {
    // Verificar si todas las celdas han sido llenadas
    for(int i = 0; i < 9; i++) {
        for(int j = 0; j < 9; j++) {
            if(matriz[i][j] == 0) {
                return false; // No es una solución si alguna celda está vacía
            }
            if (matriz[i][j] > 9 || matriz[i][j] < 0 || reject(pair<int, int>(i, j))) { // Verificar si la celda está vacía o si viola las reglas o si contiene un valor inválido
                cout << "El Sudoku no tiene solucion "<< endl;
                return false; // No es una solución si alguna celda viola las reglas
            }
        }
    }

    // Es una solución si todas las celdas han sido llenadas
    return true;
}


/**
 * La función "first" devuelve las coordenadas de la primera celda vacía en una matriz de sudoku, o (-1, -1) si no hay celdas vacías.
 */
pair<int, int> first(pair<int, int> nodo) {
    if (matriz[nodo.first][nodo.second] != 0) {
        for (int i=0; i<9; i++) {
            for (int j=0; j<9; j++) {
                if (matriz[i][j] == 0) {
                    return pair<int, int>(i, j); // Devolver la siguiente celda vacía
                }
            }
        }
        return pair<int, int>(-1, -1); // Si la celda no está vacía, no hay hijos
    }    
    
    for(int num = 1; num <= 9; num++) { // Probar todos los números del 1 al 9
        matriz[nodo.first][nodo.second] = num;
        if(!reject(nodo)) { // Si el número es válido, entonces es el primer hijo
            return nodo;
        }
    }

    matriz[nodo.first][nodo.second] = 0; // Si no hay hijos válidos, restablecer la celda a 0
    return pair<int, int>(-1, -1); // Devolver (-1, -1) para indicar que no hay hijos
}


/**
 * La función "next" devuelve el siguiente nodo válido en una matriz, dado el nodo actual.
 */
pair<int, int> next(pair<int, int> nodo) {
    // Llama al siguiente hijo
    for(int num = matriz[nodo.first][nodo.second] + 1; num <= 9; num++) { // Probar todos los números del 1 al 9
        matriz[nodo.first][nodo.second] = num; 
        if(!reject(nodo)) { // Si el número es válido, entonces es el siguiente hijo
            return nodo;
        }
    }
    matriz[nodo.first][nodo.second] = 0; // Si no hay hijos válidos, restablecer la celda a 0
    return pair<int, int>(-1, -1); // Devolver (-1, -1) para indicar que no hay hijos
}

/**
 * La función "output" imprime una matriz 9x9.
 */
void output() {
    // Imprimir la matriz
    for(int i = 0; i < 9; i++) {
        for(int j = 0; j < 9; j++) {
            cout << matriz[i][j] << " ";
        }
        cout << endl;
    }
}


/**
 * La función "backtracking" explora recursivamente todas las soluciones posibles mediante la generación y exploración todos los nodos válidos.
 */
void backtracking(pair<int, int> nodo) {
    if(reject(nodo)) return; // Rechazar si la celda viola las reglas
    if(accept(nodo)) { // Aceptar si la matriz es una solución
        output(); // Imprime salida
        exit(0); // Termina el programa
    }
    pair<int, int> hijo = first(nodo); // Obtener el primer hijo
    while(hijo != pair<int, int>(-1, -1)) { // Asume que (-1, -1) es <nulo>
        backtracking(hijo); 
        hijo = next(hijo); // Obtener el siguiente hijo
    }
}

int main() {
    // Leer la matriz desde la entrada estándar
    for(int i = 0; i < 9; i++) {
        for(int j = 0; j < 9; j++) {
            cin >> matriz[i][j];
            // matriz[i][j] = Sample1[i][j]; // Para probar el primer ejemplo medio
            // matriz[i][j] = Sample2[i][j]; // Para probar el segundo ejemplo medio
            // matriz[i][j] = Sample3[i][j]; // Para probar el tercer ejemplo medio
            // matriz[i][j] = Sample4[i][j]; // Para probar el cuarto ejemplo medio
            // matriz[i][j] = Sample5[i][j]; // Para probar el quinto ejemplo extremo no funciona
            // matriz[i][j] = Sample6[i][j]; // Para probar el sexto ejemplo extremo no funciona
        }
    }
    // output();
    // cout << endl;

    backtracking(pair<int, int>(0, 0)); // Iniciar el backtracking

    return 0;
}