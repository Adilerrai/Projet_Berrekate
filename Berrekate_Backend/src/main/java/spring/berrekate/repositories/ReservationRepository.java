package spring.berrekate.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.berrekate.entities.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
	List<Reservation> findAllByRestaurant_Id(Long restaurantId);
	List<Reservation> findAllByHotel_Id(Long hotelId);
	List<Reservation> findAllByUser_IdAndHotel_Id(Long userId, Long hotelId);
	List<Reservation> findAllByUser_IdAndRestaurant_Id(Long userId, Long restaurantId);
	Optional<Reservation> findById(Long id);
	void deleteById(Long id);
}