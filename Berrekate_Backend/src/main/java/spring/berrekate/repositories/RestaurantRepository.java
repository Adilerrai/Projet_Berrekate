package spring.berrekate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.entities.Restaurant;


@Repository

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	List<Restaurant> findAllByCity_Id(Integer cityId);

	Restaurant findById(int id);

	void deleteById(int id);

    List<ImageModel> findAllRestaurantImagesById(int id);
}