
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class RSVP extends DomainEntity {

	private boolean	cancelled;
	private boolean	joined;


	public boolean isCancelled() {
		return this.cancelled;
	}

	public boolean isJoined() {
		return this.joined;
	}

	public void setCancelled(final boolean cancelled) {
		this.cancelled = cancelled;
	}

	public void setJoined(final boolean joined) {
		this.joined = joined;
	}


	//Relationships
	private User				user;
	private Collection<Answer>	answers;
	private Rendezvous			rendezvous;


	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}

	@NotNull
	@OneToMany()
	public Collection<Answer> getAnswers() {
		return answers;
	}

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Rendezvous getRendezvous() {
		return rendezvous;
	}

	
	public void setAnswers(Collection<Answer> answers) {
		this.answers = answers;
	}

	
	public void setRendezvous(Rendezvous rendezvous) {
		this.rendezvous = rendezvous;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
