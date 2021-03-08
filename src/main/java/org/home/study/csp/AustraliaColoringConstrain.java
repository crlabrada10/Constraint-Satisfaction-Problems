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
public class AustraliaColoringConstrain extends Constrain<String, String> {

	private String startPlace;
	private String endPlace;

	public AustraliaColoringConstrain(String starPlace, String endPlace) {
		super(List.of(starPlace, endPlace));
		this.startPlace = starPlace;
		this.endPlace = endPlace;
	}

	@Override
	public boolean satisfied(Map<String, String> assigment) {
		// Verifcar si la variable no se encuentra asignada
		if(!assigment.containsKey(startPlace) || !assigment.containsKey(endPlace))
			return true;
		// Chequear que startPlace distinto de endPlace
		return !assigment.get(startPlace).equals(assigment.get(endPlace));
	}

}
