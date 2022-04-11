package acme.features.any.toolkit;

import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import javax.annotation.PostConstruct;


import acme.framework.roles.Any;
import acme.entities.toolkit.*;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class AnyToolkitController extends AbstractController<Any, Toolkit>{
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyToolkitListAllService listAllService;
	
	@Autowired
	protected AnyToolkitShowService showService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listAllService);
		super.addCommand("show", this.showService);
	}
	
}
