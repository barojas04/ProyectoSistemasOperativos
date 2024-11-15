public class Recurso {
    private int impresorasDisponibles;
    private int escaneresDisponibles;
    // Otros recursos

    public Recurso(int impresorasTotales, int escaneresTotales) {
        this.impresorasDisponibles = impresorasTotales;
        this.escaneresDisponibles = escaneresTotales;
        // Inicializar otros recursos
    }

    public boolean asignarRecursos(int impresorasRequeridas, int escaneresRequeridos) {
        if (impresorasDisponibles >= impresorasRequeridas && escaneresDisponibles >= escaneresRequeridos) {
            impresorasDisponibles -= impresorasRequeridas;
            escaneresDisponibles -= escaneresRequeridos;
            return true;
        }
        return false;
    }

    public void liberarRecursos(int impresorasLiberadas, int escaneresLiberados) {
        impresorasDisponibles += impresorasLiberadas;
        escaneresDisponibles += escaneresLiberados;
    }
}
