public class Inst_code_5 {
    private int[] coef;   
    private int degree;   
    
    public Inst_code_5(int a, int b) {
        coef = new int[b+1];
        coef[b] = a;
        reduce();
    }

    private void reduce() {
        degree = -1;
        for (int i = coef.length - 1; i >= 0; i--) {
            if (coef[i] != 0) {
                degree = i;
                return;
            }
        }
    }

    public int degree() {
        return degree;
    }

    public Inst_code_5 plus(Inst_code_5 thing) {
        Inst_code_5 poly = new Inst_code_5(0, Math.max(this.degree, thing.degree));
        for (int i = 0; i <= this.degree; i++) poly.coef[i] += this.coef[i];
        for (int i = 0; i <= thing.degree; i++) poly.coef[i] += thing.coef[i];
        poly.reduce();
        return poly;
    }

    public Inst_code_5 minus(Inst_code_5 thing) {
        Inst_code_5 poly = new Inst_code_5(0, Math.max(this.degree, thing.degree));
        for (int i = 0; i <= this.degree; i++) poly.coef[i] += this.coef[i];
        for (int i = 0; i <= thing.degree; i++) poly.coef[i] -= thing.coef[i];
        poly.reduce();
        return poly;
    }

    public Inst_code_5 times(Inst_code_5 thing) {
        Inst_code_5 poly = new Inst_code_5(0, this.degree + thing.degree);
        for (int i = 0; i <= this.degree; i++)
            for (int j = 0; j <= thing.degree; j++)
                poly.coef[i+j] += (this.coef[i] * thing.coef[j]);
        poly.reduce();
        return poly;
    }

    public Inst_code_5 compose(Inst_code_5 thing) {
        Inst_code_5 poly = new Inst_code_5(0, 0);
        for (int i = this.degree; i >= 0; i--) {
            Inst_code_5 term = new Inst_code_5(this.coef[i], 0);
            poly = term.plus(thing.times(poly));
        }
        return poly;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        Inst_code_5 thing = (Inst_code_5) other;
        if (this.degree != thing.degree) return false;
        for (int i = this.degree; i >= 0; i--)
            if (this.coef[i] != thing.coef[i]) return false;
        return true;
    }

    public Inst_code_5 differentiate() {
        if (degree == 0) return new Inst_code_5(0, 0);
        Inst_code_5 poly = new Inst_code_5(0, degree - 1);
        poly.degree = degree - 1;
        for (int i = 0; i < degree; i++)
            poly.coef[i] = (i + 1) * coef[i + 1];
        return poly;
    }

    public int evaluate(int x) {
        int p = 0;
        for (int i = degree; i >= 0; i--)
            p = coef[i] + (x * p);
        return p;
    }

    public int compareTo(Inst_code_5 thing) {
        if (this.degree < thing.degree) return -1;
        if (this.degree > thing.degree) return +1;
        for (int i = this.degree; i >= 0; i--) {
            if (this.coef[i] < thing.coef[i]) return -1;
            if (this.coef[i] > thing.coef[i]) return +1;
        }
        return 0;
    }

    @Override
    public String toString() {
        if      (degree == -1) return "0";
        else if (degree ==  0) return "" + coef[0];
        else if (degree ==  1) return coef[1] + "x + " + coef[0];
        String s = coef[degree] + "x^" + degree;
        for (int i = degree - 1; i >= 0; i--) {
            if      (coef[i] == 0) continue;
            else if (coef[i]  > 0) s = s + " + " + (coef[i]);
            else if (coef[i]  < 0) s = s + " - " + (-coef[i]);
            if      (i == 1) s = s + "x";
            else if (i >  1) s = s + "x^" + i;
        }
        return s;
    }

    public static void main(String[] args) { 

        Inst_code_5 p1   = new Inst_code_5(4, 3);
        Inst_code_5 p2   = new Inst_code_5(3, 2);
        Inst_code_5 p3   = new Inst_code_5(1, 0);
        Inst_code_5 p4   = new Inst_code_5(2, 1);
        Inst_code_5 p    = p1.plus(p2).plus(p3).plus(p4);

        Inst_code_5 q1   = new Inst_code_5(3, 2);
        Inst_code_5 q2   = new Inst_code_5(5, 0);
        Inst_code_5 q    = q1.plus(q2);


        Inst_code_5 r    = p.plus(q);
        Inst_code_5 s    = p.times(q);
        Inst_code_5 t    = p.compose(q);
        Inst_code_5 u    = p.minus(p);

        System.out.println(p.differentiate());
    }
}