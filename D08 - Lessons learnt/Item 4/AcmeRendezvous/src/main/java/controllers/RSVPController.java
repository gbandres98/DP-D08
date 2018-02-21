
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RSVPService;
import domain.RSVP;

@Controller
@RequestMapping("/rsvp")
public class RSVPController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RSVPService	rsvpService;


	// Constructors -----------------------------------------------------------

	public RSVPController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listByRendezvous(@RequestParam final int rendezvousId) {
		ModelAndView result;
		Collection<RSVP> rsvps;

		rsvps = this.rsvpService.findJoined(rendezvousId);
		result = new ModelAndView("rsvp/list");
		result.addObject("rsvps", rsvps);
		result.addObject("requestURI", "rsvp/list.do");
		return result;
	}

}
