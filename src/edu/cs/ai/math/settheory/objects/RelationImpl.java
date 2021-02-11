package edu.cs.ai.math.settheory.objects;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RelationImpl<U> implements Relation<U> {

	int arity;
	Set<Tuple<U>> tuples;

	public RelationImpl(int arity, Set<Tuple<U>> tuples) {
		this.arity = arity;
		if (tuples.stream().anyMatch(t -> t.getArity() != arity))
			throw new IllegalArgumentException("Tuples with wrong arity");
		this.tuples = Collections.unmodifiableSet(tuples);
	}

	/***
	 * Contructions the empty relation of specific arity
	 * 
	 * @param arity
	 */
	public RelationImpl(int arity) {
		this.arity = arity;
		this.tuples = new HashSet<>();
	}

	@Override
	public boolean contains(Tuple<U> tuple) {
		return tuples.contains(tuple);
	}

	@Override
	public int getArity() {
		return arity;
	}

	@Override
	public String toString() {
		return tuples.toString();
	}
}
