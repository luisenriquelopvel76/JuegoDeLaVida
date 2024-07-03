public class Animal extends Celda {
    private static final int energiaDeVida = 10;
    private static final int energiaMaxima = 50;
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
        return "Animal{" +
                "energia=" + energia +
                ", edad=" + edad +
                '}';
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public void incremetaEdad() {
        this.edad++;
    }

    @Override
    public void pasoDelTiempo() {
        this.edad++;
        this.energia--;
    }

    public boolean sigueConVida() {
        return this.energia > 0 && this.edad > edadMaxima;
    }
}
