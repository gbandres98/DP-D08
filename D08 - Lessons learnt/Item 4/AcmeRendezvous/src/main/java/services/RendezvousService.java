
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import domain.Actor;
import domain.Rendezvous;

@Service
@Transactional
public class RendezvousService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private RendezvousRepository	rendezvousRepository;


	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public RendezvousService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Rendezvous create() {
		Rendezvous result;

		result = new Rendezvous();

		return result;
	}

	public Collection<Rendezvous> findAll() {
		Collection<Rendezvous> result;

		result = this.rendezvousRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Rendezvous delete(final Rendezvous rendezvous) {
		final Actor actor;
		Rendezvous result;

		//actor = this.actorService.findByPrincipal();
		//Chekear el actor

		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getId() != 0);
		Assert.isTrue(this.rendezvousRepository.exists(rendezvous.getId()));
		rendezvous.setDeleted(true);
		result = this.save(rendezvous);

		return result;
	}

	public Rendezvous save(final Rendezvous rendezvous) {
		Rendezvous result;
		final Actor actor;

		//actor = this.actorService.findByPrincipal();
		//Assert.isTrue(actor != null);
		//Assert.isTrue(actor instanceof User);
		Assert.notNull(rendezvous);
		//Assert.isTrue(rendezvous.getUser().getId() == actor.getId());

		result = this.rendezvousRepository.save(rendezvous);

		return result;
	}

	//Other business methods

	public Rendezvous findOne(final int rendezvouzId) {
		Rendezvous result;

		result = this.rendezvousRepository.findOne(rendezvouzId);

		return result;
	}

}
