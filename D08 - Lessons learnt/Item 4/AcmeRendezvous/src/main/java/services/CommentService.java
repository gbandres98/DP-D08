
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Actor;
import domain.Administrator;
import domain.Comment;
import domain.Rendezvous;
import domain.Reply;
import domain.User;

@Service
@Transactional
public class CommentService {

	// Managed Repository ----------------------------------------------------

	@Autowired
	private CommentRepository	commentRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private RendezvousService	rendezvousService;
	@Autowired
	private ActorService	actorService;


	// Constructor ----------------------------------------------------

	public CommentService() {
		super();
	}

	// CRUD methods ----------------------------------------------------
	public Comment create(int rendezvousId) {
		Assert.notNull(rendezvousId);
		
		Comment comment;
		comment = new Comment();
		
		//le asignamos el rendezvous
		Rendezvous rendezvous=rendezvousService.findOne(rendezvousId);
		comment.setRendezvous(rendezvous);
		
		//añadimos las replies
		Collection<Reply> replies=new ArrayList<Reply>();
		comment.setReplies(replies);
		
		//Sacamos el momento del sistema
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		comment.setMoment(moment);
		
		//añadimos el actor
		Actor actor;
		 actor = this.actorService.findByPrincipal();
		 Assert.isTrue(actor != null);
		 Assert.isTrue(actor instanceof User);
		
		 User user=(User)actor;
		 comment.setUser(user);
		
		
		
		
		return comment;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = this.commentRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Comment save(final Comment comment) {


		 Assert.notNull(comment);
		//Sacamos el momento del sistema
			Date moment;
			moment = new Date(System.currentTimeMillis() - 1);
			comment.setMoment(moment);

		//checkear que el usuario tiene RSPV en el rendevous que comenta
		Assert.isTrue(comment.getUser().getRSVP().contains(comment.getRendezvous()));
		
	
		
		
		Comment commentsave = this.commentRepository.save(comment);
		return commentsave;
	}
	public Comment delete(final Comment comment) {
		Comment result;
		final Actor actor;
		Assert.notNull(comment);
		 actor = this.actorService.findByPrincipal();
		 Assert.isTrue(actor != null);

		//requeriment 6.1 need admin to delete
		 Assert.isTrue(actor instanceof Administrator);

		
	
		result = this.commentRepository.save(comment);
		return result;
	}

	// Other business methods

	public Comment findOne(final int commentId) {
		Comment result;

		result = this.commentRepository.findOne(commentId);

		return result;
	}

	public Collection<Comment> findByRendezvous(final Rendezvous rendezvous) {
		final Collection<Comment> result;

		// Tested Query 
		result = this.commentRepository.findByRendezvousId(rendezvous.getId());

		return result;
	}
}
