package edu.cs.ai.math.settheory.relation;

/***
 * The relation of two elements in a relation.
 * 
 * @author Kai Sauerwald
 *
 */
public enum RelationStatus {
//	/**
//	 * The first element is with respect to the relation lower than the second.
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	LESSER_OR_EQUAL(1 << 0 | 1 << 1),
	/**
	 * The first element is with respect to the relation strictly lower than the
	 * second.
	 * 
	 * @author Kai Sauerwald
	 */
	STRICT_LESSER(1 << 1),
	/**
	 * The first element is equal to the second with respect to the relation.
	 * 
	 * @author Kai Sauerwald
	 */
	EQUAL(1 << 0),
	/**
	 * The first element is with respect to the relation greater than the second.
	 * 
	 * @author Kai Sauerwald
	 */
//	GREATER_OR_EQUAL(1 << 0 | 1 << 2),
//	/**
//	 * The first element is with respect to the relation strictly greater than the
//	 * second.
//	 * 
//	 * @author Kai Sauerwald
//	 */
	STRICT_GREATER(1 << 2),
	/**
	 * The is no relation between the first and second element.
	 * 
	 * @author Kai Sauerwald
	 */
	INCOMPARABLE(1 << 3);

	private long cmpvalue;

	private RelationStatus(long cmpvalue) {
		this.cmpvalue = cmpvalue;
	}
	
	/***
	 * Tells whether this is comparable.
	 * @return
	 */
	public boolean isComparable() {
		return this != INCOMPARABLE;
	}
	
	/***
	 * Tells whether both elements in a strict relation
	 * @return
	 */
	public boolean isStrict() {
		return this == STRICT_GREATER || this == STRICT_LESSER;
	}
	
	/***
	 * Tells whether this is strict greater or equal.
	 * @return
	 */
	public boolean isGreater() {
		return this == STRICT_GREATER || this == EQUAL;
	}
	
	/***
	 * Tells whether this is strict lesser or equal.
	 * @return
	 */
	public boolean isLesser() {
		return this == STRICT_LESSER || this == EQUAL;
	}
	/***
	 * Tells whether this is equal.
	 * @return
	 */
	public boolean isEqual() {
		return this == EQUAL;
	}
}
