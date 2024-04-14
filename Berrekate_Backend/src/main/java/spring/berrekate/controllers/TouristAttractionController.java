package spring.berrekate.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import spring.berrekate.dto.TouristAttractionDTO;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.jwt.MessageResponse;
import spring.berrekate.repositories.TouristAttractionRepository;
import spring.berrekate.services.ImageService;
import spring.berrekate.services.TouristAttractionService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/touristAttraction")
public class TouristAttractionController {

	@Autowired
	private TouristAttractionService touristAttractionService;
	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<TouristAttractionDTO> getAllHotels() {
		return touristAttractionService.findAll();
	}

	@RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
	public List<TouristAttractionDTO> getAllTouristAttractionsById(@PathVariable("id") int id) {
		return touristAttractionService.findAllTouristAttractionsById(id);
	}

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public TouristAttractionDTO getHotelById(@PathVariable("id") int id) {
		return touristAttractionService.findTouristAttractionById(id);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@PreAuthorize("hhasRole('MODERATOR') or hasRole('ADMIN')")
	public TouristAttractionRepository insertTouristAttraction(@RequestBody TouristAttractionDTO touristAttractionDTO) {

		return (TouristAttractionRepository) touristAttractionService.create(touristAttractionDTO);
	}

	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<?> editTouristAttraction(@RequestBody TouristAttractionDTO touristAttractionDTO) {

		touristAttractionService.updateTouristAttraction(touristAttractionDTO);
		return ResponseEntity.ok(new MessageResponse("Hotel updated successfully!"));
	}

	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public void removeTouristAttraction(@PathVariable("id") int id) {
		TouristAttractionDTO touristAttraction = touristAttractionService.findTouristAttractionById(id);
		if (touristAttraction == null) {
		}
		touristAttractionService.deleteTouristAttractionById(id);
	}


	@PostMapping("/upload/{touristAttraction_id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ImageModel uplaodImage(@PathVariable("touristAttraction_id") int touristAttraction_id,
			@RequestParam("myFile") MultipartFile file) throws IOException {



		ImageModel savedImage = imageService.createTouristAttractionImages(file, touristAttraction_id);
		System.out.println("Image saved");

		return savedImage;

	}

	@RequestMapping(value = "/images/all/{id}", method = RequestMethod.GET)
	public List<ImageModel> getAllTouristAttractionImagesById(@PathVariable("id") int id) {
		return touristAttractionService.findAllTouristAttractionImagesById(id);
	}
}
