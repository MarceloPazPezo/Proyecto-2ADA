package Java;

public class Tipo {
    public char tipo;
    public int largo;
    public int marcax, marcay;

    public Tipo(char tipo, int x, int y) {
        this.tipo = tipo;
        this.marcax = x;
        this.marcay = y;
        if (tipo == 'A') {
            this.largo = 5;
        } else if (tipo == 'B') {
            this.largo = 4;
        } else if (tipo == 'C') {
            this.largo = 3;
        } else if (tipo == 'S') {
            this.largo = 3;
        } else if (tipo == 'D') {
            this.largo = 2;
        }
    }
}
