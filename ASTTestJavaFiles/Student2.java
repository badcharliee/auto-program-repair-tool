class SmallInt {

	int one = 1;
	int two = 2;

	public boolean getSmallerInteger(int a, int b) {
		if (a < b) {
			return true;
		}

		return false;
	}

	public static void main() {
		boolean smallInt = getSmallerInteger(one, two);
	}
}
