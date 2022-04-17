//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Mandelbrot extends Fractal {
    Mandelbrot() {
    }

    public int dot(Complex zc) {
        Complex z = new Complex();

        for(int i = 1; i < this.max; ++i) {

            z = z.sqr().add(zc);

            if (z.sqrModulus() >= 4.0D) {
                return i;
            }
        }

        return 0;
    }
}
