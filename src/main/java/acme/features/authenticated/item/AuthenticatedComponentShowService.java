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

package acme.features.authenticated.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.features.moneyExchange.MoneyExchangePerform;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Authenticated;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedComponentShowService implements AbstractShowService<Authenticated, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedComponentRepository repository;


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final String systemCurrency=this.repository.systemCurrency();
		if(entity.getRetailPrice().getCurrency().equals(systemCurrency)) {
			model.setAttribute("computedPrice", entity.getRetailPrice());
			
		}else {
			final Money retailPrice=MoneyExchangePerform.computeMoneyExchange(entity.getRetailPrice(),systemCurrency).getTarget();
			model.setAttribute("computedPrice", retailPrice);
		}
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "type");
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findComponentById(id);
		return result;
	}
	
}
