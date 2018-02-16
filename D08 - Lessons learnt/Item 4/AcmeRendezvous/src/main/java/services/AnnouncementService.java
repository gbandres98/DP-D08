
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import domain.Actor;
import domain.Administrator;
import domain.Announcement;
import domain.Rendezvous;

@Service
@Transactional
public class AnnouncementService {

	// Managed Repository ----------------------------------------------------

	@Autowired
	private AnnouncementRepository	announcementRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private RendezvousService		rendezvousService;


	//Constructor ----------------------------------------------------

	public AnnouncementService() {
		super();
	}

	//CRUD methods ----------------------------------------------------
	public Announcement create() {
		Announcement result;

		result = new Announcement();
		result.setMoment(new Date());
		result.setTitle("");
		result.setDescription("");

		return result;
	}

	public Collection<Announcement> findAll() {
		Collection<Announcement> result;

		result = this.announcementRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Announcement save(final Announcement announcement) {
		Assert.notNull(announcement);
		Announcement result;
		Actor actor;
		Rendezvous rendezvous;

		actor = this.actorService.findByPrincipal();
		rendezvous = this.rendezvousService.findByAnnouncement(announcement);
		Assert.isTrue(actor != null);
		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getUser().getId() == actor.getId());

		announcement.setMoment(new Date());
		result = this.announcementRepository.save(announcement);

		return result;
	}

	public void delete(final Announcement announcement) {
		Actor actor;

		actor = this.actorService.findByPrincipal();
		//Chekear el actor

		Assert.notNull(announcement);
		Assert.isTrue(announcement.getId() != 0);
		Assert.isTrue(actor instanceof Administrator);

		this.announcementRepository.delete(announcement);
	}
	//Other business methods

	public Announcement findOne(final int announcementId) {
		Announcement result;

		result = this.announcementRepository.findOne(announcementId);

		return result;
	}

	public Collection<Announcement> findByRendezvous(final Rendezvous rendezvous) {
		Collection<Announcement> result;

		result = this.announcementRepository.findByRendezvousId(rendezvous.getId());

		return result;
	}
}