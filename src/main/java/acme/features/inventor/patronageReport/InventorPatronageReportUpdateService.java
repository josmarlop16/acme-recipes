package acme.features.inventor.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportUpdateService implements AbstractUpdateService<Inventor,PatronageReport>{
	@Autowired
	protected InventorPatronageReportRepository repository;

	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		boolean result;
		int masterId;
		PatronageReport item;
		Inventor inventor;
		
		masterId=request.getModel().getInteger("id");
		item=this.repository.findPatronageReportById(masterId);
		inventor=item.getInventor();
		result= request.isPrincipal(inventor);
		
		return result;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "seqNumber", "creation", "memorandum", "optionalLink");
	
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model!=null;
		request.unbind(entity, model, "seqNumber", "creation", "memorandum", "optionalLink");
	
		
	}


	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;		
		}
	
	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;
		
		PatronageReport result;
		int id;
		
		id=request.getModel().getInteger("id");
		result=this.repository.findPatronageReportById(id);
		
		return result;
	}

	

	@Override
	public void update(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
		
	}

}
