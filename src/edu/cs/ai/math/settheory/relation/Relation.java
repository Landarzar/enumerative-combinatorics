package edu.cs.ai.math.settheory.relation;

import java.util.Set;

import edu.cs.ai.math.settheory.Tuple;

/***
 * General Interface for relations over one set
 * @author Kai Sauerwald
 *
 * @param <U> Set type of setelements
 */
public interface Relation<U> {
	
	/***
	 * Gets the Elements on which this relation is defined on.
	 * 
	 * @author Kai Sauerwald
	 * @return {@code null} when this is not specified.
	 */
	public default Set<U> getRelationBase(){
		return null;
	}

	/***
	 * Checks whether this relations contains the tuple
	 * @author Kai Sauerwald
	 * @throws IllegalArgumentException When the arity does not match
	 */
	public boolean contains(Tuple<U> tuple);

	
	/****
	 * Returns the arity of this relation.
	 * @author Kai Sauerwald
	 */
	public int getArity();

	/***
	 * Tells if this relation is guaranteed to be total
	 * @author Kai Sauerwald
	 */
	public default boolean isTotal(){
		return false;
	}

	/***
	 * Tells if this relation  is guaranteed to be modular
	 * @author Kai Sauerwald
	 */
	public default boolean isModular(){
		return false;
	} 

	/***
	 * Tells if this relation  is guaranteed to be transitive
	 * @author Kai Sauerwald
	 */
	public default boolean isTransitive() {
		return false;
	}
}
