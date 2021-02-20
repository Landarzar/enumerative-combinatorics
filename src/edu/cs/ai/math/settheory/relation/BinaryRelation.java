package edu.cs.ai.math.settheory.relation;

import java.util.Set;

/***
 * 
 * @author Kai Sauerwald
 *
 * @param <X> The type of the elements
 */
public interface BinaryRelation<X> extends Relation<X> {

//	/**
//	 * The first element is equal to the second with respect to the relation.
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static long EQUAL = (1 << 0);
//
//	/***
//	 * Is the comparison result EQUAL?
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static boolean isEQUAL(long nbr) {
//		return is(nbr, EQUAL);
//	}
//
//	/**
//	 * The first element is with respect to the relation strictly lower than the
//	 * second.
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static long STRICT_LESSER = (1 << 1);
//
//	/***
//	 * Is the comparison result EQUAL?
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static boolean isSTRICT_LESSER(long nbr) {
//		return is(nbr, STRICT_LESSER);
//	}
//
//	/**
//	 * The first element is with respect to the relation strictly greater than the
//	 * second.
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static long STRICT_GREATER = (1 << 2);
//
//	/***
//	 * Is the comparison result EQUAL?
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static boolean isSTRICT_GREATER(long nbr) {
//		return is(nbr, STRICT_GREATER);
//	}
//
//	/**
//	 * The is no relation between the first and second element.
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static long INCOMPARABLE = (1 << 3);
//
//	/***
//	 * Is the comparison result EQUAL?
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static boolean isINCOMPARABLE(long nbr) {
//		return is(nbr, INCOMPARABLE);
//	}
//
//	/**
//	 * The is a relation between the first and second element.
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static long COMPARABLE = EQUAL | STRICT_LESSER | STRICT_GREATER;
//
//	/***
//	 * Is the comparison result EQUAL?
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static boolean isCOMPARABLE(long nbr) {
//		return is(nbr, COMPARABLE);
//	}
//
//	/**
//	 * The first element is with respect to the relation greater than the second.
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static long GREATER_OR_EQUAL = EQUAL | STRICT_GREATER;
//
//	/***
//	 * Is the comparison result EQUAL?
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static boolean isGREATER_OR_EQUAL(long nbr) {
//		return is(nbr, GREATER_OR_EQUAL);
//	}
//
//	/**
//	 * The first element is with respect to the relation lower than the second.
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static long LESSER_OR_EQUAL = EQUAL | STRICT_LESSER;
//
//	/***
//	 * Is the comparison result EQUAL?
//	 * 
//	 * @author Kai Sauerwald
//	 */
//	public static boolean isLESSER_OR_EQUAL(long nbr) {
//		return is(nbr, LESSER_OR_EQUAL);
//	}
//
//	private static boolean is(long nbr, long cmpr) {
//		return (nbr & cmpr) > 0;
//	}

//	/***
//	 * Checks where the given elements are in relation with respect to this
//	 * relation.
//	 * 
//	 * @param x1
//	 * @param x2
//	 */
//	public boolean inRelation(X x1, X x2);

	/***
	 * Relates two elements of type <X> @param x1 @param x2 @return @throws
	 */
	public RelationStatus relate(X x1, X x2);

	@Override
	default int getArity() {
		// TODO Auto-generated method stub
		return 2;
	}
}
