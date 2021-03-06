package extraction;

import representations.Variable;

import java.util.*;

/**
 * FrequentItemsetMiner's class
 * <p>
 * The FrequentItemsetMiner's class is a class generating the frequent item sets based on variable transactions.
 * </p>
 *
 * @author DORANGE Martin, GARCIA Romain, QUERRÉ Maël, WILLIAMSON Christina
 * @version 2018-11-15
 * @see Variable
 */
public class FrequentItemsetMiner {
	private final BooleanDatabase booleanDatabase;

	public FrequentItemsetMiner(BooleanDatabase booleanDatabase) {
		this.booleanDatabase = booleanDatabase;
	}

	/**
	 * Method generating the frequent item sets.
	 *
	 * @param minimalSupport the minimal value of support needed to be considered a frequent item
	 * @return a map of frequent item
	 */
	public Map<Set<Variable>, Double> frequentItemsets(Double minimalSupport) {
		Map<Set<Variable>, Double> frequentItemset = new HashMap<>();

		List<Set<Variable>> subsetsList = new ArrayList<>();

		// For each transaction, we're focusing on the variable with a value of '1'
		for (Map<Variable, String> map : booleanDatabase.getTransactionList()) {
			List<Variable> trueVariableList = new ArrayList<>();

			map.forEach((key, value) -> {
				if (value.equals("1"))
					trueVariableList.add(key);
			});

			// Generating every subsets
			subsetsList.addAll(Powerset.calcPowerSet(trueVariableList));
		}

		/*
			For each subset we're checking if the frequency/support value is higher than the minimal value, if it is
			we're putting it into the frequentItemset
		 */
		for (Set<Variable> subset : subsetsList) {
			double support = (double) Collections.frequency(subsetsList, subset) / booleanDatabase.getTransactionList().size();

			if (support >= minimalSupport)
				frequentItemset.put(subset, support);
		}

		return frequentItemset;
	}
}
