package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.datatypes.Money;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {
//	Number of components,tools and patronages by their status
	@Query("select count(c) from Component c")
	Integer totalNumberOfComponents();
	
	@Query("select count(t) from Tool t")
	Integer totalNumberOfTools();
	
	@Query("select count(p) from Patronage p where p.status = 0")
	Integer totalNumberOfProposedPatronages();
	
	@Query("select count(p) from Patronage p where p.status = 1")
	Integer totalNumberOfAcceptedPatronages();

	@Query("select count(p) from Patronage p where p.status = 2")
	Integer totalNumberOfDeniedPatronages();

//	Average 
	@Query("select c.technology, avg(c.retailPrice.amount) from Component c group by c.technology")
	Money averageRetailPriceOfComponentGroupedByTechnology();
	
	@Query("select c.retailPrice.currency, avg(c.retailPrice.amount) from Component c group by c.retailPrice.currency")
	Money averageRetailPriceOfComponentGroupedByCurrency();
	
	@Query("select t.retailPrice.currency, avg(t.retailPrice.amount) from Tool t group by t.retailPrice.currency")
	Money averageRetailPriceOfToolGroupedByCurrency();
	
	@Query("select avg(p.budget) from Patronage p where p.status = 0")
	Double averageBudgetOfProposedPatronages();
	
	@Query("select avg(p.budget) from Patronage p where p.status = 1")
	Double averageBudgetOfAcceptedPatronages();
	
	@Query("select avg(p.budget) from Patronage p where p.status = 2")
	Double averageBudgetOfDeniedPatronages();
	
	
//	Deviation
	@Query("select c.technology, stddev(c.retailPrice.amount) from Component c group by c.technology")
	Money deviationRetailPriceOfComponentGroupedByTechnology();
	
	@Query("select c.retailPrice.currency, stddev(c.retailPrice.amount) from Component c group by c.retailPrice.currency")
	Money deviationRetailPriceOfComponentGroupedByCurrency();
	
	@Query("select t.retailPrice.currency, stddev(t.retailPrice.amount) from Tool t group by t.retailPrice.currency")
	Money deviationRetailPriceOfToolGroupedByCurrency();
	
	@Query("select stddev(p.budget) from Patronage p where p.status = 0")
	Long deviationBudgetOfProposedPatronages();
	
	@Query("select stddev(p.budget) from Patronage p where p.status = 1")
	Long deviationBudgetOfAcceptedPatronages();
	
	@Query("select stddev(p.budget) from Patronage p where p.status = 2")
	Long deviationBudgetOfDeniedPatronages();
	
	
//	Minimum 
	
	@Query("select c.technology, min(c.retailPrice.amount) from Component c group by c.technology")
	Money minimumRetailPriceOfComponentGroupedByTechnology();
	
	@Query("select c.retailPrice.currency, min(c.retailPrice.amount) from Component c group by c.retailPrice.currency")
	Money minimumRetailPriceOfComponentGroupedByCurrency();
	
	@Query("select t.retailPrice.currency, min(t.retailPrice.amount) from Tool t group by t.retailPrice.currency")
	Money minimumRetailPriceOfToolGroupedByCurrency();
	
	@Query("select min(p.budget) from Patronage p where p.status = 0")
	Double minimumBudgetOfProposedPatronages();

	@Query("select min(p.budget) from Patronage p where p.status = 1")
	Double minimumBudgetOfAcceptedPatronages();

	@Query("select min(p.budget) from Patronage p where p.status = 2")
	Double minimumBudgetOfDeniedPatronages();

//	Maximum 
	@Query("select c.technology, max(c.retailPrice.amount) from Component c group by c.technology")
	Money maximumRetailPriceOfComponentGroupedByTechnology();
	
	@Query("select c.retailPrice.currency, max(c.retailPrice.amount) from Component c group by c.retailPrice.currency")
	Money maximumRetailPriceOfComponentGroupedByCurrency();
	
	@Query("select t.retailPrice.currency, max(t.retailPrice.amount) from Tool t group by t.retailPrice.currency")
	Money maximumRetailPriceOfToolGroupedByCurrency();
	
	@Query("select max(p.budget) from Patronage p where p.status = 0")
	Double maximumBudgetOfProposedPatronages();

	@Query("select max(p.budget) from Patronage p where p.status = 1")
	Double maximumBudgetOfAcceptedPatronages();

	@Query("select max(p.budget) from Patronage p where p.status = 2")
	Double maximumBudgetOfDeniedPatronages();
 
}
