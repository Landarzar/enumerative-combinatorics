package edu.cs.ai.math.combinatorics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EnumerativeCombinatorics {
	static <T> void generateVariations(List<T> elements, Consumer<List<T>> eater) {
		generateVariations(elements, new LinkedList<>(), eater, elements.size());
	}

	static <T> void generateVariations(List<T> elements, LinkedList<T> variation, Consumer<List<T>> eater, int depth) {
		if (depth <= 0)
			eater.accept(variation);
		else {
			for (int i = 0; i < elements.size(); i++) {
				variation.addLast(elements.get(i));
				generateVariations(elements, variation, eater, depth - 1);
				variation.removeLast();
			}
		}
	}

	static <T> void generatePermutations(List<T> elements, Consumer<List<T>> eater) {
		generatePermutations(0, new ArrayList<>(elements), new boolean[elements.size()], new ArrayList<>(elements),
				eater);
	}

	/***
	 * https://sol.cs.hm.edu/4129/html/208-permutationen.xhtml
	 * 
	 * @param length
	 * @param permutation
	 * @param used
	 * @param elements
	 * @param eater
	 */
	static <T> void generatePermutations(int length, List<T> permutation, boolean[] used, List<T> elements,
			Consumer<List<T>> eater) {
		if (length >= used.length)
			eater.accept(permutation);
		else
			for (int n = 0; n < used.length; n++) {
				if (used[n])
					continue;
				permutation.set(length, elements.get(n));
				used[n] = true;
				generatePermutations(length + 1, permutation, used, elements, eater);
				used[n] = false;
			}
	}

	public static <T> HashSet<List<Layer<T>>> constructPreorders(final HashSet<List<Integer>> preOrds, List<T> source) {
		HashSet<List<Layer<T>>> result = new HashSet<>();

		for (List<Integer> list : preOrds) {
			int layers = Collections.max(list) + 1;

			ArrayList<Layer<T>> tmp = new ArrayList<>(layers);
			for (int schicht = 0; schicht < layers; schicht++) {
				if (schicht == 0)
					tmp.add(new Layer<>(schicht));
				else {
					tmp.get(schicht - 1).insertAfter(new Layer<>(schicht));
					tmp.add(tmp.get(schicht - 1).getNext());
				}
			}

			for (int i = 0; i < list.size(); i++) {
				int l = list.get(i);
				tmp.get(l).getElements().add(source.get(i));
			}
			result.add(tmp);
		}
		return result;
	}

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

	public static HashSet<List<Integer>> genAllPreorders(int size) {
		List<Integer> range = IntStream.rangeClosed(0, size - 1).boxed().collect(Collectors.toList());
		final HashSet<List<Integer>> preOrds = new HashSet<>();

		EnumerativeCombinatorics.generateVariations(range, new Consumer<>() {
			// int count = 0;

			@Override
			public void accept(List<Integer> variation) {
				int min = Collections.min(variation);
				ArrayList<Integer> tmp = new ArrayList<>(size);
				for (Integer i : variation) {
					tmp.add(i - min);
				}
				// Remove empty layers:
				for (int i = 1; i < Collections.max(tmp);) {
					if (!tmp.contains(i)) {
						for (int j = 0; j < tmp.size(); j++) {
							if (tmp.get(j) > i)
								tmp.set(j, tmp.get(j) - 1);
						}
					} else
						i++;
				}

				preOrds.add(tmp);
				// count += 1;
				// if (count % 1 == 0 && (tmp.toString().contains("7")))
				// System.out.println(count + " " + tmp);
			}
		});

		// System.out.println(preOrds.size());
		// LocalTime start = LocalTime.now();
		// for(long i = 0L; i < 250000000000L; i++)
		// {
		//
		// if (i % 1000000000L == 0)
		// System.out.println(i);
		// }
		// LocalTime end = LocalTime.now();
		// System.out.println("blub " + Duration.between(start, end));

		return preOrds;
	}
}
