#include <iostream>
#include "Tablero.cpp"

using namespace std;

int main() {
    int n;
    cout << "Ingrese la cantidad de tableros: ";
    cin >> n;
    // _sleep(500);
    for(int i = 0; i < n; i++) {
        Tablero tablero(10);
        _sleep(500); // Creo que las funciones que generan el tablero, talvez lo random, se demora mucho en generar un tablero y me generaba errores, tuve que darle un poco de tiempo para cada 1
        tablero.Imprimir();
    }
    return 0;
}