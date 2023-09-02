import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

public class SierpinskiTriangle extends JPanel {
    private int depth;

    public SierpinskiTriangle(int depth) {
        this.depth = depth;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        Point p1 = new Point(width / 2, 0);
        Point p2 = new Point(0, height);
        Point p3 = new Point(width, height);

        drawSierpinski(g2d, depth, p1, p2, p3);
    }

    private void drawSierpinski(Graphics2D g2d, int depth, Point p1, Point p2, Point p3) {
        if (depth == 0) {
            g2d.draw(new Line2D.Double(p1, p2));
            g2d.draw(new Line2D.Double(p2, p3));
            g2d.draw(new Line2D.Double(p3, p1));
        } else {
            Point mid1 = midpoint(p1, p2);
            Point mid2 = midpoint(p2, p3);
            Point mid3 = midpoint(p3, p1);

            drawSierpinski(g2d, depth - 1, p1, mid1, mid3);
            drawSierpinski(g2d, depth - 1, mid1, p2, mid2);
            drawSierpinski(g2d, depth - 1, mid3, mid2, p3);
        }
    }

    private Point midpoint(Point p1, Point p2) {
        return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Triángulo de Sierpinski");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);

            int depth = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el numero de iteraciones"));

            if (args.length == 1) {
                try {
                    depth = Integer.parseInt(args[0]);
                } catch (NumberFormatException e) {
                    System.err.println("La profundidad debe ser un número entero. Usando profundidad predeterminada.");
                }
            }

            frame.add(new SierpinskiTriangle(depth));
            frame.setVisible(true);
        });
    }
}
