public abstract class Celda {
    private final Tipo tipo;

    public enum Tipo {
        ANIMAL,
        PLANTA,
        VACIA
    }

    public Celda(Tipo tipo) {
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public abstract void pasoDelTiempo();
}

