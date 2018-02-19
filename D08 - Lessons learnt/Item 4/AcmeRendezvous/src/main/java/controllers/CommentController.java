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
import java.util.ListResourceBundle;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Comment;
import domain.Rendezvous;

import services.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CommentService commentService;

	// Constructors -----------------------------------------------------------

	public CommentController() {
		super();
	}
	
	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(required=true) final Integer rendezvousId,@RequestParam(required=false) final Integer commentId) {
		ModelAndView result;
		Comment comment;

		comment = commentService.create(rendezvousId,commentId);
		result = createEditModelAndView(comment);
		
		return result;
	}

	// Listing Root ----------------------------------------------------------------

	@RequestMapping(value = "/list-Root", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(required=true) final Integer rendezvousId) {
		ModelAndView result;
		Collection<Comment> comments;

		comments = commentService.findByRendezvousIdRoot(rendezvousId);

		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		

		return result;
	}
	// Listing Answers----------------------------------------------------------------

		@RequestMapping(value = "/list-Answer", method = RequestMethod.GET)
		public ModelAndView display2(@RequestParam(required=true) final Integer commentId) {
			ModelAndView result;
			Collection<Comment> comments;
			Comment parentComment;
			comments = commentService.findByParentCommentId(commentId);
			parentComment=commentService.findOne(commentId);
			result = new ModelAndView("comment/list");
			result.addObject("comments", comments);
			result.addObject("ParentComment", parentComment);
			return result;
		}
//delete from listing
		@RequestMapping(value = "/delete", method = RequestMethod.GET)
		public ModelAndView display3(@RequestParam(required=true) final Integer commentId) {
			ModelAndView result;
			Comment c=commentService.findOne(commentId);
			Rendezvous r=c.getRendezvous();
			
			try {
				
				commentService.delete(c);
				if(c.getparentComment()==null){
					result = new ModelAndView("comment/list");
				
			
				
				}else{
					result=this.display2(c.getparentComment().getId());
				}
				
			} catch (Throwable oops) {
				result = display2(c.getId());
				
			}

			return result;
		}
	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int commentId) {
		ModelAndView result;
		Comment comment;

		comment = commentService.findOne(commentId);


		result = this.createEditModelAndView(comment);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Comment comment,
			final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(comment, "comment.binding.error");
		else
			try {
				this.commentService.save(comment);
				result = new ModelAndView("redirect:/rendezvous/display.do?IdRendevous="+comment.getRendezvous().getId());
			} catch (final Throwable oops) {
				String errorMessage = "comment.commit.error";
				
				if(oops.getMessage().contains("message.error")){
					errorMessage = oops.getMessage();
				}
				result = this.createEditModelAndView(comment, errorMessage);
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Comment comment,
			final BindingResult binding) {
		ModelAndView result;

		try {
			this.commentService.delete(comment);
			result = new ModelAndView("redirect:/AcmeRendezvous/");
		} catch (final Throwable oops) {
			String errorMessage = "comment.commit.error";
			
			if(oops.getMessage().contains("message.error")){
				errorMessage = oops.getMessage();
			}
			result = this.createEditModelAndView(comment, errorMessage);
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Comment comment) {
		ModelAndView result;

		result = this.createEditModelAndView(comment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Comment comment,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("comment/edit");
		result.addObject("comment", comment);
		result.addObject("message", message);

		return result;
	}
}
