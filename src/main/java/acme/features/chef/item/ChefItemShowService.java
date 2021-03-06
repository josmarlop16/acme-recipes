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

package acme.features.chef.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.features.moneyExchange.MoneyExchangePerform;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefItemShowService implements AbstractShowService<Chef, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefItemRepository repository;


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
		
		final String systemCurrency = this.repository.systemCurrency();
		if(!entity.getRetailPrice().getCurrency().equals(systemCurrency)) {
			final Money retailPrice=MoneyExchangePerform.computeMoneyExchange(entity.getRetailPrice(),systemCurrency).getTarget();
			model.setAttribute("computedPrice", retailPrice);
		}else {
			model.setAttribute("computedPrice", entity.getRetailPrice());
		}


		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "type","published");

	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findItemById(id);

		return result;
	}

}
