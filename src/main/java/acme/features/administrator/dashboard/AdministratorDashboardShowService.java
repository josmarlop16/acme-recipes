package acme.features.administrator.dashboard;

import java.util.Map;
import java.util.stream.Collectors;

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
//		final List<Object[]> totalNumberOfPatronagesGroupedByPatronageStatus;
		final Map<PatronageStatus,Integer> totalNumberOfPatronagesGroupedByPatronageStatus;
		
//		average
/*
 * final List<Object[]> averageRetailPriceOfComponentGroupedByTechnologyAndCurrency;
 * final List<Object[]> averageRetailPriceOfToolGroupedByCurrency;
 * final List<Object[]> averageBudgetOfPatronagesGroupedByPatronageStatus;
 */
		final Map<Pair<String,String>,Double> averageRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final Map<String,Double> averageRetailPriceOfToolGroupedByCurrency;
		final Map<PatronageStatus,Double> averageBudgetOfPatronagesGroupedByPatronageStatus;
		
//		deviation
/*
 * final List<Object[]> deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency;
 * final List<Object[]> deviationRetailPriceOfToolGroupedByCurrency;
 * final List<Object[]> deviationBudgetOfPatronagesGroupedByPatronageStatus;
 */
		final Map<Pair<String,String>,Double> deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final Map<String,Double> deviationRetailPriceOfToolGroupedByCurrency;
		final Map<PatronageStatus,Double> deviationBudgetOfPatronagesGroupedByPatronageStatus;
		
//		minimum, and maximum 
/*
 * final List<Object[]> minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
 * final List<Object[]> minimumRetailPriceOfToolGroupedByCurrency;
 * final List<Object[]> minimumBudgetOfPatronagesGroupedByPatronageStatus;
 */
		final Map<Pair<String,String>,Double> minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final Map<String,Double> minimumRetailPriceOfToolGroupedByCurrency;
		final Map<PatronageStatus,Double> minimumBudgetOfPatronagesGroupedByPatronageStatus;
		
		/*
		 * final List<Object[]> maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		 * final List<Object[]> maximumRetailPriceOfToolGroupedByCurrency;
		 * final List<Object[]> maximumBudgetOfPatronagesGroupedByPatronageStatus;
		 */
		final Map<Pair<String,String>,Double> maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final Map<String,Double> maximumRetailPriceOfToolGroupedByCurrency;
		final Map<PatronageStatus,Double> maximumBudgetOfPatronagesGroupedByPatronageStatus;
		
//		Number
		totalNumberOfComponents=this.repository.totalNumberOfComponents();
		totalNumberOfTools=this.repository.totalNumberOfTools();
		totalNumberOfPatronages = this.repository.totalNumberOfPatronages();
		totalNumberOfPatronagesGroupedByPatronageStatus=this.repository.totalNumberOfPatronagesGroupedByPatronageStatus().stream()
			.collect(Collectors.toMap(x-> (PatronageStatus)x[0],x->((Long)x[1]).intValue()));
		
//		Average 

		averageRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.averageRetailPriceOfComponentGroupedByTechnologyAndCurrency().stream()
			.collect(Collectors.toMap(x->Pair.of((String)x[0], (String)x[1]), x->(Double) x[2]));
		averageRetailPriceOfToolGroupedByCurrency=this.repository.averageRetailPriceOfToolGroupedByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		averageBudgetOfPatronagesGroupedByPatronageStatus = this.repository.averageBudgetOfPatronagesGroupedByPatronageStatus().stream()
			.collect(Collectors.toMap(x->(PatronageStatus)x[0], x->(Double) x[1]));
		
//		Deviation
		deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency().stream()
			.collect(Collectors.toMap(x->Pair.of((String)x[0], (String)x[1]), x->(Double) x[2]));
		deviationRetailPriceOfToolGroupedByCurrency=this.repository.deviationRetailPriceOfToolGroupedByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		deviationBudgetOfPatronagesGroupedByPatronageStatus = this.repository.deviationBudgetOfPatronagesGroupedByPatronageStatus().stream()
			.collect(Collectors.toMap(x->(PatronageStatus)x[0], x->(Double) x[1]));
		
//		Minimum 
		minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency().stream()
			.collect(Collectors.toMap(x->Pair.of((String)x[0], (String)x[1]), x->(Double) x[2]));
	    minimumRetailPriceOfToolGroupedByCurrency=this.repository.minimumRetailPriceOfToolGroupedByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
	    minimumBudgetOfPatronagesGroupedByPatronageStatus = this.repository.minimumBudgetOfPatronagesGroupedByPatronageStatus().stream()
			.collect(Collectors.toMap(x->(PatronageStatus)x[0], x->Double.valueOf(x[1].toString())));
		
//		Maximum 
	    maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency()
	    	.stream()
			.collect(Collectors.toMap(x->Pair.of((String)x[0], (String)x[1]), x->(Double) x[2]));
		maximumRetailPriceOfToolGroupedByCurrency=this.repository.maximumRetailPriceOfToolGroupedByCurrency().stream()
			.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		maximumBudgetOfPatronagesGroupedByPatronageStatus = this.repository.maximumBudgetOfPatronagesGroupedByPatronageStatus().stream()
			.collect(Collectors.toMap(x->(PatronageStatus)x[0], x->Double.valueOf(x[1].toString())));
		
		
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
