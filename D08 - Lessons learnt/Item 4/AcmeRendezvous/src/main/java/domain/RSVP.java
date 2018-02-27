
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = {
	@Index(columnList = "joined")
})
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
		return this.answers;
	}

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Rendezvous getRendezvous() {
		return this.rendezvous;
	}

	public void setAnswers(final Collection<Answer> answers) {
		this.answers = answers;
	}

	public void setRendezvous(final Rendezvous rendezvous) {
		this.rendezvous = rendezvous;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public void addAnswer(final Answer answer) {
		this.answers.add(answer);
	}

}
