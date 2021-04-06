
public final class Inst_code_1 {
  private final int degree;
  private final double coeff[];

  public Inst_code_1(double coeff[]) {
    this.coeff = coeff;
    this.degree = coeff.length;
  }

  public int degree() {
    return degree;
  }

  public double evaluate(double x) {
    double result = 0;
    for (int i = 0; i < degree; i++) {
      result += coeff[i] * Math.pow(x, i);
    }
    return result;
  }

  public Inst_code_1 differentiate() {
    int derivativeDegree = degree - 1;
    double derivativeCoeff[] = new double[derivativeDegree];
    for (int i = 0; i < derivativeDegree; i++) {
      derivativeCoeff[i] = coeff[i + 1] * (i + 1);
    }
    return new Inst_code_1(derivativeCoeff);
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < degree; i++) {
      if (i > 0) {
        sb.append(" + ");
      }
      sb.append(coeff[i] + "·x^" + i);
    }
    return sb.toString();
  }
  public static void main(String[] args) { 
	  
	  var originalPoly = new Inst_code_1(new double[] { 1, 2, 3 });
	  System.out.println(originalPoly.differentiate());
  }
  
  }