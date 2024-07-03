import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Tablero {
    private final Celda[][] tablero;
    private final Random random;

    public Tablero() {
        this.tablero = new Celda[Configuracion.tamanioDelTablero][Configuracion.tamanioDelTablero];
        this.random = new Random();
        iniciarTablero();
    }

    @Override
    public String toString() {
        return "Tablero{" +
                "tablero=" + Arrays.toString(tablero) +
                ", random=" + random +
                '}';
    }

    private void iniciarTablero() {
        for (int i = 0; i < Configuracion.tamanioDelTablero; i++) {
            for (int j = 0; j < Configuracion.tamanioDelTablero; j++) {
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
        for (int i = 0; i < Configuracion.tamanioDelTablero; i++) {
            for (int j = 0; j < Configuracion.tamanioDelTablero; j++) {
                if (tablero[i][j] != null) {
                    tablero[i][j].pasoDelTiempo();
                }
            }

        }
        moverAnimales();
        registrarEstadisticas();
    }

    private void moverAnimales() {
        int x = 0;
        int y = 0;
        int nuevoX = x + random.nextInt(3) - 1;
        int nuevoY = y + random.nextInt(3) - 1;
        if (nuevoX >= 0 && nuevoX < Configuracion.tamanioDelTablero && nuevoY >= 0 && nuevoY < Configuracion.tamanioDelTablero) {
            x = nuevoX + 1;
            y = nuevoY + 1;
        }
    }

    private void registrarEstadisticas() {
        try (FileWriter escribir = new FileWriter("Estadisticas.csv", true)) {
            int numeroDePlantas = 0;
            int numeroDeAnimales = 0;
            int numeroDeNacimientos = 0;
            int numeroDeMuertes = 0;

            for (int i = 0; i < Configuracion.tamanioDelTablero; i++) {
                for (int j = 0; j < Configuracion.tamanioDelTablero; j++) {
                    if (tablero[i][j] instanceof Planta) {
                        numeroDePlantas++;
                    } else if (tablero[i][j] instanceof Animal) {
                        numeroDeAnimales++;
                    }
                }
            }
            escribir.append("El Numero de plantas es: " + numeroDePlantas + ", El numero de animales es: " + numeroDeAnimales + ", El numero de nacimientos es: " + numeroDeNacimientos + " y El numero de muertos es: " + numeroDeMuertes + "\n");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < Configuracion.tamanioDelTablero; i++) {
            for (int j = 0; j < Configuracion.tamanioDelTablero; j++) {
                if (tablero[i][j] == null) {
                    System.out.println("vacio\t");
                } else {
                    System.out.print(tablero[i][j] + "\t");
                }
            }
        }
        System.out.println();
    }
}
