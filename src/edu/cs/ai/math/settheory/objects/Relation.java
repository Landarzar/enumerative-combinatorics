package edu.cs.ai.math.settheory.objects;

public interface Relation<U> {

	public boolean contains(Tuple<U> tuple);

	public int getArity();

}
