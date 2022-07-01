package acme.features.chef.patronageReport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import acme.entities.patronages.PatronageReport;
import acme.features.chef.patronage.ChefPatronageRepository;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Chef;

@Service
public class ChefPatronageReportShowService implements AbstractShowService<Chef, PatronageReport> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefPatronageReportRepository repository;
	
	@Autowired
	protected ChefPatronageRepository patronageRepository;


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		model.setAttribute("patronageCode", this.patronageRepository.findAllPatronage());
		model.setAttribute("code", entity.getPatronage().getCode());

		request.unbind(entity, model, "seqNumber", "creation", "memorandum", "optionalLink");
	}

	@Override
	public PatronageReport findOne(final Request<PatronageReport> request) {
		assert request != null;

		PatronageReport result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findPatronageReportById(id);

		return result;
	}
	
}
