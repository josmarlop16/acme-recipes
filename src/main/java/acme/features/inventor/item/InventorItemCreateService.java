package acme.features.inventor.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.currency.Currency;
import acme.entities.item.Item;
import acme.entities.item.ItemType;
import acme.features.administrator.currency.AdministratorCurrencyRepository;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorItemCreateService implements AbstractCreateService<Inventor, Item>{
	
	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected AdministratorCurrencyRepository currencyRepository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "link", "type", "published");
	
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!=null;
		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "type", "published");
	
		
	}

	@Override
	public Item instantiate(final Request<Item> request) {
		assert request != null;
		
		final Item result;
		final Inventor inventor;
		final String defaultCurrencyName = this.currencyRepository.findDefaultCurrency().getName();
	
		inventor = this.repository.findInventorById(request.getPrincipal().getActiveRoleId());
		result = new Item();
		result.setName("");
		result.setCode("");
		result.setTechnology("");
		result.setDescription("");
		
		final Money defaultMoney = new Money();
		defaultMoney.setAmount(0.0);
		defaultMoney.setCurrency(defaultCurrencyName);
		result.setRetailPrice(defaultMoney);
		result.setInventor(inventor);
		result.setType(ItemType.TOOL);
		result.setPublished(false);


		return result;
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		System.out.println(entity.getRetailPrice());
		
		if (!errors.hasErrors("name")) {
            errors.state(request, SpamModule.spamValidator(entity.getName(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "name", "form.error.spam");
        }
		
		if (!errors.hasErrors("technology")) {
            errors.state(request, SpamModule.spamValidator(entity.getTechnology(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "technology", "form.error.spam");
        }
		
		if (!errors.hasErrors("description")) {
            errors.state(request, SpamModule.spamValidator(entity.getDescription(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "description", "form.error.spam");
        }
		
		if (!errors.hasErrors("code")) {
            errors.state(request, SpamModule.spamValidator(entity.getCode(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "code", "form.error.spam");
        }
		
		if (!errors.hasErrors("link")) {
            errors.state(request, SpamModule.spamValidator(entity.getLink(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "link", "form.error.spam");
        }
	
		
		if (!errors.hasErrors("code")) {
			final Item existingItem;
			
			existingItem=this.repository.findItemByCode(entity.getCode());
			errors.state(request, existingItem == null || existingItem.getId() == entity.getId(), "code", "inventor.item.form.error.code-exists");
		}
		
		if (!errors.hasErrors("retailPrice")) {
			final Boolean validateCurrency = this.validateRetailPrice(entity.getRetailPrice());
			errors.state(request, validateCurrency , "retailPrice", "inventor.invention.form.error.retailPrice-notAllowed");
		}
       
		
	}
	
	private Boolean validateRetailPrice(final Money retailPrice) {
		final List<Currency> acceptedCurrencies = this.currencyRepository.findAcceptedCurrencies();
		
		final String myCurrency = retailPrice.getCurrency();

		return acceptedCurrencies.stream().anyMatch(c -> c.getName().equals(myCurrency));
	}

	

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}
}
