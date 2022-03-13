/*
 * AdministratorDashboardShowService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.patron.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
public class PatronDashboardShowService implements AbstractShowService<Patron, Dashboard> {


	@Autowired
	protected PatronDashboardRepository repository;


	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, 
			"totalNumberOfProposedPatronages","totalNumberOfAcceptedPatronages","totalNumberOfDeniedPatronages",
			"averageNumberOfPatronages","deviationOfPatronages",
			"minimumBudgetOfProposedPatronages","minimumBudgetOfAcceptedPatronages","minimumBudgetOfDeniedPatronages",
			"maximumBudgetOfProposedPatronages","maximumBudgetOfAcceptedPatronages","maximumBudgetOfDeniedPatronages");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		
		final Dashboard result = new Dashboard();
		
//		total number of pro-posed/accepted/denied patronages
		final Integer						totalNumberOfProposedPatronages;
		final Integer						totalNumberOfAcceptedPatronages;
		final Integer						totalNumberOfDeniedPatronages;
		
//		average
		final Double						averageNumberOfPatronages;
		
//		deviation
		final Long							deviationOfPatronages;

//		minimum, and maximum budget of proposed/accepted/denied patronages grouped by currency.
		final Double						minimumBudgetOfProposedPatronages;
		final Double						minimumBudgetOfAcceptedPatronages;
		final Double						minimumBudgetOfDeniedPatronages;
		
		final Double						maximumBudgetOfProposedPatronages;
		final Double						maximumBudgetOfAcceptedPatronages;
		final Double						maximumBudgetOfDeniedPatronages;
		
		totalNumberOfProposedPatronages = this.repository.totalNumberOfProposedPatronages();
		totalNumberOfAcceptedPatronages = this.repository.totalNumberOfAcceptedPatronages();
		totalNumberOfDeniedPatronages = this.repository.totalNumberOfDeniedPatronages();
		
		averageNumberOfPatronages = this.repository.averageNumberOfPatronages();
		deviationOfPatronages = this.repository.deviationOfPatronages();
		
		minimumBudgetOfProposedPatronages = this.repository.minimumBudgetOfProposedPatronages();
		minimumBudgetOfAcceptedPatronages = this.repository.minimumBudgetOfAcceptedPatronages();
		minimumBudgetOfDeniedPatronages = this.repository.minimumBudgetOfDeniedPatronages();
		
		maximumBudgetOfProposedPatronages = this.repository.maximumBudgetOfProposedPatronages();
		maximumBudgetOfAcceptedPatronages = this.repository.maximumBudgetOfAcceptedPatronages();
		maximumBudgetOfDeniedPatronages = this.repository.maximumBudgetOfDeniedPatronages();
		
		result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
		result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
		result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);
		
		result.setAverageNumberOfPatronages(averageNumberOfPatronages);
		result.setDeviationOfPatronages(deviationOfPatronages);
		
		result.setMinimumBudgetOfProposedPatronages(minimumBudgetOfProposedPatronages);
		result.setMinimumBudgetOfAcceptedPatronages(minimumBudgetOfAcceptedPatronages);
		result.setMinimumBudgetOfDeniedPatronages(minimumBudgetOfDeniedPatronages);
		
		result.setMaximumBudgetOfProposedPatronages(maximumBudgetOfProposedPatronages);
		result.setMaximumBudgetOfAcceptedPatronages(maximumBudgetOfAcceptedPatronages);
		result.setMaximumBudgetOfDeniedPatronages(maximumBudgetOfDeniedPatronages);
		
		return result;
	}

}
