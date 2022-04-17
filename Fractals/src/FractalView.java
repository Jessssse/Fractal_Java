//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class FractalView extends Thread {
    private FractalListener listener;
    private double xmin;
    private double xmax;
    private double ymin;
    private double ymax;
    int width;
    int height;
    BufferedImage image;
    FractalPanel panel;
    Fractal fractal;
    static int nb = -1;
    int idx = 0;
    private boolean reStart = false;

    public void setFractalListener(FractalListener l) {
        this.listener = l;
    }

    public synchronized void reDo() {
        if (this.isAlive()) {
            this.reStart = true;
            this.notify();
        }

    }

    public FractalView() {
    }

    BufferedImage getImage() {
        return this.image;
    }

    int getWidth() {
        return this.width;
    }

    int getHeight() {
        return this.height;
    }

    Fractal getFractal() {
        return this.fractal;
    }

    public void setMinMax(double xMin, double xMax, double yMin, double yMax) {
        this.xmin = xMin;
        this.xmax = xMax;
        this.ymin = yMin;
        this.ymax = yMax;
        this.reDo();
    }

    public void setViewSize(Dimension sz) {
        this.width = sz.width;
        this.height = sz.height;
        this.reDo();
    }

    public void setFractal(Fractal fractal) {
        this.fractal = fractal;
    }

    public void setPanel(FractalPanel fractalPanel) {
        this.panel = fractalPanel;
    }

    Complex getPosFractaFromPanel(int x, int y) {
        double X = this.xmin + (double)x * (this.xmax - this.xmin) / (double)this.width;
        double Y = this.ymin + (double)y * (this.ymax - this.ymin) / (double)this.height;
        return new Complex(X, Y);
    }

    Point getPosViewFromFractal(Complex z) {
        int x = (int)((z.getReal() - this.xmin) * (double)this.width / (this.xmax - this.xmin));
        int y = (int)((z.getImg() - this.ymin) * (double)this.height / (this.ymax - this.ymin));
        return new Point(x, y);
    }

    public void calculate() {
        long startTime = System.currentTimeMillis();
        System.out.println("h " + this.height + " w " + this.width);
        int w = this.width;
        int h = this.height;
        this.image = new BufferedImage(w, h, 1);
        int[] data = new int[h * w];

        for(int y = 0; y < h; ++y) {
            if (this.reStart) {
                return;
            }

            for(int x = 0; x < w; ++x) {
                data[x + y * w] = 1000 * this.fractal.dot(this.getPosFractaFromPanel(x, y));
            }
        }

        this.image.getRaster().setDataElements(0, 0, w, h, data);
        System.out.println(String.format("time%d", System.currentTimeMillis() - startTime));
        if (this.listener != null) {
            this.listener.imageDone();
        }

        synchronized(this) {
            try {
                this.wait();
            } catch (InterruptedException var8) {
                var8.printStackTrace();
            }

        }
    }

    public void run() {
        while(true) {
            this.calculate();
            this.reStart = false;
        }
    }
}
