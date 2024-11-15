import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InterfazPrincipal extends JFrame {
    private JButton btnCargar, btnIniciar, btnDetener;
    private JTextArea logProcesos;
    private PanelMemoria panelMemoria;
    private PanelProceso panelProceso;
    private Planificador planificador; // Instancia del Planificador
    private Thread simulacionThread; // Hilo para la simulación

    public InterfazPrincipal() {
        setTitle("Simulador de Planificador de Procesos");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de control
        JPanel panelControl = new JPanel();
        btnCargar = new JButton("Cargar Procesos");
        btnIniciar = new JButton("Iniciar Simulación");
        btnDetener = new JButton("Detener Simulación");
        panelControl.add(btnCargar);
        panelControl.add(btnIniciar);
        panelControl.add(btnDetener);
        add(panelControl, BorderLayout.NORTH);

        // Panel de log de procesos
        logProcesos = new JTextArea();
        logProcesos.setEditable(false);
        JScrollPane scrollLog = new JScrollPane(logProcesos);
        add(scrollLog, BorderLayout.EAST);

        // Panel de memoria y procesos
        panelMemoria = new PanelMemoria();
        panelProceso = new PanelProceso();
        add(panelMemoria, BorderLayout.CENTER);
        add(panelProceso, BorderLayout.WEST);

        // Inicializar el planificador con recursos y memoria
        planificador = new Planificador(1024, 2, 1); // Memoria de ejemplo, 2 impresoras, 1 escáner

        // Agregar funcionalidad a los botones
        btnCargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarProcesos();
            }
        });

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSimulacion();
            }
        });

        btnDetener.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                detenerSimulacion();
            }
        });

        setVisible(true);
    }

    private void cargarProcesos() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // Parsear línea y crear proceso (asumimos que el archivo tiene un formato específico)
                    String[] partes = line.split(",");
                    int id = Integer.parseInt(partes[0]);
                    int prioridad = Integer.parseInt(partes[1]);
                    int tiempoCPU = Integer.parseInt(partes[2]);
                    int memoria = Integer.parseInt(partes[3]);
                    int impresoras = Integer.parseInt(partes[4]);
                    int escaneres = Integer.parseInt(partes[5]);
                    Proceso proceso = new Proceso(id, prioridad, tiempoCPU, memoria, impresoras, escaneres);
                    planificador.agregarProceso(proceso);
                    panelProceso.agregarProceso(id, prioridad, tiempoCPU, memoria);
                }
                logProcesos.append("Procesos cargados desde el archivo.\n");
            } catch (IOException ex) {
                logProcesos.append("Error al leer el archivo: " + ex.getMessage() + "\n");
            }
        }
    }

    private void iniciarSimulacion() {
        if (simulacionThread == null || !simulacionThread.isAlive()) {
            simulacionThread = new Thread(() -> {
                planificador.ejecutarProcesos();
                logProcesos.append("Simulación completada.\n");
            });
            simulacionThread.start();
            logProcesos.append("Simulación iniciada.\n");
        }
    }

    private void detenerSimulacion() {
        if (simulacionThread != null && simulacionThread.isAlive()) {
            simulacionThread.interrupt();
            logProcesos.append("Simulación detenida.\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazPrincipal());
    }
}

}

