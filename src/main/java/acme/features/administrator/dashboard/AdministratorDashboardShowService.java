package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	//	final Map<PatronageStatus,Integer> totalNumberOfPatronagesGroupedByPatronageStatus;
		final List<Object[]> totalNumberOfPatronagesGroupedByPatronageStatus;
//		average

		final List<Object[]> averageRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	//	final Map<Pair<String,String>,Double> averageRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final List<Object[]>  averageRetailPriceOfToolGroupedByCurrency;
	//	final Map<String,Double> averageRetailPriceOfToolGroupedByCurrency;
		final List<Object[]> averageBudgetOfPatronagesGroupedByPatronageStatus;
	//	final Map<PatronageStatus,Double> averageBudgetOfPatronagesGroupedByPatronageStatus;
		
//		deviation

		final List<Object[]> deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	//	final Map<Pair<String,String>,Double> deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final List<Object[]> deviationRetailPriceOfToolGroupedByCurrency;
	//	final Map<String,Double> deviationRetailPriceOfToolGroupedByCurrency;
		final List<Object[]> deviationBudgetOfPatronagesGroupedByPatronageStatus;
	//	final Map<PatronageStatus,Double> deviationBudgetOfPatronagesGroupedByPatronageStatus;
		
//		minimum, and maximum 
		final List<Object[]> minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	//	final Map<Pair<String,String>,Double> minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final List<Object[]> minimumRetailPriceOfToolGroupedByCurrency;
	//	final Map<String,Double> minimumRetailPriceOfToolGroupedByCurrency;
		final List<Object[]> minimumBudgetOfPatronagesGroupedByPatronageStatus;
	//	final Map<PatronageStatus,Double> minimumBudgetOfPatronagesGroupedByPatronageStatus;
		
		final List<Object[]> maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
	//	final Map<Pair<String,String>,Double> maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency;
		final List<Object[]> maximumRetailPriceOfToolGroupedByCurrency;
	//	final Map<String,Double> maximumRetailPriceOfToolGroupedByCurrency;
		final List<Object[]>  maximumBudgetOfPatronagesGroupedByPatronageStatus;
	//	final Map<PatronageStatus,Double> maximumBudgetOfPatronagesGroupedByPatronageStatus;
		
		
//		Number
		totalNumberOfComponents=this.repository.totalNumberOfComponents();
		totalNumberOfTools=this.repository.totalNumberOfTools();
		totalNumberOfPatronages = this.repository.totalNumberOfPatronages();
	//	totalNumberOfPatronagesGroupedByPatronageStatus=this.repository.totalNumberOfPatronagesGroupedByPatronageStatus().stream()
	//		.collect(Collectors.toMap(x-> (PatronageStatus)x[0],x->((Long)x[1]).intValue()));
		totalNumberOfPatronagesGroupedByPatronageStatus=this.repository.totalNumberOfPatronagesGroupedByPatronageStatus();
		
//		Average 

	//	averageRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.averageRetailPriceOfComponentGroupedByTechnologyAndCurrency().stream()
	//		.collect(Collectors.toMap(x->Pair.of((String)x[0], (String)x[1]), x->(Double) x[2]));
		
		averageRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.averageRetailPriceOfComponentGroupedByTechnologyAndCurrency();
		
	
	//  averageRetailPriceOfToolGroupedByCurrency=this.repository.averageRetailPriceOfToolGroupedByCurrency().stream()
	//		.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		averageRetailPriceOfToolGroupedByCurrency=this.repository.averageRetailPriceOfToolGroupedByCurrency();
		
	//	averageBudgetOfPatronagesGroupedByPatronageStatus = this.repository.averageBudgetOfPatronagesGroupedByPatronageStatus().stream()
	//		.collect(Collectors.toMap(x->(PatronageStatus)x[0], x->(Double) x[1]));
		averageBudgetOfPatronagesGroupedByPatronageStatus = this.repository.averageBudgetOfPatronagesGroupedByPatronageStatus();
		
//		Deviation
		
	//	deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency().stream()
	//		.collect(Collectors.toMap(x->Pair.of((String)x[0], (String)x[1]), x->(Double) x[2]));
		deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency();
		
	//	deviationRetailPriceOfToolGroupedByCurrency=this.repository.deviationRetailPriceOfToolGroupedByCurrency().stream()
	//		.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		deviationRetailPriceOfToolGroupedByCurrency=this.repository.deviationRetailPriceOfToolGroupedByCurrency();
		
	//	deviationBudgetOfPatronagesGroupedByPatronageStatus = this.repository.deviationBudgetOfPatronagesGroupedByPatronageStatus().stream()
	//		.collect(Collectors.toMap(x->(PatronageStatus)x[0], x->(Double) x[1]));
		deviationBudgetOfPatronagesGroupedByPatronageStatus = this.repository.deviationBudgetOfPatronagesGroupedByPatronageStatus();
		
//		Minimum 
		
	//	minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency().stream()
	//		.collect(Collectors.toMap(x->Pair.of((String)x[0], (String)x[1]), x->(Double) x[2]));
		minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
	    
	//	minimumRetailPriceOfToolGroupedByCurrency=this.repository.minimumRetailPriceOfToolGroupedByCurrency().stream()
	//		.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		minimumRetailPriceOfToolGroupedByCurrency=this.repository.minimumRetailPriceOfToolGroupedByCurrency();
		
	//   minimumBudgetOfPatronagesGroupedByPatronageStatus = this.repository.minimumBudgetOfPatronagesGroupedByPatronageStatus().stream()
	//		.collect(Collectors.toMap(x->(PatronageStatus)x[0], x->Double.valueOf(x[1].toString())));
		minimumBudgetOfPatronagesGroupedByPatronageStatus = this.repository.minimumBudgetOfPatronagesGroupedByPatronageStatus();
		
//		Maximum 
			
	 //   maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency()
	 //   	.stream()
	//		.collect(Collectors.toMap(x->Pair.of((String)x[0], (String)x[1]), x->(Double) x[2]));
			 maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency=this.repository.maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
		
	//		maximumRetailPriceOfToolGroupedByCurrency=this.repository.maximumRetailPriceOfToolGroupedByCurrency().stream()
	//		.collect(Collectors.toMap(x->(String)x[0], x->(Double) x[1]));
		maximumRetailPriceOfToolGroupedByCurrency=this.repository.maximumRetailPriceOfToolGroupedByCurrency();
		
	//	maximumBudgetOfPatronagesGroupedByPatronageStatus = this.repository.maximumBudgetOfPatronagesGroupedByPatronageStatus().stream()
	//		.collect(Collectors.toMap(x->(PatronageStatus)x[0], x->Double.valueOf(x[1].toString())));
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
