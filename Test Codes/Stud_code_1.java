
public final class Stud_code_1 {
  private int degree;
  private final double coeff[];

  public Stud_code_1(double coeff[]) {
    this.coeff = coeff;
    this.degree = coeff.length;
  }

  public int degree() {
    return degree;
  }

  public double evaluate(int x) {
    double result = 0;
    for (int i = 0; i < degree; i++) {
      result += coeff[i] * Math.pow(x, i);
    }
    return result;
  }

  public Stud_code_1 differentiate() {
    int derivativeDegree = degree - 1;
    double derivativeCoeff[] = new double[derivativeDegree];
    for (int i = 0; i < derivativeDegree; i++) {
      derivativeCoeff[i] = coeff[i + 1] * (i + 1);
    }
    return new Stud_code_1(derivativeCoeff);
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
	  
	  originalPoly = new Stud_code_1(new int[] { 1, 2});
	  System.out.println(originalPoly.differentiate());
  }
  
  }