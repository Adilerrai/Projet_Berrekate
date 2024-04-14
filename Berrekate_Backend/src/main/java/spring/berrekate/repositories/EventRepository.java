package spring.berrekate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.berrekate.entities.Event;
import spring.berrekate.entities.ImageModel;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
	Event findById(int id);

	void deleteById(int id);

	List<Event> findAllEventsByCity_Id(int cityId);

	List<ImageModel> findAllEventImagesById(int id);
}