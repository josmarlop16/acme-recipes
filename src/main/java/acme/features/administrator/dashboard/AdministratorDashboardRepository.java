package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
//	Number of components,tools and patronages by their status
	@Query("select count(i) from Item i where i.type=1")
	int totalNumberOfComponents();
	
	@Query("select count(i) from Item i where i.type=0")
	int totalNumberOfTools();
	
	@Query("select count(p) from Patronage p")
	int totalNumberOfPatronages();
	
	@Query("select p.status, count(p) from Patronage p group by p.status")
	List<Object[]> totalNumberOfPatronagesGroupedByPatronageStatus();


//	Average 

	@Query("select c.technology,c.retailPrice.currency, avg(c.retailPrice.amount) from Item c  where c.type=1 group by c.technology,c.retailPrice.currency")
	List<Object[]> averageRetailPriceOfComponentGroupedByTechnologyAndCurrency();

	
	
	@Query("select t.retailPrice.currency, avg(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
	List<Object[]> averageRetailPriceOfToolGroupedByCurrency();

	
	@Query("select p.status ,avg(p.budget.amount) from Patronage p group by p.status")
	List<Object[]> averageBudgetOfPatronagesGroupedByPatronageStatus();

	
//	Deviation
	
	@Query("select c.technology,c.retailPrice.currency, stddev(c.retailPrice.amount) from Item c where c.type=1 group by c.technology,c.retailPrice.currency")
	List<Object[]> deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency();

	
	@Query("select t.retailPrice.currency, stddev(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
	List<Object[]> deviationRetailPriceOfToolGroupedByCurrency();

	
	@Query("select p.status ,stddev(p.budget.amount) from Patronage p group by p.status")
	List<Object[]> deviationBudgetOfPatronagesGroupedByPatronageStatus();

	
//	Minimum 
	
	@Query("select c.technology,c.retailPrice.currency, min(c.retailPrice.amount) from Item c where c.type=1 group by c.technology,c.retailPrice.currency")
	List<Object[]> minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency();

	
	@Query("select t.retailPrice.currency, min(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
	List<Object[]> minimumRetailPriceOfToolGroupedByCurrency();

	
	@Query("select p.status ,min(p.budget.amount) from Patronage p group by p.status")
	List<Object[]> minimumBudgetOfPatronagesGroupedByPatronageStatus();

	
	
	
//	Maximum 
	
	@Query("select c.technology,c.retailPrice.currency, max(c.retailPrice.amount) from Item c where c.type=1 group by c.technology,c.retailPrice.currency")
	List<Object[]> maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency();

	
	
	@Query("select t.retailPrice.currency, max(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
	List<Object[]> maximumRetailPriceOfToolGroupedByCurrency();

	
	
	@Query("select p.status ,max(p.budget.amount) from Patronage p group by p.status")
	List<Object[]> maximumBudgetOfPatronagesGroupedByPatronageStatus();
	

}
