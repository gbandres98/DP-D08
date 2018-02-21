
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.GPSCoordinatesRepository;
import domain.Actor;
import domain.GPSCoordinates;
import domain.User;

@Service
@Transactional
public class GPSCoordinatesService {

	@Autowired
	private GPSCoordinatesRepository	gpsCoordinatesRepository;

	@Autowired
	private ActorService				actorService;


	//Constructors
	public GPSCoordinatesService() {
		super();
	}

	public GPSCoordinates create() {
		GPSCoordinates result;

		result = new GPSCoordinates();

		return result;
	}

	public GPSCoordinates save(final GPSCoordinates gpsCoordinates, final int userId) {
		GPSCoordinates result;
		Actor actor;

		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof User);
		Assert.isTrue(actor.getId() == userId);

		result = this.gpsCoordinatesRepository.save(gpsCoordinates);

		return result;
	}

	public void delete(final GPSCoordinates gpsCoordinates, final int userId) {
		Actor actor;

		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor instanceof User);
		Assert.isTrue(actor.getId() == userId);
		Assert.isTrue(gpsCoordinates.getId() != 0);

		this.gpsCoordinatesRepository.delete(gpsCoordinates);
	}
}
