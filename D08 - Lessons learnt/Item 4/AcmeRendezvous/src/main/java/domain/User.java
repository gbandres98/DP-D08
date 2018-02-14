
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class User extends Actor {

	//Relationships
	private Collection<Rendezvous>	RSVP;
	private Collection<Rendezvous>	rendezvouses;
	private Collection<Comment>		comments;


	@NotNull
	@ManyToMany(mappedBy = "users")
	public Collection<Rendezvous> getRSVP() {
		return this.RSVP;
	}
	@NotNull
	@OneToMany(mappedBy = "user")
	public Collection<Rendezvous> getRendezvouses() {
		return this.rendezvouses;
	}

	@NotNull
	@OneToMany()
	public Collection<Comment> getComments() {
		return this.comments;
	}

	public void setComments(final Collection<Comment> comments) {
		this.comments = comments;
	}
	public void setRSVP(final Collection<Rendezvous> rSVP) {
		this.RSVP = rSVP;
	}

	public void setRendezvouses(final Collection<Rendezvous> rendezvouses) {
		this.rendezvouses = rendezvouses;
	}

}
