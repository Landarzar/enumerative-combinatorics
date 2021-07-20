/**
 * 
 */
package edu.cs.ai.math.combinatorics;

import java.util.ArrayList;
import java.util.Collection;
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
public class PartitionsLexicographic {
	
	/**
	 * Returns a {@code Stream} of all partitions of the input set
	 * 
	 * @author Kai Sauerwald.
	 */
	public static <E> Stream<List<List<E>>> stream(List<E> input) {
		Iterator<List<List<E>>> itr = iterator(input);
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(itr, Spliterator.IMMUTABLE), false);
	}
	
	/***
	 * Returns an iterator, that allows iteration of all partitions of the given set.
	 * 
	 * @author Kai Sauerwald
	 * @param input a collection with the elements.
	 */
	public static <E> Iterator<List<List<E>>> iterator(List<E> input) {
		if (input == null)
			throw new IllegalArgumentException();
		
		// Empty iterator
		if(input.size() == 0)
		return new Iterator<List<List<E>>>() {

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public List<List<E>> next() {
				throw new NoSuchElementException();
			}
		};
		

		if(input.size() == 1)
		return new Iterator<List<List<E>>>() {
			boolean finished = false;
			E element = input.get(0);

			@Override
			public boolean hasNext() {
				return !finished;
			}

			@Override
			public List<List<E>> next() {
				if(finished)
					throw new NoSuchElementException();
				finished = true;
				return List.of(List.of(element));
			}
		};
		
		// Here starts Algorithm H		
		int n_tmp = input.size();

		int[] aj_tmp = new int[n_tmp]; // Array for a_1,\ldots a_n;
		int[] bj_tmp = new int[n_tmp]; // Array for b_1,\ldots b_n;
		
		// H1 [initialization]
		for (int i = 0; i < n_tmp; i++) {
			aj_tmp[i] =0;
			bj_tmp[i] =1;
		}

		return new Iterator<List<List<E>>>() {
			// H1 [initialization]
			List<E> list = new ArrayList<E>(input);
			boolean finished = false;
			int m = 1;
			int n = n_tmp;
			// a_1 \ldots a_{m+1}
			int[] ajs = aj_tmp;
			int[] bjs = bj_tmp;

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#hasNext()
			 */
			@Override
			public boolean hasNext() {
				return !finished;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see java.util.Iterator#next()
			 */
			@Override
			public List<List<E>> next() {
				if(finished)
					throw new NoSuchElementException();
				
				// H2 [Visit.]
				int tmp_m = m + (ajs[n-1] == m ? 1 : 0);
				ArrayList<List<E>> blocks = new ArrayList<>(tmp_m);
				for (int i = 0; i < tmp_m; i++) {
					ArrayList<E> block = new ArrayList<E>();
					blocks.add(block);
				}
				for(int i =0; i< n; i++) {
					blocks.get(ajs[i]).add(list.get(i));
				}
				
				assert !blocks.stream().anyMatch(b -> b.size()==0);
				
				// GOTO H3 or H4
				if(aj_tmp[n-1] != m)
				{
					// H3 [Increase a_n] // Remember indexshift
					aj_tmp[n-1] = aj_tmp[n-1] + 1;
					return blocks;
				}
				// H4 [Find j.]
				int j = n-1;
				while(ajs[j-1] == bjs[j-1])
					j--;
				// H5 [Increase a_j.]
				ajs[j-1] = ajs[j-1] + 1;
				if(j == 1)
				{
					finished = true;
					return blocks;
				}
				
				
				// H6 [Zero out a_{j+1} .. a_n.]
				m = bjs[j-1] + (ajs[j-1] == bjs[j-1] ? 1 : 0);
				j += 1;
				while(j<n)
				{
					ajs[j-1] = 0;
					bjs[j-1] = m;
					j += 1;
				}
				ajs[n-1] = 0;
				return blocks;
				
			}
		};
	}
	
	public static void main(String[] args) {
		iterator(List.of()).forEachRemaining(l -> {
			System.out.println(l);
		});
		iterator(List.of(1)).forEachRemaining(l -> {
			System.out.println(l);
		});
		iterator(List.of(1,2)).forEachRemaining(l -> {
			System.out.println(l);
		});
		iterator(List.of(1,2,3)).forEachRemaining(l -> {
			System.out.println(l);
		});
		
		
		
		iterator(List.of(1,2,3,4)).forEachRemaining(l -> {
			System.out.println(l);
		});
		
	}
}
