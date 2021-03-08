package org.home.study;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.home.study.csp.AustraliaColoringConstrain;
import org.home.study.csp.ConstrainSatisfiedProblem;

/**
 * @author crlabrada10
 * @version 1.0
 */
public class App 
{
    public static void main( String[] args )
    {
    	//Variables
    	List<String> variables = List.of("Western Australia", "Northern Territory", 
    				"Queensland", "South Australia", "New South Wales", "Victoria", "Tasmania");
    	// Dominios
    	Map<String, List<String>> domains = new HashMap<>();
    	
    	for (String variable : variables) {
    		domains.put(variable, List.of("rojo", "verde", "azul"));
		}
    	
    	// Restricciones
    	ConstrainSatisfiedProblem<String , String> problema = new ConstrainSatisfiedProblem<>(variables, domains);
    	problema.addConstrain(new AustraliaColoringConstrain("Western Australia", "Northern Territory"));
    	problema.addConstrain(new AustraliaColoringConstrain("Western Australia", "South Australia"));
    	
    	problema.addConstrain(new AustraliaColoringConstrain("Northern Territory", "South Australia"));
    	problema.addConstrain(new AustraliaColoringConstrain("Northern Territory", "Queensland"));
    	problema.addConstrain(new AustraliaColoringConstrain("South Australia", "Queensland"));
    	problema.addConstrain(new AustraliaColoringConstrain("New South Wales", "Queensland"));
    	problema.addConstrain(new AustraliaColoringConstrain("New South Wales", "South Australia"));
    	problema.addConstrain(new AustraliaColoringConstrain("Victoria", "South Australia"));
    	problema.addConstrain(new AustraliaColoringConstrain("New South Wales", "Victoria"));
    	problema.addConstrain(new AustraliaColoringConstrain("Tasmania", "Victoria"));
    	
    	Map<String, String> solution = problema.backtrack();
        System.out.println( "Hello World!" + solution);
    }
}
