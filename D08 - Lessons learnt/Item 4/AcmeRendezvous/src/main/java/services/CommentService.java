
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Actor;
import domain.Comment;
import domain.Rendezvous;

@Service
@Transactional
public class CommentService {

	// Managed Repository ----------------------------------------------------

	@Autowired
	private CommentRepository	commentRepository;

	// Supporting services ----------------------------------------------------

	private RendezvousService	rendezvousService;


	// Constructor ----------------------------------------------------

	public CommentService() {
		super();
	}

	// CRUD methods ----------------------------------------------------
	public Comment create() {
		Comment result;

		result = new Comment();

		return result;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = this.commentRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Comment save(final Comment comment) {
		Comment result;
		final Actor actor;

		//		Descomentar cuando este el getPrincipal963.

		// actor = this.actorService.findByPrincipal();
		// Assert.isTrue(actor != null);
		// Assert.isTrue(actor instanceof User);
		Assert.notNull(comment);
		//		 User user=(User)actor;
		// comment.setUser(user);

		//checkear que el usuario tiene RSPV en el rendevous que comenta
		//		Assert.isTrue(user.getRendezvouses().contains(comment.getRendezvous()));

		result = this.commentRepository.save(comment);
		return result;
	}
	public Comment delete(final Comment comment) {
		Comment result;
		final Actor actor;

		// actor = this.actorService.findByPrincipal();
		// Assert.isTrue(actor != null);

		//requeriment 6.1 need admin to delete
		// Assert.isTrue(actor instanceof Admin);

		Assert.notNull(comment);
		// User user=(User)actor;
		// comment.setUser(user);
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
		Collection<Comment> result;

		result = this.commentRepository.findByRendezvousId(rendezvous.getId());

		return result;
	}
}
