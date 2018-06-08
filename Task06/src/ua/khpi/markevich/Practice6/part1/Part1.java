package ua.khpi.markevich.Practice6.part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Application reads input text and counts appearing of unique words and then
 * prints them to console in descending order.
 * 
 * @author Arthur Markevich
 *
 */
public final class Part1 {

	/**
	 * Storage for inputed words.
	 */
	private static List<Word> words = new ArrayList<>();

	/**
	 * Application start point.
	 * 
	 * @param args
	 *            is ignored
	 */
	public static void main(final String[] args) {
		String input = "asd asdf asd asdf asdf 43 asdsf 43 43 434";
		String output = getOrderedWords(input);
		System.out.print(output);
	}

	/**
	 * The method parse input and counts appearing of unique words.
	 * 
	 * @param input
	 *            string which will be parsed.
	 * @return representation of result as <code>frequency : word</code> in
	 *         descending order
	 */
	public static String getOrderedWords(final String input) {
		String[] inputs = input.split("\\s");
		for (String s : inputs) {
			Word word = new Word(s);
			if (!words.contains(word)) {
				words.add(word);
			} else {
				for (Iterator<Word> it = words.iterator(); it.hasNext();) {
					Word wd = it.next();
					if (wd.equals(word)) {
						wd.increment();
					}
				}
			}
		}
		Collections.sort(words, Word.getOrderComparator());
		StringBuilder sb = new StringBuilder();
		for (Word word : words) {
			sb.append(word + "\n");
		}
		return sb.toString();
	}

	/**
	 * Private constructor.
	 */
	private Part1() {
		// TODO Auto-generated constructor stub
	}

}
