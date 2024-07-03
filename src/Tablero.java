import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.Cleaner;
import java.util.Arrays;
import java.util.Random;



public class Tablero extends Thread {
    private final Celda[][] tablero;
    private final Random random;

    public Tablero() {
        this.tablero = new Celda[Configuracion.altoDelTablero][Configuracion.anchoDelTablero];
        this.random = new Random();
        iniciarTablero();
    }



//    @Override
//    public String toString() {
//        return "Tablero"
//
//                +
//                "tablero=" + Arrays.toString(tablero) +
//                ", random=" + random +
//                '}'
//                ;
//    }

    private void iniciarTablero() {
        for (int i = 0; i < Configuracion.altoDelTablero; i++) {
            for (int j = 0; j < Configuracion.anchoDelTablero; j++) {
                int estado = random.nextInt(3);

                switch (estado) {
                    case 0:
                        tablero[i][j] = new Animal(Configuracion.energiaInicialAnimal);
                        break;
                    case 1:
                        tablero[i][j] = new Planta(Configuracion.EnergiaInicialPlanta);
                        break;
                    default:
                        tablero[i][j] = null;
                        break;
                }
            }
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
        moverAnimales();
        registrarEstadisticas();
    }

    private void moverAnimales() {
        for (int i = 0; i < Configuracion.altoDelTablero; i++) {
            for (int j = 0; j < Configuracion.anchoDelTablero; j++) {
                if (tablero[i][j] instanceof Animal) {
                    moverAnimal(i, j);
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
                    System.out.print("vacio" + " |");
                } else {
                    System.out.print(tablero[i][j] + " |");

                }
            }
            System.out.println();
        }

    }

    public void run() {
        while (true) {
            pasoTiempo();
            imprimirTablero();
            try {
                Thread.sleep(1000); // Pausa de 1 segundo entre cada paso de tiempo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
