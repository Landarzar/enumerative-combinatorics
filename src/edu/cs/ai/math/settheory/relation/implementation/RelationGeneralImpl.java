package edu.cs.ai.math.settheory.relation.implementation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import edu.cs.ai.math.settheory.Tuple;
import edu.cs.ai.math.settheory.relation.Relation;

public class RelationGeneralImpl<U> implements Relation<U> {

	int arity;
	Set<Tuple<U>> tuples;

	public RelationGeneralImpl(int arity, Set<Tuple<U>> tuples) {
		this.arity = arity;
		if (tuples.stream().anyMatch(t -> t.getArity() != arity))
			throw new IllegalArgumentException("Tuples with wrong arity");
		this.tuples = Collections.unmodifiableSet(tuples);
	}

	/***
	 * Yields the empty relation of specified arity
	 * 
	 * @param arity
	 */
	public RelationGeneralImpl(int arity) {
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

	@Override
	public boolean isTotal() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isModular() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}
}
