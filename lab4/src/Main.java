import javax.swing.*;
import java.awt.*;

public class Main {
    private static void initUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                 UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }

        JFrame frame = new JFrame("Wolfenstein/2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        BoxLayout boxLayout = new BoxLayout(contentPane, BoxLayout.PAGE_AXIS);
        frame.setLayout(boxLayout);

        WolfensteinPanel wolfensteinPanel = new WolfensteinPanel();
        contentPane.add(wolfensteinPanel);

        JLabel label = new JLabel("Здоровье: 100");
        contentPane.add(label);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(Main::initUI);
    }
}