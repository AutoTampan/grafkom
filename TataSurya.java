import java.awt.*;
import java.awt.geom.*;
import java.util.Date;

public class TataSurya extends Frame {
    private int windowWidth;
    private int windowHeight;
    private double planetAngle = 0;

    TataSurya(int height, int width) {
        addWindowListener(new MyFinishWindow());
        windowWidth = width;
        windowHeight = height;
    }

    public void sustain(long t) {
        long finish = (new Date()).getTime() + t;
        while ((new Date()).getTime() < finish) {
        }
    }

    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    
        AffineTransform yUp = new AffineTransform();
        yUp.setToScale(1, -1);
        AffineTransform translate = new AffineTransform();
        translate.setToTranslation(windowWidth / 2, windowHeight / 2);
        yUp.preConcatenate(translate);
    
        g2d.transform(yUp);
        g2d.setStroke(new BasicStroke(1.0f));
    
        // Draw the coordinate system
        drawSimpleCoordinateSystem(400, 400, g2d);
    
        g2d.setStroke(new BasicStroke(3.0f));
    
        // Draw the sun at the origin
        Ellipse2D.Double sun = new Ellipse2D.Double(-20, -20, 40, 40); // Bigger sun
        g2d.setColor(Color.YELLOW);
        g2d.fill(sun);
    
        // Calculate the position of the planet based on its orbit angle
        double planetX = 200 * Math.cos(planetAngle);
        double planetY = 200 * Math.sin(planetAngle);
    
        // Draw the planet
        Ellipse2D.Double planet = new Ellipse2D.Double(planetX - 5, planetY - 5, 10, 10);
        g2d.setColor(Color.BLUE);
        g2d.fill(planet);
    
        // Update planet angle for next frame
        planetAngle += (2 * Math.PI) / 1095; // 3 rotations per orbit
    
        // A short waiting time until the next frame is drawn.
        sustain(50);
    
        // Repaint to continue animation
        repaint();
    }
    


    /**
     * Draws a coordinate system.
     *
     * @param xmax x-coordinate to which the x-axis should extend.
     * @param ymax y-coordinate to which the y-axis should extend.
     * @param g2d  Graphics2D object for drawing.
     */
    public static void drawSimpleCoordinateSystem(int xmax, int ymax,
                                              Graphics2D g2d) {
    int xOffset = -xmax / 2;
    int step = 50;
    // Remember the actual font.
    Font fo = g2d.getFont();
    // Use a small font.
    int fontSize = 13;
    Font fontCoordSys = new Font("ARIAL", Font.PLAIN, fontSize);
    /*
     * Unfortunately, the transformation yUp applied to the Graphics2D object
     * will cause the letters to occur upside down. Therefore, generate an
     * upside down font which will appear correctly when drawn upside down.
     */
    // To make the font upside down, a reflection w.r.t. the x-axis is needed.
    AffineTransform flip = new AffineTransform();
    flip.setToScale(1, -1);
    // Shift the font back to the baseline after reflection.
    AffineTransform lift = new AffineTransform();
    lift.setToTranslation(0, fontSize);
    flip.preConcatenate(lift);

    // Generate the font with the letters upside down.
    Font fontUpsideDown = fontCoordSys.deriveFont(flip);

    g2d.setFont(fontUpsideDown);

    // x-axis
    g2d.drawLine(xOffset, 0, xmax + xOffset, 0);
    // Marks and labels for the x-axis.
    for (int i = xOffset + step; i <= xmax; i = i + step) {
        g2d.drawLine(i, -2, i, 2);
        g2d.drawString(String.valueOf(i), i - 7, -30);
    }

    // Reset to the original font.
    g2d.setFont(fo);
}



    public static void main(String[] argv) {
        int height = 600;
        int width = 600;
        TataSurya f = new TataSurya(height, width);
        f.setTitle("Tata Surya Animation");
        f.setSize(width, height);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
    }
}
