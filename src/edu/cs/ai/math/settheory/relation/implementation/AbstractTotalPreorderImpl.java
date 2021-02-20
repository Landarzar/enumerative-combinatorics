/**
 * 
 */
package edu.cs.ai.math.settheory.relation.implementation;

import java.util.AbstractCollection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import edu.cs.ai.math.settheory.Tuple;
import edu.cs.ai.math.settheory.relation.RelationStatus;
import edu.cs.ai.math.settheory.relation.TotalPreorder;

/**
 * @author Kai Sauerwald
 *
 */
public abstract class AbstractTotalPreorderImpl<U> extends AbstractCollection<U> implements TotalPreorder<U> {

	/**
	 * Constructs the modular preorder over an empty set
	 * 
	 * @author Kai Sauerwald
	 */
	public AbstractTotalPreorderImpl() {
	}
	
	public abstract List<Set<U>> getLayers();

	private Set<U> getLayer(U elem) {
		for (Set<U> set : getLayers()) {
			if (set.contains(elem))
				return set;
		}
		return null;
	}

	private Integer getLayerNbr(U elem) {
		int nbr = 0;
		for (Iterator<Set<U>> iterator = getLayers().iterator(); iterator.hasNext();) {
			Set<U> set = iterator.next();
			if (set.contains(elem)) {
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
		if (r1 == null || r2 == null)
			return RelationStatus.INCOMPARABLE;
		if (r1.equals(r2))
			return RelationStatus.EQUAL;
		if (r1 < r2)
			return RelationStatus.STRICT_LESSER;
		return RelationStatus.STRICT_GREATER;
	}

	@Override
	public boolean contains(Tuple<U> tuple) {
		return relate(tuple.getIth(0), tuple.getIth(1)).isLesser();
	}

	@Override
	public Set<U> getMinimalElements() {
		if(getLayers().isEmpty())
			return Set.of();
		return Collections.unmodifiableSet(getLayers().get(0));
	}

	@Override
	public Set<U> getMinimalElements(Set<U> subset) {
		Optional<Integer> l;
		try {
			l = subset.stream().map(s -> getLayerNbr(s)).min(Integer::compare);
		} catch (NullPointerException e) {
			return Set.of();
		}
		if(l.isEmpty())
			return Set.of();
		return getLayers().get(l.get()).stream().filter(u -> subset.contains(u)).collect(Collectors.toUnmodifiableSet());
	}

	@Override
	public Set<U> getRelationBase() {
		return getLayers().stream().collect(Collectors.flatMapping(s -> s.stream(), Collectors.toUnmodifiableSet()));
	}

	@Override
	public Iterator<U> iterator() {

		return new Iterator<U>() {

			Iterator<Set<U>> currentLayer = getLayers().iterator();
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
		return getLayers().stream().collect(Collectors.summingInt(l -> l.size()));
	}

	@Override
	public String toString() {
		return getLayers().toString();
	}
}
