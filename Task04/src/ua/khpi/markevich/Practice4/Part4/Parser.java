package ua.khpi.markevich.Practice4.Part4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Separate text by sentences.
 * 
 * @author Arthur Markevich
 *
 */
public class Parser implements Iterable<String> {

	/**
	 * Path to destination file.
	 */
	private final String path;

	/**
	 * File encoding.
	 */
	private final String encoding;

	/**
	 * Constructor set up data.
	 * 
	 * @param fileName
	 *            path to destination file
	 * @param fileEncoding
	 *            file encoding
	 */
	public Parser(final String fileName, final String fileEncoding) {
		this.path = fileName;
		this.encoding = fileEncoding;
	}

	@Override
	public final Iterator<String> iterator() {
		return new ParserIterator();
	}

	/**
	 * Iterator scanning sentence by sentence.
	 *
	 */
	private class ParserIterator implements Iterator<String> {

		/**
		 * File which will be parsed.
		 */
		private File file;

		/**
		 * Scanner to scan <tt>file</tt>.
		 */
		private Scanner scan;

		/**
		 * Contains current sentence.
		 */
		private String nextSentance;

		/**
		 * Constructor setting up data for iteration.
		 */
		ParserIterator() {
			try {
				file = new File(path);
				scan = new Scanner(file, encoding);
				scan.useDelimiter("(?<=(?<![A-ZА-Я])\\.)");
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException(e);
			}
		}

		/**
		 * Returns {@code true} if the iteration has more sentences.
		 * 
		 * @return {@code true} if the iteration has more sentences
		 */
		@Override
		public boolean hasNext() {
			return scan.hasNext();
		}

		/**
		 * Takes sentence from the file.
		 * 
		 * @return next sentence
		 */
		@Override
		public String next() {
			if (hasNext()) {
				nextSentance = scan.next().replaceAll("\n", "").trim();
			} else {
				scan.close();
			}
			return nextSentance;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
