package myflame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rvenr
 */
public class Viewer extends Canvas implements Runnable {

    private final Canvas canvas;
    int rate;
    boolean paused = false;
    boolean stoped = false;
    Flame flame;

    public Viewer(Dimension size, int rate, Flame flame) {
        this.canvas = new Canvas();
        this.rate = rate;
        this.flame = flame;
        canvas.setPreferredSize(size);
        canvas.setMaximumSize(size);
        canvas.setMinimumSize(size);        
    }

    public Canvas getCanvas() {
        return this;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public void setStoped(boolean stoped) {
        this.stoped = stoped;
    }

    @Override
    public void paint(Graphics g) {
        for (int i = 0; i < flame.getWIDTH(); i++) {
            for (int j = 0; j < flame.getHEIGHT(); j++) {
                g.drawImage(flame.getFlamei(), 0, 0, null);

            }
        }
    }

    @Override
    public void run() {
        while (stoped == false) {
            this.repaint();
            try {
                sleep(rate);
            } catch (InterruptedException ex) {
                Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
