
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RSVPRepository;
import domain.Actor;
import domain.Administrator;
import domain.RSVP;

@Service
@Transactional
public class RSVPService {

	@Autowired
	private RSVPRepository	rsvpRepository;
	@Autowired
	private ActorService		actorService;

	//Constructors
	public RSVPService() {
		super();
	}

	public RSVP create() {
		RSVP result;

		result = new RSVP();

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

	public void delete(RSVP r) {
		final Actor actor;
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof Administrator);
		rsvpRepository.delete(r);
		
	}
}
