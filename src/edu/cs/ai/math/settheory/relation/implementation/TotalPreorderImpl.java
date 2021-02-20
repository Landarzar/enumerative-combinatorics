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
public class TotalPreorderImpl<U> extends AbstractTotalPreorderImpl<U> {

	private LinkedList<Set<U>> layers;

	/**
	 * Constructs the modular preorder over an empty set
	 * 
	 * @author Kai Sauerwald
	 */
	public TotalPreorderImpl() {
		layers = new LinkedList<>();
	}

	public TotalPreorderImpl(List<Set<U>> layers) {
		this.layers = new LinkedList<>(layers);
		trim();
	}
	
	public List<Set<U>> getLayers(){
		return Collections.unmodifiableList(layers);
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
	}
}
