import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Transforms2D extends JPanel {


        private class Display extends JPanel {
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                g2.translate(300,300);  // Moves (0,0) to the center of the display.

                // TODO Apply transforms here, depending on the value of whichTransform!
                int whichTransform = transformSelect.getSelectedIndex();
                switch (whichTransform){
                    case 0: break;

                    case 1:
                        g2.scale(0.5,0.5);
                        break;

                    case 2:
                        g2.rotate(0.75);
                        break;

                    case 3:
                        g2.scale(0.4,0.8);
                        g2.rotate(Math.toRadians(180));

                        break;
                    case 4:
                        g2. shear(0.40, 0);
                        break;

                    case 5:
                        g2.scale(1,0.35);
                        g2.translate(0,-800);
                        break;

                    case 6:
                        g2.shear(0,-0.5);
                        g2.rotate(Math.PI/2);
                        break;
                    case 7:
                        g2.scale(0.35,0.8);
                        g2.rotate(Math.toRadians(180));

                        break;
                    case 8:
                        g2.translate(-70, 100);
                        g2.rotate(Math.toRadians(30));
                        g2.scale(1,0.5);

                        break;
                    case 9:
                        g2.translate(60,0);
                        g2.shear(0, 0.30);
                        g2.rotate(Math.toRadians(180));
                        break;
                }




                g2.setPaint(Color.BLUE);
                g2.drawPolygon(poly);
                g2.fill(poly);
            }
        }

        private Display display;
    Polygon poly = new Polygon();
    private JComboBox<String> transformSelect;

    public Transforms2D() throws IOException {
            int n = 3, r = 150;

            for (int i = 0; i < n; i++ ) {
                poly.addPoint((int) Math.round
                        (r * Math.cos((Math.PI / 2 + 2 * Math.PI * i) / n)),
                        (int) Math.round(r * Math.sin((Math.PI / 2 + 2 * Math.PI * i) / n)));
            }

            display = new Display();
            display.setBackground(Color.GREEN);
            display.setPreferredSize(new Dimension(600,600));
            transformSelect = new JComboBox<String>();
            transformSelect.addItem("None");
            for (int i = 1; i < 10; i++) {
                transformSelect.addItem("No. " + i);
            }
            transformSelect.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    display.repaint();
                }
            });
            setLayout(new BorderLayout(3,3));
            setBackground(Color.GRAY);
            setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
            JPanel top = new JPanel();
            top.setLayout(new FlowLayout(FlowLayout.CENTER));
            top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
            top.add(new JLabel("Transform: "));
            top.add(transformSelect);
            add(display,BorderLayout.CENTER);
            add(top,BorderLayout.NORTH);
        }

        public static void main(String[] args) throws IOException {
            JFrame window = new JFrame("2D Transforms");
            window.setContentPane(new Transforms2D());
            window.pack();
            window.setResizable(false);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
            window.setVisible(true);
        }

}
