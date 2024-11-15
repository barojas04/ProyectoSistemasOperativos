import java.util.ArrayList;
import java.util.List;

public class Memoria {
    private int totalMemoria;
    private List<BloqueMemoria> bloques;

    public Memoria(int totalMemoria) {
        this.totalMemoria = totalMemoria;
        this.bloques = new ArrayList<>();
        // Inicializar con un único bloque libre que representa toda la memoria disponible
        this.bloques.add(new BloqueMemoria(0, totalMemoria, true));
    }

    public boolean asignarMemoria(int memoriaRequerida) {
        for (BloqueMemoria bloque : bloques) {
            if (bloque.isLibre() && bloque.getTamaño() >= memoriaRequerida) {
                int indice = bloques.indexOf(bloque);
                if (bloque.getTamaño() > memoriaRequerida) {
                    // Dividir el bloque si es más grande que lo requerido
                    bloques.add(indice + 1, new BloqueMemoria(bloque.getInicio() + memoriaRequerida, bloque.getTamaño() - memoriaRequerida, true));
                    bloque.setTamaño(memoriaRequerida);
                }
                bloque.setLibre(false);
                return true;
            }
        }
        return false;
    }

    public void liberarMemoria(int idProceso) {
        // Buscar y liberar el bloque de memoria asignado al proceso
        for (BloqueMemoria bloque : bloques) {
            if (bloque.getIdProceso() == idProceso) {
                bloque.setLibre(true);
                // Combinar bloques libres contiguos
                combinarBloquesLibres();
                break;
            }
        }
    }

    private void combinarBloquesLibres() {
        for (int i = 0; i < bloques.size() - 1; i++) {
            BloqueMemoria actual = bloques.get(i);
            BloqueMemoria siguiente = bloques.get(i + 1);
            if (actual.isLibre() && siguiente.isLibre()) {
                actual.setTamaño(actual.getTamaño() + siguiente.getTamaño());
                bloques.remove(i + 1);
                i--;
            }
        }
    }
}

class BloqueMemoria {
    private int inicio;
    private int tamaño;
    private boolean libre;
    private int idProceso;

    public BloqueMemoria(int inicio, int tamaño, boolean libre) {
        this.inicio = inicio;
        this.tamaño = tamaño;
        this.libre = libre;
        this.idProceso = -1; // -1 indica que no está asignado a ningún proceso
    }

    public int getInicio() { return inicio; }
    public int getTamaño() { return tamaño; }
    public boolean isLibre() { return libre; }
    public int getIdProceso() { return idProceso; }

    public void setTamaño(int tamaño) { this.tamaño = tamaño; }
    public void setLibre(boolean libre) { this.libre = libre; }
    public void setIdProceso(int idProceso) { this.idProceso = idProceso; }
}
