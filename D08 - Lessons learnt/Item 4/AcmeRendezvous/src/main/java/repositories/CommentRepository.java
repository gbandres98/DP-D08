
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

	@Query("select c from Comment c where c.rendezvous.id=?1")
	Collection<Comment> findByRendezvousId(int id);

	@Query("select c from Comment c where c.parentComment.id=?1")
	Collection<Comment> findByParentCommentId(int id);
	
	@Query("select c from Comment c where c.rendezvous.id=?1 and c.parentComment=null")
	Collection<Comment> findByRendezvousIdRoot(int id);
}
