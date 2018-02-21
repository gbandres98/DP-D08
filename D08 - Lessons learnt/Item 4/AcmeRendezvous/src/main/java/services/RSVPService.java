
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.RSVPRepository;
import domain.RSVP;

@Service
@Transactional
public class RSVPService {

	@Autowired
	private RSVPRepository	rsvpRepository;


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
}
