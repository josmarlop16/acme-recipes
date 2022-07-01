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

package acme.features.epicure.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Epicure;

@Service
public class EpicureDashboardShowService implements AbstractShowService<Epicure, Dashboard> {


	@Autowired
	protected EpicureDashboardRepository repository;


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
			"averageBudgetOfProposedPatronages","averageBudgetOfAcceptedPatronages","averageBudgetOfDeniedPatronages",
			"deviationBudgetOfProposedPatronages","deviationBudgetOfAcceptedPatronages","deviationBudgetOfDeniedPatronages",
			"minimumBudgetOfProposedPatronages","minimumBudgetOfAcceptedPatronages","minimumBudgetOfDeniedPatronages",
			"maximumBudgetOfProposedPatronages","maximumBudgetOfAcceptedPatronages","maximumBudgetOfDeniedPatronages");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		int epicureId = request.getPrincipal().getActiveRoleId();
		final Dashboard result = new Dashboard();
		
//		total number of pro-posed/accepted/denied patronages
		final Integer totalNumberOfProposedPatronages;
		final Integer totalNumberOfAcceptedPatronages;
		final Integer totalNumberOfDeniedPatronages;
		
//		average
		final Double averageBudgetOfProposedPatronages;
		final Double averageBudgetOfAcceptedPatronages;
		final Double averageBudgetOfDeniedPatronages;
		
//		deviation
		final Long deviationBudgetOfProposedPatronages;
		final Long deviationBudgetOfAcceptedPatronages;
		final Long deviationBudgetOfDeniedPatronages;
		

//		minimum, and maximum budget of proposed/accepted/denied patronages grouped by currency.
		final Double minimumBudgetOfProposedPatronages;
		final Double minimumBudgetOfAcceptedPatronages;
		final Double minimumBudgetOfDeniedPatronages;
		
		final Double maximumBudgetOfProposedPatronages;
		final Double maximumBudgetOfAcceptedPatronages;
		final Double maximumBudgetOfDeniedPatronages;
		
//		Number of proposed patronages
		totalNumberOfProposedPatronages = this.repository.totalNumberOfProposedPatronages(epicureId);
		totalNumberOfAcceptedPatronages = this.repository.totalNumberOfAcceptedPatronages(epicureId);
		totalNumberOfDeniedPatronages = this.repository.totalNumberOfDeniedPatronages(epicureId);
		
//		Average patronage budget
		averageBudgetOfProposedPatronages = this.repository.averageBudgetOfProposedPatronages(epicureId);
		averageBudgetOfAcceptedPatronages = this.repository.averageBudgetOfAcceptedPatronages(epicureId);
		averageBudgetOfDeniedPatronages = this.repository.averageBudgetOfDeniedPatronages(epicureId);
		
		
//		Deviation budget
		deviationBudgetOfProposedPatronages = this.repository.deviationBudgetOfProposedPatronages(epicureId);
		deviationBudgetOfAcceptedPatronages = this.repository.deviationBudgetOfAcceptedPatronages(epicureId);
		deviationBudgetOfDeniedPatronages = this.repository.deviationBudgetOfDeniedPatronages(epicureId);
		
		
//		Minimum patronages budget
		minimumBudgetOfProposedPatronages = this.repository.minimumBudgetOfProposedPatronages(epicureId);
		minimumBudgetOfAcceptedPatronages = this.repository.minimumBudgetOfAcceptedPatronages(epicureId);
		minimumBudgetOfDeniedPatronages = this.repository.minimumBudgetOfDeniedPatronages(epicureId);
		
		
//		Maximum patronages budget
		maximumBudgetOfProposedPatronages = this.repository.maximumBudgetOfProposedPatronages(epicureId);
		maximumBudgetOfAcceptedPatronages = this.repository.maximumBudgetOfAcceptedPatronages(epicureId);
		maximumBudgetOfDeniedPatronages = this.repository.maximumBudgetOfDeniedPatronages(epicureId);
		
		result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
		result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
		result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);
	
		result.setAverageBudgetOfProposedPatronages(averageBudgetOfProposedPatronages);
		result.setAverageBudgetOfAcceptedPatronages(averageBudgetOfAcceptedPatronages);
		result.setAverageBudgetOfDeniedPatronages(averageBudgetOfDeniedPatronages);
		
		result.setDeviationBudgetOfProposedPatronages(deviationBudgetOfProposedPatronages);
		result.setDeviationBudgetOfDeniedPatronages(deviationBudgetOfDeniedPatronages);
		result.setDeviationBudgetOfAcceptedPatronages(deviationBudgetOfAcceptedPatronages);
		
		result.setMinimumBudgetOfProposedPatronages(minimumBudgetOfProposedPatronages);
		result.setMinimumBudgetOfAcceptedPatronages(minimumBudgetOfAcceptedPatronages);
		result.setMinimumBudgetOfDeniedPatronages(minimumBudgetOfDeniedPatronages);
		
		result.setMaximumBudgetOfProposedPatronages(maximumBudgetOfProposedPatronages);
		result.setMaximumBudgetOfAcceptedPatronages(maximumBudgetOfAcceptedPatronages);
		result.setMaximumBudgetOfDeniedPatronages(maximumBudgetOfDeniedPatronages);
		
		return result;
	}

}
