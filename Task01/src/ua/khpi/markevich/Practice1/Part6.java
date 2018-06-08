package ua.khpi.markevich.Practice1;

public final class Part6 {

	public static void main(String... args) {
		int n = Integer.parseInt(args[0]);
		run(n);
		System.out.println();
	}

	private static void run(int n) {
		if (n < 0) {
			throw new IllegalArgumentException();
		}
		int count = 0;
		for (int i = 2; count < n; i++) {
			boolean isPrime = true;

			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					isPrime = false;
					break;
				}
			}

			if (isPrime) {
				System.out.print(i + " ");
				count++;
			}
		}
	}

	private Part6() {
		// TODO Auto-generated constructor stub
	}

}
