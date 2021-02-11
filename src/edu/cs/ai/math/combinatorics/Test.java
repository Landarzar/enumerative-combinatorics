package edu.cs.ai.math.combinatorics;

import java.util.List;

public final class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> list = List.of(1,2,4);

		System.out.println("Powersets");
		PowerSet.stream(list).forEach(System.out::println);
		System.out.println("Powersets (lexicographic)");
		PowerSetLexicographic.stream(list).forEach(System.out::println);
		System.out.println("K-Tuples (k=2)");
		KTupleEnumeration.stream(list,2).forEach(System.out::println);
		System.out.println("K-Combinations (k=2)");
		KCombinationLexicographic.stream(list,2).forEach(System.out::println);

	}

}
