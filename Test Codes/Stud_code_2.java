
public final class Stud_code_2 {
  private final int degree;
  private final double coeff[];

  public Stud_code_2(double coeff[]) {
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

  public Stud_code_2 differentiate() {
    int derivativeDegree = degree - 1;
    double derivativeCoeff[] = new double[derivativeDegree];
    for (int i = 0; i < derivativeDegree; i++) {
      derivativeCoeff[i] = coeff[i + 1] * (i + 1);
    }
    return new Stud_code_2(derivativeCoeff);
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
	  Scanner sc=new Scanner(System.in);  
	  double[] array = new double[3];  
	  System.out.println("Enter the elements of the array: ");  
	  for(int i=0; i<array; i++)  
	  {    
	  array[i]=sc.nextInt();  
	  }  
	  var originalPoly = new Stud_code_2(array);
	  System.out.println(originalPoly.differentiate());
  }
  
  }