package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, Dashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;
	
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
			"totalNumberOfComponents","totalNumberOfTools","totalNumberOfProposedPatronages","totalNumberOfAcceptedPatronages","totalNumberOfDeniedPatronages",
			"averageRetailPriceOfComponentGroupedByTechnology","averageRetailPriceOfComponentGroupedByCurrency","averageRetailPriceOfToolGroupedByCurrency","averageBudgetOfProposedPatronages","averageBudgetOfAcceptedPatronages","averageBudgetOfDeniedPatronages",
			"deviationRetailPriceOfComponentGroupedByTechnology","deviationRetailPriceOfComponentGroupedByCurrency","deviationRetailPriceOfToolGroupedByCurrency","deviationBudgetOfProposedPatronages","deviationBudgetOfAcceptedPatronages","deviationBudgetOfDeniedPatronages",
			"minimumRetailPriceOfComponentGroupedByTechnology","minimumRetailPriceOfComponentGroupedByCurrency","minimumRetailPriceOfToolGroupedByCurrency","minimumBudgetOfProposedPatronages","minimumBudgetOfAcceptedPatronages","minimumBudgetOfDeniedPatronages",
			"maximumRetailPriceOfComponentGroupedByTechnology","maximumRetailPriceOfComponentGroupedByTechnology","maximumRetailPriceOfToolGroupedByCurrency","maximumBudgetOfProposedPatronages","maximumBudgetOfAcceptedPatronages","maximumBudgetOfDeniedPatronages");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;
		
		final Dashboard result = new Dashboard();
		
//		total number 
		final Integer totalNumberOfComponents;
		final Integer totalNumberOfTools;
		final Integer totalNumberOfProposedPatronages;
		final Integer totalNumberOfAcceptedPatronages;
		final Integer totalNumberOfDeniedPatronages;
		
//		average
		
		final Money averageRetailPriceOfComponentGroupedByTechnology;
		final Money averageRetailPriceOfComponentGroupedByCurrency;
		final Money averageRetailPriceOfToolGroupedByCurrency;
		final Double averageBudgetOfProposedPatronages;
		final Double averageBudgetOfAcceptedPatronages;
		final Double averageBudgetOfDeniedPatronages;
		
//		deviation
		final Money deviationRetailPriceOfComponentGroupedByTechnology;
		final Money deviationRetailPriceOfComponentGroupedByCurrency;
		final Money deviationRetailPriceOfToolGroupedByCurrency;
		final Long deviationBudgetOfProposedPatronages;
		final Long deviationBudgetOfAcceptedPatronages;
		final Long deviationBudgetOfDeniedPatronages;
		

//		minimum, and maximum 
		final Money minimumRetailPriceOfComponentGroupedByTechnology;
		final Money minimumRetailPriceOfComponentGroupedByCurrency;
		final Money minimumRetailPriceOfToolGroupedByCurrency;
		final Double minimumBudgetOfProposedPatronages;
		final Double minimumBudgetOfAcceptedPatronages;
		final Double minimumBudgetOfDeniedPatronages;
		
		final Money maximumRetailPriceOfComponentGroupedByTechnology;
		final Money maximumRetailPriceOfComponentGroupedByCurrency;
		final Money maximumRetailPriceOfToolGroupedByCurrency;
		final Double maximumBudgetOfProposedPatronages;
		final Double maximumBudgetOfAcceptedPatronages;
		final Double maximumBudgetOfDeniedPatronages;
		
//		Number
		
		totalNumberOfComponents=this.repository.totalNumberOfComponents();
		totalNumberOfTools=this.repository.totalNumberOfTools();
		totalNumberOfProposedPatronages = this.repository.totalNumberOfProposedPatronages();
		totalNumberOfAcceptedPatronages = this.repository.totalNumberOfAcceptedPatronages();
		totalNumberOfDeniedPatronages = this.repository.totalNumberOfDeniedPatronages();
		
//		Average 
		
//		averageRetailPriceOfComponentGroupedByTechnology=this.repository.averageRetailPriceOfComponentGroupedByTechnology();
//		averageRetailPriceOfComponentGroupedByCurrency=this.repository.averageRetailPriceOfComponentGroupedByCurrency();
//		averageRetailPriceOfToolGroupedByCurrency=this.repository.averageRetailPriceOfToolGroupedByCurrency();
		averageBudgetOfProposedPatronages = this.repository.averageBudgetOfProposedPatronages();
		averageBudgetOfAcceptedPatronages = this.repository.averageBudgetOfAcceptedPatronages();
		averageBudgetOfDeniedPatronages = this.repository.averageBudgetOfDeniedPatronages();
		
		
//		Deviation
		
