package ua.khpi.markevich.Practice1;

public final class Part3 {

	public static void main(String... args) {
		run(args);
	}

	private static void run(String... args) {
		if (args.length != 0) {
			for (String s : args) {
				System.out.print(s + " ");
			}
		} else {
			System.out.println("No args found");
		}
		System.out.println();
	}

	private Part3() {
		// TODO Auto-generated constructor stub
	}

}
