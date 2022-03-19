package acme.features.administrator.dashboard;

import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageStatus;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	@Autowired
	protected AdministratorDashboardRepository repository;
	
	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		
		request.unbind(entity, model, 
			"totalNumberOfComponents","totalNumberOfTools","totalNumberOfPatronages","totalNumberOfPatronagesGroupedByPatronageStatus"
			,"averageRetailPriceOfComponentGroupedByTechnologyAndCurrency","averageRetailPriceOfToolGroupedByCurrency","averageBudgetOfPatronagesGroupedByPatronageStatus"
			,"deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency","deviationRetailPriceOfToolGroupedByCurrency","deviationBudgetOfPatronagesGroupedByPatronageStatus"
			,"minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency","minimumRetailPriceOfToolGroupedByCurrency","minimumBudgetOfPatronagesGroupedByPatronageStatus"
			,"maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency","maximumRetailPriceOfToolGroupedByCurrency","maximumBudgetOfPatronagesGroupedByPatronageStatus");
	}

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;
		
		final AdministratorDashboard result = new AdministratorDashboard();
		
//		total number 
		final int totalNumberOfComponents;
		final int totalNumberOfTools;
		final int totalNumberOfPatronages;
		final Map<PatronageStatus,Integer> totalNumberOfPatronagesGroupedByPatronageStatus;
		
//		average
		final Map<Pair<String,String>,Double> averageRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final Map<String,Double> averageRetailPriceOfToolGroupedByCurrency;
		final Map<PatronageStatus,Double> averageBudgetOfPatronagesGroupedByPatronageStatus;
		
//		deviation
		final Map<Pair<String,String>,Double> deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final Map<String,Double> deviationRetailPriceOfToolGroupedByCurrency;
		final Map<PatronageStatus,Double> deviationBudgetOfPatronagesGroupedByPatronageStatus;
		
//		minimum, and maximum 
		final Map<Pair<String,String>,Double> minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final Map<String,Double> minimumRetailPriceOfToolGroupedByCurrency;
		final Map<PatronageStatus,Double> minimumBudgetOfPatronagesGroupedByPatronageStatus;
		
		final Map<Pair<String,String>,Double> maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final Map<String,Double> maximumRetailPriceOfToolGroupedByCurrency;
		final Map<PatronageStatus,Double> maximumBudgetOfPatronagesGroupedByPatronageStatus;
		
//		Number
		totalNumberOfComponents=this.repository.totalNumberOfComponents();
		totalNumberOfTools=this.repository.totalNumberOfTools();
		totalNumberOfPatronages = this.repository.totalNumberOfPatronages();
		totalNumberOfPatronagesGroupedByPatronageStatus=this.repository.totalNumberOfPatronagesGroupedByPatronageStatus();
		
//		Average 
		averageRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.averageRetailPriceOfComponentGroupedByTechnologyAndCurrency();
		averageRetailPriceOfToolGroupedByCurrency=this.repository.averageRetailPriceOfToolGroupedByCurrency();
		averageBudgetOfPatronagesGroupedByPatronageStatus = this.repository.averageBudgetOfPatronagesGroupedByPatronageStatus();
		
//		Deviation
		deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency();
		deviationRetailPriceOfToolGroupedByCurrency=this.repository.deviationRetailPriceOfToolGroupedByCurrency();
		deviationBudgetOfPatronagesGroupedByPatronageStatus = this.repository.deviationBudgetOfPatronagesGroupedByPatronageStatus();
		
//		Minimum 
		minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
	    minimumRetailPriceOfToolGroupedByCurrency=this.repository.minimumRetailPriceOfToolGroupedByCurrency();
	    minimumBudgetOfPatronagesGroupedByPatronageStatus = this.repository.minimumBudgetOfPatronagesGroupedByPatronageStatus();
		
//		Maximum 
	    maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
		maximumRetailPriceOfToolGroupedByCurrency=this.repository.maximumRetailPriceOfToolGroupedByCurrency();
		maximumBudgetOfPatronagesGroupedByPatronageStatus = this.repository.maximumBudgetOfPatronagesGroupedByPatronageStatus();
		
		result.setTotalNumberOfComponents(totalNumberOfComponents);
		result.setTotalNumberOfTools(totalNumberOfTools);
		result.setTotalNumberOfPatronages(totalNumberOfPatronages);
		result.setTotalNumberOfPatronagesGroupedByPatronageStatus(totalNumberOfPatronagesGroupedByPatronageStatus);
	
		result.setAverageRetailPriceOfComponentGroupedByTechnologyAndCurrency(averageRetailPriceOfComponentGroupedByTechnologyAndCurrency);
		result.setAverageRetailPriceOfToolGroupedByCurrency(averageRetailPriceOfToolGroupedByCurrency);
		result.setAverageBudgetOfPatronagesGroupedByPatronageStatus(averageBudgetOfPatronagesGroupedByPatronageStatus);
				
		result.setDeviationRetailPriceOfComponentGroupedByTechnologyAndCurrency(deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency);
		result.setDeviationRetailPriceOfToolGroupedByCurrency(deviationRetailPriceOfToolGroupedByCurrency);
		result.setDeviationBudgetOfPatronagesGroupedByPatronageStatus(deviationBudgetOfPatronagesGroupedByPatronageStatus);
		
		result.setMinimumRetailPriceOfComponentGroupedByTechnologyAndCurrency(minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency);
		result.setMinimumRetailPriceOfToolGroupedByCurrency(minimumRetailPriceOfToolGroupedByCurrency);
		result.setMinimumBudgetOfPatronagesGroupedByPatronageStatus(minimumBudgetOfPatronagesGroupedByPatronageStatus);
		
		result.setMaximumRetailPriceOfComponentGroupedByTechnologyAndCurrency(maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency);
		result.setMaximumRetailPriceOfToolGroupedByCurrency(maximumRetailPriceOfToolGroupedByCurrency);
		result.setMaximumBudgetOfPatronagesGroupedByPatronageStatus(maximumBudgetOfPatronagesGroupedByPatronageStatus);
		
		return result;
	}

}
