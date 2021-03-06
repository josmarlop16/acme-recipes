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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier -----------------------------------------------
	protected static final long	serialVersionUID	= 1L;


	//	total number of pro-posed/accepted/denied patronages
	
	Integer						totalNumberOfProposedPatronages;
	Integer						totalNumberOfAcceptedPatronages;
	Integer						totalNumberOfDeniedPatronages;
	
	//	average budget
	Double						averageBudgetOfProposedPatronages;
	Double						averageBudgetOfAcceptedPatronages;
	Double						averageBudgetOfDeniedPatronages;
	
	
	//	deviation budget
	Long						deviationBudgetOfProposedPatronages;
	Long						deviationBudgetOfAcceptedPatronages;
	Long						deviationBudgetOfDeniedPatronages;
	

	//	minimum, and maximum budget
	Double						minimumBudgetOfProposedPatronages;
	Double						minimumBudgetOfAcceptedPatronages;
	Double						minimumBudgetOfDeniedPatronages;
	
	Double						maximumBudgetOfProposedPatronages;
	Double						maximumBudgetOfAcceptedPatronages;
	Double						maximumBudgetOfDeniedPatronages;

}
