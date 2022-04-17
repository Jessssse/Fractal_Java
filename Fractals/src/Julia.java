//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Julia extends Fractal {
    Julia() {
    }

    public int dot(Complex zc) {
        new Complex();
        Complex c = new Complex(0.3D, 0.02D);
        Complex z = zc;

        for(int i = 1; i < this.max; ++i) {
            z = z.sqr().add(c);
            //z = z.add(z.sqr().add(zc));
            if (z.sqrModulus() >= 4.0D) {
                return i;
            }
        }

        return 0;
    }
}
