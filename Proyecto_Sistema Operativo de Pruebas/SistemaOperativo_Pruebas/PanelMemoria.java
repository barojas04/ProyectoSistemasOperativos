import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelMemoria extends JPanel {
    private List<JPanel> bloquesMemoria;
    private int totalBloques = 16; // Número de bloques de memoria (puedes ajustarlo según el tamaño de tu memoria)

    public PanelMemoria() {
        setLayout(new GridLayout(4, 4, 5, 5)); // Layout para mostrar bloques en una cuadrícula de 4x4
        bloquesMemoria = new ArrayList<>();

        // Inicializar los bloques de memoria
        for (int i = 0; i < totalBloques; i++) {
            JPanel bloque = new JPanel();
            bloque.setBackground(Color.GREEN); // Verde para representar memoria libre
            bloquesMemoria.add(bloque);
            add(bloque);
        }

        JLabel titulo = new JLabel("Estado de la Memoria");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);
    }

    // Método para actualizar el estado de un bloque de memoria (0 para libre, 1 para ocupado)
    public void actualizarBloque(int indice, boolean ocupado) {
        if (indice >= 0 && indice < bloquesMemoria.size()) {
            bloquesMemoria.get(indice).setBackground(ocupado ? Color.RED : Color.GREEN);
        }
    }

    // Método para limpiar la memoria (marcar todos los bloques como libres)
    public void limpiarMemoria() {
        for (JPanel bloque : bloquesMemoria) {
            bloque.setBackground(Color.GREEN);
        }
    }
}
