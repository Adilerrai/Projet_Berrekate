package spring.berrekate.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.berrekate.dto.CityDTO;
import spring.berrekate.entities.City;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.repositories.CityRepository;
import spring.berrekate.repositories.ImageRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CityService {

	private final CityRepository cityRepository;
	private final ImageRepository imageRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public CityService(CityRepository cityRepository, ImageRepository imageRepository, ModelMapper modelMapper) {
		this.cityRepository = cityRepository;
		this.imageRepository = imageRepository;
		this.modelMapper = modelMapper;
	}

	public List<CityDTO> findAll() {
		List<City> cities = cityRepository.findAll();
		return cities.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public CityDTO findCityById(int cityId) {
		City city = cityRepository.findById(cityId);
		return convertToDto(city);
	}

	public City create(CityDTO cityDTO) {
		City city = convertToEntity(cityDTO);
		City cityR = cityRepository.save(city);
		return cityR;
	}

	public void updateCity(CityDTO cityDTO) {
		City city = convertToEntity(cityDTO);
		cityRepository.save(city);
	}

	public void deleteCityById(int id) {
		cityRepository.deleteById(id);
	}

	private CityDTO convertToDto(City city) {
		return modelMapper.map(city, CityDTO.class);
	}

	private City convertToEntity(CityDTO cityDTO) {
		return modelMapper.map(cityDTO, City.class);
	}

	public List<ImageModel> findCityImagesById(int id) {
		return imageRepository.findImageModelsByCityId(id);
	}

	public List<ImageModel> findAllCityImagesById(int id) {
		return imageRepository.findAllByCityId(id);
	}
}