
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
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
	private Collection<User>			users;
	private User						user;
	private Collection<Comment>			comments;
	private GPSCoordinates				GPSCoordinates;


	@NotNull
	@OneToMany()
	public Collection<Announcement> getAnnouncements() {
		return this.announcements;
	}

	@NotNull
	@ManyToMany
	public Collection<User> getUsers() {
		return this.users;
	}
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	@NotNull
	@OneToMany()
	public Collection<Comment> getComments() {
		return this.comments;
	}

	@Valid
	@OneToOne(optional = true)
	public GPSCoordinates getGPSCoordinate() {
		return this.GPSCoordinates;
	}

	public void setGPSCoordinate(final GPSCoordinates gPSCoordinate) {
		this.GPSCoordinates = gPSCoordinate;
	}
	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}
	public void setAnnouncements(final Collection<Announcement> announcements) {
		this.announcements = announcements;
	}

	public void setUsers(final Collection<User> users) {
		this.users = users;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
