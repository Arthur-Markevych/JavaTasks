package ua.khpi.markevich.Practice3;

import java.security.NoSuchAlgorithmException;

import ua.khpi.markevich.Practice3.part1.Part1;
import ua.khpi.markevich.Practice3.part2.Part2;
import ua.khpi.markevich.Practice3.part3.Part3;
import ua.khpi.markevich.Practice3.part4.Part4;
import ua.khpi.markevich.Practice3.part5.Part5;

public final class Demo {

	private static final String PART1_INPUT = "Login;Name;Email\n" + "ivanov;Ivan Ivanov;ivanov@mail.ru\n"
			+ "petrov;Petr Petrov;petrov@google.com\n" + "obama;Barack Obama;obama@google.com\n"
			+ "bush;George Bush;bush@mail.ru";

	private static final String PART2_INPUT = "When I was younger, so much younger than today\n"
			+ "I never needed anybody's help in any way\n" + "But now these days are gone, I'm not so self-assured\n"
			+ "Now I find I've changed my mind\n" + "I've opened up the doors";

	private static final String PART3_INPUT = "When I was younger\n" + "I never needed";

	private static final int PART5_COUNTS = 100;

	public static void main(String[] args) {
		System.out.println("==== Part1");
		Part1 part1 = new Part1(PART1_INPUT);
		System.out.println(part1.convert1());
		System.out.println(part1.convert2());
		System.out.println(part1.convert3());
		System.out.println(part1.convert4());
		System.out.println("==== Part2");
		System.out.println(new Part2(PART2_INPUT).find());
		System.out.println("==== Part3");
		System.out.println(new Part3(PART3_INPUT).output());
		System.out.println("==== Part4");
		try {
			System.out.println(Part4.hash("password", "MD5"));
		} catch (NoSuchAlgorithmException e) {
			e.getMessage();
		}
		System.out.println("==== Part5");
		for (int i = 1; i <= PART5_COUNTS; i++) {
			Part5.output(i);
		}
	}

	private Demo() {
		/* private constructor */
	}

}
