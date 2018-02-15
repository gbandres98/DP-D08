
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	private Date	moment;
	private String	text;
	private String	picture;


	@NotNull
	public Date getMoment() {
		return this.moment;
	}
	@NotBlank
	public String getText() {
		return this.text;
	}
	@URL
	public String getPicture() {
		return this.picture;
	}

	public void setMoment(final Date moment) {
		this.moment = moment;
	}

	public void setText(final String text) {
		this.text = text;
	}

	public void setPicture(final String picture) {
		this.picture = picture;
	}


	//Relationships

	private Collection<Reply>	replies;
	private User				user;
	private Rendezvous			rendezvous;

	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<Reply> getReplies() {
		return this.replies;
	}
	@NotNull
	@ManyToOne
	public Rendezvous getRendezvous() {
		return rendezvous;
	}
	public void setRendezvous(Rendezvous rendezvous) {
		this.rendezvous = rendezvous;
	}
	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(final User user) {
		this.user = user;
	}
	public void setReplies(final Collection<Reply> replies) {
		this.replies = replies;
	}

}
