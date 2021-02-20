/**
 * 
 */
package edu.cs.ai.math.settheory.relation;

import java.util.Set;

/**
 * @author Kai Sauerwald
 *
 */
public interface Preorder<U> extends BinaryRelation<U> {

	@Override
	public default boolean isTransitive() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	/****
	 * Obtains the global minimally elements 
	 * @author Kai Sauerwald
	 */
	public Set<U> getMinimalElements();
	
	/***
	 * Computes the minimal elements w.r.t the subset
	 * @param subset
	 * @author Kai Sauerwald
	 */
	public Set<U> getMinimalElements(Set<U> subset);
}
