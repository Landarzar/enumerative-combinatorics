package edu.cs.ai.math.settheory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/***
 * Represents a tuple over a fixed type
 * 
 * @author Kai Sauerwald
 *
 */
public class Tuple<T> {

	protected ArrayList<T> implTuple = null;

	/***
	 * produces the empty tuple
	 */
	public Tuple() {
	}

	/***
	 * 
	 */
	public Tuple(T... elements) {
		implTuple = new ArrayList<>(elements.length);

		for (int i = 0; i < elements.length; i++) {
			T t = elements[i];
			implTuple.add(t);
		}
	}

	/***
	 * 
	 */
	public Tuple(List<T> elements) {
		implTuple = new ArrayList<>(elements);
	}
	

	public List<T> toList() {
		return Collections.unmodifiableList(implTuple);
	}

	@Override
	public String toString() {
		return implTuple.toString();
	}

	/***
	 * The arity of this tuple
	 */
	public int getArity() {
		if (implTuple == null)
			return 0;
		return implTuple.size();
	}

	@Override
	public int hashCode() {
		return Objects.hash(implTuple);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Tuple))
			return false;
		Tuple<T> other = (Tuple<T>) obj;
		return Objects.equals(implTuple, other.implTuple);
	}

	/***
	 * Gets the element at index {@code i}
	 * 
	 * @param i The index
	 */
	public T getIth(int i) {
		return implTuple.get(i);
	}
}