//		deviationRetailPriceOfComponentGroupedByTechnology=this.repository.deviationRetailPriceOfComponentGroupedByTechnology();
//		deviationRetailPriceOfComponentGroupedByCurrency=this.repository.deviationRetailPriceOfComponentGroupedByCurrency();
//		deviationRetailPriceOfToolGroupedByCurrency=this.repository.deviationRetailPriceOfToolGroupedByCurrency();
		deviationBudgetOfProposedPatronages = this.repository.deviationBudgetOfProposedPatronages();
		deviationBudgetOfAcceptedPatronages = this.repository.deviationBudgetOfAcceptedPatronages();
		deviationBudgetOfDeniedPatronages = this.repository.deviationBudgetOfDeniedPatronages();
		
		
//		Minimum 
		
		
//		minimumRetailPriceOfComponentGroupedByTechnology=this.repository.minimumRetailPriceOfComponentGroupedByTechnology();
//		minimumRetailPriceOfComponentGroupedByCurrency=this.repository.minimumRetailPriceOfComponentGroupedByCurrency();
//	    minimumRetailPriceOfToolGroupedByCurrency=this.repository.minimumRetailPriceOfToolGroupedByCurrency();
		minimumBudgetOfProposedPatronages = this.repository.minimumBudgetOfProposedPatronages();
		minimumBudgetOfAcceptedPatronages = this.repository.minimumBudgetOfAcceptedPatronages();
		minimumBudgetOfDeniedPatronages = this.repository.minimumBudgetOfDeniedPatronages();
		
		
//		Maximum 
		
		
//		maximumRetailPriceOfComponentGroupedByTechnology=this.repository.maximumRetailPriceOfComponentGroupedByTechnology();
//	    maximumRetailPriceOfComponentGroupedByCurrency=this.repository.maximumRetailPriceOfComponentGroupedByCurrency();
//		maximumRetailPriceOfToolGroupedByCurrency=this.repository.maximumRetailPriceOfToolGroupedByCurrency();
		maximumBudgetOfProposedPatronages = this.repository.maximumBudgetOfProposedPatronages();
		maximumBudgetOfAcceptedPatronages = this.repository.maximumBudgetOfAcceptedPatronages();
		maximumBudgetOfDeniedPatronages = this.repository.maximumBudgetOfDeniedPatronages();
		
		
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setTotalNumberOfTools(totalNumberOfTools);
		result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
		result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
		result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);
	
//		result.setAverageRetailPriceOfComponentGroupedByTechnology(averageRetailPriceOfComponentGroupedByTechnology);
//		result.setAverageRetailPriceOfComponentGroupedByCurrency(averageRetailPriceOfComponentGroupedByCurrency);
//		result.setAverageRetailPriceOfToolGroupedByCurrency(averageRetailPriceOfToolGroupedByCurrency);
		result.setAverageBudgetOfProposedPatronages(averageBudgetOfProposedPatronages);
		result.setAverageBudgetOfAcceptedPatronages(averageBudgetOfAcceptedPatronages);
		result.setAverageBudgetOfDeniedPatronages(averageBudgetOfDeniedPatronages);
		
		
//		result.setDeviationRetailPriceOfComponentGroupedByTechnology(deviationRetailPriceOfComponentGroupedByTechnology);
//		result.setDeviationRetailPriceOfComponentGroupedByCurrency(deviationRetailPriceOfComponentGroupedByCurrency);
//		result.setDeviationRetailPriceOfToolGroupedByCurrency(deviationRetailPriceOfToolGroupedByCurrency);
		result.setDeviationBudgetOfProposedPatronages(deviationBudgetOfProposedPatronages);
		result.setDeviationBudgetOfDeniedPatronages(deviationBudgetOfDeniedPatronages);
		result.setDeviationBudgetOfAcceptedPatronages(deviationBudgetOfAcceptedPatronages);
		
//		result.setMinimumRetailPriceOfComponentGroupedByTechnology(minimumRetailPriceOfComponentGroupedByTechnology);
//		result.setMinimumRetailPriceOfComponentGroupedByCurrency(minimumRetailPriceOfComponentGroupedByCurrency);
//		result.setMinimumRetailPriceOfToolGroupedByCurrency(minimumRetailPriceOfToolGroupedByCurrency);
		result.setMinimumBudgetOfProposedPatronages(minimumBudgetOfProposedPatronages);
		result.setMinimumBudgetOfAcceptedPatronages(minimumBudgetOfAcceptedPatronages);
		result.setMinimumBudgetOfDeniedPatronages(minimumBudgetOfDeniedPatronages);
		
//		result.setMaximumRetailPriceOfComponentGroupedByTechnology(maximumRetailPriceOfComponentGroupedByTechnology);
//		result.setMaximumRetailPriceOfComponentGroupedByCurrency(maximumRetailPriceOfComponentGroupedByCurrency);
//		result.setMaximumRetailPriceOfToolGroupedByCurrency(maximumRetailPriceOfToolGroupedByCurrency);
		result.setMaximumBudgetOfProposedPatronages(maximumBudgetOfProposedPatronages);
		result.setMaximumBudgetOfAcceptedPatronages(maximumBudgetOfAcceptedPatronages);
		result.setMaximumBudgetOfDeniedPatronages(maximumBudgetOfDeniedPatronages);
		
		return result;
	}

}
