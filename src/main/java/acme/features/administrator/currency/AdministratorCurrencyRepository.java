/*
 * AnonymousShoutRepository.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.currency;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.currency.Currency;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCurrencyRepository extends AbstractRepository {

	@Query("select c from Currency c where c.accepted = 1")
	Collection<Currency> findCurrencys();
	

	@Query("select c from Currency c where c.id = :id")
	Currency findCurrencyById(int id);

	@Query("select c from Currency c")
	Collection<Currency> findCurrencysAdmin();
	
	@Query("select c from Currency c where c.isDefault = 1")
	Currency findDefaultCurrency();

	@Query("select c from Currency c where c.accepted = 1")
	List<Currency> findAcceptedCurrencies();

	@Query("select c.name from Currency c where c.accepted = 1")
	List<String> findCurrencyNames();

}
