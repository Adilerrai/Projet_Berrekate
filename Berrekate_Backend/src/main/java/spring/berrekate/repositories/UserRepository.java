package spring.berrekate.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.berrekate.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	User findById(long id);
	User findByUsername(String username);
}
