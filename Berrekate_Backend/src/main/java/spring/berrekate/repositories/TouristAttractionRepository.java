package spring.berrekate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.entities.TouristAttraction;



@Repository


public interface TouristAttractionRepository extends JpaRepository<TouristAttraction, Integer> {
	List<TouristAttraction> findAllByCity_Id(Integer cityId);
	TouristAttraction findById(int id);

	void deleteById(int id);

	List<ImageModel> findAllTouristAttractionImagesById(int id);
}