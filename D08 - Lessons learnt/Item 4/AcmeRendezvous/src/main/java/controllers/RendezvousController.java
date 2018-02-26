/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.RendezvousService;
import domain.Actor;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/rendezvous")
public class RendezvousController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public RendezvousController() {
		super();
	}

	// Listing ----------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer rendezvousId) {
		ModelAndView result;
		Collection<Rendezvous> rendezvouses;

		rendezvouses = rendezvousId == null ? this.rendezvousService.findAll() : this.rendezvousService.findSimilar(rendezvousId);
		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("requestURI", "rendezvous/list.do");
		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int rendezvousId, @RequestParam(required = false) final Integer finalVersion) {
		ModelAndView result;
		Rendezvous rendezvous;
		Actor actor;
		User user;
		Boolean puedeCrear =false;
		rendezvous = this.rendezvousService.findOne(rendezvousId);
		result = new ModelAndView("rendezvous/display");
		result.addObject("rendezvous", rendezvous);
		result.addObject("requestURI", "rendezvous/display.do");

		
		
		
		if (this.actorService.isLogged()) {
			actor = this.actorService.findByPrincipal();
			
			
		if (actor instanceof User){
				result.addObject("userId", actor.getId());
			//Esto es para que no salga el create comment si no tiene RSVP
			user =(User) this.actorService.findByPrincipal();
			Collection<Rendezvous> rendevouses =this.rendezvousService.findRendevousWithRSVPbyUserId(actor.getId());
			Collection<Rendezvous> rendezvousesCreados=user.getRendezvouses() ;
			rendevouses.addAll(rendezvousesCreados);
			puedeCrear =rendevouses.contains(rendezvous);
			result.addObject("puedeCrear",puedeCrear);
		}}
		if (finalVersion != null)
			result.addObject("finalVersion", 1);
		
	
		
		
		
		
		return result;
	}
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	public ModelAndView remove(@RequestParam(required = true) final Integer rendezvousId) {
		ModelAndView result;

		final Rendezvous r = this.rendezvousService.findOne(rendezvousId);
		this.rendezvousService.remove(r);
		result = this.list(null);

		return result;
	}
}
