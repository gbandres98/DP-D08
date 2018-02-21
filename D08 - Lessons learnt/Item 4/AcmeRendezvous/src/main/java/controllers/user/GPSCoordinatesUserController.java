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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.GPSCoordinatesService;
import controllers.AbstractController;
import domain.GPSCoordinates;

@Controller
@RequestMapping("/gpscoordinates/user")
public class GPSCoordinatesUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private GPSCoordinatesService	gpsCoordinatesService;

	//	@Autowired
	//	private RendezvousService	rendezvousService;

	@Autowired
	private ActorService			actorService;


	// Constructors -----------------------------------------------------------

	public GPSCoordinatesUserController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int rendezvousId) {
		ModelAndView result;
		GPSCoordinates gpsCoordinates;

		gpsCoordinates = this.gpsCoordinatesService.create();
		result = this.createEditModelAndView(gpsCoordinates);
		result.addObject("rendezvousId", rendezvousId);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int gpsCoordinatesId, @RequestParam final int rendezvousId) {
		ModelAndView result;
		GPSCoordinates gpsCoordinates;

		gpsCoordinates = this.gpsCoordinatesService.findOne(gpsCoordinatesId);
		Assert.notNull(gpsCoordinates);
		result = this.createEditModelAndView(gpsCoordinates);
		result.addObject("rendezvousId", rendezvousId);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final GPSCoordinates gpsCoordinates, final BindingResult binding, @RequestParam final int rendezvousId) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(gpsCoordinates);
		else
			try {
				this.gpsCoordinatesService.save(gpsCoordinates, rendezvousId);
				result = new ModelAndView("redirect:/rendezvous/display.do?rendezvousId=" + rendezvousId);
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(gpsCoordinates, "gps.commit.error");
				result.addObject("rendezvousId", rendezvousId);
			}

		return result;
	}
	// Ancillary Methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final GPSCoordinates gpsCoordinates) {
		ModelAndView result;

		result = this.createEditModelAndView(gpsCoordinates, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final GPSCoordinates gpsCoordinates, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("gpscoordinates/edit");
		result.addObject("gpsCoordinates", gpsCoordinates);
		result.addObject("message", messageCode);

		return result;
	}

}
