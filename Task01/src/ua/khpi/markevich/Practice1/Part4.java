package ua.khpi.markevich.Practice1;

public final class Part4 {

	public static void main(String... args) {
		run(args);
	}

	private static void run(String... args) {
		if (args.length > 0) {
			int a = Integer.parseInt(args[0]);
			int b = Integer.parseInt(args[1]);
			nod(a, b);
		} else {
			System.out.println("no args founds");
		}
	}

	private static void nod(int a, int b) {
		if (a > 0 && b > 0) {
			while (a != 0 && b != 0) {
				if (a > b) {
					a %= b;
				} else {
					b %= a;
				}
			}
			System.out.println(a + b);
		} else {
			System.out.println("Numbers must be greater than 0");
		}
	}

	private Part4() {
		// TODO Auto-generated constructor stub
	}

}
