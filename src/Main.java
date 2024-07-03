//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Tablero tablero = new Tablero();
        for (int i = 0; i < 10; i++) {
            tablero.pasoTiempo();
            tablero.imprimirTablero();
            System.out.println();
        }
    }
}