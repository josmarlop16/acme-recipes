package acme.features.inventor.item;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.item.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorItemPublishService implements AbstractUpdateService<Inventor,Item> {
	@Autowired
	protected InventorItemRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		Item item;
		Inventor inventor;
		
		masterId=request.getModel().getInteger("id");
		item=this.repository.findItemById(masterId);
		inventor=item.getInventor();
		result=item.getPublished() == false && request.isPrincipal(inventor);
		
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
