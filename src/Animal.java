public class Animal extends Celda {
    private static final int energiaMaxima = 20;
    private static int energiaDeVida = 5;
    private static final int edadMaxima = 20;
    private int energia;
    private int edad;

    public Animal(int energiaInicial) {
        super(Tipo.ANIMAL);
        this.energia = energiaInicial;
        this.edad = 0;
    }

    @Override
    public String toString() {
        return "Animal"
//                +
//                "" +
//                "energia=" + energia +
//                ", edad=" + edad +
//                '}'
                ;
    }

//    public int getEnergia() {
//        return energia;
//    }
//
//    public void setEnergia(int energia) {
//        this.energia = energia;
//    }
//
//    public int getEdad() {
//        return edad;
//    }
//
//    public void setEdad(int edad) {
//        this.edad = edad;
//    }
//
//    public void incrementaEdad() {
//        this.edad++;
//    }

    @Override
    public void pasoDelTiempo() {
        this.edad++;
        this.energia--;
        if (edad == edadMaxima) {
            energia = 0;
        }
    }

    public boolean sigueConVida() {
        if (edadMaxima > 20 && energiaDeVida == 0) {

        }
        return false;
    }
}
