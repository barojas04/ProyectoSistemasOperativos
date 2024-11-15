import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelProceso extends JPanel {
    private JTable tablaProcesos;
    private DefaultTableModel modelo;

    public PanelProceso() {
        setLayout(new BorderLayout());

        // Configurar el modelo de la tabla
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Prioridad");
        modelo.addColumn("Tiempo Restante");
        modelo.addColumn("Memoria Requerida");

        // Configurar la tabla
        tablaProcesos = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tablaProcesos);
        add(scrollPane, BorderLayout.CENTER);

        // Título del panel
        JLabel titulo = new JLabel("Procesos en ejecución");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);
    }

    // Método para agregar un proceso a la tabla
    public void agregarProceso(int id, int prioridad, int tiempoRestante, int memoriaRequerida) {
        modelo.addRow(new Object[]{id, prioridad, tiempoRestante, memoriaRequerida});
    }

    // Método para limpiar la tabla (si quieres actualizar la lista de procesos)
    public void limpiarTabla() {
        modelo.setRowCount(0);
    }
}
