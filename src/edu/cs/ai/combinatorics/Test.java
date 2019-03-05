package edu.cs.ai.combinatorics;

import java.util.List;

public final class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PowerSet.stream(List.of()).forEach(System.out::println);
		System.out.print(PowerSet.iterator(List.of()).next());

	}

}
