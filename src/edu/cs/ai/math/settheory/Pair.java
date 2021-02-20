package edu.cs.ai.math.settheory;

import java.util.HashMap;

/***
 * A {@link HashMap} compatible Pair implementation by arturh. See the
 * coressponding StackOverflow: <a href=
 * "https://stackoverflow.com/questions/156275/what-is-the-equivalent-of-the-c-pairl-r-in-java">here</a>.
 * 
 * @author arturh, Kai Sauerwald
 * 
 * @param <A> The type of the first element
 * @param <B> The type of the second element
 */
public final class Pair<A, B> {
	private A first;
	private B second;

	public Pair(A first, B second) {
		super();
		this.first = first;
		this.second = second;
	}

	@Override
	public int hashCode() {
		int hashFirst = first != null ? first.hashCode() : 0;
		int hashSecond = second != null ? second.hashCode() : 0;

		return (hashFirst + hashSecond) * hashSecond + hashFirst;
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Pair) {
			Pair<?, ?> otherPair = (Pair<?, ?>) other;
			return ((this.first == otherPair.first
					|| (this.first != null && otherPair.first != null && this.first.equals(otherPair.first)))
					&& (this.second == otherPair.second || (this.second != null && otherPair.second != null
							&& this.second.equals(otherPair.second))));
		}

		return false;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	public void setFirst(A first) {
		this.first = first;
	}

	public void setSecond(B second) {
		this.second = second;
	}
}
