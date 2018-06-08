package ua.khpi.markevich.Practice3.part3;

public class Part3 {

	/**
	 * Data which will be transformed.
	 */
	private final String input;

	/**
	 * Constructor takes {@code String} for parsing.
	 * 
	 * @param data
	 */
	public Part3(String data) {
		this.input = data;
	}

	/**
	 * Makes every word as capitalized.
	 * 
	 * @return String with every word from input as capitalized
	 */
	public String output() {
		String[] inputArray = input.split(" ");
		StringBuilder result = new StringBuilder(input.length());
		for (String s : inputArray) {
			String start = s.substring(0, 1).toUpperCase();
			String word = s.substring(1);
			result.append(start).append(word).append(" ");
		}
		return result.toString();
	}

}
