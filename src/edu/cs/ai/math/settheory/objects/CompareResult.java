package edu.cs.ai.math.settheory.objects;

/***
 * Results of comparison with partial orders.
 * 
 * @author Kai Sauerwald
 *
 */
public enum CompareResult {
	/**
	 * The first element is with respect to the relation lower than the second.
	 * 
	 * @author Kai Sauerwald
	 */
	LESSER_OR_EQUAL(1 << 0 | 1 << 1),
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
	GREATER_OR_EQUAL(1 << 0 | 1 << 2),
	/**
	 * The first element is with respect to the relation strictly greater than the
	 * second.
	 * 
	 * @author Kai Sauerwald
	 */
	STRICT_GREATER(1 << 2),
	/**
	 * The is no relation between the first and second element.
	 * 
	 * @author Kai Sauerwald
	 */
	INCOMPARABLE(1 << 3);

	private long cmpvalue;

	private CompareResult(long cmpvalue) {
		this.cmpvalue = cmpvalue;
	}
}
