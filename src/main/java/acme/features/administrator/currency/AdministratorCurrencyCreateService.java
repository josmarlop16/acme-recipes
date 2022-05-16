package acme.features.administrator.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acme.entities.currency.Currency;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCurrencyCreateService implements AbstractCreateService<Administrator, Currency> {

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
	public Currency instantiate(final Request<Currency> request) {
		assert request != null;
		Currency result;
		result = new Currency();
		result.setName("");
		result.setAccepted(false);
		result.setIsDefault(false);
		return result;
	}

	@Override
	public void bind(final Request<Currency> request, final Currency entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "accepted", "isDefault", "confirm");
	}

	@Override
	public void validate(final Request<Currency> request, final Currency entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isConfirmed;

		isConfirmed = request.getModel().getBoolean("confirm");
		errors.state(request, isConfirmed, "confirm", "administrator.currency.form.error.must-confirm");
	}

	@Override
	public void unbind(final Request<Currency> request, final Currency entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "accepted", "isDefault");
	}


	@Override
	public void create(final Request<Currency> request, final Currency entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}


}