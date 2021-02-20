/**
 * 
 */
package edu.cs.ai.math.settheory.relation;

/**
 * @author Kai Sauerwald
 *
 */
public interface TotalPreorder<U> extends ModularPreorder<U> {
	@Override
	default boolean isTotal() {
		return true;
	}
}
