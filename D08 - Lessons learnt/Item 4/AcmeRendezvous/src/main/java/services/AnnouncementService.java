
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
import domain.User;

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
		rendezvous = announcement.getRendezvous();
		Assert.isTrue(actor != null);
		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getUser().getId() == actor.getId());
		Assert.isTrue(!(rendezvous.isFinalVersion()) || !(rendezvous.isDeleted()));

		announcement.setMoment(new Date());
		result = this.announcementRepository.save(announcement);

		return result;
	}

	public void delete(final int announcementId) {
		Actor actor;
		Announcement announcement;

		actor = this.actorService.findByPrincipal();

		Assert.isTrue(announcementId != 0);
		Assert.isTrue(actor instanceof Administrator);

		announcement = this.findOne(announcementId);
		Assert.notNull(announcement);

		this.announcementRepository.delete(announcementId);
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

	public Collection<Announcement> findByUserRSVP(final User user) {
		final Collection<Announcement> result;

		result = this.announcementRepository.findByUserRSVPId(user.getId());

		return result;
	}

	public Double averageAnnouncementperRendezvous() {
		final Double result = this.announcementRepository.averageAnnoucementsperRendezvous();
		return result;
	}

	public Double standardDeviationAnnouncementperRendezvous() {
		final Double result = this.announcementRepository.standardDeviationAnnoucementsperRendezvous();
		return result;
	}

}
