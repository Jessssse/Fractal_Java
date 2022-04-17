//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.ImageObserver;
import javax.swing.JPanel;

public class FractalPanel extends JPanel implements FractalListener {
    private static final long serialVersionUID = 1L;
    FractalView[] view = new FractalView[4];

    public FractalPanel() {
        for(int i = 0; i < this.view.length; ++i) {
            this.view[i] = new FractalView();
            this.view[i].setPanel(this);
            // Ici pour changer de type de Fractal
            this.view[i].setFractal(new Mandelbrot());
            this.view[i].setFractalListener(this);
        }

        double min = -2D;
        double max = 2D;
        double taille = max - min;
        System.out.println("length " + this.view.length);

        for(int i = 0; i < this.view.length; ++i) {
            this.view[i].setMinMax(-2.0D, 1.0D, min, min + taille / (double)this.view.length);
            min += taille / (double)this.view.length;
        }

        this.addComponentListener(new ComponentListener() {
            public void componentShown(ComponentEvent e) {
            }

            public void componentResized(ComponentEvent e) {
                Dimension sz = FractalPanel.this.getSize();
                new Dimension(sz.width, sz.height / FractalPanel.this.view.length);

                for(int i = 0; i < FractalPanel.this.view.length; ++i) {
                    FractalPanel.this.view[i].setViewSize(sz);
                    if (!FractalPanel.this.view[i].isAlive()) {
                        System.out.println("Thread is not Alive ! ");
                        FractalPanel.this.view[i].start();
                    }
                }

            }

            public void componentMoved(ComponentEvent e) {
            }

            public void componentHidden(ComponentEvent e) {
            }
        });
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension sz = this.getSize();
        if (this.view != null) {
            g.drawImage(this.view[0].getImage(), 0, 0, sz.width, sz.height / this.view.length, new ImageObserver() {
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }

        for(int i = 0; i < this.view.length; ++i) {
            if (this.view != null) {
                g.drawImage(this.view[i].getImage(), 0, i * sz.height / this.view.length, sz.width, sz.height / this.view.length, new ImageObserver() {
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                        return false;
                    }
                });
            }
        }

    }

    public void imageDone() {
        this.repaint();
    }
}
