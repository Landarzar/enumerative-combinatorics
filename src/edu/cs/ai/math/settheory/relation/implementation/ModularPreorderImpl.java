/**
 * 
 */
package edu.cs.ai.math.settheory.relation.implementation;

import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import edu.cs.ai.math.settheory.Pair;
import edu.cs.ai.math.settheory.Tuple;
import edu.cs.ai.math.settheory.relation.ModularPreorder;
import edu.cs.ai.math.settheory.relation.RelationStatus;

/**
 * @author Kai Sauerwald
 *
 */
public class ModularPreorderImpl<U> extends AbstractCollection<U> implements ModularPreorder<U> {

	private LinkedList<Set<U>> layers;
	private Set<Pair<U, U>> comparable;

	/**
	 * Constructs the modular preorder over an empty set
	 * 
	 * @author Kai Sauerwald
	 */
	public ModularPreorderImpl() {
		layers = new LinkedList<>();
		comparable = new HashSet<>();
	}

	public ModularPreorderImpl(List<Set<U>> layers, Set<Pair<U, U>> comparables) {
		this.layers = new LinkedList<>(layers);
		this.comparable = new HashSet<>(comparables);
		trim();
	}

	/***
	 * Shrinks the underlying datastructures
	 */
	private void trim() {
		for (Iterator<Set<U>> iterator = layers.iterator(); iterator.hasNext();) {
			Set<U> set = (Set<U>) iterator.next();
			if (set.isEmpty())
				iterator.remove();
		}

		for (Iterator<Pair<U, U>> iterator = comparable.iterator(); iterator.hasNext();) {
			Pair<U, U> pair = iterator.next();

			Set<U> l1 = getLayer(pair.getFirst());
			Set<U> l2 = getLayer(pair.getFirst());
			if (l1 == null || l2 == null || !l1.equals(l2)) {
				iterator.remove();
			}
		}

	}

	private Set<U> getLayer(U elem) {
		for (Set<U> set : layers) {
			if (set.contains(elem))
				return set;
		}
		return null;
	}
	
	private Integer getLayerNbr(U elem) {
		int nbr =0;
		for (Iterator<Set<U>> iterator = layers.iterator(); iterator.hasNext();) {
			Set<U> set = iterator.next();
			if(set.contains(elem)) {
				return nbr;
			}
			nbr += 1;
		}
		return null;
	}

	@Override
	public RelationStatus relate(U x1, U x2) {
		Integer r1 = getLayerNbr(x1);
		Integer r2 = getLayerNbr(x2);
		if(r1 == null || r2 == null)
			return RelationStatus.INCOMPARABLE;
		if(r1.equals(r2))
		{
			if(comparable.contains(new Pair<>(x1, x2)))
				return RelationStatus.EQUAL;
			return RelationStatus.INCOMPARABLE;
		}
		if(r1 < r2)
			return RelationStatus.STRICT_LESSER;
		return RelationStatus.STRICT_GREATER;
	}

	@Override
	public boolean contains(Tuple<U> tuple) {
		return relate(tuple.getIth(0), tuple.getIth(1)).isLesser();
	}

	@Override
	public Set<U> getMinimalElements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<U> getMinimalElements(Set<U> subset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<U> getRelationBase() {
		return layers.stream().collect(Collectors.flatMapping(s -> s.stream(), Collectors.toUnmodifiableSet()));
	}

	@Override
	public Iterator<U> iterator() {

		return new Iterator<U>() {

			Iterator<Set<U>> currentLayer = layers.iterator();
			Iterator<U> currentElem = currentLayer.next().iterator();

			@Override
			public boolean hasNext() {
				return currentElem.hasNext();
			}

			@Override
			public U next() {
				U result = currentElem.next();

				if (!currentElem.hasNext() && currentLayer.hasNext())
					currentElem = currentLayer.next().iterator();

				return result;
			}
		};
	}

	@Override
	public int size() {
		return layers.stream().collect(Collectors.summingInt(l -> l.size()));
	}

}
