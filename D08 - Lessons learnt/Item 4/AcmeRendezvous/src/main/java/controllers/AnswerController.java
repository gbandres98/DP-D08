
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AnswerService;
import domain.Answer;

@Controller
@RequestMapping("/answer")
public class AnswerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AnswerService	answerService;

	@Autowired
	private ActorService	actorService;


	// Constructors -----------------------------------------------------------

	public AnswerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int rendezvousId, final int userId) {
		final ModelAndView result;
		Collection<Answer> answers;

		answers = this.answerService.findByRendezvousUser(rendezvousId, userId);
		result = new ModelAndView("answer/list");
		result.addObject("answers", answers);
		result.addObject("requestURI", "answer/list.do");
		return result;
	}
}
