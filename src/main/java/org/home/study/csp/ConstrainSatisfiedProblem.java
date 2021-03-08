/**
 * 
 */
package org.home.study.csp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author eragon
 * @version 1.0
 */
public class ConstrainSatisfiedProblem<V, D> {

	private List<V> variables;
	private Map<V, List<D>> domains;
	private Map<V, List<Constrain<V, D>>> constrains = new HashMap<>();

	public ConstrainSatisfiedProblem(List<V> variables, Map<V, List<D>> domains) {
		this.variables = variables;
		this.domains = domains;

		for (V variable : variables) {
			constrains.put(variable, new ArrayList<Constrain<V,D>>());
			// Cada varaible debe de tener un dominio asignado
			if (!domains.containsKey(variable)) {
				throw new IllegalArgumentException("La variable " + variable + "no contiene un dominio");
			}
		}
	}

	public void addConstrain(Constrain<V, D> constrain) {
		for (V variable : constrain.variables) {
			 
			// Variable que se encuentra en el constrain tambien sea parte del ConstrainSatisfiedProblem
			if (!this.variables.contains(variable)) {
				throw new IllegalArgumentException(
						"La variable " + variable + "no se encuentra en el ConstrainSatisfiedProblem");
			}
			constrains.get(variable).add(constrain);
		}
	}
	
	public boolean consistent(V variable, Map<V, D> assignment) {
		for (Constrain<V, D> constrain : this.constrains.get(variable)) {
			if(!constrain.satisfied(assignment)) {
				return false;
			}
		}
		return true;
	}
	
	public Map<V, D> backtrack(){
		return backtrack(new HashMap<>());
	}
	
	public Map<V, D> backtrack(Map<V, D> assigment) {
		
		// Si la asigacion es completa (para cada variable existe una asignacion del domino)
		if(variables.size() == assigment.size()) {
			return assigment;
		}
		
		// Seleccionar una variable no asignada
		V unassingmed = variables.stream().filter(
					v -> !assigment.containsKey(v)).findFirst().get();
		
		for (D value: domains.get(unassingmed)) {
			System.out.println("Variable " + unassingmed + " Domain " + value);
			
			// Probar una asignacion
			// 1- Crear una copia de la asignacion anterior
			Map<V, D> localAssignment = new HashMap<>(assigment);
			// 2- Probar (asignar valor )
			localAssignment.put(unassingmed, value);
			// 3- Verificar la consistencia de la asignacion
			
			if(consistent(unassingmed, localAssignment)) {
				Map<V, D> result = backtrack(localAssignment);
				if(result != null) {
					return result;
				}
			}
		}
		return null;
	}
}
