import java.awt.*;
import java.awt.geom.*;
import java.util.concurrent.TimeUnit;

public class BeatingHeart extends Frame {
    private static final int HEART_SIZE = 100;
    private static final int ANIMATION_DURATION = 2; // in seconds
    private static final int FPS = 60;

    private double t = 0;

    public BeatingHeart(int width, int height) {
        setSize(width, height);
        setTitle("Beating Heart Animation");
        setVisible(true);

        // Assuming MyFinishWindow is defined in a separate file
        MyFinishWindow windowListener = new MyFinishWindow();
        addWindowListener(windowListener);

        Thread animationThread = new Thread(() -> {
            while (true) {
                t += 1.0 / (ANIMATION_DURATION * FPS);
                if (t >= 1.0) {
                    t = 0;
                }
                repaint();
                try {
                    TimeUnit.MILLISECONDS.sleep(1000 / FPS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        animationThread.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        double scale = 1 + Math.sin(2 * Math.PI * t) * 0.1;

        int heartX = getWidth() / 2;
        int heartY = getHeight() / 2;
        drawHeart(g2d, heartX, heartY, scale);
    }

    private void drawHeart(Graphics2D g2d, int x, int y, double scale) {
        g2d.setColor(Color.RED);
        Shape heart = createHeartShape(x, y, scale);
        g2d.fill(heart);
    }

    private Shape createHeartShape(int x, int y, double scale) {
        Path2D.Double path = new Path2D.Double();

        int size = (int) (HEART_SIZE * scale);

        path.moveTo(x, y - size / 4);
        path.curveTo(x + size / 2, y - size,
                x + size / 2, y - size / 4,
                x, y + size / 2);
        path.curveTo(x - size / 2, y - size / 4,
                x - size / 2, y - size,
                x, y - size / 4);

        return path;
    }

    public static void main(String[] args) {
        int width = 800;
        int height = 600;
        BeatingHeart f = new BeatingHeart(width, height);
        f.setLocationRelativeTo(null);
    }
}
