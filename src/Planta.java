public class Planta extends Celda {
    private static final int energiaMaxima = 50;
    private int energia;

    public Planta(int energiaInicial) {
        super(Tipo.PLANTA);
        this.energia = energiaInicial;
    }

    @Override
    public String toString() {
        return "Planta{" +
                "energia=" + energia +
                '}';
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void aumentaEnergia() {
        if (this.energia < energiaMaxima) {
            this.energia++;
        }
    }

    @Override
    public void pasoDelTiempo() {
        aumentaEnergia();
    }
}
