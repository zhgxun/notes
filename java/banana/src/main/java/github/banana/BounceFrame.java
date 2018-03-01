package github.banana;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class BounceFrame extends JFrame {
    private BallComponent comp;
    private static final int STEPS = 1000;
    private static final int DELAY = 30;

    BounceFrame() {
        setTitle("Bounce");
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event -> addBall());
        addButton(buttonPanel, "Close", event -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    private void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    /**
     * 每次点击都开启一个线程来运行
     */
    private void addBall() {
        Ball ball = new Ball();
        comp.add(ball);

        (new Thread(() -> {
            try {
                for (int i = 1; i <= STEPS; i++) {
                    ball.move(comp.getBounds());
                    comp.paint(comp.getGraphics());
                    Thread.sleep(DELAY);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
    }
}