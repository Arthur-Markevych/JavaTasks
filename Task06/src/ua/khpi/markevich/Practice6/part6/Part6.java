package ua.khpi.markevich.Practice6.part6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Application takes text file and parse it in special way chosen by command.
 * 
 * @author Arthur Markevich
 *
 */
public final class Part6 {

	/**
	 * Default encoding.
	 */
	private static final String ENCODING = "cp1251";

	/**
	 * Text file to parse.
	 */
	private static String file;

	/**
	 * Name of parsing command.
	 */
	private static String task;

	/**
	 * Parsing commands processor.
	 *
	 */
	enum Command {

		/**
		 * Find three words which appear more frequency time from file and gather them
		 * in ascending order of frequency as word ==> frequency count.
		 */
		FREQUENCY {
			@Override
			public String getOutput(final String input) {
				Map<String, Integer> map = new TreeMap<>();
				StringTokenizer tokenizer = getTokenizer(input);
				while (tokenizer.hasMoreTokens()) {
					String word = tokenizer.nextToken();
					if (map.containsKey(word)) {
						int n = map.get(word);
						map.put(word, ++n);
					} else {
						map.put(word, 1);
					}
				}

				TreeMap<Integer, String> revMap = new TreeMap<>();
				for (Map.Entry<String, Integer> entry : map.entrySet()) {
					revMap.put(entry.getValue(), entry.getKey());
				}

				int count = 0;
				NavigableMap<Integer, String> navMap = revMap.descendingMap();
				Map<String, Integer> res = new TreeMap<>(new Comparator<String>() {

					@Override
					public int compare(final String o1, final String o2) {
						return o2.toLowerCase().compareTo(o1.toLowerCase());
					}

				});
				for (Map.Entry<Integer, String> entry : navMap.entrySet()) {
					res.put(entry.getValue(), entry.getKey());
					if (++count == COUNT) {
						break;
					}
				}

				StringBuilder sb = new StringBuilder();
				for (Map.Entry<String, Integer> entry : res.entrySet()) {
					sb.append(entry.getKey() + " ==> ").append(entry.getValue() + "\n");
				}
				return sb.toString();
			}

		},

		/**
		 * Find three the longest words from file and gather them in descending order of
		 * word length as word ==> word length count.
		 */
		LENGTH {
			@Override
			public String getOutput(final String input) {
				Set<String> set = new TreeSet<>(new Comparator<String>() {

					@Override
					public int compare(final String o1, final String o2) {
						return o2.length() - o1.length();
					}
				});
				StringTokenizer tokenizer = getTokenizer(input);
				while (tokenizer.hasMoreTokens()) {
					String word = tokenizer.nextToken();
					set.add(word);
				}

				int count = 0;
				StringBuilder sb = new StringBuilder();
				for (String s : set) {
					sb.append(s + " ==> ").append(s.length() + System.lineSeparator());
					if (++count == COUNT) {
						break;
					}
				}
				return sb.toString();
			}

		},

		/**
		 * Find three from firsts appeared words which have duplicates and gather them
		 * in reverse mode.
		 */
		DUPLICATES {
			@Override
			public String getOutput(final String input) {
				Set<String> filter = new HashSet<>();
				Set<String> output = new HashSet<>();
				StringTokenizer tokenizer = getTokenizer(input);
				while (tokenizer.hasMoreTokens()) {
					String word = tokenizer.nextToken();
					if (!filter.add(word)) {
						if (output.add(word)) {
							if (output.size() == COUNT) {
								break;
							}
						}
					}
				}
				StringBuilder sb = new StringBuilder();
				for (String word : output) {
					StringBuilder sbWord = new StringBuilder(word);
					String reverse = sbWord.reverse().toString();
					sb.append(reverse.toUpperCase() + "\n");
				}
				return sb.toString();
			}
		};

		/**
		 * Words count for output.
		 */
		private static final int COUNT = 3;

		/**
		 * Should implement special commands of text transformation.
		 * 
		 * @param input
		 *            text file
		 * @return transformed strong
		 */
		public abstract String getOutput(String input);

		/**
		 * StringTokenizer split text by words.
		 * 
		 * @param input
		 *            text file
		 * @return Configured StringTokenizer instance
		 */
		private static StringTokenizer getTokenizer(final String input) {
			return new StringTokenizer(input, " {}()[]<>#*!?,:;-\"/");
		}

	}

	/**
	 * Start point of application. Show work with commands.
	 * 
	 * @param args
	 *            takes input which contains file path and command.
	 *            <code>-i input.txt -t frequency</code>
	 *            <code>--input input.txt --task length</code>
	 */
	public static void main(final String[] args) {
		parseCommandLine(args);
		try {
			String fileData = getStringFromFile(file, ENCODING);
			doTask(task, fileData);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Parse command line arguments and execute the particular command.
	 * 
	 * @param input
	 *            array of words
	 */
	private static void parseCommandLine(final String[] input) {
		for (int i = 0; i < input.length; i++) {
			if (input[i].equals("-i") || input[i].equals("--input")) {
				file = input[i + 1].toLowerCase();
			}
			if (input[i].equals("-t") || input[i].equals("--task")) {
				task = input[i + 1].toLowerCase();
			}
		}
	}

	/**
	 * Start particular command.
	 * 
	 * @param task
	 *            command
	 * @param file
	 *            text file
	 */
	private static void doTask(final String task, final String file) {
		switch (task) {
		case "frequency":
			System.out.println(Command.FREQUENCY.getOutput(file));
			break;

		case "length":
			System.out.println(Command.LENGTH.getOutput(file));
			break;

		case "duplicates":
			System.out.println(Command.DUPLICATES.getOutput(file));
			break;

		default:
			System.out.println("Commant \"" + task + "\" not found");
			break;
		}
	}

	/**
	 * Obtain text from file.
	 * 
	 * @param path
	 *            text file
	 * @param encoding
	 *            file encoding
	 * @return text from file
	 * @throws IOException
	 *             in case of reading file problem
	 */
	public static String getStringFromFile(final String path, final String encoding) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), encoding))) {
			String str = "";
			while ((str = in.readLine()) != null) {
				sb.append(str);
			}
		} catch (IOException e) {
			throw new IOException(e);
		}
		return sb.toString();
	}

	/**
	 * Private constructor.
	 */
	private Part6() {
		// TODO Auto-generated constructor stub
	}

}
