import javax.swing.*;
import java.awt.*;

class InterfazPrincipal extends JFrame {
    private JButton btnCargar, btnIniciar, btnDetener;
    private JTextArea logProcesos;
    private PanelMemoria panelMemoria;
    private PanelProceso panelProceso;

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

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InterfazPrincipal());
    }
}

