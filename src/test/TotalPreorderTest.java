/**
 * 
 */
package test;

import java.util.List;
import java.util.Set;

import edu.cs.ai.math.settheory.relation.TotalPreorder;
import edu.cs.ai.math.settheory.relation.implementation.TotalPreorderImpl;

/**
 * @author Kai Sauerwald
 *
 */
class TotalPreorderTest {
	
	public static void main(String[] args) {
		
		Set<String> worlds = Set.of("ab","a-b","-ab","-a-b");
		
		TotalPreorder<String> tpo = new TotalPreorderImpl<>(List.of(Set.of("ab","a-b"),Set.of(), Set.of("-ab","-a-b")));
		
		System.out.println(tpo);
		
		System.out.println(tpo.relate("ab", "-ab"));
	}
}
