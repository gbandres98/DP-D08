
package services;

import java.util.Collection;
import java.util.HashSet;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import domain.Actor;
import domain.Administrator;
import domain.Announcement;
import domain.Comment;
import domain.GPSCoordinates;
import domain.Question;
import domain.RSVP;
import domain.Rendezvous;
import domain.User;
import forms.SimilarForm;

@Service
@Transactional
public class RendezvousService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private RendezvousRepository	rendezvousRepository;

	// Supporting services ----------------------------------------------------
	@Autowired
	private ActorService			actorService;
	@Autowired
	private UserService				userService;
	@Autowired
	private AnnouncementService		announcementService;
	@Autowired
	private RSVPService				RSVPService;
	@Autowired
	private CommentService			commentService;
	@Autowired
	private QuestionService			questionService;
	@Autowired
	private GPSCoordinatesService	gpscoordinatesService;


	// Constructors -----------------------------------------------------------

	public RendezvousService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	public Rendezvous create() {
		Rendezvous result;
		User user;

		user = (User) this.actorService.findByPrincipal();
		result = new Rendezvous();
		result.setUser(user);
		result.setQuestions(new HashSet<Question>());
		result.setAnnouncements(new HashSet<Announcement>());
		result.setRendezvouses(new HashSet<Rendezvous>());

		return result;
	}

	public Collection<Rendezvous> findAll() {
		Collection<Rendezvous> result;

		result = this.rendezvousRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Rendezvous delete(final int rendezvousId) {
		Actor actor;
		Rendezvous result, rendezvous;

		actor = this.actorService.findByPrincipal();
		rendezvous = this.findOne(rendezvousId);
		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getId() != 0);
		Assert.isTrue(actor.getId() == rendezvous.getUser().getId());

		rendezvous.setDeleted(true);
		result = this.rendezvousRepository.save(rendezvous);

		return result;
	}
	public void remove(final Rendezvous rendezvous) {
		Actor actor;

		final Collection<Announcement> announcements = rendezvous.getAnnouncements();
		final Collection<RSVP> RSVPs = this.RSVPService.findbyRendezvous(rendezvous.getId());
		final Collection<Comment> comments = this.commentService.findByRendezvousIdRoot(rendezvous.getId());
		final Collection<Question> questions = rendezvous.getQuestions();
		actor = this.actorService.findByPrincipal();
		for (final Announcement a : announcements)
			this.announcementService.delete(a.getId());
		final GPSCoordinates gps = rendezvous.getGPSCoordinates();

		for (final RSVP r : RSVPs)
			this.RSVPService.delete(r);
		for (final Comment c : comments)
			this.commentService.delete(c);
		for (final Question q : questions)
			this.questionService.delete(q);

		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getId() != 0);

		Assert.isTrue(actor instanceof Administrator);

		this.rendezvousRepository.delete(rendezvous.getId());
		if (gps != null)
			this.gpscoordinatesService.delete(gps);

	}

	public Rendezvous save(final Rendezvous rendezvous) {
		Rendezvous result;
		Actor actor;

		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor != null);
		Assert.isTrue(actor instanceof User);
		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getUser().getId() == actor.getId());
		Assert.isTrue(!rendezvous.isFinalVersion());
		Assert.isTrue(rendezvous.isDeleted() == false);

		result = this.rendezvousRepository.save(rendezvous);

		return result;
	}

	public Rendezvous setFinal(final int rendezvousId) {
		Rendezvous result, rendezvous;
		Actor actor;

		actor = this.actorService.findByPrincipal();
		Assert.isTrue(actor != null);
		Assert.isTrue(actor instanceof User);

		rendezvous = this.findOne(rendezvousId);
		Assert.notNull(rendezvous);
		Assert.isTrue(rendezvous.getUser().getId() == actor.getId());
		Assert.isTrue(!rendezvous.isFinalVersion());
		Assert.isTrue(rendezvous.isDeleted() == false);
		rendezvous.setFinalVersion(true);

		result = this.rendezvousRepository.save(rendezvous);

		return result;
	}

	//Other business methods

	public Rendezvous findOne(final int rendezvousId) {
		Rendezvous result;

		result = this.rendezvousRepository.findOne(rendezvousId);

		return result;
	}

	public Rendezvous findByAnnouncement(final Announcement announcement) {
		Rendezvous result;

		Assert.notNull(announcement);
		Assert.isTrue(announcement.getId() != 0);

		result = this.rendezvousRepository.findByAnnouncementId(announcement.getId());

		return result;
	}

	public Collection<Rendezvous> findRendevousWithRSVPbyUserId(final int id) {
		Collection<Rendezvous> result;

		result = this.rendezvousRepository.findRendevousWithRSVPbyUserId(id);

		return result;
	}

	public Collection<Rendezvous> findByUser(final int userId) {
		Collection<Rendezvous> result;

		result = this.rendezvousRepository.findByUser(userId);

		return result;
	}

	public Collection<Rendezvous> findSimilar(final int rendezvousId) {
		Collection<Rendezvous> result;

		result = this.rendezvousRepository.findSimilar(rendezvousId);

		return result;
	}

	public void save(final SimilarForm similarForm) {
		Rendezvous rendezvous;
		Rendezvous similar;
		User user;

		user = this.userService.findByPrincipal();

		rendezvous = this.rendezvousRepository.findOne(similarForm.getRendezvous());
		Assert.isTrue(rendezvous.getUser().equals(user));
		similar = this.rendezvousRepository.findOne(similarForm.getSimilar());
		if (!rendezvous.getRendezvouses().contains(similar))
			rendezvous.getRendezvouses().add(similar);
		this.rendezvousRepository.save(rendezvous);
	}

	public Collection<Rendezvous> findAllFinal() {
		Collection<Rendezvous> result;

		result = this.rendezvousRepository.findAllFinal();
		Assert.notNull(result);

		return result;
	}

}
