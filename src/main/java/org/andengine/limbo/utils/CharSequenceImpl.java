package org.andengine.limbo.utils;

/**
 * A class which implement CharSequence.
 */
public class CharSequenceImpl implements CharSequence {

	protected char[] mSequence;

	/** Create a new character sequence containing the given characters. */
	public CharSequenceImpl(char[] sequence) {
		this.mSequence = sequence;
	}

	/** @see java.lang.CharSequence#charAt(int) */
	public char charAt(int index) {
		if (index > -1 && index < mSequence.length) {
			return mSequence[index];
		} else {
			throw new IndexOutOfBoundsException("Invalid index " + index);
		}
	}

	/** @see java.lang.CharSequence#length() */
	public int length() {
		return mSequence.length - 1;
	}

	/** @see java.lang.CharSequence#subSequence(int, int) */
	public CharSequence subSequence(int start, int end) {
		if (start > end) {
			throw new IllegalArgumentException("End index must be equal to or greater than start index.");
		} else if (start < 0) {
			throw new IndexOutOfBoundsException("Invalid start index " + start);
		} else if (end >= mSequence.length) {
			throw new IndexOutOfBoundsException("Invalid end index " + end);
		}
		char[] subseq = new char[end - start + 1];
		int i = 0;
		for (int j = start; j <= end; j++) {
			subseq[i++] = mSequence[j];
		}
		return new CharSequenceImpl(subseq);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new String(mSequence);
	}
}