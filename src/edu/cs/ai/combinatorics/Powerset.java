/**
 * 
 */
package edu.cs.ai.combinatorics;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Kai Sauerwald
 *
 */
public class Powerset {
	
	/***
	 * Constructs the powerset over the given collection.
	 * Note this operation really constructs sets, notably it removes duplicates.
	 * @author Kai Sauerwald
	 * @param set
	 * @return The set of all subsets
	 */
	public static <E> Set<Set<E>> powerSet(Collection<E> set) {
		Set<Set<E>> result = new HashSet<>();
		result.add(new HashSet<E>());
		for (E element : set) {
			Set<Set<E>> previousSets = new HashSet<>(result);
			for (Set<E> subSet : previousSets) {
				Set<E> newSubSet = new HashSet<E>(subSet);
				newSubSet.add(element);
				result.add(newSubSet);
			}
		}
		return result;
	}
	
	public static void test() {

	}
}
