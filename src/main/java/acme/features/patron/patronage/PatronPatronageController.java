package acme.features.patron.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
public class PatronPatronageController extends AbstractController<Patron, Patronage>{

	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageListByPatronService listAllService;
	
	@Autowired
	protected PatronPatronageCreateService createService;
	
	@Autowired
	protected PatronPatronageDeleteService deleteService;
	
	@Autowired
	protected PatronPatronageUpdateService updateService;
	
	@Autowired
	protected PatronPatronagePublishService publishService;
	
	@Autowired
	protected PatronPatronageShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish", this.publishService);
		super.addCommand("show", this.showService);
	}
}
