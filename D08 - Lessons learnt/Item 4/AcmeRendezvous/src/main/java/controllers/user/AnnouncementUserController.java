/*
 * WelcomeController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.user;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AnnouncementService;
import services.RendezvousService;
import controllers.AbstractController;
import domain.Actor;
import domain.Announcement;
import domain.User;

@Controller
@RequestMapping("/announcement/user")
public class AnnouncementUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AnnouncementService	announcementService;

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private ActorService		actorService;


	// Constructors -----------------------------------------------------------

	public AnnouncementUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------		

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Announcement> announcements;
		Actor principal;

		principal = this.actorService.findByPrincipal();
		announcements = this.announcementService.findByUserRSVP((User) principal);
		result = new ModelAndView("announcement/list");
		result.addObject("announcements", announcements);
		result.addObject("requestURI", "announcement/user/list.do");
		return result;
	}

}
