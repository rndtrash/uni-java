import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.TimerTask;

public class WolfensteinPanel extends JPanel {
    public static final int SCREEN_WIDTH = 320;
    public static final int SCREEN_HEIGHT = 240;

    private WolfensteinGameManager wolfGameManager = new WolfensteinGameManager();

    public WolfensteinPanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setFocusable(true);
        grabFocus();

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_W)
                    wolfGameManager.MoveForwards();
                if (keyCode == KeyEvent.VK_S)
                    wolfGameManager.MoveBackwards();
                if (keyCode == KeyEvent.VK_A)
                    wolfGameManager.LookLeft();
                if (keyCode == KeyEvent.VK_D)
                    wolfGameManager.LookRight();
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        Timer fpsTimer = new Timer(1000 / 60, e -> repaint());
        fpsTimer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Рисуем небо и землю
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.CYAN);
        g2d.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT / 2);
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0, SCREEN_HEIGHT / 2, SCREEN_WIDTH, SCREEN_HEIGHT / 2);

        int[] columns = wolfGameManager.getColumns(SCREEN_WIDTH);
        for (int i = 0; i < columns.length; i++) {
            int column = columns[i];
            if (column <= 0)
                continue;

            double columnNormalized = column / WolfensteinGameManager.PLAYER_VIEW_DISTANCE;
            int columnScreen = (int) (columnNormalized * SCREEN_HEIGHT);

            g2d.setColor(Color.getHSBColor(0f, 1f, (float) columnNormalized));
            g2d.drawLine(i, SCREEN_HEIGHT / 2 - columnScreen / 2, i, SCREEN_HEIGHT / 2 + columnScreen / 2);
        }

        // Рисуем карту
        for (int i = 0; i < WolfensteinGameManager.WORLD_WIDTH; i++)
            for (int j = 0; j < WolfensteinGameManager.WORLD_HEIGHT; j++) {
                g2d.setColor(wolfGameManager.World[i][j] == WolfensteinGameManager.CELL_WALL
                        ? Color.GREEN
                        : new Color(0, 0, 0, 0));
                g2d.fillRect(i * WolfensteinGameManager.CELL_SIZE, j * WolfensteinGameManager.CELL_SIZE, WolfensteinGameManager.CELL_SIZE, WolfensteinGameManager.CELL_SIZE);
            }

        // Рисуем игрока
        g2d.setColor(Color.RED);
        g2d.fillOval((int) (wolfGameManager.PlayerPosition.X - 3), (int) (wolfGameManager.PlayerPosition.Y - 3), 6, 6);
        g2d.drawLine((int) wolfGameManager.PlayerPosition.X,
                (int) wolfGameManager.PlayerPosition.Y,
                (int) (wolfGameManager.PlayerPosition.X + (int) (Math.cos(wolfGameManager.PlayerDirection) * 10)),
                (int) (wolfGameManager.PlayerPosition.Y + (int) (Math.sin(wolfGameManager.PlayerDirection) * 10)));
    }
}
