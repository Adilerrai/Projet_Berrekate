package spring.berrekate.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.berrekate.dto.TouristAttractionDTO;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.entities.TouristAttraction;
import spring.berrekate.repositories.TouristAttractionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TouristAttractionService {

	private final TouristAttractionRepository touristAttractionRepository;
	private final ModelMapper modelMapper;

	@Autowired
	public TouristAttractionService(TouristAttractionRepository touristAttractionRepository, ModelMapper modelMapper) {
		this.touristAttractionRepository = touristAttractionRepository;
		this.modelMapper = modelMapper;
	}

	public List<TouristAttractionDTO> findAll() {
		List<TouristAttraction> touristAttractions = touristAttractionRepository.findAll();
		return touristAttractions.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}

	public TouristAttractionDTO findTouristAttractionById(int touristAttractionId) {
		TouristAttraction touristAttraction = touristAttractionRepository.findById(touristAttractionId);
		return convertToDto(touristAttraction);
	}

	public TouristAttraction create(TouristAttractionDTO touristAttractionDTO) {
		TouristAttraction touristAttraction = convertToEntity(touristAttractionDTO);
		TouristAttraction savedTouristAttraction = touristAttractionRepository.save(touristAttraction);
		return savedTouristAttraction;
	}

	public void updateTouristAttraction(TouristAttractionDTO touristAttractionDTO) {
		TouristAttraction touristAttraction = convertToEntity(touristAttractionDTO);
		touristAttractionRepository.save(touristAttraction);
	}

	public void deleteTouristAttractionById(int id) {
		touristAttractionRepository.deleteById(id);
	}

	private TouristAttractionDTO convertToDto(TouristAttraction touristAttraction) {
		return modelMapper.map(touristAttraction, TouristAttractionDTO.class);
	}

	private TouristAttraction convertToEntity(TouristAttractionDTO touristAttractionDTO) {
		return modelMapper.map(touristAttractionDTO, TouristAttraction.class);
	}

	public List<ImageModel> findAllTouristAttractionImagesById(int id) {
		List<ImageModel> images = touristAttractionRepository.findAllTouristAttractionImagesById(id);
		return images;
	}

	public List<TouristAttractionDTO> findAllTouristAttractionsById(int id) {
		List<TouristAttraction> touristAttractions = touristAttractionRepository.findAllByCity_Id(id);
		return touristAttractions.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
	}
}