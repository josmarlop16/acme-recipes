/*
 * AdministratorDashboardRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronDashboardRepository extends AbstractRepository {

//	Number of patronages by statuses
	@Query("select count(p) from Patronage p where p.status = 0 and p.patron.id = :patronId")
	Integer totalNumberOfProposedPatronages(int patronId);

	@Query("select count(p) from Patronage p where p.status = 1 and p.patron.id = :patronId")
	Integer totalNumberOfAcceptedPatronages(int patronId);

	@Query("select count(p) from Patronage p where p.status = 2 and p.patron.id = :patronId")
	Integer totalNumberOfDeniedPatronages(int patronId);

	
	
	
	
//	Average budget from patronages by statuses
	@Query("select avg(p.budget.amount) from Patronage p where p.status = 0 and p.patron.id = :patronId group by p.budget.currency")
	Double averageBudgetOfProposedPatronages(int patronId);
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = 1 and p.patron.id = :patronId group by p.budget.currency")
	Double averageBudgetOfAcceptedPatronages(int patronId);
	
	@Query("select avg(p.budget.amount) from Patronage p where p.status = 2 and p.patron.id = :patronId group by p.budget.currency")
	Double averageBudgetOfDeniedPatronages(int patronId);

	
//	Deviation of budgets by patronage statuses
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = 0 and p.patron.id = :patronId group by p.budget.currency")
	Long deviationBudgetOfProposedPatronages(int patronId);
	
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = 1 and p.patron.id = :patronId group by p.budget.currency")
	Long deviationBudgetOfAcceptedPatronages(int patronId);
	
	@Query("select stddev(p.budget.amount) from Patronage p where p.status = 2 and p.patron.id = :patronId group by p.budget.currency")
	Long deviationBudgetOfDeniedPatronages(int patronId);
	
	
//	Minimum budget of patronages by statuses
	@Query("select min(p.budget.amount) from Patronage p where p.status = 0 and p.patron.id = :patronId group by p.budget.currency")
	Double minimumBudgetOfProposedPatronages(int patronId);

	@Query("select min(p.budget.amount) from Patronage p where p.status = 1 and p.patron.id = :patronId group by p.budget.currency")
	Double minimumBudgetOfAcceptedPatronages(int patronId);

	@Query("select min(p.budget.amount) from Patronage p where p.status = 2 and p.patron.id = :patronId group by p.budget.currency")
	Double minimumBudgetOfDeniedPatronages(int patronId);

//	Maximum budget of patronages by statuses
	@Query("select max(p.budget.amount) from Patronage p where p.status = 0 and p.patron.id = :patronId group by p.budget.currency")
	Double maximumBudgetOfProposedPatronages(int patronId);

	@Query("select max(p.budget.amount) from Patronage p where p.status = 1 and p.patron.id = :patronId group by p.budget.currency")
	Double maximumBudgetOfAcceptedPatronages(int patronId);

	@Query("select max(p.budget.amount) from Patronage p where p.status = 2 and p.patron.id = :patronId group by p.budget.currency")
	Double maximumBudgetOfDeniedPatronages(int patronId);
 
}
