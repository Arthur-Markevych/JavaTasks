package ua.khpi.markevich.Practice1;

public final class Part2 {

	public static void main(String... args) {
		if (args.length > 0) {
			double a = Double.parseDouble(args[0]);
			double b = Double.parseDouble(args[1]);
			run(a, b);
		} else {
			System.out.println("please type numbers");
		}

	}

	private static void run(double a, double b) {
		System.out.println(a + b);
	}

	private Part2() {
		// TODO Auto-generated constructor stub
	}

}
