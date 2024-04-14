package spring.berrekate.controllers;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.berrekate.dto.CityDTO;
import spring.berrekate.entities.City;
import spring.berrekate.entities.ImageModel;
import spring.berrekate.jwt.MessageResponse;
import spring.berrekate.services.CityService;
import spring.berrekate.services.ImageService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/city")
public class CityController {

	@Autowired
	private CityService cityService;
	@Autowired
	private ImageService imageService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<CityDTO> getAllCities() {
		return cityService.findAll();
	}

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public CityDTO getCityById(@PathVariable("id") int id) {
		return cityService.findCityById(id);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public City insertCity(@RequestBody CityDTO cityDTO) {
		return cityService.create(cityDTO);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@RequestMapping(value = "/edit", method = RequestMethod.PUT)
	public ResponseEntity<?> editCity(@RequestBody CityDTO cityDTO){
		cityService.updateCity(cityDTO);
		return ResponseEntity.ok(new MessageResponse("Hotel updated successfully!"));
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE)
	public void removeCity(@PathVariable("id") int id) {
		cityService.deleteCityById(id);
	}

	@PostMapping("/upload/{city_id}")
	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public ImageModel uplaodImage(@PathVariable("city_id") int city_id, @RequestParam("myFile") MultipartFile file) throws IOException {
		return imageService.createCityImages(file, city_id);
	}

	@RequestMapping(value = "/image/{id}", method = RequestMethod.GET)
	public List<ImageModel> getCityImagesById(@PathVariable("id") int id) {
		return cityService.findCityImagesById(id);
	}

	@RequestMapping(value = "/images/all/{id}", method = RequestMethod.GET)
	public List<ImageModel> getAllCityImagesById(@PathVariable("id") int id) {
		return cityService.findAllCityImagesById(id);
	}

	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	@RequestMapping(value = "/images/remove/{id}", method = RequestMethod.DELETE)
	public void deleteImageById(@PathVariable("id") int id) {
		long imageID= (long)id;
		imageService.deleteById(imageID);
	}
}