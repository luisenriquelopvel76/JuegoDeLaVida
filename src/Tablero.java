import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.Cleaner;
import java.util.Arrays;
import java.util.Random;



public class Tablero implements Runnable{
    private final Celda[][] tablero;
    private final Random random;

    public Tablero() {
        this.tablero = new Celda[Configuracion.altoDelTablero][Configuracion.anchoDelTablero];
        this.random = new Random();
        iniciarTablero();
    }

    private void iniciarTablero() {
        for (int i = 0; i < Configuracion.altoDelTablero; i++) {
            for (int j = 0; j < Configuracion.anchoDelTablero; j++) {
                tablero[i][j] = celdaAleatoria();
            }
        }
    }

    private Celda celdaAleatoria(){
                int estado = random.nextInt(3);
                switch (estado) {
                    case 0:
                        return new  Animal(Configuracion.energiaInicialAnimal);

                    case 1:
                       return new Planta(Configuracion.EnergiaInicialPlanta);

                    default:
                       return null;

                }
            }
    public void pasoTiempo() {
        for (int i = 0; i < Configuracion.altoDelTablero; i++) {
            for (int j = 0; j < Configuracion.anchoDelTablero; j++) {
                if (tablero[i][j] != null) {
                    tablero[i][j].pasoDelTiempo();
                }
            }
        }
        iniciarTablero();
        moverAnimales();
        registrarEstadisticas();
    }

    private void moverAnimales() {
        for (int i = 0; i < Configuracion.altoDelTablero; i++) {
            for (int j = 0; j < Configuracion.anchoDelTablero; j++) {
                if (tablero[i][j] instanceof Animal) {
                    moverAnimal(i, j) ;
                }
            }
        }

    }

    private void moverAnimal(int x, int y) {

        int nuevoX = x + random.nextInt(3) - 1;
        int nuevoY = y + random.nextInt(3) - 1;
        if (nuevoX >= 0 && nuevoX < Configuracion.altoDelTablero && nuevoY >= 0 && nuevoY < Configuracion.anchoDelTablero) {
            x += nuevoX;
            y += nuevoY;
        }
    }

    private void registrarEstadisticas() {
        try (FileWriter escribir = new FileWriter("Estadisticas.csv", true)) {
            int numeroDePlantas = 0;
            int numeroDeAnimales = 0;
            int numeroDeNacimientos = 0;
            int numeroDeMuertes = 0;

            for (int i = 0; i < Configuracion.altoDelTablero; i++) {
                for (int j = 0; j < Configuracion.anchoDelTablero; j++) {
                    if (tablero[i][j] instanceof Planta) {
                        numeroDePlantas++;
                    } else if (tablero[i][j] instanceof Animal) {
                        numeroDeAnimales++;
                    }
                }
            }
            escribir.append("El Numero de plantas es: ").append(String.valueOf(numeroDePlantas)).append(", El numero de animales es: ").append(String.valueOf(numeroDeAnimales)).append(", El numero de nacimientos es: ").append(String.valueOf(numeroDeNacimientos)).append(" y El numero de muertos es: ").append(String.valueOf(numeroDeMuertes)).append("\n");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < Configuracion.altoDelTablero; i++) {
            for (int j = 0; j < Configuracion.anchoDelTablero; j++) {
                if (tablero[i][j] == null) {
                    System.out.print(" . ");
                } else {
                    System.out.print(tablero[i][j]);

                }
            }
            System.out.println();
        }

    }
    public void reiniciarTablero() {
        // Usar secuencia de escape ANSI para limpiar la pantalla
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public void run() {
        while (true) {
            reiniciarTablero();
            pasoTiempo();
            imprimirTablero();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
