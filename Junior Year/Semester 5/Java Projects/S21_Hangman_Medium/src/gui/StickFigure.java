// add to package
package gui;

// import package modules
import gameLogic.Round;

// import java gui dependencies
import javax.swing.*;
import java.awt.*;

/**
 * Draws stick figure
 */
public class StickFigure extends JPanel {

    /**
     * Paint stick figure
     *
     * OVERRIDE: JComponent
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // I will not be drawing the gallows for this project. Here is my podium for the loser
        g2d.fillRect(50, 270, 150, 30);
        g2d.fillRect(70, 240, 110, 30);
        g2d.fillRect(90, 210, 70, 30);

        // conditional drawing (torso -> arms -> legs -> head)
        g2d.setStroke(new BasicStroke(5));
        if (Round.getLivesLeft() <= (Round.getStartingLives() - 6)) {
            g2d.drawOval(110, 20, 40, 40);
        }
        if (Round.getLivesLeft() <= (Round.getStartingLives() - 5)) {
            g2d.drawLine(130, 120, 140, 170);
        }
        if (Round.getLivesLeft() <= (Round.getStartingLives() - 4)) {
            g2d.drawLine(130, 120, 80, 170);
        }
        if (Round.getLivesLeft() <= (Round.getStartingLives() - 3)) {
            g2d.drawLine(130, 80, 180, 100);
        }
        if (Round.getLivesLeft() <= (Round.getStartingLives() - 2)) {
            g2d.drawLine(130, 80, 80, 100);
        }
        if (Round.getLivesLeft() <= (Round.getStartingLives() - 1)) {
            g2d.drawLine(130, 60, 130, 120);
        }
    }
}