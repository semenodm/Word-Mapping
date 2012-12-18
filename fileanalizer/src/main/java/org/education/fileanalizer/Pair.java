package org.education.fileanalizer;

public class Pair implements Comparable<Pair> {


	private final String word;
	private final Long frequency;

	public Pair(String string, long l) {
		this.word = string;
		this.frequency = l;

	}

	public String getWord() {
		return word;
	}

	public long getFequency() {
		return frequency;
	}

	@Override
	public int compareTo(Pair o) {
		//int wordsDiff = word.compareTo(o.word);
		int countDiff = frequency.compareTo(o.frequency);
		return countDiff == 0? word.compareTo(o.word) : countDiff;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((frequency == null) ? 0 : frequency.hashCode());
		result = prime * result + ((word == null) ? 0 : word.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair other = (Pair) obj;
		if (frequency == null) {
			if (other.frequency != null)
				return false;
		} else if (!frequency.equals(other.frequency))
			return false;
		if (word == null) {
			if (other.word != null)
				return false;
		} else if (!word.equals(other.word))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "word	" + "frequency=" + frequency + "]";
	}
}
