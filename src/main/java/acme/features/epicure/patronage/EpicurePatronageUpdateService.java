package acme.features.epicure.patronage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.patronages.Patronage;
import acme.features.administrator.currency.AdministratorCurrencyRepository;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Epicure;

@Service
public class EpicurePatronageUpdateService implements AbstractUpdateService<Epicure, Patronage>{

	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicurePatronageRepository repository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;
	
	@Autowired
	protected AdministratorCurrencyRepository currencyRepository;

	// AbstractUpdateService<Patron, Patronage> -------------------------------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		boolean result;
		int patronageId;
		Patronage patronage;

		patronageId = request.getModel().getInteger("id");
		patronage = this.repository.findOnePatronageById(patronageId);
		result = (patronage != null && !patronage.getPublished() && request.isPrincipal(patronage.getEpicure()));

		return result;
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

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setChef(this.repository.findChefById(Integer.valueOf(request.getModel().getAttribute("chefId").toString())));
		request.bind(entity, errors, "status", "code", "stuff", "budget","creationMoment", "startDate","endDate", "optionalLink", "published");
	}

	@Override
	public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
            errors.state(request, SpamModule.spamValidator(entity.getCode(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "code", "form.error.spam");
        }
		if (!errors.hasErrors("stuff")) {
            errors.state(request, SpamModule.spamValidator(entity.getStuff(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "stuff", "form.error.spam");
        }
		if (!errors.hasErrors("optionalLink")) {
            errors.state(request, SpamModule.spamValidator(entity.getOptionalLink(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "optionalLink", "form.error.spam");
        }

		
		
		if (!errors.hasErrors("budget")) {

			final List<String> acceptedCurrency= this.currencyRepository.findCurrencyNames();
			final String selectedCurrency = entity.getBudget().getCurrency();
			errors.state(request,acceptedCurrency.contains(selectedCurrency),"budget", "epicure.patronage.form.error.accepted-currency");
				
			
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "epicure.patronage.form.error.negative-budget");
		}
		

	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "status", "code", "stuff", "budget","creationMoment","startDate","endDate", "optionalLink", "published");
		model.setAttribute("chefs", this.repository.findAllChefs());
		model.setAttribute("chefId", entity.getChef().getId());
	}

	@Override
	public void update(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

	
}