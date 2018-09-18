package examples;

import representations.AllEqualConstraint;
import representations.Variable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
	public static void main(String[] args) {
		Set<String> colorSet = new HashSet<>();
		colorSet.add("black");
		colorSet.add("blue");
		colorSet.add("green");
		colorSet.add("red");

		Set<String> booleanSet = new HashSet<>();
		booleanSet.add("True");
		booleanSet.add("False");

		// Colors

		Variable roofColor = new Variable("couleur_toit", colorSet);
		Variable hoodColor = new Variable("couleur_capot", colorSet);
		Variable tailgateColor = new Variable("couleur_hayon", colorSet);

		Variable leftSide = new Variable("couleur_gauche", colorSet);
		Variable rightSide = new Variable("couleur_droit", colorSet);

		// Options

		Variable openingRoof = new Variable("toit_ouvrant", booleanSet);
		Variable sono = new Variable("sono", booleanSet);

		Map<Variable, String> allEqualColors = new HashMap<>();
		allEqualColors.put(roofColor, "black");
		allEqualColors.put(hoodColor, "black");
		allEqualColors.put(tailgateColor, "black");

		Map<Variable, String> notAllEqualColor = new HashMap<>();
		notAllEqualColor.put(roofColor, "black");
		notAllEqualColor.put(hoodColor, "red");
		notAllEqualColor.put(tailgateColor, "blue");

		AllEqualConstraint allEqualConstraint = new AllEqualConstraint();
		System.out.println(allEqualConstraint.isSatisfiedBy(allEqualColors));
		System.out.println(!allEqualConstraint.isSatisfiedBy(notAllEqualColor));
	}
}
