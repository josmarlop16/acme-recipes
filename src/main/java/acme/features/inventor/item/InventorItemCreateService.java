package acme.features.inventor.item;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.item.Item;
import acme.entities.item.ItemType;
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
	
		inventor = this.repository.findInventorById(request.getPrincipal().getActiveRoleId());
		result = new Item();
		result.setName("");
		result.setCode("");
		result.setTechnology("");
		result.setDescription("");
		
		final Money defaultMoney = new Money();
		defaultMoney.setAmount(0.0);
		defaultMoney.setCurrency("EUR");
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
			final Set<String> allowedCurrencies;
			allowedCurrencies=new HashSet<String>();
			final String[] allowedCurrenciesArray=this.repository.findAllowedCurrencies();
			Collections.addAll(allowedCurrencies, allowedCurrenciesArray);
			
			errors.state(request, allowedCurrencies.contains(entity.getRetailPrice().getCurrency()) , "retailPrice", "inventor.invention.form.error.retailPrice-notAllowed");
		}
       
		
	}

	

	@Override
	public void create(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}
}
