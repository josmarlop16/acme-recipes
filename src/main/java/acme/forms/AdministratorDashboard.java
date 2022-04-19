/*
 * Dashboard.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.forms;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import acme.entities.patronages.PatronageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorDashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------
	protected static final long	serialVersionUID	= 1L;


	//	total number 
	int						totalNumberOfComponents;
	int						totalNumberOfTools;
	int						totalNumberOfPatronages;
	Map<PatronageStatus,Integer> totalNumberOfPatronagesGroupedByPatronageStatus;

	
	//	average 
	Map<Pair<String,String>,Double> averageRetailPriceOfComponentGroupedByTechnologyAndCurrency;

	Map<String,Double> averageRetailPriceOfToolGroupedByCurrency;

	Map<PatronageStatus,Double> averageBudgetOfPatronagesGroupedByPatronageStatus;


	//	deviation 
	Map<Pair<String,String>,Double> deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency;

	Map<String,Double> deviationRetailPriceOfToolGroupedByCurrency;

	Map<PatronageStatus,Double> deviationBudgetOfPatronagesGroupedByPatronageStatus;


	//	minimum, and maximum 
	Map<Pair<String,String>,Double> minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency;

	Map<String,Double> minimumRetailPriceOfToolGroupedByCurrency;

	Map<PatronageStatus,Double> minimumBudgetOfPatronagesGroupedByPatronageStatus;

	
	Map<Pair<String,String>,Double> maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency;

	Map<String,Double> maximumRetailPriceOfToolGroupedByCurrency;

	Map<PatronageStatus,Double> maximumBudgetOfPatronagesGroupedByPatronageStatus;


}
