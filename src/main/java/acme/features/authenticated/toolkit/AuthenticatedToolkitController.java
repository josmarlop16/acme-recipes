package acme.features.authenticated.toolkit;

import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import javax.annotation.PostConstruct;


import acme.framework.roles.Authenticated;
import acme.entities.toolkit.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AuthenticatedToolkitController extends AbstractController<Authenticated, Toolkit>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedToolkitListAllService listAllService;
	
	@Autowired
	protected AuthenticatedToolkitShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
		super.addCommand("show", this.showService);
	}
	
}
