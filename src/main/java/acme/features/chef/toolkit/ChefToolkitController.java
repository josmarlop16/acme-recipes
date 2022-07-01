package acme.features.chef.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkit.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Chef;

@Controller
public class ChefToolkitController extends AbstractController<Chef, Toolkit>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected ChefToolkitListService		listService;

	@Autowired
	protected ChefToolkitShowService		showService;

	@Autowired
	protected ChefToolkitCreateService 		createService;

	@Autowired
	protected ChefToolkitPublishService 	publishService;

	@Autowired
	protected ChefToolkitDeleteService 		deleteService;

	@Autowired
	protected ChefToolkitUpdateService 		updateService;

	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish","update" ,this.publishService);
		super.addCommand("delete", this.deleteService);
	}
}
