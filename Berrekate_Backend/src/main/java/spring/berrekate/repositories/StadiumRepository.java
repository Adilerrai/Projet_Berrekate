package spring.berrekate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.berrekate.entities.Stadium;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium, Integer> {

}
