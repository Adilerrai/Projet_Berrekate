package spring.berrekate.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.berrekate.dto.HotelDTO;
import spring.berrekate.entities.Hotel;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.repositories.HotelRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HotelService {

	private final HotelRepository hotelRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public HotelService(HotelRepository hotelRepository, ModelMapper modelMapper) {
		this.hotelRepository = hotelRepository;
		this.modelMapper = modelMapper;
	}

	public List<HotelDTO> findAll() {
		List<Hotel> hotels = hotelRepository.findAll();
		return hotels.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public HotelDTO findHotelById(int hotelId) {
		Hotel hotel = hotelRepository.findById(hotelId);
		return convertToDto(hotel);
	}

	public Hotel create(HotelDTO hotelDTO) {
		Hotel hotel = convertToEntity(hotelDTO);
		Hotel savedHotel = hotelRepository.save(hotel);
		return savedHotel;
	}

	public void updateHotel(HotelDTO hotelDTO) {
		Hotel hotel = convertToEntity(hotelDTO);
		hotelRepository.save(hotel);
	}

	public void deleteHotelById(int id) {
		hotelRepository.deleteById(id);
	}

	private HotelDTO convertToDto(Hotel hotel) {
		return modelMapper.map(hotel, HotelDTO.class);
	}

	private Hotel convertToEntity(HotelDTO hotelDTO) {
		return modelMapper.map(hotelDTO, Hotel.class);
	}

	public List<HotelDTO> findAllHotelsById(int id) {
		List<Hotel> hotels = hotelRepository.findAllHotelsByCity_Id(id);
		return hotels.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public List<ImageModel> findAllHotelImagesById(int id) {
		List<ImageModel> images = hotelRepository.findAllHotelImagesById(id);
		return images.stream()
				.collect(Collectors.toList());
	}
}