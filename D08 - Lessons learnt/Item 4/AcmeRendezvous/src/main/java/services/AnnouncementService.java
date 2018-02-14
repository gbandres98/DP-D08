
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnnouncementRepository;
import domain.Announcement;
import domain.Rendezvous;

@Service
@Transactional
public class AnnouncementService {

	// Managed Repository ----------------------------------------------------

	@Autowired
	private AnnouncementRepository	announcementRepository;


	// Supporting services ----------------------------------------------------

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
