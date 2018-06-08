package ua.khpi.markevich.Practice3.part2;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Class contains method which find unique words with min and max length.
 * 
 * @author Arthur Markevich
 */
public final class Part2 {

	/**
	 * The string which will parsed.
	 */
	private final String input;

	/**
	 * Constructor takes {@code String} for parsing.
	 * 
	 * @param data
	 */
	public Part2(String data) {
		this.input = data;
	}

	/**
	 * Parse input and get all unique words with min and max length.
	 * 
	 * @return String with all unique words with min and max length
	 */
	public String find() {
		String line = input.replaceAll("\\W", " ");
		String[] words = line.split("[\\s+]");

		int min = words[0].length();
		int max = words[0].length();

		Set<String> setMin = new LinkedHashSet<>();
		Set<String> setMax = new LinkedHashSet<>();

		for (String s : words) {
			if (s.length() < min && !s.equals("")) {
				min = s.length();
			}
		}

		for (String s : words) {
			if (s.length() == min) {
				setMin.add(s);
			}
		}

		for (String s : words) {
			if (s.length() > max) {
				max = s.length();
			}
		}
		for (String s : words) {
			if (s.length() == max) {
				setMax.add(s);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (String s : setMin) {
			sb.append(s).append(" ");
		}
		sb.append(System.lineSeparator());
		for (String s : setMax) {
			sb.append(s).append(" ");
		}
		return sb.toString();
	}

}
