package spring.berrekate.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import spring.berrekate.dto.ReservationDTO;
import spring.berrekate.entities.Reservation;
import spring.berrekate.jwt.MessageResponse;
import spring.berrekate.services.ReservationService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	ReservationService reservationService;
	
	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public ReservationDTO getReservationById(@PathVariable("id") Long id) {
		return reservationService.findReservationById(id);
	}
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<ReservationDTO> getAllReservations() {
		return reservationService.findAll();
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST) 
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	  public Reservation insertReservation(@RequestBody ReservationDTO reservationDTO) {
		  return reservationService.create(reservationDTO);
	  }
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editReservation(@RequestBody ReservationDTO reservationDTO){
		reservationService.updateReservation(reservationDTO);
		return ResponseEntity.ok(new MessageResponse("Reservation updated successfully!"));
	}
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeHotel(@PathVariable("id") int id) {
		
		reservationService.deleteReservationById(id);
	}
	
	@RequestMapping(value = "/hotel/{id}", method = RequestMethod.GET)
	public List<ReservationDTO> getAllHotelReservationsById(@PathVariable("id") long hotel_id) {
		return reservationService.findAllHotelReservationsById(hotel_id);
	}
	
	@RequestMapping(value = "/restaurant/{id}", method = RequestMethod.GET)
	public List<ReservationDTO> getAllRestaurantReservationsById(@PathVariable("id") long restaurant_id) {
		return reservationService.findAllRestaurantReservationsById(restaurant_id);
	}
	
	@RequestMapping(value = "/hotel/{user_id}/{hotel_id}", method = RequestMethod.GET)
	public List<ReservationDTO> getAllUserHotelReservations(@PathVariable("user_id") long user_id,@PathVariable("hotel_id") long hotel_id) {
		return reservationService.findAllUserReservationsHotel(user_id, hotel_id);
	}
	@RequestMapping(value = "/restaurant/{user_id}/{restaurant_id}", method = RequestMethod.GET)
	public List<ReservationDTO> getAllUserRestaurantReservations(@PathVariable("user_id") long user_id,@PathVariable("restaurant_id") long restaurant_id) {
		return reservationService.findAllUserReservationsRestaurant(user_id, restaurant_id);
	}
}
