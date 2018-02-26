
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RSVPRepository;
import domain.Actor;
import domain.Administrator;
import domain.Answer;
import domain.RSVP;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class RSVPService {

	@Autowired
	private RSVPRepository		rsvpRepository;
	@Autowired
	private ActorService		actorService;
	@Autowired
	private RendezvousService	rendezvousService;


	//Constructors
	public RSVPService() {
		super();
	}

	public RSVP create(final int rendezvousId) {
		RSVP rsvp, result;
		User user;
		Rendezvous rendezvous;

		rendezvous = this.rendezvousService.findOne(rendezvousId);
		user = (User) this.actorService.findByPrincipal();
		Assert.isTrue(rendezvous.getUser().getId() != user.getId());
		rsvp = this.rsvpRepository.existByRendezvousIdUserId(rendezvousId, user.getId());
		Assert.isTrue(rsvp == null);
		rsvp = new RSVP();
		rsvp.setUser(user);
		rsvp.setRendezvous(rendezvous);
		rsvp.setCancelled(false);
		rsvp.setJoined(false);
		rsvp.setAnswers(new HashSet<Answer>());

		result = this.save(rsvp);

		return result;
	}

	public Collection<RSVP> findJoined(final int rendezvousId) {
		Collection<RSVP> result;

		result = this.rsvpRepository.findJoinedByRendezvousId(rendezvousId);

		return result;
	}

	public Collection<RSVP> findNotJoined(final int rendezvousId) {
		Collection<RSVP> result;

		result = this.rsvpRepository.findNotJoinedByRendezvousId(rendezvousId);

		return result;
	}
	public Collection<RSVP> findbyRendezvous(final int rendezvousId) {
		Collection<RSVP> result;

		result = this.rsvpRepository.findByRendezvousId(rendezvousId);

		return result;
	}

	public void delete(final RSVP r) {
		final Actor actor;
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof Administrator);
		this.rsvpRepository.delete(r);

	}

	public RSVP save(final RSVP rsvp) {
		RSVP result;
		Actor actor;

		Assert.isTrue(this.actorService.isLogged());
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(rsvp.getUser().getId() == actor.getId());

		if (rsvp.getRendezvous().getQuestions().size() == rsvp.getAnswers().size() && rsvp.isJoined() == false)
			rsvp.setJoined(true);
		if (rsvp.isCancelled())
			rsvp.setJoined(false);

		result = this.rsvpRepository.save(rsvp);

		return result;
	}

	public RSVP findOne(final int rsvpId) {
		RSVP result;

		result = this.rsvpRepository.findOne(rsvpId);

		return result;
	}

	public RSVP existByRendezvousIdUserId(final int rendezvousId, final int userId) {
		RSVP result;

		result = this.rsvpRepository.existByRendezvousIdUserId(rendezvousId, userId);

		return result;
	}
}
