//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends Thread {
    public static void main(String[] args) {

        Tablero tablero = new Tablero();
        Thread hilo = new Thread(tablero);

//        tablero.pasoTiempo();
//        tablero.imprimirTablero();
        hilo.start();


    }
    }
