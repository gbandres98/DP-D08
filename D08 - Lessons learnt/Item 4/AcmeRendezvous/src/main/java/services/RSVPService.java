
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
	private UserService			userService;
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
		Assert.isTrue(rsvp == null || rsvp.isCancelled());
		if (rsvp == null) {
			rsvp = new RSVP();
			rsvp.setUser(user);
			rsvp.setRendezvous(rendezvous);
			rsvp.setCancelled(false);
			rsvp.setJoined(false);
			rsvp.setAnswers(new HashSet<Answer>());
		} else if (rsvp.isCancelled())
			rsvp.setCancelled(false);
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

	public Double averageRSVPperRendezvous() {
		final Double result = this.rsvpRepository.averageRSVPperUser();
		return result;
	}

	public Double standardDeviationRSVPperRendezvous() {
		final Double auxavg = this.averageRSVPperRendezvous();
		final Collection<Rendezvous> aux = this.rendezvousService.findAll();
		Collection<RSVP> auxr = new ArrayList<RSVP>();
		Double auxsum = 0.0;
		for (final Rendezvous r : aux) {
			auxr = this.rsvpRepository.findByRendezvousId(r.getId());
			auxsum = auxsum + (auxr.size() * auxr.size());
		}

		final Double result = Math.sqrt(auxsum / aux.size() - (auxavg * auxavg));
		return result;
	}

	public Double averageRSVPperUser() {
		final Double result = this.rsvpRepository.averageRSVPperUser();
		return result;
	}

	public Double standardDeviationRSVPperUser() {
		final Double auxavg = this.averageRSVPperUser();
		final Collection<User> aux = this.userService.findAll();
		Collection<RSVP> auxr = new ArrayList<RSVP>();
		Double auxsum = 0.0;
		for (final User s : aux) {
			auxr = this.rsvpRepository.findByUser(s.getId());
			auxsum = auxsum + (auxr.size() * auxr.size());
		}

		final Double result = Math.sqrt(auxsum / aux.size() - (auxavg * auxavg));
		return result;

	}

	public RSVP save(final RSVP rsvp) {
		RSVP result;
		Actor actor;
		Date today;

		Assert.isTrue(this.actorService.isLogged());
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(rsvp.getUser().getId() == actor.getId());
		Assert.isTrue(rsvp.getRendezvous().isFinalVersion() == true);
		today = new Date();
		Assert.isTrue(rsvp.getRendezvous().getMoment().after(today));

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

	public Collection<RSVP> findAll() {
		final Collection<RSVP> result = this.rsvpRepository.findAll();
		return result;
	}

	public RSVP existByRendezvousIdUserId(final int rendezvousId, final int userId) {
		RSVP result;

		result = this.rsvpRepository.existByRendezvousIdUserId(rendezvousId, userId);

		return result;
	}

	public void cancel(final RSVP rsvp) {
		Actor actor;

		actor = this.actorService.findByPrincipal();
		Assert.notNull(rsvp);
		Assert.isTrue(rsvp.getId() != 0);
		Assert.isTrue(actor.getId() == rsvp.getUser().getId());

		rsvp.setCancelled(true);
		this.rsvpRepository.save(rsvp);

	}

}
