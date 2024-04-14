package spring.berrekate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.berrekate.entities.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	Comment findById(int id);

	void deleteById(int id);

}