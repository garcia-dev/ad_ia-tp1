package representations;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * @author Romain Garcia
 * @version 2018-09-11
 */

public class AllEqualConstraint implements Constraint {
	private Set<Variable> variables;

	public AllEqualConstraint(Set<Variable> variables) {
		this.variables = variables;
	}

	@Override
	public Set<Variable> getScope() {
		return variables;
	}

	@Override
	public boolean isSatisfiedBy(Map<Variable, String> allocation) {
		String value = null;

		for (Variable key : variables) {
			if (value == null) {
				value = allocation.get(key);
				continue;
			}

			if (!allocation.get(key).equals(value))
				return false;
		}

		return true;
	}

	@Override
	public boolean filter(Map<Variable, String> allocation, Map<Variable, Set<String>> variableDomain) {
		for (Map.Entry<Variable, String> entryAllocation : allocation.entrySet())
			if (getScope().contains(entryAllocation.getKey())) {
				for (Map.Entry<Variable, Set<String>> entryVariableDomain : variableDomain.entrySet())
					if (getScope().contains(entryVariableDomain.getKey()))
						variableDomain.put(entryVariableDomain.getKey(), new HashSet<>(Collections.singletonList(entryAllocation.getValue())));

				return true;
			}

		return false;
	}
}
