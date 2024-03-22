import javax.swing.*;
import java.awt.*;

class BackgroundPanel extends JPanel {

    //Objeto Image para guardar la imagen del fondo
    private Image backgroundImage;

    public BackgroundPanel() {
        //Obtenemos la imagen que ira de fondo en el panel
        Image originalImage = Toolkit.getDefaultToolkit()
                .getImage(getClass().getResource("/Recursos/fondoGirasoles.png"));
        //Escalamos la imagen del fondo al tamaño adecuado del panel de destino
        backgroundImage = originalImage
                .getScaledInstance(400, 550, Image.SCALE_SMOOTH);

    }

    // Sobreescribimos el metodo para mostrar nuestra imagen como fondo
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, this);
        }
    }
}
