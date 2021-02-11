package edu.cs.ai.math.combinatorics;

import java.util.HashSet;
import java.util.Set;

public class Layer<E> {
//	RankedDistanceStructure<E> mother;
	Set<E> elements;
	int rank;
	Layer<E> prev;
	Layer<E> next;

//	public Layer(int rank, RankedDistanceStructure<E> mother) {
//		this.elements = new HashSet<>();
//		this.rank = rank;
//		this.mother = mother;
//		this.prev = this;
//		this.next = this;
//	}
//
//	public Layer(E obj, int rank, RankedDistanceStructure<E> mother) {
//		this.elements = new HashSet<>(Set.of(obj));
//		this.rank = rank;
//		this.mother = mother;
//		this.prev = this;
//		this.next = this;
//	}

	public Layer(int rank) {
		this.elements = new HashSet<>();
		this.rank = rank;
		this.prev = this;
		this.next = this;
	}

	public Layer(int rank, E obj) {
		this.elements = new HashSet<>(Set.of(obj));
		this.rank = rank;
		this.prev = this;
		this.next = this;
	}

	public Layer(int rank, E... obj) {
		this.elements = new HashSet<>(Set.of(obj));
		this.rank = rank;
		this.prev = this;
		this.next = this;
	}

//	/**
//	 * @return the mother
//	 */
//	public RankedDistanceStructure<E> getMother() {
//		return mother;
//	}

	/**
	 * @return the prev
	 */
	public Layer<E> getPrev() {
		return prev;
	}

	/**
	 * @return the next
	 */
	public Layer<E> getNext() {
		return next;
	}

	/**
	 * @return the elements
	 */
	public Set<E> getElements() {
		return elements;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/***
	 * Inserts the element <layer> after this element
	 * 
	 * @param layer
	 */
	public void insertAfter(Layer<E> layer) {
//		mother.layers.add(layer);
//		layer.mother = this.mother;
		layer.next = this.next;
		layer.next.prev = layer;
		this.next = layer;
		layer.prev = this;

//		if (layer.next == mother.root)
//			layer.rank = this.rank + mother.defaultDistance;
//		else { 
//			layer.rank = this.rank + mother.defaultDistance;
//			if (layer.next.rank <= this.rank + mother.defaultDistance)
//				layer.next.addRankAllAfter(mother.defaultDistance - (layer.next.rank - this.rank) + 1);
//	}
	}

	/***
	 * Inserts the element <layer> before this element
	 * 
	 * @param layer
	 */
	public void insertBefore(Layer<E> layer) {
//		mother.layers.add(layer);
//		layer.mother = this.mother;
		layer.prev = this.prev;
		layer.prev.next = layer;
		this.prev = layer;
		layer.next = this;

//		if (this == mother.root)
//			layer.rank = this.rank - mother.defaultDistance;
//		else 
//			layer.rank = this.rank - mother.defaultDistance;
//			if (layer.prev.rank + mother.defaultDistance >= this.rank)
//			addRankAllAfter(mother.defaultDistance - (layer.prev.rank - this.rank) + 1);
	}

	private void addRankAllAfter(int a) {
		rank += a;
//		if (next != mother.root)
//			next.addRankAllAfter(a);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		boolean first = true;
		for (E e : elements) {
			if (!first)
				builder.append(',');
			builder.append(e);
			first = false;
		}
		builder.append("}");
		return builder.toString();
	}

	public String toStringWithoutBraces() {
		StringBuilder builder = new StringBuilder();
		boolean first = true;
		for (E e : elements) {
			if (!first)
				builder.append(',');
			builder.append(e);
			first = false;
		}
		return builder.toString();
	}
}
