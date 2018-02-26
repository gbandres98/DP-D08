/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AnnouncementService;
import services.RendezvousService;
import controllers.AbstractController;

@Controller
@RequestMapping("/announcement/administrator")
public class AnnouncementAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AnnouncementService	announcementService;

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public AnnouncementAdministratorController() {
		super();
	}

	// Deleting ----------------------------------------------------------------	

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int announcementId, final int rendezvousId) {
		ModelAndView result;

		this.announcementService.delete(announcementId);
		result = new ModelAndView("redirect:/announcement/list.do?rendezvousId=" + rendezvousId);

		return result;
	}

	// Ancillary Methods ------------------------------------------------------

}
