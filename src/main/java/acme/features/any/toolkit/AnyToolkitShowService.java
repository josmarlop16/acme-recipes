package acme.features.any.toolkit;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.features.moneyExchange.MoneyExchangePerform;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit>{


	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyToolkitRepository repository;


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final String systemCurrency=this.repository.systemCurrency();
		List<Item> items;
		items=this.repository.findItemsByToolkitId(entity.getId()).stream().collect(Collectors.toList());
		
		final List<Money> retailPrices = new ArrayList<>();
		for (final Item item:items) {
			retailPrices.add(item.getRetailPrice());
		}
		
		final Money computedPrice=new Money();
		computedPrice.setCurrency(systemCurrency);
		Double amounts=0.0;
		for(final Money retailPrice:retailPrices) {
			amounts+=MoneyExchangePerform.computeMoneyExchange(retailPrice, systemCurrency).getTarget().getAmount();
			
		}
		
		computedPrice.setAmount(amounts);
		computedPrice.setCurrency(systemCurrency);
		model.setAttribute("computedPrice", computedPrice);
//		model.setAttribute("itemPrice", entity.getItem().getRetailPrice());
		
		

		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link");
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneToolkitById(id);

		return result;
	}
	
}
