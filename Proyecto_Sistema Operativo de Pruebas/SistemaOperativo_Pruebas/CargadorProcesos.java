import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CargadorProcesos {

    private String rutaArchivo;

    public CargadorProcesos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public void cargarProcesos(Planificador planificador) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                int tiempoLlegada = Integer.parseInt(datos[1]);
                int prioridad = Integer.parseInt(datos[2]);
                int tiempoCPU = Integer.parseInt(datos[3]);
                int memoriaRequerida = Integer.parseInt(datos[4]);
                int impresorasNecesarias = Integer.parseInt(datos[5]);
                int escaneresNecesarios = Integer.parseInt(datos[6]);
                Proceso proceso = new Proceso(id, prioridad, tiempoCPU, memoriaRequerida, impresorasNecesarias, escaneresNecesarios);
                planificador.agregarProceso(proceso);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
