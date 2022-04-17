//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

public class Complex implements Cloneable {
    private double re = 0.0D ;
    private double im = 0.0D;

    public Complex() {
    }

    public Complex(double r, double i) {
        this.re = r;
        this.im = i;
    }

    public double sqrModulus() {
        return this.re * this.re + this.im * this.im;
    }

    public Complex sqr() {
        return new Complex(this.re * this.re - this.im * this.im, 2.0D * this.re * this.im);
    }

    public Complex add(Complex z) {
        return new Complex(this.re + z.re, this.im + z.im);
    }

    public double getReal() {
        return this.re;
    }

    public double getImg() {
        return this.im;
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
