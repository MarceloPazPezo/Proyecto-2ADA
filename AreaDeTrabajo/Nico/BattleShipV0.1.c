#include <stdlib.h>
#include <stdio.h>

void creaciontablero(short matriz[10][10]) {
    for (size_t i = 0; i < 10; i++) {
        for (size_t j = 0; j < 10; j++) {
            matriz[i][j] = 0;
        }
    }
}

void llenadotableroPC(short tipoBarco, short matriz[10][10]){
    
    short x = rand()%10;
    short y = rand()%10;
    printf("%d,%d",x,y);
    int seleccion = 1;
    //printf("----%d------", seleccion);
    
    if (matriz[x][y] == 0)
    {
        switch (seleccion)
        {
            case 1:

                //horizontal
                for (size_t i = 0; i <= tipoBarco; i++){
                    if (matriz[x+i][y] != 0)
                    {
                        y++;
                    }
                }
                for (size_t i = 0; i <= tipoBarco; i++){
                    if (matriz[x+i][y] != 0)
                    {
                        x++;
                    }
                    if (x+i > 10)
                    {
                        matriz[x-i+1][y] = tipoBarco;
                    }else{
                        matriz[x+i][y] = tipoBarco;
                    }
                }

            break;
        
            case 2:

                //vertical
                //horizontal
                for (size_t i = 0; i <= tipoBarco; i++){
                    if (x+i > 10)
                    {
                        matriz[x][y-i+1] = tipoBarco;
                    }else{
                        matriz[x][y+i] = tipoBarco;
                    }
                }
                
            break;

            default:
                break;
        }
        
    }
}

int main() {
    
    short PortaAviones = 5;
    short Acorazado = 4;
    short Submarino = 3;
    short Destructor = 3;
    short BarcoPatrulla = 3;

    short tableroPC[10][10];
    creaciontablero(tableroPC);

    //Asignacion posicion barcos PC
    llenadotableroPC(Acorazado,tableroPC);     

    for (size_t i = 0; i < 10; i++) {
        if (i == 9) {
            printf("%d>", i + 1);
        } else {
            printf("%d> ", i + 1);
        }
        for (size_t j = 0; j < 10; j++) {
            printf(" %d ", tableroPC[i][j]);
        }
        printf("\n");
    }

    
    return 0;
}
