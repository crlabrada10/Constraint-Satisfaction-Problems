/**
 * 
 */
package org.home.study.csp;

import java.util.List;
import java.util.Map;

/**
 * @author eragon
 * @version 1.0
 */
public abstract class Constrain<V, D> {

	protected List<V> variables;

	public Constrain(List<V> variables) {
		this.variables = variables;
	}

	public abstract boolean satisfied(Map<V, D> assigment);

}
