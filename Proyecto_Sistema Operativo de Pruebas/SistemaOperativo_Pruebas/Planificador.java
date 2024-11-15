import java.util.LinkedList;
import java.util.Queue;

class Planificador {
    private Queue<Proceso> colaTiempoReal = new LinkedList<>();
    private Queue<Proceso> colaPrioridad1 = new LinkedList<>();
    private Queue<Proceso> colaPrioridad2 = new LinkedList<>();
    private Queue<Proceso> colaPrioridad3 = new LinkedList<>();

    @SuppressWarnings("unused")
    private int memoriaDisponible = 2048; // Memoria total en MB

    public void agregarProceso(Proceso proceso) {
        if (proceso.prioridad == 0) {
            colaTiempoReal.add(proceso);
        } else if (proceso.prioridad == 1) {
            colaPrioridad1.add(proceso);
        } else if (proceso.prioridad == 2) {
            colaPrioridad2.add(proceso);
        } else {
            colaPrioridad3.add(proceso);
        }
    }

    public void ejecutar() {
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
        // Simulación de la ejecución del proceso
        System.out.println("Ejecutando proceso ID: " + proceso.id);
        // Aquí agregar lógica para actualizar recursos, tiempo restante, y cambiar colas si es necesario
    }

    public static void main(String[] args) {
        Planificador planificador = new Planificador();

        // Agregar algunos procesos de ejemplo
        planificador.agregarProceso(new Proceso(1, 0, 0, 1, 64, 0, 0, 0, 0));
        planificador.agregarProceso(new Proceso(2, 1, 1, 2, 128, 1, 0, 0, 1));
        planificador.agregarProceso(new Proceso(3, 2, 3, 6, 128, 1, 0, 1, 2));

        // Iniciar la simulación del planificador
        planificador.ejecutar();
    }
}