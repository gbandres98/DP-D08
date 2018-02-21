
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import domain.Actor;
import domain.Announcement;
import domain.Question;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class RendezvousService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private RendezvousRepository	rendezvousRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ActorService			actorService;


	// Constructors -----------------------------------------------------------

	public RendezvousService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Rendezvous create() {
		Rendezvous result;
		User user;

		user = (User) this.actorService.findByPrincipal();
		result = new Rendezvous();
		result.setUser(user);
		result.setQuestions(new HashSet<Question>());
		result.setAnnouncements(new HashSet<Announcement>());
		result.setRendezvouses(new HashSet<Rendezvous>());

		return result;
	}

	public Collection<Rendezvous> findAll() {
		Collection<Rendezvous> result;

		result = this.rendezvousRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Rendezvous delete(final Rendezvous rendezvous) {
		Actor actor;
		Rendezvous result;

		actor = this.actorService.findByPrincipal();

		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getId() != 0);
		Assert.isTrue(this.rendezvousRepository.exists(rendezvous.getId()));
		Assert.isTrue(actor.getId() == rendezvous.getUser().getId());

		rendezvous.setDeleted(true);
		result = this.save(rendezvous);

		return result;
	}

	public Rendezvous save(final Rendezvous rendezvous) {
		Rendezvous result;
		Actor actor;

		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor != null);
		Assert.isTrue(actor instanceof User);
		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getUser().getId() == actor.getId());
		Assert.isTrue(!rendezvous.isFinalVersion());

		result = this.rendezvousRepository.save(rendezvous);

		return result;
	}

	public Rendezvous setFinal(final int rendezvousId) {
		Rendezvous result, rendezvous;
		Actor actor;

		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor != null);
		Assert.isTrue(actor instanceof User);

		rendezvous = this.findOne(rendezvousId);
		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getUser().getId() == actor.getId());
		Assert.isTrue(!rendezvous.isFinalVersion());

		result = this.rendezvousRepository.save(rendezvous);

		return result;
	}

	//Other business methods

	public Rendezvous findOne(final int rendezvousId) {
		Rendezvous result;

		result = this.rendezvousRepository.findOne(rendezvousId);

		return result;
	}

	public Rendezvous findByAnnouncement(final Announcement announcement) {
		Rendezvous result;

		Assert.notNull(announcement);
		Assert.isTrue(announcement.getId() != 0);

		result = this.rendezvousRepository.findByAnnouncementId(announcement.getId());

		return result;
	}

	public Collection<Rendezvous> findRendevousWithRSVPbyUserId(final int id) {
		Collection<Rendezvous> result;

		result = this.rendezvousRepository.findRendevousWithRSVPbyUserId(id);

		return result;
	}

	public Collection<Rendezvous> findByUser(final int userId) {
		Collection<Rendezvous> result;

		result = this.rendezvousRepository.findByUser(userId);

		return result;
	}
}
