package spring.berrekate.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import spring.berrekate.entities.ImageModel;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel, Long> {

	@Query("SELECT t FROM ImageModel t WHERE t.user.userProfileImages=?1")
	ImageModel findUserImageById(Integer user_id);

	ImageModel findById(long id);
	List<ImageModel> findByCityId(Integer cityId);

	List<ImageModel> findImageModelsByCityId(int cityId);
	List<ImageModel> findAllByCityId(int cityId);

    List<ImageModel> findAllByRestaurant_id(int id);
}