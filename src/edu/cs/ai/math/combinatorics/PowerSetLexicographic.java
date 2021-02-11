/**
 * 
 */
package edu.cs.ai.math.combinatorics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author Kai Sauerwald
 *
 */
public class PowerSetLexicographic {

	public static void main(String[] args) {
		ArrayList<Integer> fuenf = new ArrayList<>(50);
		for (int i = 1; i <= 5; i++) {
			fuenf.add(i);
		}
//		Iterator<Set<Integer>> itr = KCombinationLex.iterator(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);
		Iterator<Set<Integer>> itr = PowerSetLexicographic.iterator(fuenf);
		while (itr.hasNext())
			System.out.println(itr.next());
	}

	/**
	 * Returns a {@code Stream} of all Subsets of the input collection
	 * 
	 * @author Kai Sauerwald.
	 */
	public static <E> Stream<Set<E>> stream(List<E> input) {
		Iterator<Set<E>> itr = iterator(input);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itr, Spliterator.IMMUTABLE), false);
	}

	/***
	 * Returns an iterator, that allows iteration of all elements of the powerset,
	 * respecting the lexicographic order given by the input list
	 * 
	 * @author Kai Sauerwald
	 * @param input a collection with the elements.
	 */
	public static <E> Iterator<Set<E>> iterator(List<E> input) {
		if (input == null)
			throw new IllegalArgumentException();

		// This iterator, implements the same loop as the construct method
		return new Iterator<Set<E>>() {
			private int k = -1;
			boolean hasnext = true;
			private Iterator<Set<E>> current = null;
			private List<E> list = input;

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#hasNext()
			 */
			@Override
			public boolean hasNext() {
				return hasnext;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#next()
			 */
			@Override
			public Set<E> next() {
				if (!hasNext())
					throw new NoSuchElementException();

				if (current == null || !current.hasNext()) {
					k += 1;
					current = KCombinationLexicographic.iterator(list, k);
				}

				Set<E> result = current.next();

				if (k >= list.size() && !current.hasNext()) {
					hasnext = false;
					list = null;
					current = null;
				}

				return result;

			}
		};
	}
}
