package acme.features.chef.patronage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.features.moneyExchange.MoneyExchangePerform;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefPatronageShowService implements AbstractShowService<Chef, Patronage>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefPatronageRepository repository;


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final String systemCurrency = this.repository.systemCurrency();
		
		if(!entity.getBudget().getCurrency().equals(systemCurrency)) {
			final Money retailPrice=MoneyExchangePerform.computeMoneyExchange(entity.getBudget(),systemCurrency).getTarget();
			model.setAttribute("computedPrice", retailPrice);
		}else {
			model.setAttribute("computedPrice", entity.getBudget());
		}

		request.unbind(entity, model, "status", "code", "stuff", "budget", "creationMoment", "startDate", "endDate", "optionalLink",
				 "patron.company", "patron.statement", "patron.optionalLink", "published");
		model.setAttribute("patronUsername", entity.getPatron().getUserAccount().getUsername());
	}
	
	@Override
	public Patronage findOne(final Request<Patronage> request) {
		assert request != null;

		Patronage result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOnePatronageById(id);

		return result;
	}
	
}
