/**
 * 
 */
package edu.cs.ai.math.settheory.relation;

/**
 * @author Kai Sauerwald
 *
 */
public interface ModularPreorder<U> extends Preorder<U> {
	
	@Override
	default boolean isModular() {
		return true;
	}
}
