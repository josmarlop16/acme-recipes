package acme.features.patron.patronage;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.currency.Currency;
import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageStatus;
import acme.features.administrator.currency.AdministratorCurrencyRepository;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageRepository repository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;
	
	@Autowired
	protected AdministratorCurrencyRepository currencyRepository;
	

	// AbstractCreateService<Any, Chirp> interface ---------------


	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;
		boolean result;
		result = request.getPrincipal().hasRole(Patron.class);
		return result;
	}
	
	@Override
	public Patronage instantiate(final Request<Patronage> request) {
		assert request != null;
		
		final Patron p = this.repository.findPatronById(request.getPrincipal().getActiveRoleId());
		
		final Patronage result;
		final String defaultCurrencyName = this.currencyRepository.findDefaultCurrency().getName();
		Date endDate;
		Date startDate;
		
		final Date now = new Date(System.currentTimeMillis());
		
		startDate = DateUtils.addMonths( now , 1);
		endDate = DateUtils.addMonths( startDate , 1);
		
		final Money defaultMoney = new Money();
		defaultMoney.setAmount(0.0);
		defaultMoney.setCurrency(defaultCurrencyName);
		
		result = new Patronage();
		result.setBudget(defaultMoney);
		result.setStartDate(startDate);
		result.setEndDate(endDate);
		result.setCreationMoment(now);
		result.setStatus(PatronageStatus.PROPOSED);
		result.setPatron(p);
		
		return result;
	}

	@Override
	public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setInventor(this.repository.findInventorById(Integer.valueOf(request.getModel().getAttribute("inventorId").toString())));
		request.bind(entity, errors, "status", "code", "stuff", "budget","creationMoment","startDate","endDate", "optionalLink", "published");
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
			final Boolean validateCurrency = this.validateBudget(entity.getBudget());
			errors.state(request, validateCurrency , "budget", "inventor.invention.form.error.retailPrice-notAllowed");
		}
		
		if (!errors.hasErrors("budget")) {

			final List<String> acceptedCurrency= this.currencyRepository.findCurrencyNames();
			final String selectedCurrency = entity.getBudget().getCurrency();
			errors.state(request,acceptedCurrency.contains(selectedCurrency),"budget", "patron.patronage.form.error.accepted-currency");
				
			
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "patron.patronage.form.error.negative-budget");
		}
		
		
		
	}
	
	private Boolean validateBudget(final Money retailPrice) {
		final List<Currency> acceptedCurrencies = this.currencyRepository.findAcceptedCurrencies();
		
		final String myCurrency = retailPrice.getCurrency();

		return acceptedCurrencies.stream().anyMatch(c -> c.getName().equals(myCurrency));
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final List<Inventor> lsInventors= this.repository.findAllInventors();
		model.setAttribute("inventors", lsInventors);
		request.unbind(entity, model, "status", "code", "stuff", "budget","creationMoment","startDate","endDate", "optionalLink", "published");
	}


	@Override
	public void create(final Request<Patronage> request, final Patronage entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}
	

	
}