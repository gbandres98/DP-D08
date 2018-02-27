
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "finalVersion")
})
public class Rendezvous extends DomainEntity {

	private String	name;
	private String	description;
	private Date	moment;
	private String	picture;
	private boolean	adultOnly;
	private boolean	finalVersion;
	private boolean	deleted;


	@NotBlank
	public String getName() {
		return this.name;
	}
	@NotBlank
	public String getDescription() {
		return this.description;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return this.moment;
	}

	@URL
	public String getPicture() {
		return this.picture;
	}

	public boolean isAdultOnly() {
		return this.adultOnly;
	}

	public boolean isFinalVersion() {
		return this.finalVersion;
	}

	public boolean isDeleted() {
		return this.deleted;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}

	public void setAdultOnly(final boolean adultOnly) {
		this.adultOnly = adultOnly;
	}

	public void setFinalVersion(final boolean finalVersion) {
		this.finalVersion = finalVersion;
	}

	public void setDeleted(final boolean deleted) {
		this.deleted = deleted;
	}


	//Relationships

	private Collection<Announcement>	announcements;
	private User						user;
	private GPSCoordinates				GPSCoordinates;
	private Collection<Question>		questions;
	private Collection<Rendezvous>		rendezvouses;


	@NotNull
	@OneToMany(mappedBy = "rendezvous")
	public Collection<Announcement> getAnnouncements() {
		return this.announcements;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	@Valid
	@OneToOne(optional = true)
	public GPSCoordinates getGPSCoordinates() {
		return this.GPSCoordinates;
	}

	@NotNull
	@OneToMany(mappedBy = "rendezvous")
	public Collection<Question> getQuestions() {
		return this.questions;
	}
	@NotNull
	@ManyToMany
	public Collection<Rendezvous> getRendezvouses() {
		return this.rendezvouses;
	}

	public void setRendezvouses(final Collection<Rendezvous> rendezvouses) {
		this.rendezvouses = rendezvouses;
	}
	public void setQuestions(final Collection<Question> questions) {
		this.questions = questions;
	}
	public void setGPSCoordinates(final GPSCoordinates gPSCoordinate) {
		this.GPSCoordinates = gPSCoordinate;
	}

	public void setAnnouncements(final Collection<Announcement> announcements) {
		this.announcements = announcements;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
