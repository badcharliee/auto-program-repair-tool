class SmallInt {

	int one = 1;
	int two = 2;

	public int getSmallerInteger(int a, int b) {
		if (a <= b) {
			return a;
		}

		return b;
	}

	public static void main() {
		int smallerInt = getSmallerInteger(one, two);
	}
}
