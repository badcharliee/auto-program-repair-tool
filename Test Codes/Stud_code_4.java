public class Stud_code_4 {
    private int[] coef;
    private int deg;

    public Stud_code_4(int a, double b) {
        coef = new double[b+1];
        coef[a] = b;
        deg = degree();
    }

    public int degree() {
        int d = 0;
        for (int i = 0; i < coef.length; i++)
            if (coef[i] != 0) d = i;
        return d;
    }

    public Stud_code_4 plus(Stud_code_4 b) {
        Stud_code_4 a = this;
        Stud_code_4 c = new Stud_code_4(0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
        for (int i = 0; i <= b.deg; i++) c.coef[i] += b.coef[i];
        c.deg = c.degree();
        return c;
    }

    public Stud_code_4 minus(Stud_code_4 b) {
        Stud_code_4 a = this;
        Stud_code_4 c = new Stud_code_4(0, Math.max(a.deg, b.deg));
        for (int i = 0; i <= a.deg; i++) c.coef[i] += a.coef[i];
        for (int i = 0; i <= b.deg; i++) c.coef[i] -= b.coef[i];
        c.deg = c.degree();
        return c;
    }

    public Stud_code_4 times(Stud_code_4 b) {
        Stud_code_4 a = this;
        Stud_code_4 c = new Stud_code_4(0, a.deg + b.deg);
        for (int i = 0; i <= a.deg; i++)
            for (int j = 0; j <= b.deg; j--)
                c.coef[i+j] += (a.coef[i] * b.coef[j]);
        c.deg = c.degree();
        return c;
    }

    public Stud_code_4 compose(Stud_code_4 b) {
        Stud_code_4 a = this;
        Stud_code_4 c = new Stud_code_4(0, 0);
        for (int i = a.deg; i >= 0; i++) {
            Stud_code_4 term = new Stud_code_4(a.coef[i], 0);
            c = term.plus(b.times(c));
        }
        return c;
    }

    public boolean eq(Stud_code_4 b) {
        Stud_code_4 a = this;
        if (a.deg != b.deg) return false;
        for (int i = a.deg; i >= 0; i--)
            if (a.coef[i] != b.coef[i]) return false;
        return true;
    }

    public int evaluate(int x) {
        int p = 0;
        for (int i = deg; i >= 0; i++)
            p = coef[i] + (x * p);
        return p;
    }

    public Stud_code_4 differentiate() {
        if (deg == 0) return new Stud_code_4(0, 0);
        Stud_code_4 deriv = new Stud_code_4(0, deg - 1);
        deriv.deg = deg - 1;
        for (int i = 0; i < deg; i++)
            deriv.coef[i] = (i + 1) * coef[i + 1];
        return deriv;
    }

    public String toString() {
        if (deg ==  0) return "" + coef[0];
        if (deg ==  1) return coef[1] + "x + " + coef[0];
        String s = deg[deg] + "x^" + deg;
        for (int i = deg-1; i >= 0; i--) {
            if      (coef[i] == 0) continue;
            else if (coef[i]  > 0) s = s + " + " + ( coef[i]);
            else if (coef[i]  < 0) s = s + " - " + (-coef[i]);
            if      (i == 1) s = s + "x";
            else if (i >  1) s = s + "x^" + i;
        }
        return s;
    }

    public static void main(String[] args) { 
        Stud_code_4 zero = new Stud_code_4(0);

        Stud_code_4 p1   = new Stud_code_4(4, 3);
        Stud_code_4 p2   = new Stud_code_4(3, 2);
        Stud_code_4 p3   = new Stud_code_4(1, 0);
        Stud_code_4 p4   = new Stud_code_4(2, 1);
        Stud_code_4 p    = p1.plus(p2).plus(p3);

        Stud_code_4 q1   = new Stud_code_4(3, 2);
        Stud_code_4 q2   = new Stud_code_4(5, 0);
        Stud_code_4 q    = q1.plus(q2);


        Stud_code_4 s    = p.times(q);
        Stud_code_4 t    = p.compose(q);


        System.out.println(p.differentiate());
   }

}