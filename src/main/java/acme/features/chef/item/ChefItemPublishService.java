package acme.features.chef.item;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.item.Item;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Chef;

@Service
public class ChefItemPublishService implements AbstractUpdateService<Chef,Item> {
	@Autowired
	protected ChefItemRepository repository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;
	
	

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Item item;
		Chef chef;
		
		masterId=request.getModel().getInteger("id");
		item=this.repository.findItemById(masterId);
		chef=item.getChef();
		result=!item.getPublished() && request.isPrincipal(chef);
		
		return result;
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
			errors.state(request, existingItem == null || existingItem.getId() == entity.getId(), "code", "chef.item.form.error.code-exists");
		}
		
		if (!errors.hasErrors("retailPrice")) {
			final Set<String> allowedCurrencies;
			allowedCurrencies=new HashSet<String>();
			final String[] allowedCurrenciesArray=this.repository.findAllowedCurrencies();
			Collections.addAll(allowedCurrencies, allowedCurrenciesArray);
			
			errors.state(request, allowedCurrencies.contains(entity.getRetailPrice().getCurrency()) , "retailPrice", "chef.invention.form.error.retailPrice-notAllowed");
		}
		
		}
	
	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;
		
		Item result;
		int id;
		
		id=request.getModel().getInteger("id");
		result=this.repository.findItemById(id);
		
		return result;
	}

	

	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;
		
		entity.setPublished(true);
		this.repository.save(entity);
		
	}
}
