package acme.features.inventor.toolkit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acme.entities.toolkit.Toolkit;
import acme.features.inventor.item.InventorItemRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitCreateService implements AbstractCreateService<Inventor, Toolkit> {
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected InventorItemRepository ItemRepository;

	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "title", "code", "description", "assemblyNotes", "link", "published");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!=null;
		request.unbind(entity, model, "title", "code", "description", "assemblyNotes", "link", "published");
	}

	@Override
	public Toolkit instantiate(final Request<Toolkit> request) {
		assert request != null;

		final Toolkit result;
		final Inventor inventor;

		inventor = this.repository.findInventorById(request.getPrincipal().getActiveRoleId());
		result = new Toolkit();
		result.setTitle("");
		result.setCode("");
		result.setDescription("");
		result.setAssemblyNotes("");
		result.setInventor(inventor);
		result.setPublished(false);
		
		return result;
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			final Toolkit existingToolkit;
			existingToolkit=this.repository.findToolkitByCode(entity.getCode());
			errors.state(request, existingToolkit == null || existingToolkit.getId() == entity.getId(), "code", "inventor.toolkit.form.error.code-exists");
		}
	}

	@Override
	public void create(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}
	
}