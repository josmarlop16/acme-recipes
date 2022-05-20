package acme.features.inventor.toolkit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.SpamModule;
import acme.entities.item.Item;
import acme.entities.toolkit.Toolkit;
import acme.features.administrator.spam.AdministratorSpamRepository;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor,Toolkit> {
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected AdministratorSpamRepository spamRepository;
	
	@Autowired
	protected InventorItemRepository itemRepository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		boolean result;
		int masterId;
		Toolkit toolkit;
		Inventor inventor;

		masterId=request.getModel().getInteger("id");
		toolkit=this.repository.findToolkitById(masterId);
		inventor=toolkit.getInventor();
		result=toolkit.getPublished() == false && request.isPrincipal(inventor);

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setItem(this.itemRepository.findItemById(Integer.valueOf(request.getModel().getAttribute("itemId").toString())));
		request.bind(entity, errors, "title", "code", "description", "assemblyNotes", "link", "published");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!=null;
		
		final List<Item> Item = this.itemRepository.findAllItem();
		model.setAttribute("allItems", Item);
		
		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link", "published");
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("title")) {
            errors.state(request, SpamModule.spamValidator(entity.getTitle(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "title", "form.error.spam");
        }

		if (!errors.hasErrors("code")) {
            errors.state(request, SpamModule.spamValidator(entity.getCode(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "code", "form.error.spam");
        }
		
		if (!errors.hasErrors("description")) {
            errors.state(request, SpamModule.spamValidator(entity.getDescription(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "description", "form.error.spam");
        }
		
		if (!errors.hasErrors("assemblyNotes")) {
            errors.state(request, SpamModule.spamValidator(entity.getAssemblyNotes(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "assemblyNotes", "form.error.spam");
        }
		
		if (!errors.hasErrors("link")) {
            errors.state(request, SpamModule.spamValidator(entity.getLink(), this.spamRepository.findWeakSpamsWords(), this.spamRepository.findStrongSpamsWords()), "link", "form.error.spam");
        }

		if (!errors.hasErrors("code")) {
			final Toolkit existingToolkit;
			existingToolkit=this.repository.findToolkitByCode(entity.getCode());
			errors.state(request, existingToolkit == null || existingToolkit.getId() == entity.getId(), "code", "inventor.toolkit.form.error.code-exists");
		}
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;
		Toolkit result;
		int id;
		id=request.getModel().getInteger("id");
		result=this.repository.findToolkitById(id);
		return result;
	}

	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}
	
}
