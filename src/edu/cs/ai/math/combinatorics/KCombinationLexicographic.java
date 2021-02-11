package edu.cs.ai.math.combinatorics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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
public class KCombinationLexicographic {

	public static void main(String[] args) {
		ArrayList<Integer> fuenfzig = new ArrayList<>(50);
		for (int i = 1; i <= 50; i++) {
			fuenfzig.add(i);

		}
//		Iterator<Set<Integer>> itr = KCombinationLex.iterator(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);
		Iterator<Set<Integer>> itr = KCombinationLexicographic.iterator(fuenfzig, 3);
		while (itr.hasNext())
			System.out.println(itr.next());
	}

	/**
	 * Returns a {@code Stream} of all Subsets of the input collection
	 * 
	 * @author Kai Sauerwald.
	 */
	public static <E> Stream<Set<E>> stream(List<E> input, int k) {
		Iterator<Set<E>> itr = iterator(input, k);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itr, Spliterator.IMMUTABLE), false);
	}

	/***
	 * Returns an iterator, that allows iteration of all elements of k-Permutations
	 * 
	 * @author Kai Sauerwald
	 * @param input a list with the elements.
	 */
	public static <E> Iterator<Set<E>> iterator(List<E> input, int k) {
		if (input == null)
			throw new IllegalArgumentException();
		if (k < 0 || k > input.size())
			throw new IllegalArgumentException();

		// Return the iterator with the empty set.
		if (k == 0)
			return new Iterator<Set<E>>() {
				private Set<E> emptySet = new HashSet<>();
				private boolean done = false;

				@Override
				public boolean hasNext() {
					return !done;
				}

				@Override
				public Set<E> next() {
					if (done)
						throw new NoSuchElementException();

					done = true;
					return emptySet;
				}

			};

		ArrayList<Integer> startpos = new ArrayList<>(k);
		for (int i = 0; i < k; i++) {
			startpos.add(i);
		}

		// This iterator implements a loop
		return new Iterator<Set<E>>() {
			private int ik = k;
			private boolean hasnext = true;
			private ArrayList<Integer> pos = startpos;
			private List<E> list = Collections.unmodifiableList(input);

			@Override
			public boolean hasNext() {
				return hasnext;
			}

			@Override
			public Set<E> next() {
				if (!hasNext())
					throw new NoSuchElementException();

				HashSet<E> set = new HashSet<>();

				for (Integer ipos : pos) {
					set.add(list.get(ipos));
				}

				boolean done = false;
				int i = ik - 1;
				int imax = list.size() - 1;
				while (!done) {
					if (pos.get(i) == imax) {
						if (i == 0) {
							done = true;
							hasnext = false;
						} else {
							i -= 1;
							imax -= 1;
						}
						continue;
					}

					pos.set(i, pos.get(i) + 1);
					for (int j = 1; i + j < ik; j++) {
						pos.set(i + j, pos.get(i) + j);
					}
					done = true;
				}

				return set;
			}
		};
	}
}
