package spring.berrekate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import spring.berrekate.entities.Hotel;
import spring.berrekate.entities.ImageModel;

@Repository

public interface HotelRepository extends JpaRepository<Hotel, Integer> {
	List<Hotel> findAllHotelsByCity_Id(int cityId);


	Hotel findById(int id);

	void deleteById(int id);

	List<ImageModel> findAllHotelImagesById(int id);
}