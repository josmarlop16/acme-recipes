/*
 * AnonymousJobShowService.java
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.currency.Currency;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorCurrencyShowService implements AbstractShowService<Administrator, Currency> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorCurrencyRepository repository;


	@Override
	public boolean authorise(final Request<Currency> request) {
		assert request != null;
		boolean result;
		result = request.getPrincipal().hasRole(Administrator.class);
		return result;
	}


	@Override
	public void unbind(final Request<Currency> request, final Currency entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "accepted", "isDefault");
	}

	@Override
	public Currency findOne(final Request<Currency> request) {
		assert request != null;
		
		Currency result;
		int id;
		
		id=request.getModel().getInteger("id");
		result=this.repository.findCurrencyById(id);
		
		return result;
	}

}
