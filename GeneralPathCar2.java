import java.awt.*;
import java.awt.geom.*;

public class GeneralPathCar2 extends Frame {
    // Constructor
    GeneralPathCar2() {
        // Enables the closing of the window.
        addWindowListener(new MyFinishWindow());
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Use antialiasing to have nicer lines.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // The lines should have a thickness of 3.0 instead of 1.0.
        BasicStroke bs = new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        g2d.setStroke(bs);

        // The GeneralPath to describe the car.
        GeneralPath gp = new GeneralPath();

        // Start at the lower front of the car.
        gp.moveTo(60, 120);
        // front underbody
        gp.lineTo(80, 120);
        // front wheel
        gp.quadTo(90, 140, 100, 120);
        // middle underbody
        gp.lineTo(160, 120);
        // rear wheel
        gp.quadTo(170, 140, 180, 120);
        // rear underbody
        gp.lineTo(200, 120);
        // rear
        gp.curveTo(195, 100, 200, 80, 160, 80);
        // roof
        gp.lineTo(110, 80);
        // windscreen
        gp.lineTo(90, 100);
        // bonnet
        gp.lineTo(60, 100);
        // front
        gp.lineTo(60, 120);

        // Draw the car.
        g2d.draw(gp);

        // Draw control points
        drawDot(g2d, 90, 140, 5);
        drawDot(g2d, 170, 140, 5);
        drawDot(g2d, 195, 100, 5);

        // Reset the stroke to solid line for the coordinate system
        g2d.setStroke(new BasicStroke(1.0f));

        // Draw a coordinate system.
        drawSimpleCoordinateSystem(200, 150, g2d);
    }

    /**
     * Draws a coordinate system (according to the window coordinates).
     *
     * @param xmax x-coordinate to which the x-axis should extend.
     * @param ymax y-coordinate to which the y-axis should extend.
     * @param g2d  Graphics2D object for drawing.
     */
    public static void drawSimpleCoordinateSystem(int xmax, int ymax, Graphics2D g2d) {
        // Implementation remains the same as provided in the original code
    }

    /**
     * Draw a dot at the specified location.
     *
     * @param g2d  Graphics2D object for drawing.
     * @param x    x-coordinate of the center of the dot.
     * @param y    y-coordinate of the center of the dot.
     * @param size diameter of the dot.
     */
    public static void drawDot(Graphics2D g2d, int x, int y, int size) {
        g2d.setColor(Color.RED);
        g2d.fillOval(x - size / 2, y - size / 2, size, size);
        g2d.setColor(Color.BLACK);
    }

    public static void main(String[] argv) {
        GeneralPathCar2 f = new GeneralPathCar2();
        f.setTitle("General Path Car");
        f.setSize(300, 300);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
