#include <iostream>
#include <vector>
#include <random>
#include <chrono>

using namespace std;

class Tablero {
    static default_random_engine generator;
    vector<vector<char>> t;
    int intentos = 0;

    public:
        Tablero(int n) {
            unsigned seed = chrono::system_clock::now().time_since_epoch().count();
            generator.seed(seed);
            if(n < 10) n = 10;
            t = vector<vector<char>>(n+2, vector<char>(n+2, '0'));
            while(!ubicarNave(5, 'A'));
            while(!ubicarNave(4, 'B'));
            while(!ubicarNave(3, 'C'));
            while(!ubicarNave(3, 'S'));
            while(!ubicarNave(2, 'D'));
            intentos = 0;
        }

        char disparo(int x, int y) {
            intentos++;
            if(x < 1 || x > static_cast<int>(t.size())) return 0;
            if(y < 1 || y > static_cast<int>(t[0].size())) return 0;
            char aux = t[x][y];
            if(aux != '0') t[x][y] = 'X';
            return aux;
        }

    private:
        bool ubicarNave(int l, char tipo) {
            uniform_int_distribution<int> distribution(1, t.size()-2);
            int x = distribution(generator);
            int y = distribution(generator);
            int dir = generator() % 4;
            if(dir == 0 && x - (l-1) < 1) return false;
            if(dir == 1 && x + (l-1) > static_cast<int>(t.size())-1) return false;
            if(dir == 2 && y - (l-1) < 1) return false;
            if(dir == 3 && y + (l-1) > static_cast<int>(t[0].size())-1) return false;
            for(int i = 0; i < l; i++) {
                if(dir == 0 && (t[x-i][y] != '0' || t[x-i][y+1] != '0' || t[x-i][y-1] != '0')) return false;
                if(dir == 1 && (t[x+i][y] != '0' || t[x+i][y+1] != '0' || t[x+i][y-1] != '0')) return false;
                if(dir == 2 && (t[x][y-i] != '0' || t[x-1][y-i] != '0' || t[x+1][y-i] != '0')) return false;
                if(dir == 3 && (t[x][y+i] != '0' || t[x-1][y+i] != '0' || t[x+1][y+i] != '0')) return false;
            }
            if(dir == 0 && t[x-l][y] != '0') return false;
            if(dir == 1 && t[x+l][y] != '0') return false;
            if(dir == 2 && t[x][y-l] != '0') return false;
            if(dir == 3 && t[x][y+l] != '0') return false;
            for(int i = 0; i < l; i++) {
                if(dir == 0) t[x-i][y] = tipo;
                if(dir == 1) t[x+i][y] = tipo;
                if(dir == 2) t[x][y-i] = tipo;
                if(dir == 3) t[x][y+i] = tipo;
            }
            return true;
        }

    public:
        int ganar() {
            for(int i = 1; i < static_cast<int>(t[0].size())-1; i++)
                for(int j = 1; j < static_cast<int>(t.size())-1; j++)
                    if(t[i][j] != '0' && t[i][j] != 'X') return 0;
            return intentos;
        }

    public: // Privado
        void Imprimir() {
            for(int i = 1; i < static_cast<int>(t[0].size())-1; i++) {
                for(int j = 1; j < static_cast<int>(t.size())-1; j++)
                    cout << t[i][j] << " ";
                cout << endl;
            }
            cout << endl;
        }
};

default_random_engine Tablero::generator;