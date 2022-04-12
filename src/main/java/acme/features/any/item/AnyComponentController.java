/*
 * AnonymousShoutController.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.any.item;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import acme.entities.item.Item;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
public class AnyComponentController extends AbstractController<Any, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyComponentListService		listService;
	
	@Autowired
	protected AnyComponentShowService		showService;
	
	@Autowired
	protected AnyComponentToolListService		toollistService;
	
	@Autowired
	protected AnyComponentToolShowService		toolshowService;

//	@Autowired
//	protected AnonymousComponentCreateService	createService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list-component", "list", this.listService);
		super.addCommand("list-tool", "list", this.toollistService);
		super.addCommand("show", this.toolshowService);
//		super.addCommand("create", this.createService);
	}

}
