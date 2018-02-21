
package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/law")
public class LawController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public LawController() {
		super();
	}

	// Laws

	@RequestMapping(value = "/terms-conditions")
	public ModelAndView terms() {
		ModelAndView result;

		result = new ModelAndView("law/terms-conditions");

		return result;
	}
}
