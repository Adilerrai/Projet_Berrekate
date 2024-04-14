package spring.berrekate.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.berrekate.dto.ReservationDTO;
import spring.berrekate.entities.Reservation;
import spring.berrekate.repositories.ReservationRepository;

@Service
@Transactional
public class ReservationService {

	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	ModelMapper modelMapper;

	public ReservationDTO findReservationById(Long reservationId) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationId);
		return modelMapper.map(reservation, ReservationDTO.class);
	}

	public List<ReservationDTO> findAll() {
		List<Reservation> reservations = reservationRepository.findAll();
		List<ReservationDTO> reservationDTOs = new ArrayList<>();
		for (Reservation reservation : reservations) {
			reservationDTOs.add(modelMapper.map(reservation, ReservationDTO.class));
		}
		return reservationDTOs;
	}

	public Reservation create(ReservationDTO reservationDTO) {
		Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
		return reservationRepository.save(reservation);
	}

	public void updateReservation(ReservationDTO reservationDTO) {
		Reservation reservation = modelMapper.map(reservationDTO, Reservation.class);
		reservationRepository.save(reservation);
	}

	public void deleteReservationById(long id) {
		reservationRepository.deleteById(id);
	}

	public List<ReservationDTO> findAllHotelReservationsById(Long hotel_id) {
		List<Reservation> reservations = reservationRepository.findAllByHotel_Id(hotel_id);
		List<ReservationDTO> reservationDTOs = new ArrayList<>();
		for (Reservation reservation : reservations) {
			reservationDTOs.add(modelMapper.map(reservation, ReservationDTO.class));
		}
		return reservationDTOs;
	}

	public List<ReservationDTO> findAllRestaurantReservationsById(long restaurant_id) {
		List<Reservation> reservations = reservationRepository.findAllByRestaurant_Id(restaurant_id);
		List<ReservationDTO> reservationDTOs = new ArrayList<>();
		for (Reservation reservation : reservations) {
			reservationDTOs.add(modelMapper.map(reservation, ReservationDTO.class));
		}
		return reservationDTOs;
	}

	public List<ReservationDTO> findAllUserReservationsHotel(long user_id, long hotel_id) {
		List<Reservation> reservations = reservationRepository.findAllByUser_IdAndHotel_Id(user_id, hotel_id);
		List<ReservationDTO> reservationDTOs = new ArrayList<>();
		for (Reservation reservation : reservations) {
			reservationDTOs.add(modelMapper.map(reservation, ReservationDTO.class));
		}
		return reservationDTOs;
	}

	public List<ReservationDTO> findAllUserReservationsRestaurant(long user_id, long restaurant_id) {
		List<Reservation> reservations = reservationRepository.findAllByUser_IdAndRestaurant_Id(user_id, restaurant_id);
		List<ReservationDTO> reservationDTOs = new ArrayList<>();
		for (Reservation reservation : reservations) {
			reservationDTOs.add(modelMapper.map(reservation, ReservationDTO.class));
		}
		return reservationDTOs;
	}
}
