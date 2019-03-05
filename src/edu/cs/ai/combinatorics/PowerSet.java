/**
 * 
 */
package edu.cs.ai.combinatorics;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
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
public class PowerSet {

	/***
	 * Constructs the powerset over the given collection. Note this operation really
	 * constructs sets, notably it removes duplicates.
	 * 
	 * @author Kai Sauerwald
	 * @param set
	 * @return The set of all subsets
	 */
	public static <E> Set<Set<E>> construct(Collection<E> set) {
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
	
    /**
     * Returns a {@code Stream} of all Subsets of the input collection
     * @author Kai Sauerwald.
     */
	public static <E> Stream<Set<E>> stream(Collection<E> input){
		Iterator<Set<E>> itr = iterator(input);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itr, Spliterator.IMMUTABLE), false);
	}

	/***
	 * Returns an iterator, that allows iteration of all elements of the powerset.
	 * @author Kai Sauerwald
	 * @param input a collection with the elements.
	 */
	public static <E> Iterator<Set<E>> iterator(Collection<E> input) {
		if(input == null)
			throw new IllegalArgumentException();
		
		// This iterator, implements the same loop as the construct method
		return new Iterator<Set<E>>() {
			private Set<Set<E>> resultMem = null; 
			private Set<Set<E>> previousResultMem = null;
			private final Iterator<E> baseItr = new HashSet<>(input).iterator();
			private E element = null;
			private Iterator<Set<E>> curItr = null;

			/*
			 * (non-Javadoc)
			 * @see java.util.Iterator#hasNext()
			 */
			@Override
			public boolean hasNext() {
				return resultMem == null || baseItr.hasNext() || (curItr == null ? false : curItr.hasNext());
			}

			/*
			 * (non-Javadoc)
			 * @see java.util.Iterator#next()
			 */
			@Override
			public Set<E> next() {
				Set<E> returnV;
				// resultMem == null marks the situation of the empty set
				if(resultMem == null) {
					resultMem = new HashSet<>();
					returnV = new HashSet<E>();
					resultMem.add(returnV);
					return returnV;
				}
				// curItr == null is the beginning of a new loop pass.
				if (curItr == null) {
					if(!baseItr.hasNext()) // if baseItr.hasNext() == false, then we added all elements from the source collection.
						throw new NoSuchElementException();
					element = baseItr.next();
					previousResultMem = new HashSet<>(resultMem);
					curItr = previousResultMem.iterator();
				}
				
				Set<E> newSubSet = new HashSet<E>(curItr.next());
				newSubSet.add(element);
				resultMem.add(newSubSet);
				
				if(!curItr.hasNext())
					curItr = null;
					
				return newSubSet;
				
			}
		};
	}
}
