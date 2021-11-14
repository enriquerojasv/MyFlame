package myflame;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rvenr
 */
public class Flame extends Thread {

    int WIDTH;
    int HEIGHT;
    int[][] temperature;
    BufferedImage flamei;
    byte[] buffer;
    Random rand = new Random();

    public Flame(int WIDTH, int HEIGHT) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.temperature = new int[WIDTH][HEIGHT];
        this.flamei = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_4BYTE_ABGR);
        this.buffer = ((DataBufferByte) this.flamei.getRaster().getDataBuffer()).getData();
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
    }

    public int[][] getTemperature() {
        return temperature;
    }

    public void setTemperature(int[][] temperature) {
        this.temperature = temperature;
    }

    public BufferedImage getFlamei() {
        return flamei;
    }

    public void setFlamei(BufferedImage flamei) {
        this.flamei = flamei;
    }

    public void setRate() {

    }

    public void setPalette() {

    }

    public void flameEvolve() {
        createFlameImage(this.temperature);

        final int pixelLength = 4;
        for (int pixel = 0, row = 0, col = 0;
                pixel + 3 < this.buffer.length; pixel += pixelLength) {

            buffer[pixel] = (byte) 255; // alpha

            if (temperature[col][row] > 200) {

                buffer[pixel + 1] = (byte) 255; // blue
                buffer[pixel + 2] = (byte) 255; // green
                buffer[pixel + 3] = (byte) 255; // red
            } else if (temperature[col][row] > 150) {

                buffer[pixel + 1] = (byte) 0; // blue
                buffer[pixel + 2] = (byte) 255; // green
                buffer[pixel + 3] = (byte) 255; // red
            } else if (temperature[col][row] > 125) {

                buffer[pixel + 1] = (byte) 0; // blue
                buffer[pixel + 2] = (byte) 150; // green
                buffer[pixel + 3] = (byte) 255; // red
            } else if (temperature[col][row] > 100) {

                buffer[pixel + 1] = (byte) 0; // blue
                buffer[pixel + 2] = (byte) 100; // green
                buffer[pixel + 3] = (byte) 255; // red
            } else if (temperature[col][row] > 75) {

                buffer[pixel + 1] = (byte) 0; // blue
                buffer[pixel + 2] = (byte) 0; // green
                buffer[pixel + 3] = (byte) 255; // red
            } else if (temperature[col][row] > 50) {

                buffer[pixel + 1] = (byte) 0; // blue
                buffer[pixel + 2] = (byte) 0; // green
                buffer[pixel + 3] = (byte) 185; // red
            } else if (temperature[col][row] > 10) {

                buffer[pixel + 1] = (byte) 0; // blue
                buffer[pixel + 2] = (byte) 0; // green
                buffer[pixel + 3] = (byte) temperature[col][row]; // red
            } else if (temperature[col][row] > 0) {

                buffer[pixel + 1] = (byte) temperature[col][row]; // blue
                buffer[pixel + 2] = (byte) temperature[col][row]; // green
                buffer[pixel + 3] = (byte) temperature[col][row]; // red
            }

            col++;
            if (col == this.WIDTH) {

                col = 0;
                row++;
            }
        }
    }

    public void createCoal() {
    }

    public void createSparks() {

        final int pixelLength = 4;
        for (int pixel = 0, row = 0, col = 0;
                pixel + 3 < this.buffer.length; pixel += pixelLength) {

            if (row == this.HEIGHT - 48) {

                int random = rand.nextInt(100);

                if (random < 40) {
                    temperature[col][row] = 255;
                    buffer[pixel] = (byte) 255; // alpha
                    buffer[pixel + 1] = (byte) 0; // blue
                    buffer[pixel + 2] = (byte) 0; // green
                    buffer[pixel + 3] = (byte) 255; // red
                }
            }

            col++;
            if (col == this.WIDTH) {

                col = 0;
                row++;
            }
        }
    }

    private void createFlameImage(int[][] temperature) {
        int up;
        int left;
        int right;
        int down;
        int cRand = 2;
        int[][] temp = new int[temperature.length][temperature[0].length];

        for (int i = 1; i < temperature.length - 1; i++) {
            for (int j = 1; j < temperature[0].length - 1; j++) {
                up = temperature[i - 1][j];
                left = temperature[i][j - 1];
                right = temperature[i][j + 1];
                down = temperature[i + 1][j];

                int avg = (up + left + right + down) / 4;
                int cooling = rand.nextInt(cRand);
                avg -= cooling;
                if (avg < 0) {
                    avg = 0;
                }
                temp[i][j - 1] = avg;
            }
        }

        System.arraycopy(temp, 0, temperature, 0, temp.length);

    }

    @Override
    public void run() {

        while (true) {
            createSparks();
            flameEvolve();
            try {
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Viewer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
