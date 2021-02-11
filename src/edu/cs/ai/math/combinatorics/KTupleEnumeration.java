package edu.cs.ai.math.combinatorics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import edu.cs.ai.math.settheory.objects.Tuple;

/**
 * @author Kai Sauerwald
 *
 */
public class KTupleEnumeration {

	public static void main(String[] args) {
		ArrayList<Integer> fuenfzig = new ArrayList<>(50);
		for (int i = 1; i <= 10; i++) {
			fuenfzig.add(i);

		}
//		Iterator<Set<Integer>> itr = KCombinationLex.iterator(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);
		Iterator<Tuple<Integer>> itr = KTupleEnumeration.iterator(fuenfzig, 3);
		while (itr.hasNext())
			System.out.println(itr.next());
	}

	/**
	 * Returns a {@code Stream} of all Subsets of the input collection
	 * 
	 * @author Kai Sauerwald.
	 */
	public static <E> Stream<Tuple<E>> stream(List<E> input, int k) {
		Iterator<Tuple<E>> itr = iterator(input, k);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itr, Spliterator.IMMUTABLE), false);
	}

	/***
	 * Returns an iterator, that allows iteration of all k-tuples over the input
	 * 
	 * @author Kai Sauerwald
	 * @param input a list with the elements.
	 * @param k     the arity of the tuple
	 */
	public static <E> Iterator<Tuple<E>> iterator(List<E> input, int k) {
		if (input == null)
			throw new IllegalArgumentException();
		if (k < 0)
			throw new IllegalArgumentException();

		// Return the iterator with the empty tuple.
		if (k == 0)
			return new Iterator<Tuple<E>>() {
				private Tuple<E> emptyTuple = new Tuple<>();
				private boolean done = false;

				@Override
				public boolean hasNext() {
					return !done;
				}

				@Override
				public Tuple<E> next() {
					if (done)
						throw new NoSuchElementException();

					done = true;
					return emptyTuple;
				}

			};

		int[] startpos = new int[k];
		for (int i = 0; i < k; i++) {
			startpos[i] = 0;
		}

		// This iterator implements a loop
		return new Iterator<Tuple<E>>() {
			private int ik = k;
			private boolean hasnext = true;
			private int[] pos = startpos;
			private List<E> list = Collections.unmodifiableList(input);

			@Override
			public boolean hasNext() {
				return hasnext;
			}

			@Override
			public Tuple<E> next() {
				if (!hasNext())
					throw new NoSuchElementException();

				List<E> lres = new ArrayList<E>(ik);
				for (int i = 0; i < startpos.length; i++) {
					int j = startpos[i];
					lres.add(list.get(j));
				}
				Tuple<E> result = new Tuple<>(lres);

				boolean done = false;
				int i = ik - 1;
				int imax = list.size() - 1;
				while (!done) {
					if (pos[i] == imax) {
						if (i == 0) {
							done = true;
							hasnext = false;
						} else {
							i -= 1;
						}
						continue;
					}

					pos[i] = pos[i] + 1;
					for (int j = 1; i + j < ik; j++) {
						pos[i + j] = 0;
					}
					done = true;
				}

				return result;
			}
		};
	}
}
