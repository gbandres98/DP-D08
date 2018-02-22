
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.GPSCoordinatesRepository;
import domain.Actor;
import domain.Administrator;
import domain.GPSCoordinates;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class GPSCoordinatesService {

	@Autowired
	private GPSCoordinatesRepository	gpsCoordinatesRepository;

	@Autowired
	private ActorService				actorService;

	@Autowired
	private RendezvousService			rendezvousService;


	//Constructors
	public GPSCoordinatesService() {
		super();
	}

	public GPSCoordinates create() {
		GPSCoordinates result;

		result = new GPSCoordinates();

		return result;
	}

	public GPSCoordinates save(final GPSCoordinates gpsCoordinates, final int rendezvousId) {
		GPSCoordinates result;
		Actor actor;
		Rendezvous rendezvous;

		rendezvous = this.rendezvousService.findOne(rendezvousId);
		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof User);
		Assert.isTrue(!rendezvous.isFinalVersion());
		Assert.isTrue(actor.getId() == rendezvous.getUser().getId());

		result = this.gpsCoordinatesRepository.save(gpsCoordinates);

		if (gpsCoordinates.getId() == 0) {
			rendezvous.setGPSCoordinates(result);
			this.rendezvousService.save(rendezvous);
		}

		return result;
	}

	public void delete(final GPSCoordinates gpsCoordinates) {
		Actor actor;

		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof Administrator);
		Assert.isTrue(gpsCoordinates.getId() != 0);

		this.gpsCoordinatesRepository.delete(gpsCoordinates);
	}

	public GPSCoordinates findOne(final int gpsCoordinatesId) {
		GPSCoordinates result;

		result = this.gpsCoordinatesRepository.findOne(gpsCoordinatesId);

		return result;
	}
}
