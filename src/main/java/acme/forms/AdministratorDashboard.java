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
import java.util.List;

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
//	Map<PatronageStatus,Integer> totalNumberOfPatronagesGroupedByPatronageStatus;
	List<Object[]> totalNumberOfPatronagesGroupedByPatronageStatus;
	
	//	average 
	//Map<Pair<String,String>,Double> averageRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	List<Object[]> averageRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	//Map<String,Double> averageRetailPriceOfToolGroupedByCurrency;
	List<Object[]> averageRetailPriceOfToolGroupedByCurrency;
	//Map<PatronageStatus,Double> averageBudgetOfPatronagesGroupedByPatronageStatus;
	List<Object[]> averageBudgetOfPatronagesGroupedByPatronageStatus;

	//	deviation 
	//Map<Pair<String,String>,Double> deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	List<Object[]> deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	//Map<String,Double> deviationRetailPriceOfToolGroupedByCurrency;
	List<Object[]> deviationRetailPriceOfToolGroupedByCurrency;
	//Map<PatronageStatus,Double> deviationBudgetOfPatronagesGroupedByPatronageStatus;
	List<Object[]> deviationBudgetOfPatronagesGroupedByPatronageStatus;

	//	minimum, and maximum 
	//Map<Pair<String,String>,Double> minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	List<Object[]> minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	//Map<String,Double> minimumRetailPriceOfToolGroupedByCurrency;
	List<Object[]> minimumRetailPriceOfToolGroupedByCurrency;
	//Map<PatronageStatus,Double> minimumBudgetOfPatronagesGroupedByPatronageStatus;
	List<Object[]> minimumBudgetOfPatronagesGroupedByPatronageStatus;
	
	//Map<Pair<String,String>,Double> maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	List<Object[]> maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	//Map<String,Double> maximumRetailPriceOfToolGroupedByCurrency;
	List<Object[]> maximumRetailPriceOfToolGroupedByCurrency;
	//Map<PatronageStatus,Double> maximumBudgetOfPatronagesGroupedByPatronageStatus;
	List<Object[]>  maximumBudgetOfPatronagesGroupedByPatronageStatus;

}
