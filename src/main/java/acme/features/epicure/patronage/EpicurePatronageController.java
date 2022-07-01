package acme.features.epicure.patronage;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Epicure;

@Controller
public class EpicurePatronageController extends AbstractController<Epicure, Patronage>{

	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected EpicurePatronageListByPatronService listAllService;
	
	@Autowired
	protected EpicurePatronageCreateService createService;
	
	@Autowired
	protected EpicurePatronageDeleteService deleteService;
	
	@Autowired
	protected EpicurePatronageUpdateService updateService;
	
	@Autowired
	protected EpicurePatronagePublishService publishService;
	
	@Autowired
	protected EpicurePatronageShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
		super.addCommand("create", this.createService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish", "update", this.publishService);
		super.addCommand("show", this.showService);
	}
}
