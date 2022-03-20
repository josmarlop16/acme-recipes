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
//	@Query("select p.status, count(p) from Patronage p group by p.status")
//	Map<PatronageStatus,Integer> totalNumberOfPatronagesGroupedByPatronageStatus();
// 	@Query("select count(p) from Patronages p group by p.status")
// 	int totalNumberOfPatronages();

//	Average 

	@Query("select c.technology,c.retailPrice.currency, avg(c.retailPrice.amount) from Item c  where c.type=1 group by c.technology,c.retailPrice.currency")
	List<Object[]> averageRetailPriceOfComponentGroupedByTechnologyAndCurrency();
//	@Query("select c.technology,c.retailPrice.currency, avg(c.retailPrice.amount) from Item c  where c.type=1 group by c.technology,c.retailPrice.currency")
//	Map<Pair<String,String>,Double> averageRetailPriceOfComponentGroupedByTechnologyAndCurrency();
//  @Query("select avg(c.retailPrice.amount) from Item c where c.type=1 group by c.technology, c.retailPrice.currency")
//	Double averageRetailPriceOfComponentGroupedByTechnologyAndCurrency();
	
	
	@Query("select t.retailPrice.currency, avg(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
	List<Object[]> averageRetailPriceOfToolGroupedByCurrency();
//	@Query("select t.retailPrice.currency, avg(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
//	Map<String,Double> averageRetailPriceOfToolGroupedByCurrency();
//  @Query("select avg(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
//	Double averageRetailPriceOfComponentGroupedByTechnologyAndCurrency();
	
	@Query("select p.status ,avg(p.budget) from Patronage p group by p.status")
	List<Object[]> averageBudgetOfPatronagesGroupedByPatronageStatus();
//	@Query("select p.status ,avg(p.budget) from Patronage p group by p.status")
//	Map<PatronageStatus,Double> averageBudgetOfPatronagesGroupedByPatronageStatus();
//  @Query("select avg(p.budget) from Patronage p group by p.status")
//	Double averageBudgetOfPatronagesGroupedByPatronageStatus();
	
//	Deviation
	
	@Query("select c.technology,c.retailPrice.currency, stddev(c.retailPrice.amount) from Item c where c.type=1 group by c.technology,c.retailPrice.currency")
	List<Object[]> deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency();
//	@Query("select c.technology,c.retailPrice.currency, stddev(c.retailPrice.amount) from Item c where c.type=1 group by c.technology,c.retailPrice.currency")
//	Map<Pair<String,String>,Double> deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency();
//  @Query("select stddev(c.retailPrice.amount) from Item c where c.type=1 group by c.technology, c.retailPrice.currency")
//	Double deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency();
	
	@Query("select t.retailPrice.currency, stddev(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
	List<Object[]> deviationRetailPriceOfToolGroupedByCurrency();
//	@Query("select t.retailPrice.currency, stddev(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
//	Map<String,Double> deviationRetailPriceOfToolGroupedByCurrency();
//  @Query("select stddev(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
//	Double deviationRetailPriceOfComponentGroupedByTechnologyAndCurrency();
	
	@Query("select p.status ,stddev(p.budget) from Patronage p group by p.status")
	List<Object[]> deviationBudgetOfPatronagesGroupedByPatronageStatus();
//	@Query("select p.status ,stddev(p.budget) from Patronage p group by p.status")
//	Map<PatronageStatus,Double> deviationBudgetOfPatronagesGroupedByPatronageStatus();
//  @Query("select stddev(p.budget) from Patronage p group by p.status")
//	Double deviationBudgetOfPatronagesGroupedByPatronageStatus();
	
//	Minimum 
	
	@Query("select c.technology,c.retailPrice.currency, min(c.retailPrice.amount) from Item c where c.type=1 group by c.technology,c.retailPrice.currency")
	List<Object[]> minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
//	@Query("select c.technology,c.retailPrice.currency, min(c.retailPrice.amount) from Item c where c.type=1 group by c.technology,c.retailPrice.currency")
//	Map<Pair<String,String>,Double> minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
//  @Query("select min(c.retailPrice.amount) from Item c where c.type=1 group by c.technology, c.retailPrice.currency")
//	Double minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
	
	@Query("select t.retailPrice.currency, min(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
	List<Object[]> minimumRetailPriceOfToolGroupedByCurrency();
//	@Query("select t.retailPrice.currency, min(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
//	Map<String,Double> minimumRetailPriceOfToolGroupedByCurrency();
//  @Query("select min(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
//	Double minimumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
	
	@Query("select p.status ,min(p.budget) from Patronage p group by p.status")
	List<Object[]> minimumBudgetOfPatronagesGroupedByPatronageStatus();
//	@Query("select p.status ,min(p.budget) from Patronage p group by p.status")
//	Map<PatronageStatus,Double> minimumBudgetOfPatronagesGroupedByPatronageStatus();
//  @Query("select min(p.budget) from Patronage p group by p.status")
//	Double minimumBudgetOfPatronagesGroupedByPatronageStatus();
	
	
	
//	Maximum 
	
	@Query("select c.technology,c.retailPrice.currency, max(c.retailPrice.amount) from Item c where c.type=1 group by c.technology,c.retailPrice.currency")
	List<Object[]> maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
//	@Query("select c.technology,c.retailPrice.currency, max(c.retailPrice.amount) from Item c where c.type=1 group by c.technology,c.retailPrice.currency")
//	Map<Pair<String,String>,Double> maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
//  @Query("select max(c.retailPrice.amount) from Item c where c.type=1 group by c.technology, c.retailPrice.currency")
//	Double maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
	
	
	@Query("select t.retailPrice.currency, max(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
	List<Object[]> maximumRetailPriceOfToolGroupedByCurrency();
//	@Query("select t.retailPrice.currency, max(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
//	Map<String,Double> maximumRetailPriceOfToolGroupedByCurrency();
//  @Query("select max(t.retailPrice.amount) from Item t where t.type=0 group by t.retailPrice.currency")
//	Double maximumRetailPriceOfComponentGroupedByTechnologyAndCurrency();
	
	
	@Query("select p.status ,max(p.budget) from Patronage p group by p.status")
	List<Object[]> maximumBudgetOfPatronagesGroupedByPatronageStatus();
	
//	@Query("select p.status ,max(p.budget) from Patronage p group by p.status")
//	Map<PatronageStatus,Double> maximumBudgetOfPatronagesGroupedByPatronageStatus();
//  @Query("select max(p.budget) from Patronage p group by p.status")
//	Double maximumBudgetOfPatronagesGroupedByPatronageStatus();
}