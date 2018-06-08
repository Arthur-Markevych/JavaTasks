package ua.khpi.markevich.Practice6.part1;

import java.util.Comparator;

/**
 * Type which contain string word and count.
 * 
 * @author Arthur Markevich
 *
 */
public class Word {

	/**
	 * Some word.
	 */
	private String word;

	/**
	 * Frequency of appearing the word in some string.
	 */
	private Integer frequency;

	/**
	 * Constructor takes a word and set frequency = 1.
	 * 
	 * @param word
	 *            special string
	 */
	public Word(final String word) {
		this.word = word;
		frequency = 1;
	}

	/**
	 * Increment frequency of the word.
	 */
	public void increment() {
		frequency++;
	}

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word
	 *            the word to set
	 */
	public void setWord(final String word) {
		this.word = word;
	}

	/**
	 * @return the frequency
	 */
	public Integer getFrequency() {
		return frequency;
	}

	/**
	 * @param frequency
	 *            the frequency to set
	 */
	public void setFrequency(final int frequency) {
		this.frequency = frequency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + frequency;
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Word other = (Word) obj;
		if (word == null) {
			if (other.word != null) {
				return false;
			}
		} else if (!word.equals(other.word)) {
			return false;
		}
		return true;
	}

	/**
	 * Print Word instance to console.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return word + " : " + frequency;
	}

	/**
	 * 
	 * @return the orderComparator
	 */
	public static Comparator<Word> getOrderComparator() {
		return orderComparator;
	}

	/**
	 * Comparator which order words by frequency in descending way.
	 */
	private static Comparator<Word> orderComparator = new Comparator<Word>() {

		@Override
		public int compare(final Word o1, final Word o2) {
			if (!o2.getFrequency().equals(o1.getFrequency())) {
				return o2.getFrequency().compareTo(o1.getFrequency());
			} else {
				return o1.getWord().compareTo(o2.getWord());
			}
		}

	};

}
