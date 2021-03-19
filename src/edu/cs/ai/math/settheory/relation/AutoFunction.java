package edu.cs.ai.math.settheory.relation;

import java.util.Set;

import edu.cs.ai.math.settheory.Tuple;

/***
 * General Interface for relations over one set
 * @author Kai Sauerwald
 *
 * @param <U> type of input and output elements
 */
public interface AutoFunction<U> extends java.util.function.Function<Tuple<U>, U> {
	
	/***
	 * Gets the Elements on which this function is defined on.
	 * 
	 * @author Kai Sauerwald
	 * @return {@code null} when this is not specified.
	 */
	public default Set<U> getFunctionBase(){
		return null;
	}

//	/***
//	 * Checks whether this relations contains the tuple
//	 * @author Kai Sauerwald
//	 * @throws IllegalArgumentException When the arity does not match
//	 */
//	public boolean contains(Tuple<U> tuple);

	
	/****
	 * Returns the arity of this relation.
	 * @author Kai Sauerwald
	 */
	public int getArity();


}
