
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {

	private Date	moment;
	private String	text;
	private String	picture;


	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
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

	private Rendezvous			rendezvous;
	private Comment				parentComment;
	private User				user;


	//se refiere como comentario padre a que este comentario es una respuesta a dicho comentario padre
	//en caso de no ser una respuesta el comentario padre sera 
	@Valid
	@ManyToOne(optional = true)
	public Comment getparentComment() {
		return parentComment;
	}
	public void setparentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getUser() {
		return this.user;
	}
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Rendezvous getRendezvous() {
		return this.rendezvous;
	}
	public void setUser(final User user) {
		this.user = user;
	}
	public void setRendezvous(final Rendezvous rendezvous) {
		this.rendezvous = rendezvous;
	}

	

}
