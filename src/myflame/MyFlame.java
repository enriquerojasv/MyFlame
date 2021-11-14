package myflame;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

public class MyFlame{
    private static JFrame frame;
    private static final String TITLE = "My Flame";
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 500;
    private static final Dimension SIZE = new Dimension(WIDTH, HEIGHT);

    public void setFlamePalette(){        
    }
    
    public void setFlameRate(){        
    }
    
    public void setViewerRate(){        
    }
    
    public void setPause(){        
    }
    
    public void setStop(){        
    }
        
    public static void main(String[] args) {
        frame = new JFrame(TITLE);
        frame.setPreferredSize(SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.BLACK);

        Flame flame1 = new Flame(WIDTH, HEIGHT);
        Viewer viewer = new Viewer(SIZE, 500, flame1);

        frame.add(viewer.getCanvas());

        frame.pack();
        frame.setVisible(true);

        flame1.run();
        viewer.run();
    }
}
