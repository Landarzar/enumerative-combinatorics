package edu.cs.ai.math.settheory.objects;

public interface SetOperations<SET> {

//	/***
//	 * Constructs the belief set which correspond to the complement of this set with respect to the given universe {@link u}
//	 * @author Kai Sauerwald 
//	 * @param u The universe for the complem
//	 * @return The mentioned set
//	 */
//	public abstract SET complement(SET universe);

	/***
	 * Constructs a new belief set which contains the contant of this set and the
	 * other belief set {@code o2}.
	 * 
	 * @author Kai Sauerwald
	 * @param o2
	 * @return The union of this and {@code o2}
	 */
	public abstract SET union(SET o2);

	/***
	 * Constructs a new belief set which contains only element which containted in
	 * this set and the other belief set {@code o2}.
	 * 
	 * @author Kai Sauerwald
	 * @param o2 The other belief set
	 * @return The intersection of this and {@code o2}
	 */
	public abstract SET intersection(SET o2);

	/***
	 * Constructs a new belief set which contains only element which containted in
	 * this set and not in the other belief set {@code o2}.
	 * 
	 * @author Kai Sauerwald
	 * @param o2 The other belief set
	 * @return The result of {@code this} minus {@code o2}
	 */
	public abstract SET setminus(SET o2);
}
