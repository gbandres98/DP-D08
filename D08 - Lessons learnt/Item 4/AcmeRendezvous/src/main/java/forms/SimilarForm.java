
package forms;

public class SimilarForm {

	private Integer	rendezvous;
	private Integer	similar;


	public SimilarForm() {
	}

	public SimilarForm(final Integer rendezvous) {
		this.rendezvous = rendezvous;
	}

	public Integer getRendezvous() {
		return this.rendezvous;
	}

	public void setRendezvous(final Integer rendezvous) {
		this.rendezvous = rendezvous;
	}

	public Integer getSimilar() {
		return this.similar;
	}

	public void setSimilar(final Integer similar) {
		this.similar = similar;
	}

}
