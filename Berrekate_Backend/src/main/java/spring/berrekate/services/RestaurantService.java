package spring.berrekate.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.berrekate.dto.RestaurantDTO;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.entities.Restaurant;
import spring.berrekate.repositories.ImageRepository;
import spring.berrekate.repositories.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RestaurantService {

	private final RestaurantRepository restaurantRepository;
	private final ModelMapper modelMapper;
	private final ImageRepository imageRepository;

	@Autowired
	public RestaurantService(RestaurantRepository restaurantRepository, ModelMapper modelMapper, ImageRepository imageRepository) {
		this.restaurantRepository = restaurantRepository;
		this.modelMapper = modelMapper;
        this.imageRepository = imageRepository;
    }

	public List<RestaurantDTO> findAll() {
		List<Restaurant> restaurants = restaurantRepository.findAll();
		return restaurants.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public RestaurantDTO findRestaurantById(int restaurantId) {
		Restaurant restaurant = restaurantRepository.findById(restaurantId);
		return convertToDto(restaurant);
	}

	public Restaurant create(RestaurantDTO restaurantDTO) {
		Restaurant restaurant = convertToEntity(restaurantDTO);
		Restaurant savedRestaurant = restaurantRepository.save(restaurant);
		return savedRestaurant;
	}

	public void updateRestaurant(RestaurantDTO restaurantDTO) {
		Restaurant restaurant = convertToEntity(restaurantDTO);
		restaurantRepository.save(restaurant);
	}

	public void deleteRestaurantById(int id) {
		restaurantRepository.deleteById(id);
	}

	private RestaurantDTO convertToDto(Restaurant restaurant) {
		return modelMapper.map(restaurant, RestaurantDTO.class);
	}

	private Restaurant convertToEntity(RestaurantDTO restaurantDTO) {
		return modelMapper.map(restaurantDTO, Restaurant.class);
	}

	public List<ImageModel> findAllRestaurantImagesById(int id) {
		List<ImageModel> images = restaurantRepository.findAllRestaurantImagesById(id);
		return new ArrayList<>(images);
	}

	public List<ImageModel> findAllImagesByRestaurantId(int id) {
		return imageRepository.findAllByRestaurant_id(id);
	}

	public List<RestaurantDTO> findAllRestaurantsById(int id) {
		List<Restaurant> restaurants = restaurantRepository.findAllByCity_Id(id);
		return restaurants.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());

	}
}