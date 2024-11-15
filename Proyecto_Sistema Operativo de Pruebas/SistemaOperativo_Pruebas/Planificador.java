import java.util.LinkedList;
import java.util.Queue;

import java.util.LinkedList;
import java.util.Queue;

public class Planificador {
    private Queue<Proceso> colaTiempoReal;
    private Queue<Proceso> colaPrioridad1;
    private Queue<Proceso> colaPrioridad2;
    private Queue<Proceso> colaPrioridad3;
    private int memoriaDisponible;
    private int impresorasDisponibles;
    private int escaneresDisponibles;
    private Memoria memoria;
    private Recurso recursos;

    public Planificador(int memoriaTotal, int impresorasTotales, int escaneresTotales) {
        this.colaTiempoReal = new LinkedList<>();
        this.colaPrioridad1 = new LinkedList<>();
        this.colaPrioridad2 = new LinkedList<>();
        this.colaPrioridad3 = new LinkedList<>();
        this.memoriaDisponible = memoriaTotal;
        this.impresorasDisponibles = impresorasTotales;
        this.escaneresDisponibles = escaneresTotales;
        this.memoria = new Memoria(memoriaTotal);
        this.recursos = new Recurso(impresorasTotales, escaneresTotales);
    }

    public void agregarProceso(Proceso proceso) {
        switch(proceso.getPrioridad()) {
            case 0:
                colaTiempoReal.add(proceso);
                break;
            case 1:
                colaPrioridad1.add(proceso);
                break;
            case 2:
                colaPrioridad2.add(proceso);
                break;
            case 3:
                colaPrioridad3.add(proceso);
                break;
        }
    }

    public void ejecutarProcesos() {
        while (!colaTiempoReal.isEmpty() || !colaPrioridad1.isEmpty() || !colaPrioridad2.isEmpty() || !colaPrioridad3.isEmpty()) {
            if (!colaTiempoReal.isEmpty()) {
                ejecutarProceso(colaTiempoReal.poll());
            } else if (!colaPrioridad1.isEmpty()) {
                ejecutarProceso(colaPrioridad1.poll());
            } else if (!colaPrioridad2.isEmpty()) {
                ejecutarProceso(colaPrioridad2.poll());
            } else if (!colaPrioridad3.isEmpty()) {
                ejecutarProceso(colaPrioridad3.poll());
            }
        }
    }

    private void ejecutarProceso(Proceso proceso) {
        if (memoria.asignarMemoria(proceso.getMemoriaRequerida()) && recursos.asignarRecursos(proceso.getImpresorasNecesarias(), proceso.getEscaneresNecesarios())) {
            // Simular ejecución del proceso (por ejemplo, reducir el tiempo de CPU restante)
            while (proceso.getTiempoCPURestante() > 0) {
                proceso.actualizarTiempoCPU(1); // Supongamos que cada iteración representa un ciclo de CPU
            }
            // Liberar recursos y memoria al finalizar el proceso
            memoria.liberarMemoria(proceso.getId());
            recursos.liberarRecursos(proceso.getImpresorasNecesarias(), proceso.getEscaneresNecesarios());
        } else {
            // Reasignar el proceso a una cola de menor prioridad si no se pudieron asignar recursos
            if (proceso.getPrioridad() < 3) {
                proceso.setPrioridad(proceso.getPrioridad() + 1);
                agregarProceso(proceso);
            } else {
                // Manejar el caso en que no se pueda reasignar a una cola de menor prioridad
            }
        }
    }
}
