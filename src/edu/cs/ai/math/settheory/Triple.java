package edu.cs.ai.math.settheory;

import java.util.HashMap;
import java.util.Objects;

/***
 * A {@link HashMap} compatible Triple implementation, based on a Pair
 * implementation by arturh. See the coressponding StackOverflow: <a href=
 * "https://stackoverflow.com/questions/156275/what-is-the-equivalent-of-the-c-pairl-r-in-java">here</a>.
 * 
 * @author arturh, Kai Sauerwald (documentation)
 * 
 * @param <A> The type of the first element
 * @param <B> The type of the second element
 */
public final class Triple<A, B, C> {
	private A first;
	private B second;
	private C third;

	public Triple(A first, B second, C third) {
		super();
		this.first = first;
		this.second = second;
		this.third = third;
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second, third);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Triple) {
			Triple<?, ?, ?> otherTriple = (Triple<?, ?, ?>) other;
			return ((this.first == otherTriple.first
					|| (this.first != null && otherTriple.first != null && this.first.equals(otherTriple.first)))
					&& (this.second == otherTriple.second || (this.second != null && otherTriple.second != null
							&& this.second.equals(otherTriple.second)))
					&& (this.third == otherTriple.third || (this.third != null && otherTriple.third != null
							&& this.third.equals(otherTriple.third))));
		}

		return false;
	}

	@Override
	public String toString() {
		return "(" + first + ", " + second + ", " + third + ")";
	}

	public A getFirst() {
		return first;
	}

	public B getSecond() {
		return second;
	}

	public C getThird() {
		return third;
	}
}
