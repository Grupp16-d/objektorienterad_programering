import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphicsDemo implements Runnable {

    private class Surface extends JPanel implements ActionListener {

        int width = -1, height;
        double radiusTime = 0.0;

        private class MyMouseListener extends MouseAdapter {
            @Override
            public void mouseClicked(MouseEvent e) {
                width = e.getX();
                height = e.getY();
                repaint();
            }
        }


        public Surface() {
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(500,500));

            addMouseListener(new MyMouseListener());
            Timer timer = new Timer(100, this);
            timer.start();
        }

        @Override
        public void paintComponent(Graphics g) {
            g.clearRect(0,0,getWidth(),getHeight());
            if (width == -1) {
                width = getWidth();
                height = getHeight();
            }
            for (int i = 0; i < width; i += 5) {
                g.drawLine(0, 0, i, height - 1);
            }

            int ovalw = (int)(0.5 * (Math.sin(radiusTime) + 1.0) * width);
            int ovalh = (int)(0.5 * (Math.sin(radiusTime) + 1.0) * height);
            g.fillOval(width/2  - ovalw/2, height/2 - ovalh/2, ovalw, ovalh);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            radiusTime += 0.1;
            repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GraphicsDemo());
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("GraphicsDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Surface surface = new Surface();
        frame.add(surface);

        frame.pack();
        frame.setVisible(true);

    }

}