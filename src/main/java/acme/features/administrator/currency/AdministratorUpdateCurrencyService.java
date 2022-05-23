package acme.features.administrator.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.currency.Currency;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorUpdateCurrencyService implements AbstractUpdateService<Administrator, Currency> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorCurrencyRepository repository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;

	@Override
	public boolean authorise(final Request<Currency> request) {
		assert request != null;
		boolean result;
		result = request.getPrincipal().hasRole(Administrator.class);
		return result;
	}

	@Override
	public void bind(final Request<Currency> request, final Currency entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "accepted", "isDefault");
	}

	@Override
	public void validate(final Request<Currency> request, final Currency entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("name")) {
            errors.state(request, SpamModule.spamValidator(entity.getName(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "name", "form.error.spam");
        }
		
		final boolean defaultSelected = request.getModel().getAttribute("isDefault").equals("true");
		
		if(defaultSelected) {
			final Currency currentDefault = this.repository.findDefaultCurrency();	
			
			final String currentSelectedName = entity.getName();
			final String currentDefaultName = currentDefault.getName();
			
			if(!currentSelectedName.equals(currentDefaultName)) {
				currentDefault.setIsDefault(false);
				this.repository.save(currentDefault);
			}
		}
		
		
		
	}

	@Override
	public void unbind(final Request<Currency> request, final Currency entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "accepted", "isDefault");
	}


	@Override
	public void update(final Request<Currency> request, final Currency entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
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