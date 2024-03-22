import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.util.Objects;


public class VentanaGirasol extends JFrame {

    // Constructor de la clase
    public VentanaGirasol() throws HeadlessException, IOException, FontFormatException {
        // Configuración básica de la ventana
        configurarVentana();
        // Inicialización de los componentes de la ventana
        iniciarComponentes();
    }

    // Método para configurar la ventana
    private void configurarVentana() {
        setTitle("Flores Amarillas");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Recursos/Girasol.png")));
        setSize(400, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0, 0, this.getBounds().width, this.getBounds().height, 30, 30));
        // Manejo del cierre de la ventana con la tecla Escape
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    System.out.println("Hasta pronto corazón...");
                    System.exit(0);
                }
            }
        });
    }

    // Método para inicializar los componentes de la ventana
    private void iniciarComponentes() throws IOException, FontFormatException {
        getContentPane().setLayout(null);

        // Panel de fondo mdoficado con una imagen
        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 400, 550);
        panel.setBackground(Color.WHITE);

        // Manejo del clic en el panel para mostrar los girasoles
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                animatedLabel(panel, e.getX(), e.getY());
            }
        });

        // Carga de fuente personalizada
        Font miamaFont = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(getClass().getResourceAsStream("/Recursos/miama.otf")));
        miamaFont = miamaFont.deriveFont(30f);

        // Etiqueta de texto del fondo
        JLabel txt = new JLabel("Tan lindas como tú");
        txt.setBounds(0, 550, 400, 50);
        txt.setHorizontalAlignment(JLabel.CENTER);
        txt.setVerticalAlignment(JLabel.BOTTOM);
        txt.setForeground(new Color(33, 162, 30));
        txt.setFont(miamaFont);

        // Agregar componentes a la ventana
        getContentPane().add(panel);
        getContentPane().add(txt);
    }

    // Método para animar la etiqueta al hacer clic
    private static void animatedLabel(JPanel panel, int x, int y) {
        ImageIcon flor = new ImageIcon(Toolkit.getDefaultToolkit().getImage(VentanaGirasol.class.getResource("/Recursos/flor.png")).getScaledInstance(50, 50, Image.SCALE_SMOOTH));

        JLabel label = new JLabel();
        label.setSize(10, 10);
        label.setIcon(flor);
        label.setLocation(x - 5, y - 5);
        panel.add(label);
        panel.repaint();

        Timer timerAparicion = new Timer(20, new ActionListener() {
            int currentSize = 10;

            @Override
            public void actionPerformed(ActionEvent e) {
                currentSize += 2;

                if (currentSize <= 50) {
                    label.setSize(currentSize, currentSize);
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timerAparicion.start();

        Timer timerDesvanecer = new Timer(7000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();
                panel.remove(label);
                panel.repaint();
            }
        });
        timerDesvanecer.start();
    }
}
